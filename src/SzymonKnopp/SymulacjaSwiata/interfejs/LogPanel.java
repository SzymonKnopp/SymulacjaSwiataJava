package SzymonKnopp.SymulacjaSwiata.interfejs;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class LogPanel extends JPanel{
	private final JTextArea _log;

	public LogPanel(){
		setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		setPreferredSize(new Dimension(500,60));

		_log = new JTextArea();
		_log.setEditable(false);
		JScrollPane suwak = new JScrollPane(_log);
		suwak.setPreferredSize(new Dimension(500,60));
		suwak.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(suwak);
	}

	public void dodajKomunikat(String komunikat){
		_log.append(komunikat + '\n');
		_log.setCaretPosition(_log.getDocument().getLength());
	}
}
