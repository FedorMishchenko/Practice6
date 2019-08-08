package ua.nure.mishchenko.practice6.part3;

public final class Part3 {

    private Part3() {
    }

    public static void main(String[] args) {

        Parking parking = new Parking(5);
        parking.print();
        parking.arrive(1);
        parking.arrive(2);
        parking.print();
        parking.depart(2);
        parking.print();

    }
}
