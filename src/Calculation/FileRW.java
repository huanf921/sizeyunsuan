package Calculation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import javax.imageio.spi.RegisterableService;


public class FileRW {

	/**
	 * 将题目和答案写出在txt文件中
	 * @param expList 题目集合
 	 * @param answerList 答案集合
	 * @return  true 成功
	 * 			false 失败
	 */
	public boolean fileWrite(ArrayList<String> expList,ArrayList<Calcul_Expression> answerList) {
		ArrayList<String>  outList = new ArrayList<>();	
		if (expList.size()!=answerList.size()) {
			System.out.println("答案与题目数目不匹配！");
			return false;
		}
		
		for (int i = 0; i < expList.size(); i++) {
			System.out.println();
			outList.add(expList.get(i) + " = " +answerList.get(i).fenzi + "/"+ answerList.get(i).fenmu);
		}
		//开始写入文件
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
			for(String s:outList){
				bw.write(s);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * 把题目写出到文件
	 * @param expList 
	 * @return
	 */
	public File writeQ(ArrayList<String> expList) {
		int count = 1;
		File file = new File("Question.txt");
			try {
				FileWriter fWriter = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fWriter);
				for(String s:expList){
					bw.write( count++ + "." +s);
					bw.newLine();
					bw.flush();
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return file;
	}
	
	/**
	 * 把答案写出到文件
	 * @param answerList
	 * @return
	 * @throws IOException 
	 */
	public File writeA(ArrayList<String> answerList) throws IOException {
		int count = 1;
		File file = new File("Answer.txt");
		FileWriter fWriter = new FileWriter(file);
		try {
			BufferedWriter bw = new BufferedWriter(fWriter);
			for(String s:answerList){
				bw.write( count++ + "." +s);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
	
/**
 * 
 * @param answerFile 待检测文件
 * @param answerList 正确答案文件
 * @return
 */
	public File checkAnswer(File answerFile,File answerList) {

		try {
			BufferedReader rightReader = new BufferedReader(new FileReader(answerList));
			BufferedReader anserReader = new BufferedReader(new FileReader(answerFile));
			String rightString = null;
			String answerString = null;
			int right = 0;
			int wrong = 0; 
			int line = 1;
			
			String Rnumber="";
			String Wnumber="";
			//比较对错
			while((rightString=rightReader.readLine())!=null&&(answerString=anserReader.readLine())!=null){
				if(rightString.equals(answerString)){
					right ++;
					if (Rnumber.equals("")) {
						Rnumber = Rnumber +line;
					}
					else{
						Rnumber = Rnumber + ","+line;
					}
				}
				else {
					System.out.println(rightString);
					System.out.println(answerString);
					wrong++;
					if (Wnumber.equals("")) {
						Wnumber = Wnumber +line;
					}else{
						Wnumber = Wnumber + "," +line;
					}
				}
				line++;
			}
			
			//写入到answerfile中
			BufferedWriter bw = new BufferedWriter(new FileWriter(answerFile, true));
			bw.newLine();
			bw.write("right: "+ right + " " + "("+ Rnumber +")" + ";");
			bw.newLine();
			bw.write("wrong: "+ wrong + " " + "("+ Wnumber + ")"+ ";");
			bw.flush();
			bw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return answerFile;
		
	}
	
}

