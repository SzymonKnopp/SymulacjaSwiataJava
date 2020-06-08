package SzymonKnopp.SymulacjaSwiata.organizmy.zwierzeta;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.Swiat;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;
import SzymonKnopp.SymulacjaSwiata.organizmy.rosliny.BarszczSosnowskiego;

import java.awt.*;
import java.util.Random;

public class CyberOwca extends Zwierze {
	private Pole _poleDocelowe;

	public CyberOwca(Swiat swiat, Pole pole) {
		super(swiat, pole);

		_sila = 11;
		_inicjatywa = 4;
		_poleDocelowe = null;
	}
	public CyberOwca(Swiat swiat, Pole pole, int wiek, int sila) {
		super(swiat, pole, wiek, sila);

		_inicjatywa = 4;
		_poleDocelowe = null;
	}

	@Override
	public char gatunek() {
		return 'C';
	}

	@Override
	public Color getKolor(){
		return Color.getHSBColor(28, 0,73);
	}

	@Override
	protected Organizm potomek(Pole pole) {
		return new CyberOwca(_swiat, pole);
	}

	@Override
	public void akcja(){
		if (!_jestZywy) {
			return;
		}
		_wiek++;

		//jeśli na docelowym polu nie ma już barszczu, zapomnij pole
		if(_poleDocelowe != null && !(_swiat.getOrganizmNaPolu(_poleDocelowe) instanceof BarszczSosnowskiego)){
			_poleDocelowe = null;
		}
		//jeśli nie ma docelowego pola, szukaj barszczu
		if(_poleDocelowe == null){
			int odleglosc = odlegloscOdNajdalszegoRogu();
			for(int i = 1; i <= odleglosc; i++){
				_poleDocelowe = szukajBarszczuWOdleglosci(i);
				if(_poleDocelowe != null){
					break;
				}
			}
		}
		Pole nowaPozycja;
		if(_poleDocelowe != null){
			nowaPozycja = poleNajblizejDocelowego();
		}
		else{
			Random random = new Random();
			kierunek_t kierunekRuchu = kierunek_t.values()[random.nextInt(4)];
			nowaPozycja = getMiejsceObok(kierunekRuchu);
		}
		testujPole(nowaPozycja);
	}

	private int odlegloscOdNajdalszegoRogu(){
		int wLewo = _pozycja.x;
		int wPrawo = _swiat.getWymiar().x - _pozycja.x - 1;
		int wGore = _pozycja.y;
		int wDol = _swiat.getWymiar().y - _pozycja.y - 1;
		int odleglosc = wLewo + wGore;
		odleglosc = Math.max(odleglosc, wPrawo + wGore);
		odleglosc = Math.max(odleglosc, wLewo + wDol);
		odleglosc = Math.max(odleglosc, wPrawo + wDol);
		return odleglosc;
	}

	//sprawdź pola w konkretnej odległości od pozycji
	//     #
	//   #   #
	// #   X   #
	//   #   #
	//     #
	private Pole szukajBarszczuWOdleglosci(int odleglosc){
		for (int i = 0; i <= odleglosc; i++) {
			int y = _pozycja.y - odleglosc + i;
			Pole badanePole = new Pole(_pozycja.x - i, y);
			if(barszczNaPolu(badanePole)){
				return badanePole;
			}
			badanePole = new Pole(_pozycja.x + i, y);
			if(barszczNaPolu(badanePole)){
				return badanePole;
			}
		}
		for (int i = 0; i < odleglosc; i++) {
			int y = _pozycja.y + odleglosc - i;
			Pole badanePole = new Pole(_pozycja.x - i, y);
			if(barszczNaPolu(badanePole)){
				return badanePole;
			}
			badanePole = new Pole(_pozycja.x + i, y);
			if(barszczNaPolu(badanePole)){
				return badanePole;
			}
		}
		return null;
	}

	private boolean barszczNaPolu(Pole pole){
		if(_swiat.wWymiarachSwiata(pole)){
			Organizm naPolu = _swiat.getOrganizmNaPolu(pole);
			if(naPolu instanceof BarszczSosnowskiego){
				return true;
			}
		}
		return false;
	}

	private Pole poleNajblizejDocelowego(){
		if(_poleDocelowe.x - _pozycja.x > 0){
			return getMiejsceObok(kierunek_t.prawo);
		}
		if(_poleDocelowe.x - _pozycja.x < 0){
			return getMiejsceObok(kierunek_t.lewo);
		}
		if(_poleDocelowe.y - _pozycja.y > 0){
			return getMiejsceObok(kierunek_t.gora);
		}
		if(_poleDocelowe.y - _pozycja.y < 0){
			return getMiejsceObok(kierunek_t.dol);
		}
		return null;
	}
}
