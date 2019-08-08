package ua.nure.mishchenko.practice6.part1;

import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {
    private static final String QUIT = "stop";

    public static void main(String[] args) {
        wordCounter();
    }

    public static void wordCounter() {
        Scanner scann = new Scanner(System.in);
        WordContainer container = new WordContainer(new Word.WordComparator());
        while (scann.hasNextLine()) {
            String word = Pattern.compile(" ")
                    .splitAsStream(scann.next())
                    .collect(Collectors.joining());
            if(word.contains(QUIT)){
                Matcher matcher = Pattern.compile(" ")
                        .matcher(word);
                while (matcher.find()){
                    container.add(new Word(matcher.group()));
                }
                break;
            }
                if (QUIT.equals(word)) {
                    break;
                }
            container.add(new Word(word));
        }

        Iterator iterator = container.frequencyIterator();
        while (iterator.hasNext()) {
            Word w = (Word) iterator.next();
            System.out.printf("%s : %s%n", w.getValue(), w.getFrequency());
        }
    }
}
