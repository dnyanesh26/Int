package org.example.DSA.Claude;

import java.util.*;


public class GroupAnagrams {

    /*
    Group anagrams together.
    Pattern: Hash map with sorted tuple as key
     */

    public List<List<String>> groupAnagramsNaive(String[] strs){

        List<List<String>> result = new ArrayList<>();
        boolean[] used = new boolean[strs.length];

        for(int i=0;i<strs.length;i++){
            if(used[i]) continue;

            List<String> group = new ArrayList<>();
            group.add(strs[i]);
            used[i]=true;

            for(int j=i+1;j<strs.length;j++){
                if(!used[j] && areAnagrams(strs[i],strs[j])){
                    group.add(strs[j]);
                    used[j]=true;
                }
            }
            result.add(group);

        }
        return result;
    }

    private boolean areAnagrams(String str, String str1) {

        char[] arr1 = str.toCharArray();
        char[] arr2 = str1.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    public List<List<String>> groupAnagramsOptimized(String[] strs){

        Map<String, List<String>> result = new HashMap<>();
        for (String str : strs) {

            char[] chararr = str.toCharArray();

            Arrays.sort(chararr);
            result.putIfAbsent(Arrays.toString(chararr),new ArrayList<>());
            result.get(Arrays.toString(chararr)).add(str);

        }

        return new ArrayList<>(result.values());
    }

    public List<List<String>> groupAnagramsOptimal(String[] strs){

        Map<String,List<String>> result = new HashMap<>();
        for(String str: strs){

            char[] chararr = new char[26];
            for(char c: str.toCharArray()){
                chararr[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<26;i++){
                if(chararr[i]>0){
                    sb.append(i + 'a');
                    sb.append(chararr[i]);
                    System.out.println(chararr[i] + 'a');
                }
            }

            String key = sb.toString();
            result.putIfAbsent(key,new ArrayList<>());
            result.get(key).add(str);

        }
        return new ArrayList<>(result.values());
    }
    public static void main(String[] args) {
        GroupAnagrams sol = new GroupAnagrams();
        String[] strs = {"eatt", "ttea", "tan", "ate", "nat", "bat"};
        System.out.println(sol.groupAnagramsOptimal(strs));
        // [[eat, tea, ate], [tan, nat], [bat]]
    }
}
