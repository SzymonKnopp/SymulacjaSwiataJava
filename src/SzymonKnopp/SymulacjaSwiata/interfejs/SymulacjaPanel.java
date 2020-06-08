package SzymonKnopp.SymulacjaSwiata.interfejs;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import javax.swing.*;
import java.awt.*;

public class SymulacjaPanel extends JPanel {
	private final PlanszaPanel _planszaPanel;
	private final SimMenuPanel _simMenuPanel;

	public SymulacjaPanel(Pole pola){
		setLayout(new FlowLayout());

		_planszaPanel = new PlanszaPanel(pola);
		_simMenuPanel = new SimMenuPanel();

		add(_planszaPanel);
		add(_simMenuPanel);
	}

	public void ustawOrganizmy(Organizm[][] organizmy){
		_planszaPanel.ustawOrganizmy(organizmy);
	}

	public InputCzlowieka zabierzInputCzlowieka(){
		return _simMenuPanel.zabierzInputCzlowieka();
	}

	public void ustawKomunikatONiesmiertelnosci(int turyNiesmiertelnosci){
		_simMenuPanel.ustawKomunikatONiesmiertelnosci(turyNiesmiertelnosci);
	}

	public String zabierzNazwaDoZapisu(){
		return _simMenuPanel.zabierzNazwaDoZapisu();
	}
}
