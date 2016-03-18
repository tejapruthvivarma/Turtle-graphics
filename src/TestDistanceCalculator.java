package turtle;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TestDistanceCalculator {

	@Test
	public void test() throws FileNotFoundException {
		String filename = "turtle.txt";
		Context aContext = new Context();
		Turtle aTurtle = new Turtle();
		aContext.setTurtle(aTurtle);
		Parser aParser = new Parser(aContext, filename);
		DistanceCalculator aDistanceCalculator = new DistanceCalculator(aContext);
		aParser.accept(aDistanceCalculator);
        assertTrue( (430 - aDistanceCalculator.getDistance()) <= 0.01);
	}

}
