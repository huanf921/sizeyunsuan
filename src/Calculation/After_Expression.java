package Calculation;

import java.util.ArrayList;
import java.util.Stack;


public class After_Expression {  //生成后缀表达式
		/**
	     *  将中序表达式转换成后序表达式
	     * @param str 生成的中序表达式
	     */
		public static Stack<String> toAfter_Expression(ArrayList<String> list){
			Stack<String> stack=new Stack<String>();//栈
			Stack<String> right=new Stack<String>();//右序表达式
			String ysf;//运算符
				
			for(int i=0;i<list.size();i++){
				String ch=list.get(i);
				if(isysf(ch)){//当前字符为运算符
					if(stack.empty()==true || ch=="("){//栈为空或者为（直接入栈
						stack.push(ch);
					}else{//非栈空、非左括号
						if(ch==")"){//如果为）
							while(true){//将（后的运算符出栈并加到后续表达式中
								if((!stack.empty()) && (!stack.peek().equals("("))){
									ysf=stack.pop();
									right.push(ysf);
								}else{
									if(!stack.empty())//如果栈顶元素为（
										stack.pop();
									break;
								}
							}
						}else{//非栈空、非左括号、非右括号
							while(true){//栈不为空，优先级低
								if(!stack.empty() && priority(ch,stack.peek())){
									ysf=stack.pop()+"";
									if(!ysf.equals("(")){
										right.push(ysf);
									}
								}else{
									break;
								}
							}
							stack.push(ch+"");
						}
					}
					
				}else{
					right.push(ch+"");//操作数
				}
			}
			while(!stack.empty()){
				ysf=stack.pop()+"";
				if(!ysf.equals("("))
					right.push(ysf);
			}
			return right;
		}
		
	
		// 判断是否为运算符
		public static boolean isysf(String ch){
			if((ch.equals("+"))||(ch.equals("-"))||(ch.equals("*"))||(ch.equals("÷"))||(ch.equals("("))||(ch.equals(")")))
				return true;
			else 
				return false;
		}

		/**
		 * 设置运算符的优先级别
		 * @param ysfout当前中序表达式字符
		 * @param ysfin栈中字符
		 * @return
		 */
		public static boolean priority(String ysfout, String ysfin) {
			int m = -1, n = -1;
			String a_ysf[][] = { { "+", "-", "*", "÷", "(", ")" },
					{ "+", "-", "*", "÷", "(", ")" } };
			int first[][] = { { 1, 1, 2, 2, 2, 0 }, { 1, 1, 2, 2, 2, 0 },
					{ 1, 1, 1, 1, 2, 0 }, { 1, 1, 1, 1, 2, 0 },
					{ 2, 2, 2, 2, 2, 0 }, { 2, 2, 2, 2, 2, 2 } };
			for (int i = 0; i < 6; i++) {
				if (ysfin.equalsIgnoreCase(a_ysf[0][i]))
					m = i;
			}
			for (int i = 0; i < 6; i++) {
				if (ysfout.equalsIgnoreCase(a_ysf[1][i]))
					n = i;
			}
			if (m == -1 && n == -1)
				return false;
			else if (m == -1 && n != -1)
				return false;
			else if (m != -1 && n == -1)
				return true;
			else if (first[m][n] == 1) {
				return true;
			} else
				return false;
		}

}
