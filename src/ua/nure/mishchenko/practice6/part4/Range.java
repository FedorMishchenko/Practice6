package ua.nure.mishchenko.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

    private int startIndex;
    private int endIndex;
    private boolean reverse;

    Range(int n, int m) {
        startIndex = n;
        endIndex = m;
    }

    Range(int n, int m, boolean reverse) {
        startIndex = n;
        endIndex = m;
        this.reverse = reverse;
    }

    @Override
    public String toString() {
        return "";
    }


    @Override
    public Iterator<Integer> iterator() {
        if(reverse){
            return new ReverseIteratorImpl();
        }
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Integer> {

        private int cursor = startIndex - 1;

        @Override
        public boolean hasNext() {
            return cursor < endIndex;
        }

        @Override
        public Integer next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return ++cursor;
        }
    }


    private class ReverseIteratorImpl extends IteratorImpl {

        private int cursor = endIndex + 1;
        @Override
        public boolean hasNext() {
            return cursor > startIndex;
        }

        @Override
        public Integer next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return --cursor;
        }
    }
}
