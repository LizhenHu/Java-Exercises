/*****************************
 * a program that evaluates a compound expression with multiple 
 * operators and parentheses (e.g., (15 + 2) * 34 Ð 2). For simplicity, 
 * assume that the operands are integers and the operators are of 
 * four types: +, -, *, and /.
 ***************************/


import java.util.Stack;

public class EvaluateExpression {
	public static void main(String[] args){
		//check number of arguments passed
		if(args.length != 1){
			System.out.println(
					"Usage: java EvaluateExpressions \"expression\"");
			System.exit(1);
		}
		
		try{
			System.out.println(evaluationExpression(args[0]));
		} catch (Exception ex) {
			System.out.println("Wrong expression: " + args[0]);
		}
	}
	
	//Evaluate an expression
	public static int evaluationExpression(String expression){
		//create operandStack to store operands
		Stack<Integer> operandStack = new Stack<Integer>();
		
		//create operatorStack to store operator
		Stack<Character> operatorStack = new Stack<Character>();
		
		//insert blanks around operators for split it in the next step
		expression = insertBlanks(expression);
		
		//split operands and operators
		String[] tokens = expression.split(" ");
		
		//scan tokens
		for(String token: tokens){
			if(token.length() == 0) //blank space
				continue;
			else if (token.charAt(0) == '+' || token.charAt(0) == '-'){
				//process all +, -, *, / in the top of the operator stack
				while (!operatorStack.isEmpty() && 
						(operatorStack.peek() == '+'||
						operatorStack.peek() == '-' || 
						operatorStack.peek() == '*' || 
						operatorStack.peek() == '/')) {
						processOperator(operandStack, operatorStack);
				}
				
				//push the + or -  operator in to the operator stack
				operatorStack.push(token.charAt(0));
			}
			
			else if(token.charAt(0) == '*'|| token.charAt(0) == '/'){
				//process all *, /in the top of the operator stack
				while (!operatorStack.isEmpty() && (
						operatorStack.peek() == '*' ||
						operatorStack.peek() == '/')){
					processOperator(operandStack, operatorStack);
				}
				operatorStack.push(token.charAt(0));
			}
			
			else if (token.trim().charAt(0) == '('){
				operatorStack.push('(');
			}
			else if (token.trim().charAt(0) == ')'){
				while(operatorStack.peek() != '('){
					processOperator(operandStack, operatorStack);
				}
				operatorStack.pop();
			}
			else{//an operand scanned, then puch it to the stack
				operandStack.push(new Integer(token));
			}
		}
		
		
		//process all the remaining operatiors in the stack
		while(!operatorStack.isEmpty()){
			processOperator(operandStack, operatorStack);
		}
		
		return operandStack.pop();
	}
	
	//Process one operator
	public static void processOperator(
			Stack<Integer> operandStack, Stack<Character> operatorStack){
		char operator = operatorStack.pop();
		int operand1 = operandStack.pop();
		int operand2 = operandStack.pop();
		
		switch (operator){
		case '+' : operandStack.push(operand1 + operand2);
					break;
		case '-' : operandStack.push(operand1 - operand2);
					break;
		case '*' : operandStack.push(operand1 * operand2);
					break;
		case '/' : operandStack.push(operand1 / operand2);
					break;
					
		}
		
	}
	
	
	
	public static String insertBlanks(String s){
		String result = "";
		
		for (int i = 0; i < s.length(); i++){
			//for character, use single quote mark ''
			if (s.charAt(i) == '(' || s.charAt(i) == ')' 
					|| s.charAt(i) == '+' || s.charAt(i) == '-' 
					||s.charAt(i) == '*' || s.charAt(i) == '/')
			
			result += " " + s.charAt(i) + " ";
			else 
				result += s.charAt(i);
		}
		
		return result;
	}
}
