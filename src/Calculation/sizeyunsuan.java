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
				System.out.println("使用 -n 参数控制生成题目的个数");
				String commend1=scan.nextLine();
				String[] array1=commend1.split(" ");
				int number=Integer.valueOf(array1[array1.length-1]);//题目数量
				
				System.out.println("使用 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围");
				String commend2=scan.nextLine();
				String[] array2=commend2.split(" ");
				int range=Integer.valueOf(array2[array2.length-1]);//数值范围
				
				int count=0;
				ArrayList<String> finalExp=new ArrayList<>();//最终所有表达式
				ArrayList<Calcul_Expression> finalRes=new ArrayList<>();//最终所有结果（假分数）
				ArrayList<String> realRes =new ArrayList<>();//最终所有结果（真分数）
				ArrayList<Stack<Calcul_Expression>> checkArray=new ArrayList<>();//查重存放数组
				Repeat repeat=new Repeat();
				
				do {
					boolean flag=false;
					Stack<String> ac=new Stack<String>();
					ac=After_Expression.toAfter_Expression(Ran_Expression.creatAc(range));//没带分母的后缀表达式
					CaoZuoShu_handle handle=new CaoZuoShu_handle(ac);//带分母的后缀表达式
					Calcul_Expression node=new Calcul_Expression();
					Stack<Calcul_Expression> stack=new Stack<Calcul_Expression>();
					stack=node.calculate(handle.posfixStack);//计算结果
					if(stack.peek().ysf!="#"&&count==0){//非负，第一个表达式
						checkArray.add(handle.posfixStack);
						finalExp.add(Ran_Expression.exp_str);
						finalRes.add(stack.peek());
						count++;
					}
					if(stack.peek().ysf!="#"&&count>0){//非负，第二个表达式开始
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
					System.out.println("第"+i+"题："+finalExp.get(i));
				}
				for (int i = 0; i < realRes.size(); i++) {
					System.out.println("第"+i+"题答案："+realRes.get(i));
				}
				System.out.println("答案真正长度"+finalRes.size());	


				/*
				 * 生成txt
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
					String exerciseFile_name=array3[2];//题目文件
					String answerFile_name=array3[4];//答案文件
					File exe = new File(exerciseFile_name);
					File ans = new File(answerFile_name);
					fileRW.checkAnswer(exe, ans);//检查对错
				}
				scan.close();
			
			

		}

	
}
