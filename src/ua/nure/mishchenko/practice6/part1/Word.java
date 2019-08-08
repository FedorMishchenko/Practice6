package ua.nure.mishchenko.practice6.part1;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Word {
    private String value;

    private int frequency;

    Word(String value) {
        this.value = value;
        frequency = 1;
    }

    String getValue() {
        return value;
    }

    int getFrequency() {
        return frequency;
    }

    void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        return this.getClass() == o.getClass() && frequency == ((Word) o).getFrequency()
                && value.equals(((Word) o).getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getFrequency()) * 31;
    }



    static class WordComparator implements Comparator<Word> , Serializable {
        private static final long serialVersionUID = 1658209960442879367L;

        @Override
        public int compare(Word o1, Word o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }

    static class CompareByFrequency implements Comparator<Word>, Serializable {
        private static final long serialVersionUID = -4973793391579444742L;

        @Override
        public int compare(Word o1, Word o2) {
            int comparison = o2.getFrequency() - o1.getFrequency();
            if (comparison == 0) {
                return new WordComparator().compare(o1, o2);
            }
            return comparison;
        }
    }
}

