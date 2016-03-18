package turtle;

public class ConstantExpression implements Expression {
	private int value;

	public ConstantExpression(int value) {
		this.value = value;
	}

	@Override
	public void setNext(Expression next) {
	}

	@Override
	public Expression getNext() {
		return null;
	}

	@Override
	public void accept(Visitor visitor) {

	}

	@Override
	public int getValue(Context aContext) {
		return value;

	}

	@Override
	public void setCount(int repeatExpressionCount) {
	}

	@Override
	public int interpreter(Context aContext) {
		return value;
	}

}