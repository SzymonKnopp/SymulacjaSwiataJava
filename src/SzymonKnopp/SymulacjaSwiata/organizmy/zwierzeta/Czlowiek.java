package SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

public class Czlowiek extends Zwierze {
	private static final int DLUGOSC_ZDOLNOSCI = 5;
	private boolean _specjalnaZdolnosc;
	private int _timerZdolnosci;
	private boolean _cooldown;
	private int _input;

	public Czlowiek(Swiat swiat, Pole pole) {
		super(swiat, pole);

		_sila = 5;
		_inicjatywa = 4;
		_specjalnaZdolnosc = false;
		_timerZdolnosci = 0;
		_cooldown = false;
		_input = -1;
	}

	public Czlowiek(Swiat swiat, Pole pole, int wiek, int sila, boolean specjalnaZdolnosc, int timerZdolnosci) {
		super(swiat, pole, wiek, sila);

		_inicjatywa = 4;
		_specjalnaZdolnosc = specjalnaZdolnosc;
		_timerZdolnosci = timerZdolnosci;
		_input = 0;
	}

	@Override
	public char gatunek() {
		return '@';
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return null;
	}

	@Override
	public void akcja() {
		if (!_jestZywy) {
			return;
		}
		ustawSpecjalnaZdolnosc();

		kierunek_t kierunekRuchu;
		switch (_input) {
			case 'i':
				kierunekRuchu = kierunek_t.dol; // kierunek w osi y obrócony, bo rysowane od y=0 (u góry)
				break;
			case 'l':
				kierunekRuchu = kierunek_t.prawo;
				break;
			case 'k':
				kierunekRuchu = kierunek_t.gora;
				break;
			case 'j':
				kierunekRuchu = kierunek_t.lewo;
				break;
			default:
				return;
		}
		Pole nowaPozycja = getMiejsceObok(kierunekRuchu);
		testujPole(nowaPozycja);
	}

	@Override
	public void zgin() {
		if (_specjalnaZdolnosc) {
			return;
		}

		_jestZywy = false;
		_swiat.wyszyscPole(_pozycja);
		//_swiat.zabijOrganizm(this); // człowiek nie jest usuwany, żeby można było sprawdzić warunek zakończenia
		System.out.println("Na polu (" + _pozycja.toString() + ") zginął człowiek.");
	}

	public int getIleTurZdolnosci() {
		return _timerZdolnosci;
	}

	public void setInput(int input) {
		_input = input;
	}

	public boolean czyZdolnoscAktywowana() {
		return _specjalnaZdolnosc;
	}

	@Override
	public String toString() {
		StringBuilder organizm = new StringBuilder();
		organizm.append(gatunek());
		organizm.append(" ");
		organizm.append(_pozycja.x);
		organizm.append(" ");
		organizm.append(_pozycja.y);
		organizm.append(" ");
		organizm.append(_wiek);
		organizm.append(" ");
		organizm.append(_sila);
		organizm.append(" ");
		if(_specjalnaZdolnosc){
			organizm.append("1");
		}
		else{
			organizm.append("0");
		}
		organizm.append(" ");
		organizm.append(_timerZdolnosci);
		organizm.append("\n");
		return organizm.toString();
	}

	private void ustawSpecjalnaZdolnosc() {
		if (_timerZdolnosci > 0)
			_timerZdolnosci--;

		if (_timerZdolnosci == 0) {
			if (!_cooldown && _specjalnaZdolnosc) {
				_timerZdolnosci = DLUGOSC_ZDOLNOSCI;
				_specjalnaZdolnosc = false;
				_cooldown = true;
			}
			else if (_cooldown) {
				_cooldown = false;
			}
			else if (_input == 'n') {
				_timerZdolnosci = DLUGOSC_ZDOLNOSCI;
				_specjalnaZdolnosc = true;
			}
		}
	}

	@Override
	protected void atakuj(Pole pole) {
		Organizm przeciwnik = _swiat.getOrganizmNaPolu(pole);
		boolean wygral = przeciwnik.kolizja(this);
		if (wygral) {
			przeciwnik = _swiat.getOrganizmNaPolu(pole);
			if (przeciwnik == null) {
				przesunSie(pole);
			}
		}
		else {
			zgin();
		}
	}

}
