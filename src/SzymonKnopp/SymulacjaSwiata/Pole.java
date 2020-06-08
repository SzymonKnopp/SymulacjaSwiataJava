package SzymonKnopp.SymulacjaSwiata;

public class Pole {
	public final int x;
	public final int y;

	public Pole(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
}
