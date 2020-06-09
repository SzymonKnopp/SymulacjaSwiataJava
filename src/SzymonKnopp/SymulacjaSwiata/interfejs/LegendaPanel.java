package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;

public class LegendaPanel extends JPanel {
	public LegendaPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200,500));

		add(new OrganizmPanel(Color.BLUE, "Człowiek"));
		add(new OrganizmPanel(Color.GREEN, "Trawa"));
		add(new OrganizmPanel(Color.BLACK, "Wilk"));
		add(new OrganizmPanel(Color.LIGHT_GRAY, "Owca"));
		add(new OrganizmPanel(Color.GRAY, "Cyberowca"));
		add(new OrganizmPanel(Color.YELLOW, "Mlecz"));
		add(new OrganizmPanel(Color.RED, "Guarana"));
		add(new OrganizmPanel(Color.getHSBColor(0.1f, 1f, 0.6f), "Żółw"));
		add(new OrganizmPanel(Color.getHSBColor(0.4f, 1f, 0.3f), "Barszcz Sosnowskiego"));
		add(new OrganizmPanel(Color.getHSBColor(0.75f, 1f, 0.3f), "Wilcze jagody"));
		add(new OrganizmPanel(Color.ORANGE, "Lis"));
		add(new OrganizmPanel(Color.PINK, "Antylopa"));
	}

	private static class OrganizmPanel extends JPanel{
		private final Color _kolor;

		public OrganizmPanel(Color kolor, String nazwa){
			setLayout(new FlowLayout());
			setPreferredSize(new Dimension(200, 35));

			_kolor = kolor;

			add(new JLabel(nazwa));
		}

		@Override
		protected void paintComponent(Graphics pedzel) {
			super.paintComponent(pedzel);
			pedzel.setColor(_kolor);
			pedzel.fillOval(0,2, 30, 30);
		}
	}
}
