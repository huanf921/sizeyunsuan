package Calculation;

import java.util.ArrayList;
import java.util.Stack;

/**
 * ���췽�������ɲ��ر��ʽ
 * @author LHY
 *
 */
public class Repeat {
	/**
	 * @param profixStack ��׺���ʽջ
	 */
	public Stack<Calcul_Expression> checkRepeat(Stack<Calcul_Expression> profixStack) {
		// TODO Auto-generated constructor stub
		Stack<Calcul_Expression> numberStack = new Stack<>(); //����һ���м�ջ���������
		Stack<Calcul_Expression> checkStack = new Stack<>(); //��Ų��ر��ʽջ
				
//		System.out.println(1);
		Calcul_Expression bookNode = new Calcul_Expression(true, 0,0);
//		System.out.println(2);
		Calcul_Expression node1 = new Calcul_Expression();
		Calcul_Expression node2 = new Calcul_Expression();
		while(!profixStack.isEmpty()){//ɨ���׺���ʽջֱ����Ϊ��
			Calcul_Expression proStack_top = profixStack.pop();//��ʼɨ���һ��
			if (!proStack_top.isysf&&!(proStack_top.fenzi==0&&proStack_top.fenmu==0)) {//����׺���ʽջ��Ԫ��Ϊ���֡�����#���numberStack
					numberStack.push(proStack_top);
//					System.out.println(proStack_top);
			}
//			else if (proStack_top.isOperator&&proStack_top.numerator==0&&proStack_top.denominator==0) {
//				numberStack.pop();
//			}
			else if (proStack_top.isysf) {//��׺���ʽջ��Ϊ�����,���checkStack,��pop��������,����#ѹ������
				checkStack.push(proStack_top);
				if (numberStack.size() > 1) {
					if (!(node1=numberStack.pop()).isysf&&!(node1.fenzi==0)&&!(node1.fenmu==0)) {//��#
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
		//ת�ɲ��ر��ʽ
		ArrayList<Calcul_Expression> temp=new ArrayList<Calcul_Expression>();//�м���ջ1
		Calcul_Expression tempNode=new Calcul_Expression();//�м佻�����
		Stack<Calcul_Expression> checkRepeat1=repeat.checkRepeat(exp1);
		Stack<Calcul_Expression> checkRepeat2=repeat.checkRepeat(exp2);
		Stack<Calcul_Expression> newStack=new Stack<Calcul_Expression>();//���������ջ
		int lengthRe1=checkRepeat1.size();
		int lengthRe2=checkRepeat2.size();
		System.out.println(1);
		if(lengthRe1!=lengthRe2) return false;//�����Ȳ���ȣ�����ʽһ����ͬ
		System.out.println(2);
		for(Calcul_Expression n:checkRepeat1){
			temp.add(n);
		}
		if (this.isEqual(checkRepeat1, checkRepeat2)) {//��ȫһ���򷵻�true
			return true;
		}

		if(temp.get(0).ysf.equals("+")||temp.get(0).ysf.equals("*")){//ֻ�мӻ�˵�����ſ��ܳ��� �������Ҳ����������ظ��ı��ʽ
			tempNode=temp.get(1);
			temp.set(1, temp.get(2));
			temp.set(2, tempNode);
		}
		for(Calcul_Expression p:temp){
			newStack.push(p);
		}
		if(this.isEqual(newStack, checkRepeat2)) return true;//��������Ҳ������ظ�
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
			if (s1_top.isysf) {//��s1ջ��Ϊ�����
				if (s2_top.isysf&&(!s2_top.ysf.equals(s1_top.ysf))) {//s2��Ϊ�������s1������s2
					return false;
				}
				else if (!s2_top.isysf) {//s1Ϊ�������s2�������
					return false;
				}
			}
			else if (!s1_top.isysf) {//��s1������
				if (s2_top.isysf) {//s2Ϊ�����
					return false;
				}
				else if (!s2_top.isysf&&(s2_top.compareFraction(s1_top, s2_top)!=1)) {//s2Ϊ��������������s1
				return false;	
				}
			}
			
		}		
		return true;
	}
}

