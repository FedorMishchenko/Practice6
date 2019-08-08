package ua.nure.mishchenko.practice6.part1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class WordContainer extends TreeSet<Word>{

    private static final long serialVersionUID = -3247228646147954626L;

    WordContainer(Comparator<? super Word> comparator) {
        super(comparator);
    }

    public static void main(String[] args) {
        Part1.wordCounter();
    }

    @Override
    public boolean add(Word word) {
        if (!contains(word)) {
            super.add(word);
            return false;
        }
        for (Word next : this) {
            if ((comparator() != null ? comparator().compare(next, word) : 0) == 0) {
                next.setFrequency(next.getFrequency() + 1);
                return true;
            }
        }
        return false;
    }


    Iterator<Word> frequencyIterator() {
        TreeSet<Word> words = new TreeSet<>(new Word.CompareByFrequency());
        words.addAll(this);
        return words.iterator();
    }
}
