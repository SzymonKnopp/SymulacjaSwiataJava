package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WczytajSwiatPanel extends JPanel implements ActionListener {
	private final JTextField _poleNazwa;
	private String _nazwaZapisu;
	private final JLabel _nieZnalezionoZapisuLabel;

	public WczytajSwiatPanel(){
		_poleNazwa = new JTextField();
		_poleNazwa.setPreferredSize(new Dimension(100, 20));
		_poleNazwa.setHorizontalAlignment(SwingConstants.RIGHT);
		_nazwaZapisu = null;

		_nieZnalezionoZapisuLabel = new JLabel("Nie znaleziono takiego zapisu");
		_nieZnalezionoZapisuLabel.setVisible(false);

		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));
		setPreferredSize(new Dimension(300, 300));

		add(new JLabel("Podaj nazwę zapisu:"){
			{
				setFont(getFont().deriveFont(20f));
			}
		});

		add(new JPanel(){
			{
				add(_poleNazwa);
				add(new JLabel(".save"));
			}
		});

		JButton wczytajSwiatButton = new JButton("Wczytaj świat!");
		wczytajSwiatButton.setPreferredSize(new Dimension(150, 50));
		wczytajSwiatButton.addActionListener(this);
		add(wczytajSwiatButton);

		add(_nieZnalezionoZapisuLabel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		_nazwaZapisu = _poleNazwa.getText() + ".save";

		Thread hideLabelThread = new Thread(() -> {
			_nieZnalezionoZapisuLabel.setVisible(true);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			_nieZnalezionoZapisuLabel.setVisible(false);
		});
		hideLabelThread.start();
	}

	public String zabierzNazwaZapisu(){
		String buforNazwa = _nazwaZapisu;
		_nazwaZapisu = null;
		return buforNazwa;
	}
}
