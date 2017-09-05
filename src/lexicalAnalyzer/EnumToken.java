package lexicalAnalyzer;

public enum EnumToken {

	PR(new String[] {"leer", "haupt", "alle", "show", "lesen", "Text", "real", 
					 "logisch", "wenn", "sonst", "zum", "out"}),
	DE(new String[] {"[", "]", "<<", ">>", ",", "."}),
	CL(new String[] {"0..9+", "[a-zA-Z][a-zA-Z0-9]*"}),
	OP(new String[] {"+", "-", "/", "*", "^", "<=", "<-", "<"});
	
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
