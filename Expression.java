package project3;

import java.util.*;

public class Expression {
	
	/*
	 * Input the infix.
	 */
	
	private String infix;
	
	public Expression() {
		infix = "";
	}
	
	public Expression(String infix) {
		this.infix = infix;
	}
	
	/**
	 * Convert the infix string into a postfix.
	 * @ return Postfix
	 */
	
	public ArrayList<String> InfixToPostfix() {
		StringTokenizer infixstr = new StringTokenizer(infix, "+-*/()", true);
		GenericStack<String> stack = new GenericStack<>();
		ArrayList<String> FinalExpr = new ArrayList<>();
		
		while (infixstr.hasMoreTokens()) { //if it has more tokens, run the while loop
			String token = infixstr.nextToken();
			if (!isOperator(token)) { // append the operand
				FinalExpr.add(token);
			} else if (isOperator(token)) {
				if (isOpen(token)) { // if there is an open parenthesis set, push it to the stack
					stack.push(token);
				} else if (isClosed(token)) { // if closed parenthesis, pop all until open parenthesis
					while (!stack.peek().equals("(")) {
						FinalExpr.add(stack.pop());
					}
					stack.pop();
				} else if (stack.isEmpty()) {
					stack.push(token);
				} else { // if the stack isn't empty do:
					if (Precedence(token) >= 1) { //if the token has greater than or equal to precedence
						
						while (!stack.isEmpty()) { //if the peek doesn't equal open parenthesis
							
							String str = stack.peek();
							if (str.equals("(")) {
								stack.push(token);
								break;
							}
							
							if (Precedence(str) <= Precedence(token)) { // if he token has a higher precedence value:
								FinalExpr.add(stack.pop());
								continue;
							}
						}
						
						if (stack.isEmpty()) { // Push to the stack:
							stack.push(token);
						}
			
					} else {
						stack.push(token);
					}
				}
			}
		}
		
		while (!stack.isEmpty()) { //One there aren't anymore tokens, pop everything in the stack.
			FinalExpr.add(stack.pop());
		}
		return FinalExpr; // Hold
	}
	
	/**
	 * Evaluate the Postfix Expression
	 * 
	 * @return Integer
	 * 
	 */
	
	public int evaluate() {
		ArrayList<String> evaList = InfixToPostfix();
		GenericStack<Integer> stack = new GenericStack<>();
		String s = "";
		int t, first, second, num;
		num = 0;
		
		for (int i = 0; i < evaList.size(); i++) {
			s = evaList.get(i);
			if (!isOperator(s)) {
				t = Integer.parseInt(s);
				stack.push(t);
			} else {
				first = stack.pop();
				second = stack.pop();
				
				if (s.equals("+")) {
					num = second + first;
				} else if (s.equals("-")) {
					num = second - first;
				} else if (s.equals("*")) {
					num = second * first;
				} else if (s.equals("/")) {
					num = second / first;
				}
				stack.push(num);
			}
		}
		num = stack.pop();
		
		return num;
	}
	
	/**
	 * Method to test the difference between operators and operands
	 * @param s string
	 * @return true or false logic
	 */
	
	public boolean isOperator(String s) {
		switch (s) {
		case "+":
			return true;
		case "-":
			return true;
		case "*":
			return true;
		case "/":
			return true;
		case ")":
			return true;
		case "(":
			return true;
			
		default:
			return false;
		}
	}
	
	/**
	 * Set the precedence to a string.
	 * @param s string
	 * @return precedence value
	 */
	
	public int Precedence(String s) {
		switch(s) {
		case "*":
			return 2;
		case "/":
			return 2;
			
		default:
			return 1;
		}
	}
	
	/**
	 * Method to test if the string is an Open set of parenthesis.
	 * @param s if Open parenthesis.
	 * @return true or false
	 */
	
	public boolean isOpen(String s) {
		if (s.equals("(")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method to test if the string is an Closed set of parenthesis.
	 * @param s if Closed parenthesis.
	 * @return true or false
	 */
	
	public boolean isClosed(String s) {
		if (s.equals(")")) {
			return true;
		}
		return false;
	}

}
