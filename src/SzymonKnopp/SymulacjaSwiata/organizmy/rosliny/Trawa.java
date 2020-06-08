package SzymonKnopp.SymulacjaSwiata.organizmy.rosliny;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;

public class Trawa extends Roslina {
	public Trawa(Swiat swiat, Pole pole) {
		super(swiat, pole);
	}
	public Trawa(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);
	}

	@Override
	public char gatunek() {
		return 'T';
	}

	@Override
	public Color getKolor(){
		return Color.getHSBColor(110,100,30);
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new Trawa(_swiat, pole);
	}
}
