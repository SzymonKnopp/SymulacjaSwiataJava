package SzymonKnopp.SymulacjaSwiata.organizmy.rosliny;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

public class WilczeJagody extends Roslina {
	public WilczeJagody(Swiat swiat, Pole pole) {
		super(swiat, pole);

		_sila = 99;
	}
	public WilczeJagody(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);
	}

	@Override
	public char gatunek() {
		return 'J';
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new WilczeJagody(_swiat, pole);
	}
}
