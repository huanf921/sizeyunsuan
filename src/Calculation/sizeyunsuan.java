package Calculation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Calculation.Calcul_Expression;
import Calculation.Repeat;

//
//import com.sun.org.apache.regexp.internal.recompile;
//import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;
//
//import sun.nio.cs.ext.ISCII91;

public class sizeyunsuan {

	public static void main(String[] args) {	
				FileRW fileRW=new FileRW();
				Scanner scan=new Scanner(System.in);
				System.out.println("ʹ�� -n ��������������Ŀ�ĸ���");
				String commend1=scan.nextLine();
				String[] array1=commend1.split(" ");
				int number=Integer.valueOf(array1[array1.length-1]);//��Ŀ����
				
				System.out.println("ʹ�� -r ����������Ŀ����ֵ����Ȼ������������������ĸ���ķ�Χ");
				String commend2=scan.nextLine();
				String[] array2=commend2.split(" ");
				int range=Integer.valueOf(array2[array2.length-1]);//��ֵ��Χ
				
				int count=0;
				ArrayList<String> finalExp=new ArrayList<>();//�������б��ʽ
				ArrayList<Calcul_Expression> finalRes=new ArrayList<>();//�������н�����ٷ�����
				ArrayList<String> realRes =new ArrayList<>();//�������н�����������
				ArrayList<Stack<Calcul_Expression>> checkArray=new ArrayList<>();//���ش������
				Repeat repeat=new Repeat();
				
				do {
					boolean flag=false;
					Stack<String> ac=new Stack<String>();
					ac=After_Expression.toAfter_Expression(Ran_Expression.creatAc(range));//û����ĸ�ĺ�׺���ʽ
					CaoZuoShu_handle handle=new CaoZuoShu_handle(ac);//����ĸ�ĺ�׺���ʽ
					Calcul_Expression node=new Calcul_Expression();
					Stack<Calcul_Expression> stack=new Stack<Calcul_Expression>();
					stack=node.calculate(handle.posfixStack);//������
					if(stack.peek().ysf!="#"&&count==0){//�Ǹ�����һ�����ʽ
						checkArray.add(handle.posfixStack);
						finalExp.add(Ran_Expression.exp_str);
						finalRes.add(stack.peek());
						count++;
					}
					if(stack.peek().ysf!="#"&&count>0){//�Ǹ����ڶ������ʽ��ʼ
						for(Stack<Calcul_Expression> p:checkArray){
							
							if(repeat.isEqual(p, handle.posfixStack)){
								flag=true;
							}
						}
						if(!flag){
							checkArray.add(handle.posfixStack);
							finalExp.add(Ran_Expression.exp_str);
							finalRes.add(stack.peek());
							count++;
						}
					}
				} while (count!=number);
				Calcul_Expression transNode=new Calcul_Expression();
				realRes=transNode.imTomix(finalRes);
				
				for (int i = 0; i < finalExp.size(); i++) {
					System.out.println("��"+i+"�⣺"+finalExp.get(i));
				}
				for (int i = 0; i < realRes.size(); i++) {
					System.out.println("��"+i+"��𰸣�"+realRes.get(i));
				}
				System.out.println("����������"+finalRes.size());	


				/*
				 * ����txt
				 */
				fileRW.writeQ(finalExp);
				try {
					fileRW.writeA(realRes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String commend3=scan.nextLine();
				String[] array3=commend3.split(" ");
				if(array3.length>1){
					String exerciseFile_name=array3[2];//��Ŀ�ļ�
					String answerFile_name=array3[4];//���ļ�
					File exe = new File(exerciseFile_name);
					File ans = new File(answerFile_name);
					fileRW.checkAnswer(exe, ans);//���Դ�
				}
				scan.close();
			
			

		}

	
}
