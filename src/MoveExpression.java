package turtle;

public class MoveExpression implements Expression {
	private Expression operand;
	private int repeatExpressionCount = 0;
	private Expression next;

	public MoveExpression(Expression operand) {
		this.operand = operand;

		this.next = null;
	}

	public Expression getNext() {
		return next;
	}

	public void setNext(Expression next) {
		this.next = next;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitMove(this);
	}

	@Override
	public int getValue(Context aContext) {
		int distance = operand.interpreter(aContext);
		return distance;
	}

	@Override
	public void setCount(int repeatExpressionCount) {
		this.repeatExpressionCount = repeatExpressionCount;

	}

	@Override
	public int interpreter(Context aContext) {
		Turtle aTurtle = aContext.getTurtle();
		int distance = operand.interpreter(aContext);
		aTurtle.move(distance);
		if (repeatExpressionCount > 0) {

			repeatExpressionCount--;
			if (repeatExpressionCount > 0) {
				if (next != null) {
					next.setCount(repeatExpressionCount);
					next.interpreter(aContext);
				}
			}

		} else {
			if (next != null) {
				next.interpreter(aContext);
			}
		}
		return 0;
	}

}
