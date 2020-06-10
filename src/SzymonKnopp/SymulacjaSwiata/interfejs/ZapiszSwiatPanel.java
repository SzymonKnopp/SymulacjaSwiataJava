package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZapiszSwiatPanel extends JPanel implements ActionListener {
	private final JTextField _nazwaSwiataField;
	private final JLabel _zapisanoLabel;
	private String _nazwaDoZapisu;

	public ZapiszSwiatPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200, 300));

		_nazwaSwiataField = new JTextField();
		_nazwaSwiataField.setPreferredSize(new Dimension(100, 20));
		_nazwaSwiataField.setHorizontalAlignment(SwingConstants.RIGHT);
		_zapisanoLabel = new JLabel("Zapisano świat!");
		_nazwaDoZapisu = null;

		add (new JLabel("ZAPISYWANIE"){
			{
				setFont(getFont().deriveFont(20f));
			}
		});
		add(new JLabel("Podaj nazwę zapisu:"));
		add(_nazwaSwiataField);
		add(new JLabel(".save"));

		JButton zapiszButton = new JButton("Zapisz świat!");
		zapiszButton.setPreferredSize(new Dimension(120, 20));
		zapiszButton.addActionListener(this);
		add(zapiszButton);
		add(_zapisanoLabel);
		_zapisanoLabel.setVisible(false);
	}

	public String zabierzNazwaDoZapisu(){
		String buforNazwa = _nazwaDoZapisu;
		_nazwaDoZapisu = null;
		return buforNazwa;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		_nazwaDoZapisu = _nazwaSwiataField.getText();

		Thread hideLabelThread = new Thread(() -> {
			_zapisanoLabel.setVisible(true);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			_zapisanoLabel.setVisible(false);
		});
		hideLabelThread.start();
	}
}
