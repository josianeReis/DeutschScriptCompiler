package lexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;

public enum EnumToken {

	PR(new String[] {"leer", "haupt", "alle", "show", "lesen"}),
	DE(new String[] {"[", "]", "<<", ">>", ",", "."}),
	CL(new String[] {"0..9+", "[a-zA-Z][a-zA-Z0-9]*"});
//	alle, //int 
//	text, //string
//	real, //real
//	logisch, //boolean    
//	haupt, //main
//	show, //print
//	lesen, //read
//	wenn, //if
//	sonst, //else
//	zum, //while
//	out; //return
	
	public String[] type;
	
	private EnumToken(String[] type) {
		this.type = type;
	}
	
	private EnumToken(){}
	
	public String[] getType() {
		return type;
	}

	public void setType(String[] type) {
		this.type = type;
	}

}
