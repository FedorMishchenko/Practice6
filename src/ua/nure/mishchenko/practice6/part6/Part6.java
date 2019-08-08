package ua.nure.mishchenko.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;
import java.util.ArrayList;

import static java.util.Comparator.*;

public class Part6 {
    private String fileName;
    private String[] input;
    private final String encoding;
    private final String regex;

    public Part6() {
        encoding = "CP1251";
        regex = "[\\w]+";
    }

    public static void main(String[] args) {
        new Part6().display(args[0], args[1], args[2], args[3]);
    }

    private void display(String input, String fileName, String task, String op) {
        if (!("--input".equals(input) || "-i".equals(input))) {
            return;
        }
        if (!("--task".equals(task) || "-t".equals(task))) {
            return;
        }
        this.fileName = fileName;
        init();
        switch (op) {
            case "frequency":
                frequency();
                break;
            case "length":
                length();
                break;
            case "duplicates":
                duplicates();
                break;
            default:
        }
    }

    private void init() {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = Pattern.compile(regex).matcher(getInput());
        while (matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        input = sb.toString().split(" ");
    }

    private String getInput() {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fileName), encoding)) {
            while (scanner.hasNext()) {
                sb.append(scanner.next()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File: %s not found", fileName));
        }
        return sb.toString();
    }

    private void frequency() {

        final HashMap<String, Counter2> wordCounts = new HashMap<>();
        IntStream.range(0, input.length)
                .forEachOrdered((int place) -> {
                    String w = input[place];
                    Counter2 counter = wordCounts.get(w);
                    if (counter == null) {
                        wordCounts.put(w, new Counter2(place));
                    } else {
                        counter.setCount(counter.getCount() + 1);
                    }
                });

        TreeMap<String, Counter2> sortedWords = new TreeMap<>((String a, String b) -> {
            Counter2 countA = wordCounts.get(a);
            Counter2 countB = wordCounts.get(b);
            int count = countB.count - countA.count;
            return count == 0 ? (countA.place - countB.place) : count;
        });
        sortedWords.putAll(wordCounts);

        TreeSet<String> firstStrings = new TreeSet<>(reverseOrder());
        int i = 0;
        for (String s : sortedWords.keySet()) {
            if (i != 3) {
                i++;
                firstStrings.add(s);
            } else {
                break;
            }
        }
        firstStrings.stream()
                .map(s -> s + " ==> " + sortedWords.get(s).getCount())
                .forEach(System.out::println);
    }

    private void length() {

        final HashMap<String, Counter> wordCounts = new HashMap<>();
        IntStream.range(0, input.length).forEachOrdered((int place) -> {
            String w = input[place];
            Counter countWithPlace = wordCounts.get(w);
            if (countWithPlace == null) {
                wordCounts.put(w, new Counter(w.length(), place));
            }
        });

        TreeMap<String, Counter> sortedWords = new TreeMap<>((String a, String b) -> {
            Counter countA = wordCounts.get(a);
            Counter countB = wordCounts.get(b);
            int length = countB.length - countA.length;
            return length == 0 ? (countA.place - countB.place) : length;
        });
        sortedWords.putAll(wordCounts);

        int i = 0;
        List<String> list = new ArrayList<>();
        for (String s : sortedWords.keySet()) {
            if (i != 3) {
                list.add(i, s);
                i++;
            } else {
                break;
            }
        }
        list.stream()
                .map(s -> s + " ==> " + sortedWords.get(s).getLength())
                .forEach(System.out::println);


    }


    private void duplicates() {
        final Map<String, Integer> wordCounts =
                Arrays.stream(input)
                        .collect(Collectors
                                .toMap(w -> w, w -> 1, (a, b) -> a + b, LinkedHashMap::new));
        int i = 0;
        for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
            if (i != 3) {
                if (wordCount.getValue() > 1) {
                    i++;
                    System.out.println(new StringBuilder(
                            wordCount.getKey())
                            .reverse()
                            .toString()
                            .toUpperCase(Locale.ENGLISH));
                }
            } else {
                break;
            }
        }

    }

    private static final class Counter {
        private final int length;
        private final int place;

        private Counter(int length, int place) {
            this.length = length;
            this.place = place;
        }

        private int getLength() {
            return length;
        }
    }
    private static final class Counter2 {
        private int count = 1;
        private final int place;

        private Counter2(int place) {
            this.place = place;
        }

        private int getCount() {
            return count;
        }

        private void setCount(int count) {
            this.count = count;
        }
    }
}
