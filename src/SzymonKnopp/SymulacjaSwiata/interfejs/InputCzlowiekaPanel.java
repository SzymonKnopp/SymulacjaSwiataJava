package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputCzlowiekaPanel extends JPanel implements ActionListener {
	private InputCzlowieka _inputCzlowieka;
	private final JLabel _niesmiertelnoscLabel;
	private final JButton _niesmiertelnoscButton;
	private final JButton _goraButton;
	private final JButton _lewoButton;
	private final JButton _dolButton;
	private final JButton _prawoButton;

	public InputCzlowiekaPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200, 200));

		_inputCzlowieka = null;
		_niesmiertelnoscLabel = new JLabel();
		_niesmiertelnoscButton = new JButton("Aktywuj nieśmiertelność");
		_goraButton = new JButton("GÓRA");
		_goraButton.setPreferredSize(new Dimension(160, 20));
		_lewoButton = new JButton("LEWO");
		_lewoButton.setPreferredSize(new Dimension(80, 20));
		_dolButton = new JButton("DÓŁ");
		_dolButton.setPreferredSize(new Dimension(160, 20));
		_prawoButton = new JButton("PRAWO");
		_prawoButton.setPreferredSize(new Dimension(80, 20));


		_niesmiertelnoscButton.addActionListener(this);
		_goraButton.addActionListener(this);
		_lewoButton.addActionListener(this);
		_dolButton.addActionListener(this);
		_prawoButton.addActionListener(this);
		add(_niesmiertelnoscButton);
		add(_niesmiertelnoscLabel);
		add(_goraButton);
		add(_lewoButton);
		add(_prawoButton);
		add(_dolButton);
	}

	public InputCzlowieka zabierzInputCzlowieka(){
		InputCzlowieka buforInput = _inputCzlowieka;
		_inputCzlowieka = null;
		return buforInput;
	}

	public void ustawKomunikatONiesmiertelnosci(int turyNiesmiertelnosci){
		if(turyNiesmiertelnosci == -5){
			_niesmiertelnoscButton.setEnabled(true);
			_niesmiertelnoscLabel.setText("GOTOWA");
		}
		else{
			_niesmiertelnoscButton.setEnabled(false);
			if(turyNiesmiertelnosci > 0){
				_niesmiertelnoscLabel.setText("<html>Nieśmiertelność aktywna<br>jeszcze przez " + turyNiesmiertelnosci + " tur</html>");
			}
			else{
				_niesmiertelnoscLabel.setText("<html>Nieśmiertelność gotowa<br>za " + (turyNiesmiertelnosci + 5) + " tur</html>");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object przycisk = event.getSource();
		if(przycisk == _niesmiertelnoscButton)
			_inputCzlowieka = InputCzlowieka.ZDOLNOSC;
		else if(przycisk == _goraButton)
			_inputCzlowieka = InputCzlowieka.GORA;
		else if(przycisk == _lewoButton)
			_inputCzlowieka = InputCzlowieka.LEWO;
		else if(przycisk == _dolButton)
			_inputCzlowieka = InputCzlowieka.DOL;
		else if(przycisk == _prawoButton)
			_inputCzlowieka = InputCzlowieka.PRAWO;
	}

	public void odswiez(){
		revalidate();
		repaint();
	}
}