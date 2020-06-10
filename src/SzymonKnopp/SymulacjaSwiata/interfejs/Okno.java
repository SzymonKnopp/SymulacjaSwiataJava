package SzymonKnopp.SymulacjaSwiata.interfejs;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Okno extends JFrame implements KeyListener {
	private final MenuPanel _menuPanel;
	private SymulacjaPanel _symulacjaPanel;
	private InputCzlowieka _inputCzlowieka;

	public Okno() {
		super("Symulacja świata - Szymon Knopp, 175550");
		setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

		_menuPanel = new MenuPanel();
		_symulacjaPanel = null;
		_inputCzlowieka = null;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(120, 60);
		setSize(1000, 600);
		setResizable(false);

		addKeyListener(this);

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
		requestFocus();
	}

	public void ustawOrganizmy(Organizm[][] organizmy){
		_symulacjaPanel.ustawOrganizmy(organizmy);
	}

	public void dodajKomunikat(String komunikat){_symulacjaPanel.dodajKomunikat(komunikat);}

	public InputCzlowieka zabierzInputCzlowieka(){
		if(_inputCzlowieka != null){
			InputCzlowieka bufor = _inputCzlowieka;
			_inputCzlowieka = null;
			return bufor;
		}
		else return _symulacjaPanel.zabierzInputCzlowieka();

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
		requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int przycisk = event.getKeyCode();
		switch (przycisk) {
			case KeyEvent.VK_UP:
				_inputCzlowieka = InputCzlowieka.GORA;
				break;
			case KeyEvent.VK_LEFT:
				_inputCzlowieka = InputCzlowieka.LEWO;
				break;
			case KeyEvent.VK_DOWN:
				_inputCzlowieka = InputCzlowieka.DOL;
				break;
			case KeyEvent.VK_RIGHT:
				_inputCzlowieka = InputCzlowieka.PRAWO;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {}
	@Override
	public void keyReleased(KeyEvent event) {}
}
