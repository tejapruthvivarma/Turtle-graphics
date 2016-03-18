package turtle;

public class VariableExpression implements Expression {
	String name;

	public VariableExpression(String name) {
		this.name = name;
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

		return aContext.getValue(name);
	}

	@Override
	public void setCount(int repeatExpressionCount) {

	}

	@Override
	public int interpreter(Context aContext) {
		return aContext.getValue(name);
	}

}