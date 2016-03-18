package turtle;

public class PenDownCommand implements Command {
	private Turtle aTurtle;
	private boolean isExcecuted = false;

	public PenDownCommand(Turtle aTurtle) {

		this.aTurtle = aTurtle;
	}

	@Override
	public Turtle execute() {
		aTurtle.penDown();
		isExcecuted = true;
		return aTurtle;

	}

	@Override
	public Turtle undo() {
		if (isExcecuted) {
			aTurtle.penUp();
			return aTurtle;
		}
		return aTurtle;
	}

}
