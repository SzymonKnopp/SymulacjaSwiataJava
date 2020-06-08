package SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;

public class Wilk extends Zwierze {
	public Wilk(Swiat swiat, Pole pole) {
		super(swiat, pole);

		_sila = 9;
		_inicjatywa = 5;
	}
	public Wilk(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);

		_inicjatywa = 5;
	}

	@Override
	public char gatunek() {
		return 'W';
	}

	@Override
	public Color getKolor(){
		return Color.getHSBColor(0,0,35);
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new Wilk(_swiat, pole);
	}
}
