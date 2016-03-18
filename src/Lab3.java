package turtle;

import java.io.IOException;
import java.util.ArrayList;

public class Lab3 {
	public static void main(String args[]) throws IOException {
		String filename = "turtle.txt";
		Context aContext = new Context();
		Turtle aTurtle = new Turtle();
		aContext.setTurtle(aTurtle);
		Parser aParser = new Parser(aContext, filename);
		Expression root = aParser.parseCommandsIntoTree();
		aContext = aParser.getContext();
		//root.interpreter(aContext);
		System.out.println("x : " + aTurtle.location().getX() + " y : " + aTurtle.location().getY() + " " + aTurtle.isPenDown());
		 CommandVisitor aCommandVisitor = new CommandVisitor(aContext);
		
		 Parser aParser1 = new Parser(aContext, filename);
		
		
		 aParser1.accept(aCommandVisitor);
		
		
		 Turtle a = new Turtle();
		 ArrayList<Command> alist = aCommandVisitor.getList();
		 for(int i=0;i<alist.size();i++){
		 a = alist.get(i).execute();
		 }
		 System.out.println(alist.size());
		 System.out.println("x : " + a.location().getX() + " y : " +
		 a.location().getY());
//		DistanceCalculator aDistanceCalculator = new DistanceCalculator(aContext);
//		aParser.accept(aDistanceCalculator);
//		System.out.println(aDistanceCalculator.getDistance());
	}

}
