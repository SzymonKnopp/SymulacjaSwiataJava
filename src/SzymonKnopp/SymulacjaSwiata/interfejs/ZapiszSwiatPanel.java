package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZapiszSwiatPanel extends JPanel implements ActionListener {
	private final JTextField _nazwaSwiataField;
	private String _nazwaDoZapisu;

	public ZapiszSwiatPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200, 300));

		_nazwaSwiataField = new JTextField();
		_nazwaSwiataField.setPreferredSize(new Dimension(100, 20));
		_nazwaDoZapisu = null;

		add (new JLabel("ZAPISYWANIE"){
			{
				setFont(getFont().deriveFont(20f));
			}
		});
		add(new JLabel("Podaj nazwę zapisu:"));
		add(_nazwaSwiataField);
		add(new JLabel(".save"));
	}

	public String zabierzNazwaDoZapisu(){
		String buforNazwa = _nazwaDoZapisu;
		_nazwaDoZapisu = null;
		return buforNazwa;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		_nazwaDoZapisu = _nazwaSwiataField.getText();
	}

	public void odswiez(){
		revalidate();
		repaint();
	}
}
