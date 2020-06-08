package SzymonKnopp.SymulacjaSwiata;

import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner skaner = new Scanner(System.in);
        Kontroler kontroler = new Kontroler();

        System.out.println("1. Stworzenie nowej symulacji");
        System.out.println("2. Wczytanie symulacji z pliku");

        int opcja = skaner.nextInt();

        if (opcja == 2)
            kontroler.wczytajSwiat();
        else
            kontroler.przygotujSwiat();
        kontroler.przeprowadzSymulacje();
    }
}
