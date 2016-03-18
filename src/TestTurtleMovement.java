package turtle;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TestTurtleMovement {

	@Test
	public void testMovement() throws FileNotFoundException {
		String filename = "turtle.txt";
		Context aContext = new Context();
		Turtle aTurtle = new Turtle();
		aContext.setTurtle(aTurtle);
		Parser aParser = new Parser(aContext, filename);
		Expression root = aParser.parseCommandsIntoTree();
		root.interpreter(aContext);
	    assertTrue( (-70) - (aTurtle.location().getX()) <= 0.01);
        assertTrue( (120) - (aTurtle.location().getY()) <= 0.01);
	}

	@Test
	public void testIsPenDown() throws FileNotFoundException {
		String filename = "turtle.txt";
		Context aContext = new Context();
		Turtle aTurtle = new Turtle();
		aContext.setTurtle(aTurtle);
		Parser aParser = new Parser(aContext, filename);
		Expression root = aParser.parseCommandsIntoTree();
		root.interpreter(aContext);
		assertTrue(aTurtle.isPenDown());

	}

}
