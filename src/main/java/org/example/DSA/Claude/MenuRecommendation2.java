package org.example.DSA.Claude;

import java.util.*;
import java.io.*;

class MenuItem2 {
    int itemId;
    String displayName;
    boolean inStock = true;
    boolean dealOfDay = false;
    long sumOfRating;
    int numberOfPeopleWhoRatedIt;
}

interface IMenuRecommendation2 {
    void addItem(int id, String displayName);
    MenuItem2 getRecommendedItem();
    void outOfStockItem(int itemId);
    void restockItem(int itemId);
    void makeDealOfTheDayItem(int itemId);
    void rateItem(int itemId, int rating);
}


class MenuRecommendation2 implements IMenuRecommendation2 {
    Map<Integer , MenuItem2> items = new HashMap<>();

    @Override
    public void addItem(int id, String displayName) {
        MenuItem2 item = new MenuItem2();
        item.itemId=id;
        item.displayName=displayName;
        item.dealOfDay = false;
        item.inStock = true;
        items.put(id,item);

    }

    @Override
    public MenuItem2 getRecommendedItem() {
        MenuItem2 best = null;
        for(MenuItem2 item : items.values()){
            if(!item.inStock) continue;
            if(best==null){
                best = item;
                continue;
            }
            if (item.dealOfDay && !best.dealOfDay) { best = item; continue; }
            if (!item.dealOfDay && best.dealOfDay) continue;
            double iAvg = item.numberOfPeopleWhoRatedIt == 0 ? 0 : (double)item.sumOfRating / item.numberOfPeopleWhoRatedIt;
            double bAvg = best.numberOfPeopleWhoRatedIt == 0 ? 0 : (double)best.sumOfRating / best.numberOfPeopleWhoRatedIt;
            if (iAvg > bAvg) { best = item; continue; }
            if (iAvg < bAvg) continue;
            if (item.numberOfPeopleWhoRatedIt > best.numberOfPeopleWhoRatedIt) { best = item; continue; }
            if (item.numberOfPeopleWhoRatedIt < best.numberOfPeopleWhoRatedIt) continue;
            if (item.itemId > best.itemId) best = item;
        }
        return best;

    }

    @Override
    public void outOfStockItem(int itemId) {
        MenuItem2 item = items.get(itemId);
        if (item != null) {
            item.inStock = false;
            item.dealOfDay = false;}

    }

    @Override
    public void makeDealOfTheDayItem(int itemId) {
        for (MenuItem2 item : items.values()) item.dealOfDay = false;
        MenuItem2 item = items.get(itemId);
        if (item != null) item.dealOfDay = true;
    }

    @Override
    public void rateItem(int itemId, int rating) {
        MenuItem2 item = items.get(itemId);
        if (item != null) {
            item.sumOfRating += rating;
            item.numberOfPeopleWhoRatedIt++;
        }
    }

    @Override
    public void restockItem(int itemId) {
        MenuItem2 item = items.get(itemId);
        if (item != null) item.inStock = true;

    }
}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        int totalNumberOfRequests = Integer.parseInt(br.readLine().trim());

        String arr[];

        IMenuRecommendation2 menuRecommendation = new MenuRecommendation2();

        for(int requestNumber = 1; requestNumber <= totalNumberOfRequests; requestNumber++) {
            arr = br.readLine().trim().split(" ");
            switch(arr[0]) {
                case "addItem":
                    int id = Integer.parseInt(arr[1]);
                    String displayName = arr[2];
                    menuRecommendation.addItem(id, displayName);
                    break;
                case "getRecommendedItem":
                    MenuItem2 menuItem = menuRecommendation.getRecommendedItem();
                    if(menuItem == null) {
                        out.println("N/A");
                        break;
                    }
                    double averageRating = (menuItem.numberOfPeopleWhoRatedIt == 0) ?
                            0 :
                            (menuItem.sumOfRating / (menuItem.numberOfPeopleWhoRatedIt * 1.0d));
                    out.println(menuItem.itemId + " " + menuItem.displayName + " Rating: " + averageRating) ;
                    break;
                case "outOfStockItem":
                    int itemId = Integer.parseInt(arr[1]);
                    menuRecommendation.outOfStockItem(itemId);
                    break;
                case "restockItem":
                    itemId = Integer.parseInt(arr[1]);
                    menuRecommendation.restockItem(itemId);
                    break;
                case "makeDealOfTheDayItem":
                    itemId = Integer.parseInt(arr[1]);
                    menuRecommendation.makeDealOfTheDayItem(itemId);
                    break;
                case "rateItem":
                    itemId = Integer.parseInt(arr[1]);
                    int rating = Integer.parseInt(arr[2]);
                    menuRecommendation.rateItem(itemId, rating);
                    break;
            }
        }

        out.flush();
        out.close();
    }
}
