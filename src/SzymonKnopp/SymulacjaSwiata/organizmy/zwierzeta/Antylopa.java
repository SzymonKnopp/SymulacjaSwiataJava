package SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;
import java.util.Random;

public class Antylopa extends Zwierze {
	public Antylopa(Swiat swiat, Pole pole) {
		super(swiat, pole);

		_sila = 4;
		_inicjatywa = 4;
	}
	public Antylopa(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);

		_inicjatywa = 4;
	}

	@Override
	public char gatunek() {
		return 'A';
	}

	@Override
	public Color getKolor(){
		return Color.PINK;
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new Antylopa(_swiat, pole);
	}

	@Override
	public void akcja() {
		if (!_jestZywy)
			return;
		_wiek++;
		Random random = new Random();
		kierunek_t kierunekRuchu = kierunek_t.values()[random.nextInt(4)];
		Pole nowaPozycja = getMiejsceDwaPolaDalej(kierunekRuchu); //charakterystyka antylopy
		testujPole(nowaPozycja);
	}

	@Override
	public boolean kolizja(Organizm atakujacy) {
		if (ucieczka()) { //charakterystyka antylopy
			return true;
		}
		return bronSie(atakujacy);
	}


	private Pole getMiejsceDwaPolaDalej(kierunek_t kierunek) {
		switch (kierunek) {
			case gora:
				return new Pole(_pozycja.x, _pozycja.y+2);
			case prawo:
				return new Pole(_pozycja.x+2, _pozycja.y);
			case dol:
				return new Pole(_pozycja.x, _pozycja.y-2);
			default:
				return new Pole(_pozycja.x-2, _pozycja.y);
		}
	}

	private boolean ucieczka() {
		Random random = new Random();
		if (random.nextInt(2) == 0) {
			return false;
		}
		Pole nowePole = znajdzWolnePoleDwaDalej();
		if(nowePole == null){
			System.out.println("Antylopa na polu (" + _pozycja.toString() + ") nie znalazła pola na które może uciec!");
			return false;
		}
		przesunSie(nowePole);
		System.out.println("Antylopa na polu (" + _pozycja.toString() + ") uciekła od konfliktu");
		return true;
	}

	private Pole znajdzWolnePoleDwaDalej() {
		Random random = new Random();
		int kierunekInt = random.nextInt(4);
		for (int i = 0; i < 4; i++) {
			Pole poleDlaDziecka = getMiejsceObok(kierunek_t.values()[(kierunekInt+i)%4]);
			if (_swiat.wWymiarachSwiata(poleDlaDziecka) && _swiat.getOrganizmNaPolu(poleDlaDziecka) == null) {
				return poleDlaDziecka;
			}
		}
		return null;
	}
}
