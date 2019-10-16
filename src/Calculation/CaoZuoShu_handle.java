package Calculation;

import java.util.Stack;

public class CaoZuoShu_handle {


	 //该类将后续表达式stack转化为有分子分母的后续表达式
	 //存于Calcul_Expression对象的calculatorStack中

	Stack<Calcul_Expression> posfixStack;
		
		public CaoZuoShu_handle(Stack<String> stack) {
			Stack<Calcul_Expression> stack1 = new Stack<>();//中间栈
			Stack<Calcul_Expression> stack2 = new Stack<>();//中间栈
			while(!stack.isEmpty()){
				String string = stack.pop();
				//运算符直接进栈
				if(string.equals("+")||string.equals("-")||string.equals("*")||string.equals("÷")){
					Calcul_Expression calcul_exp = new Calcul_Expression(true,string);
					stack1.push(calcul_exp);
				}
				else if(!string.contains("/")){
					string = string + "/1";
					Calcul_Expression node = new Calcul_Expression(false,string);
					stack1.push(node);
				}
				else {
					Calcul_Expression calculator = new Calcul_Expression(false,string);
					stack1.push(calculator);
				}
			}
			for(Calcul_Expression c:stack1){
				stack2.push(c);
			}
			this.posfixStack = stack2;

		}
	

}
