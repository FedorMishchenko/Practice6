package ua.nure.mishchenko.practice6.part4;

public class Part4 {
    public static void main(String[] args) {
        Range range = new Range(3,10);
        for (int i: range){
            System.out.printf("%d ", i);
        }

        System.out.println();

        Range range2 = new Range(3,10, true);
        for (int i: range2){
            System.out.printf("%d ", i);
        }
    }
}
