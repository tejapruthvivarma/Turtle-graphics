package turtle;

public class DistanceCalculator implements Visitor {

	private int distance = 0;

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance += distance;
	}

	private Context aContext;

	public DistanceCalculator(Context aContext) {
		this.aContext = aContext;
	}

	@Override
	public void visitMove(MoveExpression aMoveExpression) {
		int value = aMoveExpression.getValue(aContext);
		aContext.getTurtle().move(value);
		setDistance(value);
	}

	@Override
	public void visitTurn(TurnExpression aTurnExpression) {
		int degrees = aTurnExpression.getValue(aContext);
		aContext.getTurtle().turn(degrees);

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
		aContext.getTurtle().penDown();
	}

	@Override
	public void visitPenUp(PenUpExpression aPenUpExpression) {
		aContext.getTurtle().penUp();
	}

}
