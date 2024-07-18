package calculator;

import jserver.XSendAdapter;

public abstract class OperatorButton {
	int x;
	int y;
	
	public OperatorButton(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public float pressed (float num1, float num2) {
		return 0.f;
	}

	public abstract void zeichneOperator(XSendAdapter xsend);

	public boolean clicked(int x, int y) {
		return this.x == x & this.y == y;
	};
	
	public String getOperator() {
		return "op";
	}

	public String useOperator(String num1, String num2, String operator) {
		return "op";
	}
}
