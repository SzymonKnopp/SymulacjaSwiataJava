package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputCzlowiekaPanel extends JPanel implements ActionListener, KeyListener {
	private InputCzlowieka _inputCzlowieka;
	private final JLabel _niesmiertelnoscLabel;
	private final JButton _niesmiertelnoscButton;

	public InputCzlowiekaPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200, 200));

		_inputCzlowieka = null;
		_niesmiertelnoscLabel = new JLabel();
		_niesmiertelnoscButton = new JButton("Aktywuj nieśmiertelność");

		_niesmiertelnoscButton.addActionListener(this);
		_niesmiertelnoscButton.addKeyListener(this);
		add(_niesmiertelnoscButton);
		add(_niesmiertelnoscLabel);
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
				_niesmiertelnoscLabel.setText("Nieśmiertelność aktywna jeszce przez " + turyNiesmiertelnosci);
			}
			else{
				_niesmiertelnoscLabel.setText("Nieśmiertelność gotowa za " + (turyNiesmiertelnosci + 5));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		_inputCzlowieka = InputCzlowieka.ZDOLNOSC;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()){
			case KeyEvent.VK_UP:
				_inputCzlowieka = InputCzlowieka.GORA;
				break;
			case KeyEvent.VK_LEFT:
				_inputCzlowieka = InputCzlowieka.LEWO;
				break;
			case KeyEvent.VK_DOWN:
				_inputCzlowieka = InputCzlowieka.DOL;
				break;
			case KeyEvent.VK_RIGHT:
				_inputCzlowieka = InputCzlowieka.PRAWO;
				break;
		}
	}

	public void odswiez(){
		revalidate();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent event) {}
	@Override
	public void keyReleased(KeyEvent event) {}
}