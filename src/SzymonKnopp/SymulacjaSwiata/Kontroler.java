package SzymonKnopp.SymulacjaSwiata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;
import SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta.*;
import SzymonKnopp.SymulacjaSwiata.organizmy.rosliny.*;

public class Kontroler {
	private Swiat _swiat;
	private Pole _wymiarySwiata;
	private int _poziomZapelnieniaSwiata;
	private Czlowiek _czlowiek;

	public void przygotujSwiat() {
		Scanner skaner = new Scanner(System.in);
		int x,y;
		System.out.println("Podaj wymiar 'x' świata: ");
		x = skaner.nextInt();
		System.out.println("Podaj wymiar 'y' świata: ");
		y = skaner.nextInt();
		_wymiarySwiata = new Pole(x,y);

		System.out.println("Podaj poziom zapełnienia świata organizmami <0,10>: ");
		_poziomZapelnieniaSwiata = skaner.nextInt();
		System.out.println("#######################################");

		_swiat = new Swiat(_wymiarySwiata);

		zapelnijSwiat();
	}

	public void wczytajSwiat() {
		Scanner skaner = new Scanner(System.in);
		System.out.println("Podaj nazwe zapisu (bez '.save'): ");
		String nazwa = skaner.nextLine();
		try{
			File plik = new File(nazwa + ".save");
			Scanner plikSkaner = new Scanner(plik);

			Pole wymiary = new Pole(plikSkaner.nextInt(), plikSkaner.nextInt());
			int licznikTur = plikSkaner.nextInt();

			_swiat = new Swiat(wymiary, licznikTur);
			_wymiarySwiata = wymiary;
			wczytajOrganizmy(plikSkaner);
		}
		catch (FileNotFoundException ex) {
			System.out.println(("Nie znaleziono takiego zapisu!"));
		}
	}

	public void przeprowadzSymulacje() {
		System.out.println("Autor: Szymon Knopp, 175550");
		_swiat.rysujSwiat();
		System.out.println();
		System.out.println("\t -- Log wydarzeń --");
		wypiszKomunikat();
		przetworzInput();
		System.out.println("#######################################");
		while (true) {
			System.out.println("Autor: Szymon Knopp, 175550");
			_swiat.rysujSwiat();
			System.out.println();
			System.out.println("\t -- Log wydarzeń --");
			_swiat.wykonajTure();
			_swiat.rysujSwiat();
			if (!_czlowiek.jestZywy()) {
				System.out.println("Człowiek zginął, koniec gry!");
				return;
			}
			wypiszKomunikat();
			przetworzInput();
			System.out.println("#######################################");
		}
	}


	private void zapiszSwiat() {
		Scanner skaner = new Scanner(System.in);
		System.out.print("Podaj nazwę zapisu: ");
		String nazwa = skaner.nextLine();
		File plik = new File(nazwa + ".save");
		try {
			if(plik.createNewFile()) {
				FileWriter doPlikuZapisu = new FileWriter(nazwa + ".save");
				doPlikuZapisu.write(_swiat.toString() + "\nX");
				doPlikuZapisu.close();
				System.out.println("Zapisano swiat w pliku '" + nazwa + ".save'.");
			}
			else {
				System.out.println("Zapis o takiej nazwie już istnieje! Kontynuowanie symulacji...");
			}
		}
		catch (IOException ex){
			System.out.println("Nie udało się stworzyć zapisu! Kontynuowanie symulacji...");
		}
	}

	private void zapelnijSwiat() {
		Random random = new Random();
		Pole pozycjaCzlowieka = losowaPozycjaWSwiecie();
		Czlowiek czlowiek = new Czlowiek(_swiat, pozycjaCzlowieka);
		_czlowiek = czlowiek;
		_swiat.dodajOrganizm(czlowiek, pozycjaCzlowieka);

		for (int y = 0; y < _wymiarySwiata.y; y++) {
			for (int x = 0; x < _wymiarySwiata.x; x++) {
				if (!(y == pozycjaCzlowieka.y && x == pozycjaCzlowieka.x) && (random.nextInt(10)) + 1 <= _poziomZapelnieniaSwiata) {
					Pole pole = new Pole(x, y);
					zapelnijPole(pole);
				}
			}
		}
		_swiat.aktywujNoweOrganizmy();
	}

	private Pole losowaPozycjaWSwiecie() {
		Random random = new Random();
		return new Pole(random.nextInt(_wymiarySwiata.x), random.nextInt(_wymiarySwiata.y));
	}

