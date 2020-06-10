package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;

public class SimMenuPanel extends JPanel {
	private final InputCzlowiekaPanel inputCzlowiekaPanel;
	private final ZapiszSwiatPanel _zapiszSwiatPanel;

	public SimMenuPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200, 600));

		inputCzlowiekaPanel = new InputCzlowiekaPanel();
		_zapiszSwiatPanel = new ZapiszSwiatPanel();

		add(inputCzlowiekaPanel);
		add(_zapiszSwiatPanel);
	}

	public InputCzlowieka zabierzInputCzlowieka(){
		return inputCzlowiekaPanel.zabierzInputCzlowieka();
	}

	public void ustawKomunikatONiesmiertelnosci(int turyNiesmiertelnosci){
		inputCzlowiekaPanel.ustawKomunikatONiesmiertelnosci(turyNiesmiertelnosci);
	}

	public String zabierzNazwaDoZapisu(){
		return _zapiszSwiatPanel.zabierzNazwaDoZapisu();
	}
}
