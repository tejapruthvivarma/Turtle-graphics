package turtle;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

public class TestTurtleUndo {

	@Test
	public void testTurtleExcecute() throws FileNotFoundException {
		String filename = "turtle.txt";
		Context aContext = new Context();
		Turtle aTurtle = new Turtle();
		aContext.setTurtle(aTurtle);
		Parser aParser = new Parser(aContext, filename);
		CommandVisitor aCommandVisitor = new CommandVisitor(aContext);
		aParser.accept(aCommandVisitor);
		ArrayList<Command> alist = aCommandVisitor.getList();
		for (int i = 0; i < alist.size(); i++) {
			aTurtle = alist.get(i).execute();
		}
		assertTrue( (-70) - (aTurtle.location().getX()) <= 0.01);
        assertTrue( (120) - (aTurtle.location().getY()) <= 0.01);
		}

	public void testTurtleUndo() throws FileNotFoundException {
		String filename = "turtle.txt";
		Context aContext = new Context();
		Turtle aTurtle = new Turtle();
		aContext.setTurtle(aTurtle);
		Parser aParser = new Parser(aContext, filename);
		CommandVisitor aCommandVisitor = new CommandVisitor(aContext);
		aParser.accept(aCommandVisitor);
		ArrayList<Command> alist = aCommandVisitor.getList();
		int i = alist.size();
		i--;
		aTurtle = alist.get(i).undo();
		assertTrue( (-50) - (aTurtle.location().getX()) <= 0.01);
        assertTrue( (120) - (aTurtle.location().getY()) <= 0.01);
	}
}
