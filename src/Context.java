package turtle;

import java.util.Hashtable;

public class Context {
	private Hashtable<String, Integer> values = new Hashtable<String, Integer>();
	private Turtle aTurtle;

	public int getValue(String variableName) {
		return values.get(variableName);
	}

	public Turtle getTurtle() {
		return aTurtle;
	}

	public void setTurtle(Turtle aTurtle) {
		this.aTurtle = aTurtle;
	}

	public void setValue(String variableName, int value) {
		values.put(variableName, value);
	}
}
