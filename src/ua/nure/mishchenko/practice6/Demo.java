package ua.nure.mishchenko.practice6;

import ua.nure.mishchenko.practice6.part1.Part1;
import ua.nure.mishchenko.practice6.part2.Part2;
import ua.nure.mishchenko.practice6.part3.Part3;
import ua.nure.mishchenko.practice6.part4.Part4;
import ua.nure.mishchenko.practice6.part5.Part5;
import ua.nure.mishchenko.practice6.part6.Part6;

import java.io.ByteArrayInputStream;

public class Demo {
    public static void main(String[] args) {
        System.setIn(new ByteArrayInputStream(
                ("asd 43 asdf asd 43 434 asdf kasdf asdf stop asdf stop")
                .replace(" ", System.lineSeparator()).getBytes()));
        Part1.main(args);
        System.setIn(System.in);
        System.out.println();
        Part2.main(args);
        System.out.println();
        Part3.main(new String[0]);
        System.out.println();
        Part4.main(new String[0]);
        System.out.println();
        Part5.main(new String[0]);
        System.out.println();
        Part6.main(new String[]{"--input", "part6.txt", "--task", "frequency"});
        System.out.println();
        Part6.main(new String[]{"--input", "part6.txt", "--task", "length"});
        System.out.println();
        Part6.main(new String[]{"--input", "part6.txt", "--task", "duplicates"});
    }
}
