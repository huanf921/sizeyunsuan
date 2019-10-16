package Calculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Calcul_Expression {	//������ʽ


		
		private static final int String = 0;
		Integer fenzi;//����
		Integer fenmu;//��ĸ
		boolean isysf;
		String ysf;
		Calcul_Expression lChild;
		Calcul_Expression rChild;
		
		
		//��������췽�� 
		public Calcul_Expression(boolean isysf,Integer num,Integer den) {
			this.isysf = isysf;
			this.fenmu = den;
			this.fenzi = num;
			
		}
		
		public Calcul_Expression(boolean isysf,String stackElement) {
			if(isysf == true){//Ϊ�����
				this.isysf = true;
				this.ysf = stackElement;	
			}
			else if (isysf == false && stackElement.contains("'")){//Ϊ������
				String[] split1 = stackElement.split("'");
				String[] split2 = split1[1].split("\\/");
				this.fenzi = Integer.parseInt(split1[0])*Integer.parseInt(split2[1]) 
						+ Integer.parseInt(split2[0]);
				this.fenmu = Integer.parseInt(split2[1]);
			}
			else if(isysf == false && (!stackElement.contains("'"))){//Ϊ����
				String[] s = stackElement.split("\\/");
				this.fenzi = Integer.parseInt(s[0]);
				this.fenmu = Integer.parseInt(s[1]);
			}
		}
		
	
		
		public Calcul_Expression() {
		}
		
		//���ݺ�׺���ʽ�����ӷ�ĸ��ʽ������
		//��������������stack2��
		public Stack<Calcul_Expression> calculate(Stack<Calcul_Expression> stackOld){
			Stack<Calcul_Expression> stack=(Stack<Calcul_Expression>) stackOld.clone();
			Stack<Calcul_Expression> stack2 = new Stack<>();
			Calcul_Expression calculator;
			while(!stack.isEmpty()){
				if(!(calculator = stack.pop()).isysf){//������ֱ����ջ
					stack2.push(calculator);
				}
				else  if(calculator.isysf){//��Ϊ�����
					
					//ÿ��ȥ��ջ������Ԫ��
					Calcul_Expression calculator1 = stack2.pop();
					Calcul_Expression calculator2 = stack2.pop();
					
					switch (calculator.ysf) {
					case "+":
						stack2.push(calculator.add(calculator2, calculator1));
						break;
						
					case "-":
						Calcul_Expression res=calculator.sub(calculator2, calculator1);
						if(res.fenzi>0){
							stack2.push(res);
						}else{
							res.ysf="#";
							stack2.push(res);
							return stack2;
						}
						stack2.push(calculator.sub(calculator2, calculator1));
						break;
						
					case "*":
						stack2.push(calculator.mul(calculator2, calculator1));
						break;
						
					case "��":
						stack2.push(calculator.div(calculator2, calculator1));
						break;
		
					default:
						break;
					}
					
				}
			}
			return stack2;
		}
		
		//�ӷ�
		public Calcul_Expression add(Calcul_Expression calculator1,Calcul_Expression calculator2) {
			Integer num = calculator1.fenzi*calculator2.fenmu +
					calculator2.fenzi*calculator1.fenmu;
					Integer den = calculator1.fenmu*calculator2.fenmu;
					int g = gcd(num, den);
					Calcul_Expression calculator = new Calcul_Expression(false, num/g, den/g);
					return calculator;
		}
		
		//����
		public Calcul_Expression sub(Calcul_Expression calculator1,Calcul_Expression calculator2) {
			Integer num = calculator1.fenzi*calculator2.fenmu -
					calculator2.fenzi*calculator1.fenmu;
			Integer den = calculator1.fenmu*calculator2.fenmu;
			int g = gcd(num, den);
			Calcul_Expression calculator = new Calcul_Expression(false, num/g, den/g);
			return calculator;
		}
		
		//�˷�
		public Calcul_Expression mul(Calcul_Expression calculator1,Calcul_Expression calculator2) {
			Integer num = calculator1.fenzi*calculator2.fenzi;
			Integer den = calculator1.fenmu*calculator2.fenmu;
			int g = gcd(num, den);
			Calcul_Expression calculator = new Calcul_Expression(false, num/g, den/g);
			return calculator;
		}
		
		//����
		public Calcul_Expression div(Calcul_Expression calculator1,Calcul_Expression calculator2) {
			Integer num = calculator1.fenzi*calculator2.fenmu;
			Integer den = calculator1.fenmu*calculator2.fenzi;
			int g = gcd(num, den);
			Calcul_Expression calculator = new Calcul_Expression(false, num/g, den/g);
			return calculator;
		}
		
		//���Լ��
		public int gcd(int a, int b){
			int m = Math.max(Math.abs(a), Math.abs(b));
			int n = Math.min(Math.abs(a), Math.abs(b));
			int r;
			while(n!=0){
				r = m % n;
				m = n;
				n = r;
			}
			return m;
		}
		
		//�Ƚ�������������С
		/**
		 * 
		 * @return 	f1 > f2 ���� 2
		 * 			f1 = f2����1
		 * 			f1 < f2����-1
		 * 			���� ����0
		 */
		public int compareFraction(Calcul_Expression f1,Calcul_Expression f2) {
			Calcul_Expression node = new Calcul_Expression();
					
			if (f1.isysf||f2.isysf) {
				System.out.println("���������ֽ��бȽϣ�");
				return 0;
			}
			Calcul_Expression compare = node.sub(f1, f2);//f1 - f2 ���ΪCalcul_Expression����
			int result = compare.fenzi/compare.fenmu;//f1 - f2�Ľ��
			if (result == 0) {
				return 1;
			}
			else if (result > 0) {
				return 2;
			}
			else if (result < 0) {
				return -1;
			} 
				
			return 0;
		}
		
		/**
		 * 
		 * @param o1
		 * @param o2
		 * @return 	o1 > o2 return 2
		 * 			o1 = o2 return 1
		 * 			o1 < o2 return -1
		 * 			����                 return 0
		 */
		public int  compareysf(Calcul_Expression o1,Calcul_Expression o2) {
			if (!o1.isysf||!o2.isysf) {
				System.out.println("��������ȷ�������");
				return 0;
			}
			HashMap<String, Integer> priMap = new HashMap<>();
			priMap.put("+", 0);
			priMap.put("-", 0);
			priMap.put("*", 1);
			priMap.put("��", 1);
			
			if (priMap.get(o1.ysf) > priMap.get(o2.ysf)) {
				//o1 ���� o2
				return 2;
			}
			else if (priMap.get(o1.ysf) == priMap.get(o2.ysf)) {
				//o1 ����o2
				return 1;
			}
			else if (priMap.get(o1.ysf) < priMap.get(o2.ysf)) {
				//o1����o2
				return 1;
			}
			return 0;
		}
		
		//�ٷ���ת���������
			public ArrayList<String> imTomix(ArrayList<Calcul_Expression> answerList) {
				ArrayList<String> arrayList = new ArrayList<>();
				for (int i = 0; i < answerList.size(); i++) {
					if (answerList.get(i).isysf) {
						System.out.println("����������ˣ�");
					}
					else if (answerList.get(i).fenmu == 1){//��ĸΪ1,����= ���ӵ�ֵ
						arrayList.add(answerList.get(i).fenzi + "");
					}
					else if ((answerList.get(i).fenzi == 0) ||(answerList.get(i).fenzi == 0)) {//������Ϊ0�������Ϊ0
						arrayList.add(answerList.get(i).fenzi + "");
					}
					else if (answerList.get(i).fenzi == answerList.get(i).fenmu) {//���ӵ��ڷ�ĸ��answer=1
						arrayList.add(1+"");
					}
					else if (answerList.get(i).fenzi%answerList.get(i).fenmu == 0) {//������������ĸ
						arrayList.add(answerList.get(i).fenzi/answerList.get(i).fenmu + "");
					} 
					else if((answerList.get(i).fenmu!=0)&&answerList.get(i).fenzi/answerList.get(i).fenmu> 1) {//�ٷ�����ת������
						arrayList.add(answerList.get(i).fenzi/answerList.get(i).fenmu + "'" 
								+ answerList.get(i).fenzi%answerList.get(i).fenmu + "/" + answerList.get(i).fenmu);
					}
					else {
						arrayList.add(answerList.get(i).fenzi + "/" + answerList.get(i).fenmu + "");
					}
				}
				return arrayList;
			}
		
		
	

}
