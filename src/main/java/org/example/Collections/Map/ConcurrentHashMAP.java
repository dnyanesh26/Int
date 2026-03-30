package org.example.Collections.Map;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMAP {
    public static void main(String[] args) {


        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        map.put(1, "Apple");
        map.put(2, "Banana");
        System.out.println(map);

        map.putIfAbsent(1, "Orange");
        map.putIfAbsent(2, "Banana");
        System.out.println(map);

        System.out.println(map.get(1));

        map.remove(1);
        map.put(1, "Apple");
        map.remove(1, "Apple");

        map.replace(1, "Orange");// doesnt work if key is absent
        map.replace(2, "Orange");
        System.out.println(map);

        map.put(1, "Apple");
        map.replace(1, "Apple", "Mango");//Replaces value only if old value matches.
        System.out.println(map);

        map.compute(1, (k,v) -> v + " Fruit");//Recalculates value for a key using a function.
        System.out.println(map);

        map.computeIfAbsent(2, k -> "Banana");
        map.computeIfAbsent(3, k -> "Banana");
        System.out.println(map);

        map.computeIfPresent(2, (k,v) -> v + " Updated");
        System.out.println(map);

        map.merge(3, " Fruit", (oldVal, newVal) -> oldVal + newVal);
        map.merge(4, " Fruit", (oldVal, newVal) -> oldVal + newVal);// adds if key not present
        System.out.println(map);

        System.out.println(map.containsKey(1));
        System.out.println(map.containsValue("Apple"));
        System.out.println(map.containsValue(" Fruit"));

        System.out.println(map.size());
//        map.clear();

        Set<Integer> keys = map.keySet();
        for(Integer k: keys){
            System.out.print(k+" ");
        }

        Collection<String> values = map.values();
        for(String v: values){
            System.out.print(v+" ");
        }
        System.out.println();

        map.put(1,"A");
        map.put(2,"B");

        for(Map.Entry<Integer,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}
