package calculator;

import jserver.XSendAdapter;

public class MinusButton extends OperatorButton {
	private String operator = "-";
	
	public MinusButton(int x, int y) {
		super(x, y);
	}
	
	public void zeichneOperator(XSendAdapter xsend) {
		xsend.text2(y, x, operator);
	}

	@Override
	public String getOperator() {
		return operator;
	}
	
	@Override
	public String useOperator(String num1, String num2, String operator) {
		return num1 + " " + operator;
	}
}