	private void zapelnijPole(Pole pole) {
		Organizm organizm = losowyOrganizm(pole);
		_swiat.dodajOrganizm(organizm, pole);
	}

	private Organizm losowyOrganizm(Pole pole) {
		Random random = new Random();
		final String gatunki = "ABCGLMOTJWZ";
		int wybrany = random.nextInt(gatunki.length());
		return nowyOrganizm(gatunki.charAt(wybrany), pole);
	}

	private void wypiszKomunikat() {
		if (_czlowiek.czyZdolnoscAktywowana()) {
			System.out.println();
			System.out.println("Nieśmiertelność aktywna jeszcze przez " + _czlowiek.getIleTurZdolnosci() + " tur.");
			System.out.println("Strzałki - ruch człowiekiem | s - zapisanie świata");
			System.out.println("AKTWYNA NIEŚMIERTELNOŚĆ" );
		}
		else{
			System.out.println();
			System.out.println("ijkl - ruch człowiekiem | spacja - nieśmiertelność | s - zapisanie świata");
		}
	}

	private void przetworzInput() {
		Scanner skaner = new Scanner(System.in);
		char input = skaner.next().charAt(0);
		if (input == 's') {
			zapiszSwiat();
			wypiszKomunikat();
			przetworzInput();
		}
		else {
			_czlowiek.setInput(input);
		}
	}

	private void wczytajOrganizmy(Scanner plikSkaner) {
		while (true) {
			char gatunek = plikSkaner.next().charAt(0);
			if (gatunek == 'X') {
				break;
			}

			Pole pozycja = new Pole(plikSkaner.nextInt(), plikSkaner.nextInt());
			int wiek = plikSkaner.nextInt();
			int sila = plikSkaner.nextInt();
			if(gatunek == '@'){ //człowiek
				int specjalnaZdolnoscInt = plikSkaner.nextInt();
				int timerZdolnosci = plikSkaner.nextInt();
				boolean specjalnaZdolnosc = (specjalnaZdolnoscInt == 1);
				Czlowiek czlowiek = new Czlowiek(_swiat, pozycja, wiek, sila, specjalnaZdolnosc, timerZdolnosci);
				_czlowiek = czlowiek;
				_swiat.dodajOrganizm(czlowiek, pozycja);
			}
			else {
				Organizm organizm = nowyOrganizm(gatunek, pozycja, wiek, sila);
				_swiat.dodajOrganizm(organizm, pozycja);
			}
		}
	}

	private Organizm nowyOrganizm(char gatunek, Pole pole) {
		switch (gatunek) {
			case 'A':
				return new Antylopa(_swiat, pole);
			case 'B':
				return new BarszczSosnowskiego(_swiat, pole);
			case 'C':
				return new CyberOwca(_swiat, pole);
			case 'G':
				return new Guarana(_swiat, pole);
			case 'L':
				return new Lis(_swiat, pole);
			case 'M':
				return new Mlecz(_swiat, pole);
			case 'O':
				return new Owca(_swiat, pole);
			case 'T':
				return new Trawa(_swiat, pole);
			case 'J':
				return new WilczeJagody(_swiat, pole);
			case 'W':
				return new Wilk(_swiat, pole);
			case 'Z':
				return new Zolw(_swiat, pole);
			default:
				return null;
		}
	}

	private Organizm nowyOrganizm(char gatunek, Pole pole, int wiek, int sila) {
		switch (gatunek) {
			case 'A':
				return new Antylopa(_swiat, pole, wiek, sila);
			case 'B':
				return new BarszczSosnowskiego(_swiat, pole, wiek, sila);
			case 'C':
				return new CyberOwca(_swiat, pole, wiek, sila);
			case 'G':
				return new Guarana(_swiat, pole, wiek, sila);
			case 'L':
				return new Lis(_swiat, pole, wiek, sila);
			case 'M':
				return new Mlecz(_swiat, pole, wiek, sila);
			case 'O':
				return new Owca(_swiat, pole, wiek, sila);
			case 'T':
				return new Trawa(_swiat, pole, wiek, sila);
			case 'J':
				return new WilczeJagody(_swiat, pole, wiek, sila);
			case 'W':
				return new Wilk(_swiat, pole, wiek, sila);
			case 'Z':
				return new Zolw(_swiat, pole, wiek, sila);
			default:
				return null;
		}
	}
}
