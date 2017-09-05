package lexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
 * corrigir: quando aperta enter no txtScript, a linha em branco fica com alguns " ",
 *  e o prog esá contando como linha
 */

public class LexicalAnalyzer {
	private String line;
	static List<Token> allTokens;
		
	
	public static void main(String[]args) throws IOException{
		
		allTokens = fileReader();
		
//		System.out.println(">" + padLeft("asdf", 10) + "<");
		
		printLexemeTable(allTokens);
		

//		verifyClassToken(allTokens);

	}
	
	private static List<Token> fileReader() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Files/DeutschScriptFatorial.txt"));
		String line = br.readLine();
		Token lexeme;
		
		String[] readToken;
		allTokens = new ArrayList<>();
		int sLine, sColumn, cont = 0;
		
		
		while(line != null){
			if(!line.isEmpty()) //só conta como linha se nao for em branco 
				cont++;
			
			if(!line.startsWith("$")){  //ignora as linhas que comecam com $
				
				line = line.replaceAll("[\t]", "    "); //substitui os tabs por 4 espaços em branco p/ contar as colunas
				readToken = line.split("\\s+");	//faz split ignorando mais de 1 espaço em branco
				sLine = cont;
				
				for(int i = 0 ; i < readToken.length; i++){
					
					sColumn = line.indexOf(readToken[i]); //pega coluna em que a string começa, conta por caractere incluindo os " "
				
//					System.out.println("token " +readToken[i]+ " na linha " +sLine+ " e coluna " +(sColumn+1));
					if (!readToken[i].trim().isEmpty()){ //se o token lido n for em branco, adiciona as propriedades (imagens, coluna e linha)
						lexeme = new Token();
						lexeme.setImage(readToken[i]);
						lexeme.setClassId(verifyClassToken(readToken[i])); //verifica no enum a quall classe o token pertence
						lexeme.setId(-1);
						lexeme.setLine(sLine);
						lexeme.setColumn(sColumn+1);
						allTokens.add(lexeme); //armazena o lexema na tabela de tokens (imagem, classe, id, linha e coluna)
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

	public static String verifyClassToken(String token){
		
		for(EnumToken t : EnumToken.values()){ // percorre todos os enums
			for(int i = 0; i < t.type.length; i++){
				if(token.equals(t.type[i])){ //se o token atual for encontrado nos enums, retorna a classe do enum PR, ID, CL etc
					return t.toString();
				}
			}
		}
		return "NaL";
	}
	
	private static void printLexemeTable(List<Token> allTokens) {
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
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
}