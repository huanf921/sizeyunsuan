package Calculation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Ran_Expression {  //随机生成初步的表达式



		public static char[] ysf={'+','-','*','÷'};
		public static String exp_str;//四则运算表达式
		
		//获取输入
		public static String getScanner(){
			Scanner scan=new Scanner(System.in);
			String input=scan.nextLine();
			scan.close();
			return input;
		}
		
		
		//随机获取运算符+ - * ÷
		public static char getysf(){
			Random ran=new Random();
			int ysfNum=ran.nextInt(4);
			return ysf[ysfNum];
		}
		
		/**
		 * 获取操作数
		 * @param range 数值范围
		 * @return
		 */
		public static String getNumber(int range){
			Random ran=new Random();
			int index=ran.nextInt(3);
			String num="";
			
			/**
			 * 随机获取数字，0获取自然数，1获取真分数，2获取带分数
			 */
			if(index==0){//自然数
				Random ran0=new Random();
				num=ran0.nextInt(range-1)+1+"";
			}
			if(index==1){//真分数
				Random ran1=new Random();
				int fenmu=ran1.nextInt(range-2)+2;//分母[2,range)
				int fenzi=ran1.nextInt(fenmu-1)+1;//分子
				num=fenzi+"/"+fenmu+"";
			}
			if(index==2){//带分数
				Random ran2=new Random();
				int leftNum=ran2.nextInt(range-1)+1;//左整数部分
				int rightFM=ran2.nextInt(range-2)+2;//右真分数部分-分母[2,range)
				int rightFZ=ran2.nextInt(rightFM-1)+1;//右真分数部分-分子
				num=leftNum+"'"+rightFZ+"/"+rightFM+"";
			}
			return num;
		}
		
		/**
		 * 去表达式最前和最后的括号
		 * @param str表达式
		 * @return
		 */
		public static String deleteKuoHao(String str){
			if((str.substring(0, 1).equals("(")) && (str.substring(str.length()-1).equals(")"))){
				str=str.substring(1, str.length()-1);
			}
			return str;
		}
		
		
		 //生成四则运算表达式
		 //range题目中操作数的范围
		public static ArrayList<String> creatAc(int range){
			Random ran4=new Random();
			ArrayList<String> list=new ArrayList<String>();//存放每个表达式中的运算符和操作数
			boolean zuoKuoHao=false;//左括号
			boolean youKuoHao=false;//右括号
			boolean tem=false;
			int ysfNum=ran4.nextInt(3)+1;//每个表达式运算符个数1-3个
			exp_str="";
			
			//------------开始生成--------------
			for(int j=0;j<ysfNum;j++){
				
				//决定是否加入左括号
				if(!zuoKuoHao && ran4.nextBoolean()){
					exp_str+="(";
					list.add("(");
					zuoKuoHao=true;
				}
				String sz1=getNumber(range);
				exp_str+=sz1;
				list.add(sz1);
				//决定是否加入右括号
				if(zuoKuoHao && !youKuoHao && tem){
					if(ran4.nextBoolean()){
						exp_str+=")";
						list.add(")");
						youKuoHao=true;
					}
				}
				char char1=getysf();
				exp_str+=char1;
				list.add(char1+"");
				if(zuoKuoHao){
					tem=true;
				}
			}
			String sz2=getNumber(range);
			exp_str+=sz2;
			list.add(sz2);
			if(zuoKuoHao && !youKuoHao){
				exp_str+=")";
				list.add(")");
			}
			exp_str=deleteKuoHao(exp_str);//去掉开头和结尾均为括号
			//------------结束生成--------------
			
			System.out.println(exp_str);
			return list;
			
		}
		
		

}
