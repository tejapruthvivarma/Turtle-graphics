package turtle;

import java.util.ArrayList;

public class CommandVisitor implements Visitor {
	private ArrayList<Command> list = new ArrayList<Command>();
	private Context aContext;

	public CommandVisitor(Context aContext) {
		this.aContext = aContext;
	}

	public void setList(ArrayList<Command> list) {
		this.list = list;
	}

	public ArrayList<Command> getList() {
		return list;
	}

	@Override
	public void visitMove(MoveExpression aMoveExpression) {
		int distance = aMoveExpression.getValue(aContext);
		Command aMoveCommand = new MoveCommand(distance, aContext.getTurtle());
		list.add(aMoveCommand);
	}

	@Override
	public void visitTurn(TurnExpression aTurnExpression) {
		int degrees = aTurnExpression.getValue(aContext);
		Command aTurnCommand = new TurnCommand(degrees, aContext.getTurtle());
		list.add(aTurnCommand);
	}

	@Override
	public void visitRepeat(RepeatExpression aRepeatExpression) {
		int value = aRepeatExpression.getValue(aContext);
		int expressionCount = aRepeatExpression.getRepeatExpressionsCount();
		int nestedValueCount = 0;
		int repeatLoop = 0;
		for (int i = 0; i < value; i++) {
			Expression next = aRepeatExpression.getNext();
			for (int j = 0; j < expressionCount; j++) {
				if (next != null) {
					next.accept(this);
					if (next.getClass() == RepeatExpression.class) {
						repeatLoop = ((RepeatExpression) next).getRepeatExpressionsCount();
						for (int k = 0; k < repeatLoop; k++) {
							next = next.getNext();
							if (next.getClass() == RepeatExpression.class) {
								nestedValueCount = ((RepeatExpression) next).getRepeatExpressionsCount();
								repeatLoop += nestedValueCount;

							}
						}
						next = next.getNext();
					} else {
						next = next.getNext();
					}
				}

			}
		}
	}

	@Override
	public void visitPenDown(PenDownExpression aPenDownExpression) {
		Command aPenDownCommand = new PenDownCommand(aContext.getTurtle());
		list.add(aPenDownCommand);
	}

	@Override
	public void visitPenUp(PenUpExpression aPenUpExpression) {
		Command aPenUpCommand = new PenUpCommand(aContext.getTurtle());
		list.add(aPenUpCommand);
	}
}
