package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;

public class SimMenuPanel extends JPanel {
	private final InputCzlowiekaPanel _ruchCzlowiekaPanel;
	private final ZapiszSwiatPanel _zapiszSwiatPanel;

	public SimMenuPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200, 600));

		_ruchCzlowiekaPanel = new InputCzlowiekaPanel();
		_zapiszSwiatPanel = new ZapiszSwiatPanel();

		add(_ruchCzlowiekaPanel);
		add(_zapiszSwiatPanel);
	}

	public InputCzlowieka zabierzInputCzlowieka(){
		return _ruchCzlowiekaPanel.zabierzInputCzlowieka();
	}

	public void ustawKomunikatONiesmiertelnosci(int turyNiesmiertelnosci){
		_ruchCzlowiekaPanel.ustawKomunikatONiesmiertelnosci(turyNiesmiertelnosci);
	}

	public String zabierzNazwaDoZapisu(){
		return _zapiszSwiatPanel.zabierzNazwaDoZapisu();
	}
}
