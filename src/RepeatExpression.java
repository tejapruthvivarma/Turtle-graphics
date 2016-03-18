package turtle;

public class RepeatExpression implements Expression {

	private Expression operand;
	private Expression next;
	private int value;
	private int repeatExpressionsCount = 0;
	private Context aContext;
	private int countFromPreviousLoop = 0;

	public int getRepeatExpressionsCount() {
		return repeatExpressionsCount;
	}

	public void setRepeatExpressionsCount(int repeatExpressionsCount) {
		this.repeatExpressionsCount = repeatExpressionsCount;
	}

	public RepeatExpression(Expression operand) {
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
	public int interpreter(Context aContext) {
		Expression temp = next;
		value = operand.interpreter(aContext);
// Nested Repeat Logic.
		if (countFromPreviousLoop > 0) {
			for (int i = 0; i < value; i++) {
				if (temp != null) {
					temp.setCount(repeatExpressionsCount);
					temp.interpreter(aContext);
				}
			}

			for (int j = 0; j < repeatExpressionsCount; j++) {
				if (temp != null) {
					if (temp.getClass() == RepeatExpression.class) {
						int value = ((RepeatExpression) temp).getRepeatExpressionsCount();
						repeatExpressionsCount += value;
					}
					temp = temp.getNext();
				}
			}
			if (temp != null) {
				countFromPreviousLoop--;
				if (countFromPreviousLoop > 0) {
					temp.setCount(countFromPreviousLoop);
					temp.interpreter(aContext);
				}

			}
		} 
		else {
			for (int i = 0; i < value; i++) {
				if (temp != null) {
					temp.setCount(repeatExpressionsCount);
					temp.interpreter(aContext);
				}
			}
			for (int j = 0; j < repeatExpressionsCount; j++) {
				if (temp != null) {
					if (temp.getClass() == RepeatExpression.class) {
						int value = ((RepeatExpression) temp).getRepeatExpressionsCount();
						repeatExpressionsCount += value;
					}
					temp = temp.getNext();
				}
			}
			if (temp != null) {
				temp.interpreter(aContext);
			}
		}
		return 0;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitRepeat(this);
	}

	@Override
	public int getValue(Context aContext) {

		value = operand.interpreter(aContext);
		return value;
	}

	public void setContext(Context aContext) {
		this.aContext = aContext;

	}

	@Override
	public void setCount(int repeatExpressionCount) {
		this.countFromPreviousLoop = repeatExpressionCount;

	}

	public Context getContext() {
		return aContext;
	}

}
