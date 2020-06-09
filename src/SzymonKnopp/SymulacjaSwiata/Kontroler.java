package SzymonKnopp.SymulacjaSwiata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import SzymonKnopp.SymulacjaSwiata.interfejs.InputCzlowieka;
import SzymonKnopp.SymulacjaSwiata.interfejs.Okno;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;
import SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta.*;
import SzymonKnopp.SymulacjaSwiata.organizmy.rosliny.*;

public class Kontroler {
	private static final int POZIOM_ZAPELNIENIA = 5;
	private Swiat _swiat;
	private Pole _wymiarySwiata;
	private Czlowiek _czlowiek;
	private volatile Okno _interfejs;
	private boolean _zaladowanoSwiat;

	public Kontroler(){
		Thread intefrejsThread = new Thread(()->{_interfejs = new Okno();});
		intefrejsThread.run();

		_zaladowanoSwiat = false;

		while(true){
			Pole pola = _interfejs.getWymiaryNowegoSwiata();
			if(pola != null){
				przygotujSwiat(pola);
				break;
			}
			String nazwaZapisu = _interfejs.zabierzNazwaZapisu();
			if(nazwaZapisu != null){
				wczytajSwiat(nazwaZapisu);
				if(_zaladowanoSwiat){
					break;
				}
			}
		}
		_interfejs.przedstawSymulacje(_wymiarySwiata);
		_interfejs.odswiez();
		przeprowadzSymulacje();
	}

	public void przygotujSwiat(Pole pola) {
		_wymiarySwiata = new Pole(pola.x,pola.y);
		_swiat = new Swiat(_wymiarySwiata);
		zapelnijSwiat();
	}

	public void wczytajSwiat(String nazwa) {
		try{
			File plik = new File(nazwa + ".save");
			_zaladowanoSwiat = true;
			Scanner plikSkaner = new Scanner(plik);

			Pole wymiary = new Pole(plikSkaner.nextInt(), plikSkaner.nextInt());
			int licznikTur = plikSkaner.nextInt();

			_swiat = new Swiat(wymiary, licznikTur);
			_wymiarySwiata = wymiary;
			wczytajOrganizmy(plikSkaner);
		}
		catch (FileNotFoundException ex) {
			_zaladowanoSwiat = false;
		}
	}

	public void przeprowadzSymulacje() {
		_interfejs.ustawOrganizmy(_swiat.getPola());
		_interfejs.ustawKomunikatONiesmiertelnosci(_czlowiek.getIleTurZdolnosci());
		_interfejs.odswiez();
		przetworzInput();
		while (true) {
			_swiat.wykonajTure();
			_interfejs.ustawOrganizmy(_swiat.getPola());
			_interfejs.ustawKomunikatONiesmiertelnosci(_czlowiek.getIleTurZdolnosci());
			_interfejs.odswiez();
			if (!_czlowiek.jestZywy()) {
				System.out.println("Człowiek zginął, koniec gry!");
				return;
			}
			przetworzInput();
		}
	}


	private void zapiszSwiat(String nazwa) {
		File plik = new File(nazwa + ".save");
		try {
			if(plik.createNewFile()) {
				FileWriter doPlikuZapisu = new FileWriter(nazwa + ".save");
				doPlikuZapisu.write(_swiat.toString() + "\nX");
				doPlikuZapisu.close();
			}
		}
		catch (IOException ex){
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
				if (!(y == pozycjaCzlowieka.y && x == pozycjaCzlowieka.x) && (random.nextInt(10)) + 1 <= POZIOM_ZAPELNIENIA) {
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

	private void przetworzInput() {
		while(true){
			InputCzlowieka input = _interfejs.zabierzInputCzlowieka();
			if(input != null){
				_czlowiek.setInput(input);
				break;
			}
			String nazwaDoZapisu = _interfejs.zabierzNazwaDoZapisu();
			if(nazwaDoZapisu != null){
				zapiszSwiat(nazwaDoZapisu);
			}
			_interfejs.odswiez();
		}
		_interfejs.odswiez();
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
