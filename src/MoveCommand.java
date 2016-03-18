package turtle;

public class MoveCommand implements Command {
	private int distance = 0;
	private Turtle aTurtle;
	private boolean isExcecuted = false;

	public MoveCommand(int distance, Turtle aTurtle) {
		this.distance = distance;
		this.aTurtle = aTurtle;
	}

	@Override
	public Turtle execute() {
		aTurtle.move(distance);
		isExcecuted = true;
		return aTurtle;

	}

	@Override
	public Turtle undo() {
		if (isExcecuted) {
			aTurtle.move(-distance);
			isExcecuted = false;
			return aTurtle;
		}
		return aTurtle;
	}

}
