package calculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import jserver.Board;
import jserver.BoardClickEvent;
import jserver.BoardClickListener;
import jserver.XSendAdapter;

public class NewCalculator implements KeyListener, BoardClickListener {
	private Board board = new Board();
	private XSendAdapter xsend = new XSendAdapter(board);
	private JTextField display = new JTextField();
	int hoehe = 6;
	int breite = 3;
	String form = "F";
	String num1 = "";
	String num2 = "";
	String result = "";
	String operator;
	boolean operatorChosen = false;
	String[][] numButtons;
	OperatorButton[] operatorButtons;

	public static void main(String[] args) {
		new NewCalculator().start();
	}

	private void start() {
		setUpBoard();
	}
	
	EqualsButton equalsButton = new EqualsButton(5, 1);

	private void setUpBoard() {
		
		board.addClickListener(this);
		display.setBounds(50, 25, 300, 50);
		display.setEditable(false);
		board.getGraphic().addBottomComponent(display);
		xsend.groesse(breite, hoehe);
		xsend.formen(form);

		numButtons = new String[][] { { "0", "1", "2", "3", "4", "." }, { "5", "6", "7", "8", "9", "=" } };
		operatorButtons = new OperatorButton[] { equalsButton, new PlusButton(2, 2), new MinusButton(3, 2), 
				new DivisionButton(4, 2), new MultiplyButton(5, 2)};

		for (OperatorButton operatorButton : operatorButtons) {
			operatorButton.zeichneOperator(xsend);
		}

		for (int row = 0; row < numButtons.length; row++) {
			for (int col = 0; col < numButtons[0].length; col++) {
				xsend.text2(row, col, numButtons[row][col]);
			}
		}
		
		xsend.text2(2, 1, "delete");
		xsend.text2(2, 0, "clear");
	}

	@Override
	public void boardClick(BoardClickEvent info) {
		int x = info.getX();
		int y = info.getY();
		
		// Operator click
		if (x > 1 && y > 1) {
			for (OperatorButton o : operatorButtons) {
				if (o.clicked(y, x)) {
					if (!operatorChosen) {
						operator = o.getOperator();
						num1 = display.getText();
						result = num1 + operator;
						operatorChosen = true;
					} else {
						String text = display.getText();
						num2 = text.substring(text.indexOf(operator) + 1);
						result = equalsButton.useOperator(num1, num2, operator);
						operator = o.getOperator();
						num1 = result;
						result = result + operator;
					}
				}
			}
		// Delete Button
		} else if (x == 2 && y == 1) {
			String text = display.getText();
			if (!text.isEmpty()) {
				result = text.substring(0, text.length() - 1);
			}
		// Clear Button
		} else if (x == 2 && y == 0) {
				num1 = "";
				num2 = "";
	            result = "";
	            operatorChosen = false;
	    // Numbers Button
		} else {
			if (equalsButton.clicked(y, x)) {
				String text = display.getText();
				num2 = text.substring(text.indexOf(operator) + 1);
				result = equalsButton.useOperator(num1, num2, operator);
				operatorChosen = false;
			} else {
				String zielWert = numButtons[x][y];
				result = display.getText() + zielWert;
			}
		}
		showResult(result);
	}

	private void showResult(String result) {
		display.setText(result);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
}

