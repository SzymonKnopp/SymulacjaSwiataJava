package SzymonKnopp.SymulacjaSwiata.organizmy.rosliny;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import java.awt.*;

public class BarszczSosnowskiego extends Roslina {
	public BarszczSosnowskiego(Swiat swiat, Pole pole) {
		super(swiat, pole);
		_sila = 10;
	}
	public BarszczSosnowskiego(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);
	}

	@Override
	public char gatunek() {
		return 'B';
	}

	@Override
	public Color getKolor(){
		return Color.getHSBColor(110,100,70);
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new BarszczSosnowskiego(_swiat, pole);
	}

	@Override
	public void akcja() {
		_wiek++;
		for (int i = 0; i < 4; i++) { //charakterystyka barszczu
			kierunek_t kierunek = kierunek_t.values()[i];
			Pole poleObok = getMiejsceObok(kierunek);
			if (!_swiat.wWymiarachSwiata(poleObok)) {
				continue;
			}
			Organizm sasiad = _swiat.getOrganizmNaPolu(poleObok);
			if (sasiad != null && sasiad.getSila() < _sila) {
				sasiad.zgin();
			}
		}
	}
}
