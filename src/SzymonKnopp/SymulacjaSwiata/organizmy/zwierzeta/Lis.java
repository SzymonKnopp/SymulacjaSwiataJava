package SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;

public class Lis extends Zwierze {
	public Lis(Swiat swiat, Pole pole) {
		super(swiat, pole);

		_sila = 3;
		_inicjatywa = 7;
	}
	public Lis(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);

		_inicjatywa = 7;
	}

	@Override
	public char gatunek() {
		return 'L';
	}

	@Override
	public Color getKolor(){
		return Color.ORANGE;
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new Lis(_swiat, pole);
	}

	@Override
	public void atakuj(Pole pole) {
		Organizm przeciwnik = _swiat.getOrganizmNaPolu(pole);
		if (przeciwnik.getSila() > _sila) { //charakterystyka lisa
			System.out.println("Dobry węch lisa na polu (" + _pozycja.toString() + ") ostrzegł go przed zagrożeniem na polu (");
			return;
		}

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
