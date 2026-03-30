package org.example.DSA.Claude;

import java.util.*;

class MenuItem {
    int id;
    String name;
    int totalRating = 0;
    int ratingCount = 0;
    boolean inStock = true;
    boolean isDeal = false;

    public MenuItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public double getAvgRating() {
        if (ratingCount == 0) return 0.0;
        return (double) totalRating / ratingCount;
    }
}

interface IMenuRecommendation {
    void addItem(int id, String displayName);
    MenuItem getRecommendedItem();
    void outOfStockItem(int itemId);
    void restockItem(int itemId);
    void makeDealOfTheDayItem(int itemId);
    void rateItem(int itemId, int rating);
}

public class MenuRecommendation1 implements IMenuRecommendation {

    private Map<Integer, MenuItem> map = new HashMap<>();
    private Integer dealOfTheDayId = null;

    @Override
    public void addItem(int id, String displayName) {
        map.put(id, new MenuItem(id, displayName));
    }

    @Override
    public MenuItem getRecommendedItem() {

        // 1. Deal of the day (if valid)
        if (dealOfTheDayId != null) {
            MenuItem dealItem = map.get(dealOfTheDayId);
            if (dealItem != null && dealItem.inStock) {
                return dealItem;
            }
        }

        // 2. Find best rated in-stock item
        MenuItem best = null;

        for (MenuItem item : map.values()) {
            if (!item.inStock) continue;

            if (best == null) {
                best = item;
            } else {
                if (compare(item, best) > 0) {
                    best = item;
                }
            }
        }

        return best;
    }

    private int compare(MenuItem a, MenuItem b) {

        // Compare avg rating
        double avgA = a.getAvgRating();
        double avgB = b.getAvgRating();

        if (avgA != avgB) {
            return Double.compare(avgA, avgB);
        }

        // Compare rating count
        if (a.ratingCount != b.ratingCount) {
            return a.ratingCount - b.ratingCount;
        }

        // Compare item ID
        return a.id - b.id;
    }

    @Override
    public void outOfStockItem(int itemId) {
        MenuItem item = map.get(itemId);
        if (item != null) {
            item.inStock = false;

            // loses deal permanently
            if (dealOfTheDayId != null && dealOfTheDayId == itemId) {
                dealOfTheDayId = null;
            }
        }
    }

    @Override
    public void restockItem(int itemId) {
        MenuItem item = map.get(itemId);
        if (item != null) {
            item.inStock = true;
        }
    }

    @Override
    public void makeDealOfTheDayItem(int itemId) {
        MenuItem item = map.get(itemId);
        if (item != null && item.inStock) {
            dealOfTheDayId = itemId;
        }
    }

    @Override
    public void rateItem(int itemId, int rating) {
        MenuItem item = map.get(itemId);
        if (item != null) {
            item.totalRating += rating;
            item.ratingCount++;
        }
    }
}
