package turtle;

public interface Visitor {
	public void visitMove(MoveExpression aMoveExpression);

	public void visitTurn(TurnExpression aTurnExpression);

	public void visitRepeat(RepeatExpression repeatExpression);

	public void visitPenDown(PenDownExpression aPenDownExpression);

	public void visitPenUp(PenUpExpression aPenUpExpression);

}
