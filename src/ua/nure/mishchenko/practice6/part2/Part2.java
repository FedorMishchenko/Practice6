package ua.nure.mishchenko.practice6.part2;


import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.stream.IntStream;

public final class Part2 {

    private static List<Integer> arrayList = new ArrayList<>();
    private static List<Integer> linkedList = new LinkedList<>();
    private final int capacity;

    private Part2(int capacity) {
        this.capacity = capacity;
    }

    private static long removeByIndex(List list, int k) {
        long time = System.currentTimeMillis();
        int local = 0;
        while (list.size() > 1) {
            local += (k - 1);
            if (local >= list.size()) {
                local %= list.size();
            }
            list.remove(local);
        }
        return System.currentTimeMillis() - time;
    }

    private static long removeByIterator(List list, int k) {
        long time = System.currentTimeMillis();
        int local = 0;
        Iterator it = list.iterator();
        while(list.size() > 1) {
            if(it.hasNext()) {
                it.next();
                local++;
                if(local == k) {
                    it.remove();
                    local = 0;
                }
            } else {
                it = list.iterator();
            }
        }
        return System.currentTimeMillis() - time;
    }

    private void fill(List<Integer> list){
        list.clear();
        IntStream.range(0, capacity).forEach(list::add);

    }

    public static void main(String[] args) {
        Part2 part2 = new Part2(10_000);
        final int k = 4;
        part2.fill(arrayList);
        part2.fill(linkedList);
        System.out.println("ArrayList#iterator: "
                + removeByIterator(arrayList, k) + " ms");
        System.out.println("LinkedList#iterator: "
                + removeByIterator(linkedList, k) + " ms");
        part2.fill(arrayList);
        part2.fill(linkedList);
        System.out.println("ArrayList#index: "
                + removeByIndex(arrayList, k) + " ms");
        System.out.println("LinkedList#index: "
                + removeByIndex(linkedList, k) + " ms");
    }
}
