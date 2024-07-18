package calculator;

import jserver.XSendAdapter;

public class EqualsButton extends OperatorButton {
	private String operator = "=";

	public EqualsButton(int x, int y) {
		super(x, y);
	}
	
	@Override 
	public float pressed (float num1, float num2) {
		return num1 + num2;
	}

	@Override
	public void zeichneOperator(XSendAdapter xsend) {
		xsend.text2(y, x, operator);
	}
	
	@Override
	public String getOperator() {
		return operator;
	}
	
	@Override
	public String useOperator(String num1, String num2, String operator) {
	    float result = 0;
	    float number1 = Float.parseFloat(num1);
	    float number2 = Float.parseFloat(num2);

	    switch (operator) {
	        case "+":
	            result = number1 + number2;
	            break;
	        case "-":
	            result = number1 - number2;
	            break;
	        case "*":
	            result = number1 * number2;
	            break;
	        case "/":
	            if (number2 != 0) {
	                result = number1 / number2;
	            } else {
	                return "Error: Nicht Erlaubt!";
	            }
	            break;
	        default:
	            return "Error: Invalid operator";
	    }
	    return String.valueOf(result);
	}
	
}
