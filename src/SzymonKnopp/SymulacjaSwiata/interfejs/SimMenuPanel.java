package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;

public class SimMenuPanel extends JPanel {
	private final InputCzlowiekaPanel _inputCzlowiekaPanel;
	private final ZapiszSwiatPanel _zapiszSwiatPanel;

	public SimMenuPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200, 600));

		_inputCzlowiekaPanel = new InputCzlowiekaPanel();
		_zapiszSwiatPanel = new ZapiszSwiatPanel();

		add(_inputCzlowiekaPanel);
		add(_zapiszSwiatPanel);
	}

	public void ustawFokusNaPoluInputu(){
		_inputCzlowiekaPanel.ustawFokus();
	}

	public InputCzlowieka zabierzInputCzlowieka(){
		return _inputCzlowiekaPanel.zabierzInputCzlowieka();
	}

	public void ustawKomunikatONiesmiertelnosci(int turyNiesmiertelnosci){
		_inputCzlowiekaPanel.ustawKomunikatONiesmiertelnosci(turyNiesmiertelnosci);
	}

	public String zabierzNazwaDoZapisu(){
		return _zapiszSwiatPanel.zabierzNazwaDoZapisu();
	}
}
