package turtle;

public class PenUpExpression implements Expression {

	private Expression next;
	private int repeatExpressionCount = 0;

	public PenUpExpression() {
		this.next = null;
	}

	@Override
	public void setNext(Expression next) {
		this.next = next;
	}

	@Override
	public Expression getNext() {
		return next;
	}

	@Override
	public int interpreter(Context aContext) {
		Turtle aTurtle = aContext.getTurtle();
		if (repeatExpressionCount > 0) {
			aTurtle.penUp();
			repeatExpressionCount--;

			if (repeatExpressionCount > 0) {
				next.setCount(repeatExpressionCount);
				next.interpreter(aContext);

			}
		}
		aTurtle.penUp();
		if (next != null) {
			next.interpreter(aContext);
		}
		return 0;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitPenUp(this);
	}

	@Override
	public int getValue(Context aContext) {
		return 0;
	}

	@Override
	public void setCount(int repeatExpressionCount) {
		this.repeatExpressionCount = repeatExpressionCount;
	}

}
