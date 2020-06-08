package SzymonKnopp.SymulacjaSwiata.interfejs;

import SzymonKnopp.SymulacjaSwiata.Pole;
import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
	private final NowySwiatPanel _nowySwiatPanel;
	private final WczytajSwiatPanel _wczytajSwiatPanel;

	public MenuPanel(){
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 150));

		_nowySwiatPanel = new NowySwiatPanel();
		_wczytajSwiatPanel = new WczytajSwiatPanel();

		add(_nowySwiatPanel);

		add(new JPanel(){
			{
				add(new JLabel("LUB"){
					{
						setFont(getFont().deriveFont(20f));
					}
				});
			}
		});

		add(_wczytajSwiatPanel);
	}

	public Pole getWymiaryNowegoSwiata(){
		return _nowySwiatPanel.getWymiaryNowegoSwiata();
	}

	public String getNazwaZapisu(){
		return  _wczytajSwiatPanel.getNazwaZapisu();
	}
}
