package SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;
import java.util.Random;

public abstract class Zwierze extends Organizm {
	Zwierze(Swiat swiat, Pole pole) {
		super(swiat, pole);
	}
	Zwierze(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);
	}

	@Override
	public abstract char gatunek();

	@Override
	public abstract Color getKolor();

	protected abstract Organizm potomek(Pole pole);

	public void akcja() {
		if (!_jestZywy) {
			return;
		}
		Random random = new Random();
		_wiek++;
		kierunek_t kierunekRuchu = kierunek_t.values()[random.nextInt(4)];
		Pole nowaPozycja = getMiejsceObok(kierunekRuchu);
		testujPole(nowaPozycja);
	}


	protected void testujPole(Pole pole) {
		if (!_swiat.wWymiarachSwiata(pole)) {
			return;
		}

		Organizm naPolu = _swiat.getOrganizmNaPolu(pole);
		if (naPolu == null) {
			przesunSie(pole);
			return;
		}
		if (naPolu.gatunek() == this.gatunek()) {
			if(_wiek >= 5) {
				rozmnozSie();
			}
		}
		else {
			atakuj(pole);
		}
	}

	protected void przesunSie(Pole nowaPozycja) {
		_swiat.wyszyscPole(_pozycja);
		_pozycja = nowaPozycja;
		_swiat.setOrganizmNaPolu(this, nowaPozycja);
	}


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

	private void rozmnozSie() {
		Pole pozycjaDziecka = znajdzMiejsceDlaDziecka();
		if (pozycjaDziecka == null){
			_swiat.dodajKomunikat("Zwierzę na (" + _pozycja.toString() + ") próbuje się rozmnożyć, ale brakuje miejsca na młode.");
			return;
		}
		_swiat.dodajOrganizm(potomek(pozycjaDziecka), pozycjaDziecka);
		_swiat.dodajKomunikat("Na polu (" + _pozycja.toString() + ") narodziło się nowe zwierzę.");
	}

	private Pole znajdzMiejsceDlaDziecka() {
		Random random = new Random();
		int kierunekInt = random.nextInt(4);
		for (int i = 0; i < 4; i++) {
			Pole poleDlaDziecka = getMiejsceObok(kierunek_t.values()[(kierunekInt+i) % 4]);
			if (_swiat.wWymiarachSwiata(poleDlaDziecka) && _swiat.getOrganizmNaPolu(poleDlaDziecka) == null) {
				return poleDlaDziecka;
			}
		}
		return null;
	}
}
