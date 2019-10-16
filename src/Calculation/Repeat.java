package Calculation;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 构造方法：生成查重表达式
 * @author LHY
 *
 */
public class Repeat {
	/**
	 * @param profixStack 后缀表达式栈
	 */
	public Stack<Calcul_Expression> checkRepeat(Stack<Calcul_Expression> profixStack) {
		// TODO Auto-generated constructor stub
		Stack<Calcul_Expression> numberStack = new Stack<>(); //构造一个中间栈，存放数字
		Stack<Calcul_Expression> checkStack = new Stack<>(); //存放查重表达式栈
				
//		System.out.println(1);
		Calcul_Expression bookNode = new Calcul_Expression(true, 0,0);
//		System.out.println(2);
		Calcul_Expression node1 = new Calcul_Expression();
		Calcul_Expression node2 = new Calcul_Expression();
		while(!profixStack.isEmpty()){//扫描后缀表达式栈直至其为空
			Calcul_Expression proStack_top = profixStack.pop();//开始扫描第一个
			if (!proStack_top.isysf&&!(proStack_top.fenzi==0&&proStack_top.fenmu==0)) {//若后缀表达式栈顶元素为数字。若非#则进numberStack
					numberStack.push(proStack_top);
//					System.out.println(proStack_top);
			}
//			else if (proStack_top.isOperator&&proStack_top.numerator==0&&proStack_top.denominator==0) {
//				numberStack.pop();
//			}
			else if (proStack_top.isysf) {//后缀表达式栈顶为运算符,则进checkStack,再pop两个数字,并把#压进数字
				checkStack.push(proStack_top);
				if (numberStack.size() > 1) {
					if (!(node1=numberStack.pop()).isysf&&!(node1.fenzi==0)&&!(node1.fenmu==0)) {//非#
						checkStack.push(node1);
					}
					if (!(node2=numberStack.pop()).isysf&&!(node2.fenzi==0)&&!(node2.fenmu==0)) {
						checkStack.push(node2);
					}
				}
				numberStack.push(bookNode);
			}
			
		}//end while
		System.out.println("size"+checkStack.size());
		for(Calcul_Expression node:checkStack){
			if (node.isysf) {
				System.out.print(node.ysf + " ");
			}else if(!node.isysf){
				System.out.print(node.fenzi + "/" + node.fenmu + " ");
			}
		}
		return checkStack;
		
	}
	
	public  boolean IsRepeat(Stack<Calcul_Expression> exp1,Stack<Calcul_Expression> exp2){
		Repeat repeat=new Repeat();
		//转成查重表达式
		ArrayList<Calcul_Expression> temp=new ArrayList<Calcul_Expression>();//中间存放栈1
		Calcul_Expression tempNode=new Calcul_Expression();//中间交换结点
		Stack<Calcul_Expression> checkRepeat1=repeat.checkRepeat(exp1);
		Stack<Calcul_Expression> checkRepeat2=repeat.checkRepeat(exp2);
		Stack<Calcul_Expression> newStack=new Stack<Calcul_Expression>();//交换后的新栈
		int lengthRe1=checkRepeat1.size();
		int lengthRe2=checkRepeat2.size();
		System.out.println(1);
		if(lengthRe1!=lengthRe2) return false;//若长度不相等，则表达式一定不同
		System.out.println(2);
		for(Calcul_Expression n:checkRepeat1){
			temp.add(n);
		}
		if (this.isEqual(checkRepeat1, checkRepeat2)) {//完全一样则返回true
			return true;
		}

		if(temp.get(0).ysf.equals("+")||temp.get(0).ysf.equals("*")){//只有加或乘的情况才可能出现 交换左右操作数当做重复的表达式
			tempNode=temp.get(1);
			temp.set(1, temp.get(2));
			temp.set(2, tempNode);
		}
		for(Calcul_Expression p:temp){
			newStack.push(p);
		}
		if(this.isEqual(newStack, checkRepeat2)) return true;//若交换后也相等则重复
		System.out.println(3);
		return false;
	}
	
	public boolean  isEqual(Stack<Calcul_Expression> stack1,Stack<Calcul_Expression> stack2) {
		Stack<Calcul_Expression> s1 = new Stack<>();
		Stack<Calcul_Expression> s2 = new Stack<>();
		
		Calcul_Expression s1_top;
		Calcul_Expression s2_top;
		for(Calcul_Expression node1:stack1){
			s1.push(node1);
		}
		for(Calcul_Expression node2:stack2){
			s2.push(node2);
		}
		
		while (!s1.isEmpty()&&!s2.isEmpty()) {
			s1_top = s1.pop();
			s2_top = s2.pop();
			if (s1_top.isysf) {//若s1栈顶为运算符
				if (s2_top.isysf&&(!s2_top.ysf.equals(s1_top.ysf))) {//s2都为运算符但s1不等于s2
					return false;
				}
				else if (!s2_top.isysf) {//s1为运算符，s2非运算符
					return false;
				}
			}
			else if (!s1_top.isysf) {//若s1操作数
				if (s2_top.isysf) {//s2为运算符
					return false;
				}
				else if (!s2_top.isysf&&(s2_top.compareFraction(s1_top, s2_top)!=1)) {//s2为操作数但不等于s1
				return false;	
				}
			}
			
		}		
		return true;
	}
}

