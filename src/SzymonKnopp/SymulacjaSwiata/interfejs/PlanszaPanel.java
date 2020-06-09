package SzymonKnopp.SymulacjaSwiata.interfejs;

import SzymonKnopp.SymulacjaSwiata.Pole;
import SzymonKnopp.SymulacjaSwiata.organizmy.Organizm;
import javax.swing.*;
import java.awt.*;

public class PlanszaPanel extends JPanel {
	private static final int WYMIAR_X = 500;
	private static final int WYMIAR_Y = 500;
	private static final Color PUSTE_POLE = Color.GREEN;
	private final int _szerokoscPola;
	private final int _polaX;
	private final int _polaY;
	private final Point[][] _pola;
	private final Color[][] _kolory;

	public PlanszaPanel(Pole pola){
		setPreferredSize(new Dimension(WYMIAR_X,WYMIAR_Y));

		_polaX = pola.x;
		_polaY = pola.y;
		_szerokoscPola = Math.min(WYMIAR_X / _polaX, WYMIAR_Y / _polaY);
		_pola = new Point[_polaX][_polaY];
		_kolory = new Color[_polaX][_polaY];

		ustawPozycjePolIKolory();
	}
	private void ustawPozycjePolIKolory() {
		for (int y = 0; y < _polaY; y++) {
			for (int x = 0; x < _polaX; x++) {
				_pola[x][y] = new Point(x*_szerokoscPola, y*_szerokoscPola);
				_kolory[x][y] = PUSTE_POLE;
			}
		}
	}

	public void ustawOrganizmy(Organizm[][] organizmy){
		for (int y = 0; y < organizmy[0].length; y++) {
			for (int x = 0; x < organizmy.length; x++) {
				if(organizmy[x][y] == null){
					_kolory[x][y] = PUSTE_POLE;
				}
				else{
					_kolory[x][y] = organizmy[x][y].getKolor();
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics pedzel) {
		super.paintComponent(pedzel);
		for (int y = 0; y < _polaY; y++) {
			for (int x = 0; x < _polaX; x++) {
				//organizm
				pedzel.setColor(_kolory[x][y]);
				pedzel.fillRect(_pola[x][y].x, _pola[x][y].y, _szerokoscPola, _szerokoscPola);

				//brzeg pola
				pedzel.setColor(Color.BLACK);
				pedzel.drawRect(_pola[x][y].x, _pola[x][y].y, _szerokoscPola, _szerokoscPola);
			}
		}
	}

	public void odswiez(){
		revalidate();
		repaint();
	}
}
