package SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;

public class Owca extends Zwierze {
	public Owca(Swiat swiat, Pole pole) {
		super(swiat, pole);

		_sila = 4;
		_inicjatywa = 4;
	}
	public Owca(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);

		_inicjatywa = 4;
	}

	@Override
	public char gatunek() {
		return 'O';
	}

	@Override
	public Color getKolor(){
		return Color.LIGHT_GRAY;
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new Owca(_swiat, pole);
	}
}
