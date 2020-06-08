package SzymonKnopp.SymulacjaSwiata.organizmy;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import java.awt.Color;

public abstract class Organizm {
	public enum kierunek_t { gora, prawo, dol, lewo }

	protected Swiat _swiat;
	protected boolean _jestZywy;
	protected Pole _pozycja;
	protected int _wiek;
	protected int _sila;
	protected int _inicjatywa;

	public Organizm(Swiat swiat, Pole pole) {
		_swiat = swiat;
		_jestZywy = true;
		_pozycja = pole;
		_wiek = 0;
		_sila = 0;
		_inicjatywa = 0;
	}
	public Organizm(Swiat swiat, Pole pole, int wiek, int sila) {
		_swiat = swiat;
		_jestZywy = true;
		_pozycja = pole;
		_wiek = wiek;
		_sila = sila;
		_inicjatywa = 0;
	}

	public abstract char gatunek();

	public abstract void akcja();

	public boolean kolizja(Organizm atakujacy) {
		return bronSie(atakujacy);
	}
	protected boolean bronSie(Organizm atakujacy) {
		if (atakujacy._sila >= _sila) {
			zgin();
			return true;
		}
		else return false;
	}

	public void zgin() {
		_jestZywy = false;
		_swiat.wyszyscPole(_pozycja);
		_swiat.zabijOrganizm(this);
		System.out.println("Na polu (" + _pozycja.toString() + ") zginął organizm.");
	}

	public String toString() {
		StringBuilder organizm = new StringBuilder();
		organizm.append(gatunek());
		organizm.append(' ');
		organizm.append(_pozycja.x);
		organizm.append(' ');
		organizm.append(_pozycja.y);
		organizm.append(' ');
		organizm.append(_wiek);
		organizm.append(' ');
		organizm.append(_sila);
		organizm.append(' ');
		return organizm.toString();
	}

	public abstract Color getKolor();

	public int getSila() {
		return _sila;
	}

	public void zwiekszSila() {
		_sila += 3;
	}

	public int getInicjatywa() {
		return _inicjatywa;
	}

	public boolean jestZywy() {
		return _jestZywy;
	}

	protected Pole getMiejsceObok(kierunek_t kierunek) {
		switch (kierunek) {
			case gora:
				return new Pole(_pozycja.x, _pozycja.y+1);
			case prawo:
				return new Pole(_pozycja.x+1, _pozycja.y);
			case dol:
				return new Pole(_pozycja.x, _pozycja.y-1);
			default:
				return new Pole(_pozycja.x-1, _pozycja.y);
		}
	}
}
