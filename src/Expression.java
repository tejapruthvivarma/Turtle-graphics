package turtle;

public interface Expression {
	public void setNext(Expression next);

	public Expression getNext();

	public void setCount(int repeatExpressionCount);

	public int interpreter(Context aContext);

	public void accept(Visitor visitor);

	public int getValue(Context aContext);

}
