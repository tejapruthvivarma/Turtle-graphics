package turtle;

public interface Command {
	public Turtle execute();

	public Turtle undo();
}
