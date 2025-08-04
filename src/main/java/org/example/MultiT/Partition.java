package org.example.MultiT;

import java.util.AbstractList;
import java.util.List;

public class Partition<T> extends AbstractList<List<T>> {

    private final List<T> list;
    private final int chunkSize;

    public Partition(List<T> list, int chunkSize) {
        this.list = list;
        this.chunkSize = chunkSize;
    }

    public static <T> List<List<T>> ofSize(List<T> list, int chunkSize) {


        return new Partition<>(list, chunkSize);
    }

    @Override
    public List<T> get(int index) {
        int start = index * chunkSize;
        int end = Math.min(start+chunkSize, list.size());

        if(start > end){
            throw new IndexOutOfBoundsException("index is out of range");
        }

        return list.subList(start,end);
    }

    @Override
    public int size() {
        return (int) Math.ceil((double) list.size()/(chunkSize));
    }
}
