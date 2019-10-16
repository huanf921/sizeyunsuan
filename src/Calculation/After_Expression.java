package Calculation;

import java.util.ArrayList;
import java.util.Stack;


public class After_Expression {  //���ɺ�׺���ʽ
		/**
	     *  ��������ʽת���ɺ�����ʽ
	     * @param str ���ɵ�������ʽ
	     */
		public static Stack<String> toAfter_Expression(ArrayList<String> list){
			Stack<String> stack=new Stack<String>();//ջ
			Stack<String> right=new Stack<String>();//������ʽ
			String ysf;//�����
				
			for(int i=0;i<list.size();i++){
				String ch=list.get(i);
				if(isysf(ch)){//��ǰ�ַ�Ϊ�����
					if(stack.empty()==true || ch=="("){//ջΪ�ջ���Ϊ��ֱ����ջ
						stack.push(ch);
					}else{//��ջ�ա���������
						if(ch==")"){//���Ϊ��
							while(true){//��������������ջ���ӵ��������ʽ��
								if((!stack.empty()) && (!stack.peek().equals("("))){
									ysf=stack.pop();
									right.push(ysf);
								}else{
									if(!stack.empty())//���ջ��Ԫ��Ϊ��
										stack.pop();
									break;
								}
							}
						}else{//��ջ�ա��������š���������
							while(true){//ջ��Ϊ�գ����ȼ���
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
					right.push(ch+"");//������
				}
			}
			while(!stack.empty()){
				ysf=stack.pop()+"";
				if(!ysf.equals("("))
					right.push(ysf);
			}
			return right;
		}
		
	
		// �ж��Ƿ�Ϊ�����
		public static boolean isysf(String ch){
			if((ch.equals("+"))||(ch.equals("-"))||(ch.equals("*"))||(ch.equals("��"))||(ch.equals("("))||(ch.equals(")")))
				return true;
			else 
				return false;
		}

		/**
		 * ��������������ȼ���
		 * @param ysfout��ǰ������ʽ�ַ�
		 * @param ysfinջ���ַ�
		 * @return
		 */
		public static boolean priority(String ysfout, String ysfin) {
			int m = -1, n = -1;
			String a_ysf[][] = { { "+", "-", "*", "��", "(", ")" },
					{ "+", "-", "*", "��", "(", ")" } };
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
