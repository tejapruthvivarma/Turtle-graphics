package turtle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	private Expression root = null;
	private Expression tail = null;
	private Context aContext = new Context();
	private Scanner fileScanner;

	public Parser(Context aContext, String filename) throws FileNotFoundException {
		this.aContext = aContext;
		fileScanner = new Scanner(new File(filename));
	}

	public Context getContext() {
		return aContext;
	}

	public void setContext(Context aContext) {
		this.aContext = aContext;
	}

	public Expression parseCommandsIntoTree() {
		String[] commandArray;
		while (fileScanner.hasNextLine()) {
			String nextCommand = fileScanner.nextLine();
			commandArray = nextCommand.split(" ");
			// $K = 1 condition.
			if (nextCommand.startsWith("$")) {
				if (commandArray.length == 3) {
					aContext.setValue(commandArray[0], Integer.parseInt(commandArray[2]));
				}
			}
			// move $k or move 90 or turn $k or turn 90 or repeat $k or repeat
			// 4.
			else if (commandArray.length == 2) {
				if (commandArray[1].startsWith("$")) {
					variableOperandAtRoot(commandArray[0], commandArray[1]);
				} else {
					constantOperandAtRoot(commandArray[0], commandArray[1]);
				}
			}
			// PenUp and PenDown
			else if (commandArray.length == 1) {
				penDownAndUp(commandArray[0]);
			}
		}
		return root;
	}

	public void penDownAndUp(String operator) {
		if (root == null) {
			if (operator.equalsIgnoreCase("penup")) {
				root = new PenUpExpression();
				tail = root;
			} else if (operator.equalsIgnoreCase("pendown")) {
				root = new PenDownExpression();
				tail = root;
			}
		} else {
			if (operator.equalsIgnoreCase("penup")) {
				Expression temp = new PenUpExpression();
				tail.setNext(temp);
				tail = temp;
			} else {
				Expression temp = new PenDownExpression();
				tail.setNext(temp);
				tail = temp;
			}
		}
	}

	public void constantOperandAtRoot(String operator, String operand) {
		ConstantExpression aConstantExpression = new ConstantExpression(Integer.parseInt(operand));
		if (root == null) {
			if (operator.equalsIgnoreCase("Move")) {
				root = new MoveExpression(aConstantExpression);
				tail = root;
			} else if (operator.equalsIgnoreCase("turn")) {
				root = new TurnExpression(aConstantExpression);
				tail = root;
			} else if (operator.equalsIgnoreCase("repeat")) {
				root = new RepeatExpression(aConstantExpression);
				int repeatExpressionsCount = 0;
				tail = root;
				String nextCommand = fileScanner.nextLine();
				while (!nextCommand.equalsIgnoreCase("end")) {
					String[] commandArray = nextCommand.split(" ");
					if (commandArray.length == 2) {

						if (commandArray[1].startsWith("$")) {
							variableOperand(commandArray[0], commandArray[1]);
							repeatExpressionsCount++;
						} else {
							constantOperand(commandArray[0], commandArray[1]);
							repeatExpressionsCount++;
						}
					} else if (commandArray.length == 1) {
						penDownAndUp(commandArray[0]);
						repeatExpressionsCount++;
					}
					nextCommand = fileScanner.nextLine();
				}
				((RepeatExpression) root).setRepeatExpressionsCount(repeatExpressionsCount);
			}
		} else {
			constantOperand(operator, operand);
		}
	}

	public void variableOperandAtRoot(String operator, String operand) {

		VariableExpression aVariableExpression = new VariableExpression(operand);
		if (root == null) {
			if (operator.equalsIgnoreCase("Move")) {
				root = new MoveExpression(aVariableExpression);
				tail = root;
			} else if (operator.equalsIgnoreCase("turn")) {
				root = new TurnExpression(aVariableExpression);
				tail = root;
			} else if (operator.equalsIgnoreCase("repeat")) {
				root = new RepeatExpression(aVariableExpression);
				int repeatExpressionsCount = 0;
				tail = root;
				String nextCommand = fileScanner.nextLine();
				while (!nextCommand.equalsIgnoreCase("end")) {
					String[] commandArray = nextCommand.split(" ");
					if (commandArray.length == 2) {
						if (commandArray[1].startsWith("$")) {
							variableOperand(commandArray[0], commandArray[1]);
							repeatExpressionsCount++;
						} else {
							constantOperand(commandArray[0], commandArray[1]);
							repeatExpressionsCount++;
						}
					} else if (commandArray.length == 1) {
						penDownAndUp(commandArray[0]);
						repeatExpressionsCount++;
					}
					nextCommand = fileScanner.nextLine();
				}
				((RepeatExpression) root).setRepeatExpressionsCount(repeatExpressionsCount);
			}
		} else {
			variableOperand(operator, operand);
		}

	}

	public void variableOperand(String aCommand, String aVariable) {
		VariableExpression aVariableExpression = new VariableExpression(aVariable);
		if (aCommand.equalsIgnoreCase("Move")) {
			Expression temp = new MoveExpression(aVariableExpression);
			tail.setNext(temp);
			tail = temp;
		} else if (aCommand.equalsIgnoreCase("turn")) {
			Expression temp = new TurnExpression(aVariableExpression);
			tail.setNext(temp);
			tail = temp;
		} else if (aCommand.equalsIgnoreCase("repeat")) {
			repeatCommandWithVariable(null, aVariableExpression);
		}
	}

	public void repeatCommandWithVariable(ConstantExpression aConstantExpression, VariableExpression aVariableExpression) {
		String[] commandArray;
		Expression temp = new RepeatExpression(aVariableExpression);
		tail.setNext(temp);
		tail = temp;
		int repeatExpressionsCount = 0;
		String nextCommand = fileScanner.nextLine();
		while (!nextCommand.equalsIgnoreCase("end")) {
			commandArray = nextCommand.split(" ");
			if (commandArray.length == 2) {

				if (commandArray[1].startsWith("$")) {
					variableOperand(commandArray[0], commandArray[1]);
					repeatExpressionsCount++;
				} else {
					constantOperand(commandArray[0], commandArray[1]);
					repeatExpressionsCount++;
				}
			} else if (commandArray.length == 1) {
				penDownAndUp(commandArray[0]);
				repeatExpressionsCount++;
			}
			nextCommand = fileScanner.nextLine();
		}
		((RepeatExpression) temp).setRepeatExpressionsCount(repeatExpressionsCount);
	}

	public void repeatCommandWithConstant(ConstantExpression aConstantExpression, VariableExpression aVariableExpression) {
		String[] commandArray;

		Expression temp = new RepeatExpression(aConstantExpression);
		tail.setNext(temp);
		tail = temp;
		int repeatExpressionsCount = 0;
		String nextCommand = fileScanner.nextLine();
		while (!nextCommand.equalsIgnoreCase("end")) {
			commandArray = nextCommand.split(" ");
			if (commandArray.length == 2) {
				if (commandArray[1].startsWith("$")) {
					variableOperand(commandArray[0], commandArray[1]);
					repeatExpressionsCount++;
				} else {
					constantOperand(commandArray[0], commandArray[1]);
					repeatExpressionsCount++;
				}
			} else if (commandArray.length == 1) {
				penDownAndUp(commandArray[0]);
				repeatExpressionsCount++;
			}
			nextCommand = fileScanner.nextLine();
		}
		((RepeatExpression) temp).setRepeatExpressionsCount(repeatExpressionsCount);
	}

	public void constantOperand(String aCommand, String aConstant) {
		ConstantExpression aConstantExpression = new ConstantExpression(Integer.parseInt(aConstant));
		if (aCommand.equalsIgnoreCase("Move")) {
			Expression temp = new MoveExpression(aConstantExpression);
			tail.setNext(temp);
			tail = temp;
		} else if (aCommand.equalsIgnoreCase("turn")) {
			Expression temp = new TurnExpression(aConstantExpression);
			tail.setNext(temp);
			tail = temp;
		} else if (aCommand.equalsIgnoreCase("repeat")) {
			repeatCommandWithConstant(aConstantExpression, null);
		}
	}

	public void accept(Visitor aCommandVisitor) {
		Expression next = root;
		int value = 0;
		int nestedValueCount = 0;
		next = parseCommandsIntoTree();
		while (next != null) {
			next.accept(aCommandVisitor);
			if (next.getClass() == RepeatExpression.class) {
				value = ((RepeatExpression) next).getRepeatExpressionsCount();
				for (int i = 0; i < value; i++) {
					next = next.getNext();
					if (next.getClass() == RepeatExpression.class) {
						nestedValueCount = ((RepeatExpression) next).getRepeatExpressionsCount();
						value += nestedValueCount;
					}
				}
				if (next != null) {
					next = next.getNext();
				}
			} else {
				if (next != null) {
					next = next.getNext();
				}
			}
		}
	}

}
