package lexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {
	private String line;
	private List<String> lexeme;
		
	
	public static void main(String[]args) throws IOException{
		
		fileReader();
		
		//verifyClassToken();

	}
	
	private static void fileReader() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Files/DeutschScriptFatorial.txt"));
		String line = br.readLine();
		
		String[] allTokens;
		
		while(line != null){
//			System.out.println(line);
			
			if(line.startsWith("$")){
				System.out.println(line);
			}
//			
//			allTokens = line.split(" ");
//			
//			for(int i =0 ; i < allTokens.length; i++){
//				System.out.println(allTokens[i]);
////				allTokens.add(line.split(" "));
////				line = br.readLine();
////				System.out.println(line);
//			}
			
			line = br.readLine();
		}
		br.close();
	}

	public static void verifyClassToken(){
		String token = ".";
		
		for(EnumToken t : EnumToken.values()){
			for(int i = 0; i < t.type.length; i++){
				if(token.equals(t.type[i])){
					System.out.println("a string "+token+ " eh igual ao enum "+t.type[i].toString() 
							+ ", da classe "+t);
				}
			}
			System.out.println();
		}
	}
}