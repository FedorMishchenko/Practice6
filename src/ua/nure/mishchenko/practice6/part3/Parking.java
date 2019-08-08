package ua.nure.mishchenko.practice6.part3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Parking {

    private List<Integer> list;
    private int size;

    public Parking(int size) {
        this.size = size;
        list = new ArrayList<>(size);
        IntStream.range(0, size).forEach(i -> list.add(null));
    }

    public boolean arrive(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException();
        }
        if (list.get(index) == null) {
            list.set(index, 1);
            return true;
        } else{
            for (int i = index; i < size; i++) {
                if (list.get(i) == null) {
                    list.set(i, 1);
                    return true;
                }
            }
        }
        if(index > 0) {
            for (int i = 0; i < index; i++) {
                if (list.get(i) == null) {
                    list.set(i, 1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean depart(Integer index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException();
        }
        if (list.get(index) != null) {
            list.set(index, null);
            return true;
        }
        return false;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (Integer car : list) {
            sb = (car == null) ? sb.append("0") : sb.append("1");
        }
        System.out.println(sb.toString());
    }
}
