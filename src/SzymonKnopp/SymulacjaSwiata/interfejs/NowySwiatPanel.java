package SzymonKnopp.SymulacjaSwiata.interfejs;

import SzymonKnopp.SymulacjaSwiata.Pole;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowySwiatPanel extends JPanel implements ActionListener {
	private final JTextField _poleX;
	private final JTextField _poleY;
	private Pole _wymiaryNowegoSwiata;

	public NowySwiatPanel(){
		_poleX = new JTextField();
		_poleX.setPreferredSize(new Dimension(30, 20));

		_poleY = new JTextField();
		_poleY.setPreferredSize(new Dimension(30, 20));

		_wymiaryNowegoSwiata = null;

		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));
		setPreferredSize(new Dimension(300, 300));
		add(new JLabel("Podaj wymiary świata:"){
			{
				setFont(getFont().deriveFont(20f));
			}
		});

		add(new JPanel(){
			{
				add(new JLabel("x:"));
				add(_poleX);
				add(new JLabel("y:"));
				add(_poleY);
			}
		});

		JButton stworzSwiatButton = new JButton("Stwórz świat!");
		stworzSwiatButton.setPreferredSize(new Dimension(150, 50));
		stworzSwiatButton.addActionListener(this);
		add(stworzSwiatButton);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int x = Integer.parseInt(_poleX.getText());
		int y = Integer.parseInt(_poleY.getText());
		_wymiaryNowegoSwiata = new Pole(x,y);

	}

	public Pole getWymiaryNowegoSwiata() {
		return _wymiaryNowegoSwiata;
	}
}
