import java.util.Stack;

class pair<T1, T2> {
	public T1 first;
	public T2 second;
	
	public pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
}

interface CalculationFunctions {
	public pair<Boolean, Float> calculateInfixExpress(String expression);
}

public class Calculation implements CalculationFunctions {
	public pair<Boolean, Float> calculateInfixExpress(String expression) {
		if(expression == null)
			return new pair<Boolean, Float>(new Boolean(false), null);
		
		char[] temp = new char[100];
		char cCurrent = ' ';
		Stack<Float> numberStack = new Stack<Float>();
		Stack<Character> operatorStack = new Stack<Character>();
		
		for(int i = expression.length() - 1; i >= 0; ) {
			int j, k, l;
			int numDot = 0;
			char currentOperator;
			
			for(j = i; j >= 0 && (cCurrent = expression.charAt(j)) != '+' && cCurrent != '-' && cCurrent != '*' && cCurrent != '/'; j --);
				if(cCurrent == '.')
					numDot ++;
				
			if(numDot > 1 && j >= 0)
				return new pair<Boolean, Float>(new Boolean(false), null);
			
			currentOperator = j < 0 ? ' ' : cCurrent;
			
			for(l = j + 1, k = 0; k < 99 && l <= i; k ++, l ++)
				temp[k] = expression.charAt(l);
			
			temp[k] = '\0';
			i = j - 1;
			
			numberStack.push(new Float(Float.parseFloat(new String(temp))));
			
			if(currentOperator != ' ') {
				if(operatorStack.isEmpty())
					operatorStack.push(new Character(currentOperator));
				else {
					char operator = operatorStack.pop().charValue();
					
					if(operator == '*' && numberStack.size() >= 2) {
						float firstNumber = numberStack.pop().floatValue();
						float secondNumber = numberStack.pop().floatValue();
						
						firstNumber *= secondNumber;
						
						numberStack.push(new Float(firstNumber));
					} else if(operator == '/') {
						if(currentOperator == '*' || currentOperator == '/')
							operatorStack.push(new Character(operator));
						else if(numberStack.size() >= 2) {
							float firstNumber = numberStack.pop().floatValue();
							float secondNumber = numberStack.pop().floatValue();
							
							firstNumber /= secondNumber;
							
							numberStack.push(new Float(firstNumber));
						}
					} else if(operator == '+') {
						if(currentOperator == '-' && numberStack.size() >= 2) {
							float firstNumber = numberStack.pop().floatValue();
							float secondNumber = numberStack.pop().floatValue();
							
							firstNumber -= secondNumber;
							
							numberStack.push(new Float(firstNumber));
						} else
							operatorStack.push(new Character(operator));
					} else if(operator == '-')
						operatorStack.push(new Character(operator));
					
					operatorStack.push(new Character(currentOperator));
				}
			}
		}
		
		while(numberStack.size() >= 2 && !operatorStack.isEmpty()) {
			float firstNumber = numberStack.pop().floatValue();
			float secondNumber = numberStack.pop().floatValue();
			char operator = operatorStack.pop().charValue();
			
			if(operator == '+')
				firstNumber += secondNumber;
			else if(operator == '-')
				firstNumber -= secondNumber;
			else if(operator == '*')
				firstNumber *= secondNumber;
			else if(operator == '/')
				firstNumber /= secondNumber;
			
			numberStack.push(new Float(firstNumber));
		}
				
		if(numberStack.size() >= 2 || !operatorStack.isEmpty())
			return new pair<Boolean, Float>(new Boolean(false), null);
		
		return new pair<Boolean, Float>(new Boolean(true), numberStack.pop());
	}
	
	public static void main(String[] args) {
		Calculation cal = new Calculation();
		
		pair<Boolean, Float> result = cal.calculateInfixExpress("81+324/2.34*32");
		
		if(result.first.booleanValue())
			System.out.println("Result is: " + result.second.floatValue());
		else
			System.out.println("Wrong calculation!");
	}
}
