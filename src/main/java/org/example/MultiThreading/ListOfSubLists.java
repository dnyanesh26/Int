package org.example.MultiThreading;

import java.util.ArrayList;
import java.util.List;

public class ListOfSubLists {


    public <T> List<List<T>> createList(List<T> finData, int nOT) {
    List<List<T>> listOfSubLists = new ArrayList<>();
    listOfSubLists = Partition.ofSize(finData,(int) Math.ceil((double) finData.size()/(nOT-1)));
    return  listOfSubLists;

    }
}
