package turtle;

public class TurnCommand implements Command {
	private int degrees;
	private Turtle aTurtle;
	private boolean isExcecuted = false;

	public TurnCommand(int degrees, Turtle aTurtle) {
		this.degrees = degrees;
		this.aTurtle = aTurtle;
	}

	@Override
	public Turtle execute() {
		aTurtle.turn(degrees);
		isExcecuted = true;
		return aTurtle;

	}

	@Override
	public Turtle undo() {
		if (isExcecuted) {
			aTurtle.turn(-degrees);
			isExcecuted = false;
			return aTurtle;
		}
		return aTurtle;
	}

}
