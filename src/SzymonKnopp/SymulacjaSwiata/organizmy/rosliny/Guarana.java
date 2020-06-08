package SzymonKnopp.SymulacjaSwiata.organizmy.rosliny;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;

public class Guarana extends Roslina{
	public Guarana(Swiat swiat, Pole pole) {
		super(swiat, pole);
	}
	public Guarana(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);
	}

	@Override
	public char gatunek() {
		return 'G';
	}

	@Override
	public Color getKolor(){
		return Color.getHSBColor(110,100,50);
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new Guarana(_swiat, pole);
	}

	@Override
	public boolean kolizja(Organizm atakujacy) {
		zgin();
		atakujacy.zwiekszSila(); //charakterystyka guarany
		return true;
	}
}
