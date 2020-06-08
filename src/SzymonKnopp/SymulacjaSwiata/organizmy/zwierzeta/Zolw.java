package SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;
import java.util.Random;

public class Zolw extends Zwierze {
	public Zolw(Swiat swiat, Pole pole) {
		super(swiat, pole);

		_sila = 2;
		_inicjatywa = 1;
	}
	public Zolw(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);

		_inicjatywa = 1;
	}

	@Override
	public char gatunek() {
		return 'Z';
	}

	@Override
	public Color getKolor(){
		return Color.getHSBColor(16,83,23);
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new Zolw(_swiat, pole);
	}

	@Override
	public void akcja() {
		if (!_jestZywy)
			return;

		Random random = new Random();
		if (random.nextInt(4) != 0) //charakterystyka żółwia
			return;

		kierunek_t kierunekRuchu = kierunek_t.values()[random.nextInt(4)];
		Pole nowaPozycja = getMiejsceObok(kierunekRuchu);
		testujPole(nowaPozycja);
	}

	@Override
	public boolean kolizja(Organizm atakujacy) {
		if (atakujacy.getSila() >= _sila) {
			if (atakujacy.getSila() < 5) { //charakterystyka żółwia
				System.out.println("Atak na żółwia na polu (" + _pozycja.toString() + ") został odparty.");
				return true;
			}
			zgin();
			return true;
		}
		else return false;
	}

}
