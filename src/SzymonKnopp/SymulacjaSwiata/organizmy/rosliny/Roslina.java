package SzymonKnopp.SymulacjaSwiata.organizmy.rosliny;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.util.Random;

public abstract class Roslina extends Organizm {
	public Roslina(Swiat swiat, Pole pole) {
		super(swiat, pole);
	}
	public Roslina(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);
	}

	@Override
	public abstract char gatunek();

	protected abstract Organizm potomek(Pole pole);

	@Override
	public void akcja() {
		_wiek++;
		rozprzestrzenSie();
	}

	protected void rozprzestrzenSie() {
		Random random = new Random();
		if (random.nextInt(20) == 0) {
			kierunek_t kierunek = kierunek_t.values()[random.nextInt(4)];
			Pole pole = getMiejsceObok(kierunek);
			if (!_swiat.wWymiarachSwiata(pole)) {
				return;
			}
			Organizm organizm = _swiat.getOrganizmNaPolu(pole);
			if (organizm == null) {
				_swiat.dodajOrganizm(potomek(pole), pole);
				System.out.println("Roślina na polu (" + _pozycja.toString() + ") rozprzestrzeniła się.");
			}
			else {
				System.out.println("Roślina na polu (" + _pozycja.toString() + ") próbowała się rozprzestrzenić, ale wybrane miejsce było zajęte.");
			}
		}
	}
}
