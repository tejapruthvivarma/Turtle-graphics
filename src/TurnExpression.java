package turtle;

public class TurnExpression implements Expression {
	private Expression operand;
	private Expression next;
	private int degrees;
	private int repeatExpressionCount = 0;

	public TurnExpression(Expression operand) {
		this.operand = operand;
		this.next = null;
	}

	public Expression getNext() {
		return next;
	}

	public void setNext(Expression next) {
		this.next = next;
	}

	public int interpreter(Context aContext) {
		if (repeatExpressionCount > 0) {
			int degrees = operand.interpreter(aContext);
			Turtle aTurtle = aContext.getTurtle();
			aTurtle.turn(degrees);
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

	@Override
	public void accept(Visitor visitor) {
		visitor.visitTurn(this);
	}

	@Override
	public int getValue(Context aContext) {
		degrees = operand.interpreter(aContext);
		return degrees;
	}

	@Override
	public void setCount(int repeatExpressionCount) {
		this.repeatExpressionCount = repeatExpressionCount;

	}
}
