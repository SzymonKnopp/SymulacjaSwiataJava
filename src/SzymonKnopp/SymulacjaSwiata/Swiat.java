package SzymonKnopp.SymulacjaSwiata;

import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;
import java.util.Vector;

public class Swiat {
	private static final int MAX_INICJATYWA = 7;
	private int _licznikTur;
	private final Vector<Organizm> _organizmy;
	private final Vector<Organizm> _noweOrganizmy; //organizmy stworzone w turze, nieaktywne, do następnej tury
	private final Vector<Organizm> _martweOrganizmy; //organizmy do usunięcia
	private final Organizm[][] _pola; //dwuwymiarowa tablica wskaźników na organizmy w konkretnym polu
	private final Pole _wymiar;
	private final Kontroler _kontroler;

	public Swiat(Pole wymiary, Kontroler kontroler) {
		_kontroler = kontroler;
		_licznikTur = 0;
		_organizmy = new Vector<>();
		_noweOrganizmy = new Vector<>();
		_martweOrganizmy = new Vector<>();
		_wymiar = wymiary;
		_pola = new Organizm[_wymiar.x][_wymiar.y];
	}
	public Swiat(Pole wymiary, int licznikTur, Kontroler kontroler) {
		_kontroler = kontroler;
		_licznikTur = licznikTur;
		_organizmy = new Vector<>();
		_noweOrganizmy = new Vector<>();
		_martweOrganizmy = new Vector<>();
		_wymiar = wymiary;
		_pola = new Organizm[_wymiar.x][_wymiar.y];
	}

	public void wykonajTure() {
		_licznikTur++;
		dodajKomunikat("Zaczyna się " + _licznikTur + " tura.");

		int inicjatywa = MAX_INICJATYWA;
		while (true) {
			int nastepnaInicjatywa = 0;
			for (Organizm organizm : _organizmy) {
				if (organizm.getInicjatywa() == inicjatywa && organizm.jestZywy())
					organizm.akcja();
				else if (organizm.getInicjatywa() > nastepnaInicjatywa && organizm.getInicjatywa() < inicjatywa) {
					nastepnaInicjatywa = organizm.getInicjatywa();
				}
			}
			if (inicjatywa == 0) {
				break;
			}
			inicjatywa = nastepnaInicjatywa;
		}

		for (Organizm organizmDoUsuniecia : _martweOrganizmy) {
			for (int i = 0; i < _organizmy.size(); i++) {
				if(_organizmy.get(i) == organizmDoUsuniecia){
					_organizmy.remove(i);
				}
			}
		}
		_martweOrganizmy.clear();

		aktywujNoweOrganizmy();
	}

	public Organizm[][] getPola() {
		return _pola;
	}

	public boolean wWymiarachSwiata(Pole pole) {
		return (pole.x >= 0 && pole.x < _wymiar.x && pole.y >= 0 && pole.y < _wymiar.y);
	}

	public Pole getWymiar(){
		return _wymiar;
	}

	public Organizm getOrganizmNaPolu(Pole pole) {
		return (_pola[pole.x][pole.y]);
	}

	public void setOrganizmNaPolu(Organizm organizm, Pole pole) {
		_pola[pole.x][pole.y] = organizm;
	}

	public void wyszyscPole(Pole pole) {
		_pola[pole.x][pole.y] = null;
	}

	public void zabijOrganizm(Organizm organizm) {
		_martweOrganizmy.add(organizm);
	}

	public void dodajOrganizm(Organizm organizm, Pole pole) {
		setOrganizmNaPolu(organizm, pole);
		_noweOrganizmy.add(organizm);
	}

	public void aktywujNoweOrganizmy() {
		_organizmy.addAll(_noweOrganizmy);
		_noweOrganizmy.clear();
	}

	public void dodajKomunikat(String komunikat){_kontroler.dodajKomunikat(komunikat);}

	@Override
	public String toString() {
		StringBuilder swiat = new StringBuilder();
		swiat.append(_wymiar.x);
		swiat.append(" ");
		swiat.append(_wymiar.y);
		swiat.append(" ");
		swiat.append(_licznikTur);
		swiat.append("\n\n");

		for (Organizm organizm : _organizmy) {
			if (organizm != null) {
				swiat.append(organizm.toString());
				swiat.append('\n');
			}
		}
		return swiat.toString();
	}
}
