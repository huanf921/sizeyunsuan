package Calculation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Ran_Expression {  //������ɳ����ı��ʽ



		public static char[] ysf={'+','-','*','��'};
		public static String exp_str;//����������ʽ
		
		//��ȡ����
		public static String getScanner(){
			Scanner scan=new Scanner(System.in);
			String input=scan.nextLine();
			scan.close();
			return input;
		}
		
		
		//�����ȡ�����+ - * ��
		public static char getysf(){
			Random ran=new Random();
			int ysfNum=ran.nextInt(4);
			return ysf[ysfNum];
		}
		
		/**
		 * ��ȡ������
		 * @param range ��ֵ��Χ
		 * @return
		 */
		public static String getNumber(int range){
			Random ran=new Random();
			int index=ran.nextInt(3);
			String num="";
			
			/**
			 * �����ȡ���֣�0��ȡ��Ȼ����1��ȡ�������2��ȡ������
			 */
			if(index==0){//��Ȼ��
				Random ran0=new Random();
				num=ran0.nextInt(range-1)+1+"";
			}
			if(index==1){//�����
				Random ran1=new Random();
				int fenmu=ran1.nextInt(range-2)+2;//��ĸ[2,range)
				int fenzi=ran1.nextInt(fenmu-1)+1;//����
				num=fenzi+"/"+fenmu+"";
			}
			if(index==2){//������
				Random ran2=new Random();
				int leftNum=ran2.nextInt(range-1)+1;//����������
				int rightFM=ran2.nextInt(range-2)+2;//�����������-��ĸ[2,range)
				int rightFZ=ran2.nextInt(rightFM-1)+1;//�����������-����
				num=leftNum+"'"+rightFZ+"/"+rightFM+"";
			}
			return num;
		}
		
		/**
		 * ȥ���ʽ��ǰ����������
		 * @param str���ʽ
		 * @return
		 */
		public static String deleteKuoHao(String str){
			if((str.substring(0, 1).equals("(")) && (str.substring(str.length()-1).equals(")"))){
				str=str.substring(1, str.length()-1);
			}
			return str;
		}
		
		
		 //��������������ʽ
		 //range��Ŀ�в������ķ�Χ
		public static ArrayList<String> creatAc(int range){
			Random ran4=new Random();
			ArrayList<String> list=new ArrayList<String>();//���ÿ�����ʽ�е�������Ͳ�����
			boolean zuoKuoHao=false;//������
			boolean youKuoHao=false;//������
			boolean tem=false;
			int ysfNum=ran4.nextInt(3)+1;//ÿ�����ʽ���������1-3��
			exp_str="";
			
			//------------��ʼ����--------------
			for(int j=0;j<ysfNum;j++){
				
				//�����Ƿ����������
				if(!zuoKuoHao && ran4.nextBoolean()){
					exp_str+="(";
					list.add("(");
					zuoKuoHao=true;
				}
				String sz1=getNumber(range);
				exp_str+=sz1;
				list.add(sz1);
				//�����Ƿ����������
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
			exp_str=deleteKuoHao(exp_str);//ȥ����ͷ�ͽ�β��Ϊ����
			//------------��������--------------
			
			System.out.println(exp_str);
			return list;
			
		}
		
		

}
