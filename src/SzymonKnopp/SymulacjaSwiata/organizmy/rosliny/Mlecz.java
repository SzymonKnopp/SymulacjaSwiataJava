package SzymonKnopp.SymulacjaSwiata.organizmy.rosliny;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

public class Mlecz extends Roslina {
	public Mlecz(Swiat swiat, Pole pole) {
		super(swiat, pole);
	}
	public Mlecz(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);
	}

	@Override
	public char gatunek() {
		return 'M';
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new Mlecz(_swiat, pole);
	}

	@Override
	public void akcja() {
		_wiek++;
		for (int i = 0; i < 3; i++) { //charakterystyka mlecza
			rozprzestrzenSie();
		}
	}
}
