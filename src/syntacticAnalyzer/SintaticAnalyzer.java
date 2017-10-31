package syntacticAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexicalAnalyzer.LexicalAnalyzer;
import lexicalAnalyzer.Token;

public class SintaticAnalyzer {
	
	private static List<Token> allTokens;
	private static List<String> errors = new ArrayList<String>();
	private int pToken;
	private Token token = new Token();
	
	public static void main(String[]args) throws IOException{
		LexicalAnalyzer lexical = new LexicalAnalyzer();
		
		allTokens = lexical.fileReader();
		
		lexical.printLexemeTable(allTokens);
	}
	
	public void readTokens(){
		
		
	}
	
	//<funcs> ::= <func> <funcs> | 
	private void funcs(){
		if(token.getImage().equals("alle") || token.getImage().equals("leer") || 
		   token.getImage().equals("text") || token.getImage().equals("real") || 
		   token.getImage().equals("logish"))
		func();
		funcs();
	}

	//<func> ::= <tipo> id '[' <params> ']' '<<' <comans> '>>'
	private void func() {
		type();
		if(token.getClassId().equals("id")){
			nextToken();
			if(token.getImage().equals("[")){
				nextToken();
				params();
				if(token.getImage().equals("]")){
					nextToken();
					if(token.getImage().equals("<<")){
						nextToken();
						comans();
						if(token.getImage().equals(">>")){
							nextToken();
						}else{
							addError("esperado '>>' ao invés de: ", token.getImage());
						}
					}else{
						addError("esperado '<<' ao invés de: ", token.getImage());
					}
				}else{
					addError("esperado ']' ao invés de: ", token.getImage());
				}
			}else{
				addError("esperado '[' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'id' ao invés de: ", token.getImage());
		}
	}

	//<tipo> ::= 'alle' | 'leer' | 'text' | 'real' | 'logisch'
	private void type() {
		if(token.getImage().equals("alle") || token.getImage().equals("leer") || 
		   token.getImage().equals("text") || token.getImage().equals("real") || 
		   token.getImage().equals("logish")){
			
			nextToken();
		}else{
			addError("Esperado alle, leer, text, real ou logish", token.getImage());
		}
	}
	
	//<params> ::= <param> <params2> | $
	private void params() {
		param();
		params2();
	}
	
	//<params2> ::= ',' <param> <params2> |
	private void params2() {
		if(token.getImage().equals(",")){
			nextToken();
			param();
			params2();
		}
	}
	
	//<param> ::= <tipo> id
	private void param() {
		type();
		if(token.getClassId().equals("id")){
			
		}
	}
	
	//<comans> ::= <coman> <comans> |
	private void comans() {
		coman();
		comans();
	}
	
	//<coman> ::= <decl> '.' | <atrib> '.' | <leitura> '.' | <escrita> '.' | <cond> | <laco> | <retorno> '.' | <chamada> '.'
	private void coman() {
		decl();
		if(token.getImage().equals(".")){
			
		}else{
			addError("esperado '.' ao invés de: ", token.getImage());
		}
	}
	
	//<decl> ::= <tipo> <ids>
	private void decl(){
		type();
		ids();
	}
	
	//<ids> ::= id <ids2>
	private void ids() {
		if(token.getClassId().equals("id")){
			nextToken();
			ids2();
		}else{
			addError("esperado 'id' ao invés de: ", token.getImage());
		}
	}
	
	//<ids2> ::= ',' id <ids2> |
	private void ids2() {
		if(token.getImage().equals(",")){
			nextToken();
			if(token.getClassId().equals("id")){
				nextToken();
				ids2();
			}else{
				addError("esperado 'id' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado ',' ao invés de: ", token.getImage());
		}
	}
	
	//<atrib> ::= id '<-' <exp>
	private void atrib(){
		if(token.getClassId().equals("id")){
			nextToken();
			if(token.getImage().equals("<-")){
				nextToken();
				exp();
			}else{
				addError("esperado '<-' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'id' ao invés de: ", token.getImage());
		}
	}

	//<exp> ::= <operan> <exp2>
	private void exp() {
		operan();
		exp2();
	}

	//<operan> ::= id | cli | clr | cls | cll |  <chamada>
	private void operan() {
		if(token.getClassId().equals("id") || token.getClassId().equals("cli") || 
		   token.getClassId().equals("clr") || token.getClassId().equals("cls") || 
		   token.getClassId().equals("cll") /* ou se for igual o first da <chamada> */){
			nextToken();
		}
	}
	
	//<exp2> ::=  | <op> <operan>
	private void exp2() {
		op();
		operan();
	}
	
	//<op> ::= '+' | '-' | '*' | '/' | '&' | '|' | '>' | '<' | '>=' | '<=' | '=' | '<>' | '@'
	private void op(){
		if(token.getImage().equals("+") || token.getImage().equals("-") || 
		   token.getImage().equals("*") || token.getImage().equals("/") ||
		   token.getImage().equals("&") || token.getImage().equals("|") ||
		   token.getImage().equals(">") || token.getImage().equals("<") ||
		   token.getImage().equals(">=") || token.getImage().equals("<=") ||
		   token.getImage().equals("=") || token.getImage().equals("<>") ||
		   token.getImage().equals("@")){
			
			nextToken();
		}
	}
	
	//<leitura> ::= 'lessen' '[' id ']'
	private void leitura(){
		if(token.getImage().equals("lessen")){
			nextToken();
			if(token.getImage().equals("[")){
				nextToken();
				if(token.getId().equals("id")){
					nextToken();
					if(token.getImage().equals("]")){
						nextToken();
					}else{
						addError("esperado ']' ao invés de: ", token.getImage());
					}
				}else{
					addError("esperado 'id' ao invés de: ", token.getImage());
				}
			}else{
				addError("esperado '[' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'lessen' ao invés de: ", token.getImage());
		}
	}
	
	//<escrita> ::= 'show' '[' <exp> ']'
	private void escrita(){
		if(token.getImage().equals("show")){
			nextToken();
			if(token.getImage().equals("[")){
				nextToken();
				exp();
				if(token.getImage().equals("]")){
					nextToken();
				}else{
					addError("esperado ']' ao invés de: ", token.getImage());
				}
			}else{
				addError("esperado '[' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'show' ao invés de: ", token.getImage());
		}
	}
	
	//<cond> ::= 'wenn' '[' <exp> ']' '<<' <comans> '>>' <senao>
	private void cond(){
		if(token.getImage().equals("wenn")){
			nextToken();
			if(token.getImage().equals("[")){
				nextToken();
				exp();
				if(token.getImage().equals("]")){
					nextToken();
					if(token.getImage().equals("<<")){
						nextToken();
						comans();
						if(token.getImage().equals(">>")){
							nextToken();
							senao();
						}else{
							addError("esperado '>>' ao invés de: ", token.getImage());
						}
					}else{
						addError("esperado '<<' ao invés de: ", token.getImage());
					}
				}else{
					addError("esperado ']' ao invés de: ", token.getImage());
				}
			}else{
				addError("esperado '[' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'wenn' ao invés de: ", token.getImage());
		}
	}
	
	//<senao> ::= | 'sont' '<<' <comans> '>>'
	private void senao(){
		if(token.getImage().equals("sont")){
			nextToken();
			if(token.getImage().equals("<<")){
				nextToken();
				comans();
				if(token.getImage().equals(">>")){
					nextToken();
				}else{
					addError("esperado '>>' ao invés de: ", token.getImage());
				}
			}else{
				addError("esperado '<<' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'sont' ao invés de: ", token.getImage());
		}
	}
	
	//<laco> ::= 'zum' '[' <exp> ']' '<<' <comans> '>>'
	private void laco(){
		if(token.getImage().equals("zum")){
			nextToken();
			if(token.getImage().equals("[")){
				nextToken();
				exp();
				if(token.getImage().equals("]")){
					nextToken();
					if(token.getImage().equals("<<")){
						nextToken();
						comans();
						if(token.getImage().equals(">>")){
							nextToken();
						}else{
							addError("esperado '>>' ao invés de: ", token.getImage());
						}
					}else{
						addError("esperado '<<' ao invés de: ", token.getImage());
					}
				}else{
					addError("esperado ']' ao invés de: ", token.getImage());
				}
			}else{
				addError("esperado '[' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'zum' ao invés de: ", token.getImage());
		}
	}
	
	//<retorno> ::= 'out' <exp>
	private void retorno(){
		if(token.getImage().equals("out")){
			nextToken();
			exp();
		}else{
			addError("esperado 'out' ao invés de: ", token.getImage());
		}
	}
	
	//<chamada> ::= id '[' <args> ']'
	private void chamada(){
		if(token.getId().equals("id")){
			nextToken();
			if(token.getImage().equals("[")){
				nextToken();
				args();
				if(token.getImage().equals("]")){
					nextToken();
				}else{
					addError("esperado ']' ao invés de: ", token.getImage());
				}
			}else{
				addError("esperado '[' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'id' ao invés de: ", token.getImage());
		}
	}
	
	//<args> ::=  | <operan> <args2>
	private void args(){
		if(token.getImage().equals("first do operan")){
			nextToken();
			if(token.getImage().equals("first do args2")){
				nextToken();
			}else{
				addError("esperado 'first do args2' ao invés de: ", token.getImage());
			}
		}else{
			addError("esperado 'first do operan' ao invés de: ", token.getImage());
		}
	}
	//<args2> ::=  | ',' <operan> <args2>
	private void args2(){
		if(token.getImage().equals(",")){
			nextToken();
			operan();
			args2();
		}else{
			addError("esperado ',' ao invés de: ", token.getImage());
		}
	}

	private void addError(String message, String token) {
		// TODO Auto-generated method stub
		
	}

	private void nextToken(){
		
		token = allTokens.get(pToken++);
	}
	
	private boolean hasErrors(){
		
		return errors.isEmpty();
	}
}
