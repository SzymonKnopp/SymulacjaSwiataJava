package SzymonKnopp.SymulacjaSwiata.interfejs;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;
import javax.swing.*;

public class Okno extends JFrame {
	private final MenuPanel _menuPanel;
	private SymulacjaPanel _symulacjaPanel;

	public Okno() {
		super("Symulacja świata - Szymon Knopp, 175550");

		_menuPanel = new MenuPanel();
		_symulacjaPanel = null;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(120, 60);
		setSize(1000, 600);
		setResizable(false);

		add(_menuPanel);
	}

	public Pole getWymiaryNowegoSwiata(){
		return _menuPanel.getWymiaryNowegoSwiata();
	}

	public String zabierzNazwaZapisu(){
		return _menuPanel.zabierzNazwaZapisu();
	}

	public void przedstawSymulacje(Pole pola){
		remove(_menuPanel);
		_symulacjaPanel = new SymulacjaPanel(pola);
		add(_symulacjaPanel);
	}

	public void ustawOrganizmy(Organizm[][] organizmy){
		_symulacjaPanel.ustawOrganizmy(organizmy);
	}

	public InputCzlowieka zabierzInputCzlowieka(){
		return _symulacjaPanel.zabierzInputCzlowieka();
	}

	public void ustawKomunikatONiesmiertelnosci(int turyNiesmiertelnosci){
		_symulacjaPanel.ustawKomunikatONiesmiertelnosci(turyNiesmiertelnosci);
	}

	public String zabierzNazwaDoZapisu(){
		return _symulacjaPanel.zabierzNazwaDoZapisu();
	}

	public void odswiez(){
		revalidate();
		repaint();
		_menuPanel.odswiez();
		_symulacjaPanel.odswiez();
		revalidate();
		repaint();
	}
}
