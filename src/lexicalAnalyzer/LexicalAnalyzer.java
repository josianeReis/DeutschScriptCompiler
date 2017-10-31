package lexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
 * TODO: quando aperta enter no txtScript, a linha em branco fica com alguns " ",
 *  e o prog está contando como linha ao invés de ignorar
 */

public class LexicalAnalyzer {
	static List<Token> allTokens;
		
	
//	public static void main(String[]args) throws IOException{
//		
//		allTokens = fileReader();
//		
////		System.out.println(">" + padLeft("asdf", 10) + "<");
//		
//		printLexemeTable(allTokens);
//		
//
////		verifyClassToken(allTokens);
//
//	}
	
	public List<Token> fileReader() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("Files/DeutschScriptFatorial.txt"));
		String line = br.readLine();
		Token lexeme;
		
		String[] readToken;
		allTokens = new ArrayList<>();
		int sLine, sColumn, cont = 0;
		
		
		while(line != null){
			if(!line.isEmpty())  
				cont++;
			
			if(!line.startsWith("$")){  
				
				line = line.replaceAll("[\t]", "    "); 
				readToken = line.split("\\s+");	
				sLine = cont;
				
				for(int i = 0 ; i < readToken.length; i++){
					
					sColumn = line.indexOf(readToken[i]); 
				
//					System.out.println("token " +readToken[i]+ " na linha " +sLine+ " e coluna " +(sColumn+1));
					if (!readToken[i].trim().isEmpty()){ 
						lexeme = new Token();
						lexeme.setImage(readToken[i]);
						lexeme.setClassId(verifyClassToken(readToken[i])); 
						lexeme.setId(-1);
						lexeme.setLine(sLine);
						lexeme.setColumn(sColumn+1);
						allTokens.add(lexeme); 
					}
//						System.out.println(lexeme.toString());
				}
//				System.out.println(line);
			}
			line = br.readLine();
		}
		br.close();
		return allTokens;
	}

	public String verifyClassToken(String token){
		
		for(EnumToken t : EnumToken.values()){ 
			for(int i = 0; i < t.type.length; i++){
				if(token.equals(t.type[i])){ 
					return t.toString();
				}
			}
		}
		return "NaL";
	}
	
	public void printLexemeTable(List<Token> allTokens) {
		System.out.println(padRight("  Imagem", 19) + padRight("Classe", 10) + 
						   padRight("ID", 5) + 
						   padRight("Line", 5) + 
						   padRight("Column", 5));
		
		for(Token t : allTokens){
			System.out.println("  "+ padRight(t.getImage(), 18) + padRight(t.getClassId(), 9) + 
					padRight(t.getId().toString(), 6) +
					padRight(t.getLine().toString(), 5) +
					padRight(t.getColumn().toString(), 5));
		}
	}
	
	public String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
}
