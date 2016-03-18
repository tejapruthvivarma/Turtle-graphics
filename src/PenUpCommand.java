package turtle;

public class PenUpCommand implements Command {
	private Turtle aTurtle;
	private boolean isExcecuted = false;

	public PenUpCommand(Turtle aTurtle) {

		this.aTurtle = aTurtle;
	}

	@Override
	public Turtle execute() {
		aTurtle.penUp();
		isExcecuted = true;
		return aTurtle;

	}

	@Override
	public Turtle undo() {
		if (isExcecuted) {
			aTurtle.penDown();
			isExcecuted = false;
			return aTurtle;
		}
		return aTurtle;
	}

}
