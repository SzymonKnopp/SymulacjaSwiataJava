package SzymonKnopp.SymulacjaSwiata.interfejs;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import javax.swing.*;
import java.awt.*;

public class SymulacjaPanel extends JPanel {
	private final PlanszaiLogPanel _planszaiLogPanel;
	private final SimMenuPanel _simMenuPanel;

	public SymulacjaPanel(Pole pola){
		setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));

		_planszaiLogPanel = new PlanszaiLogPanel(pola);
		_simMenuPanel = new SimMenuPanel();

		add(new LegendaPanel());
		add(_planszaiLogPanel);
		add(_simMenuPanel);
	}

	public void ustawOrganizmy(Organizm[][] organizmy){
		_planszaiLogPanel.ustawOrganizmy(organizmy);
	}

	public void dodajKomunikat(String komunikat){_planszaiLogPanel.dodajKomunikat(komunikat);}

	public InputCzlowieka zabierzInputCzlowieka(){
		return _simMenuPanel.zabierzInputCzlowieka();
	}

	public void ustawKomunikatONiesmiertelnosci(int turyNiesmiertelnosci){
		_simMenuPanel.ustawKomunikatONiesmiertelnosci(turyNiesmiertelnosci);
	}

	public String zabierzNazwaDoZapisu(){
		return _simMenuPanel.zabierzNazwaDoZapisu();
	}

	public void odswiez(){
		revalidate();
		repaint();
		_planszaiLogPanel.odswiez();
		_simMenuPanel.odswiez();
		revalidate();
		repaint();
	}
}
