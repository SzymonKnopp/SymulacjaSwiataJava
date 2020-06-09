package SzymonKnopp.SymulacjaSwiata.interfejs;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;

import javax.swing.*;
import java.awt.*;

public class PlanszaiLogPanel extends JPanel {
	private final PlanszaPanel _planszaPanel;
	private final LogPanel _logPanel;

	public PlanszaiLogPanel(Pole pola){
		setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		setPreferredSize(new Dimension(500,600));

		_planszaPanel = new PlanszaPanel(pola);
		_logPanel = new LogPanel();

		add(_planszaPanel);
		add(_logPanel);
	}

	public void ustawOrganizmy(Organizm[][] organizmy){
		_planszaPanel.ustawOrganizmy(organizmy);
	}

	public void dodajKomunikat(String komunikat){_logPanel.dodajKomunikat(komunikat);}


	public void odswiez(){
		revalidate();
		repaint();
		_planszaPanel.odswiez();
		_logPanel.odswiez();
		revalidate();
		repaint();
	}
}
