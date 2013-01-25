public class ScanTokens
{
	/* When you write your FSA make sure that they are private methods that
	 * can only be used by this class. Also remember that you will most
	 * likely be passed a char array and iterator.
	 * 
	 * Rachael - Can you please write the MP_IDENTIFIER and also the lookup table for if
	 * 			is a reserved word. ALso MP_PERIOD to MP_RPAREN.
	 * Andy - Can you please write MP_INTEGER_LIT, MP_FIXED_INT, and MP_FLOAT_INT.
	 * 		Also MP_EQUAL to MP_NEQUAL.
	 * Josh - Can you please write the MP_String_LIT. Also MP_ASSIGN to MP_EOF.
	 * 
	 * If you have any questions feel free to shoot me a text or phone call.
	 * Remember to commit any changes that you have made so that I can integrate
	 * everything.
	 */
}
private String mp_String_Lit(Iterator i){
	currentLexeme = "";
	String token = "";

	boolean done = false;
	//Set start state
	State state = State.s0;
	while(!done){
		switch(state){
			case State.s0:
				switch(i.next()){
					case '\'':
						i.next();
						state = State.s1;
						break;
					case '"':
						i.next();
						state = State.s2;
						break;
					default:
						Console.WriteLine("Initial Scan error");
						done = true;
						break;
				}
				break;
			case State.s1:
				if(i.next() == '\''){
					state = State.s3;
				}
				else if(i.next() < 0)
				{
					token = "MP_RUN_STRING";
					done = true;
				}
				else
				{
					currentLexeme += (char) i.next();
				}
				break;
			case State.s2:
				if(i.next() == '"'){
					state = State.s3;
				}
				else if(i.next() < 0)
				{
					token = "MP_RUN_STRING";
					done = true;
				}
				else
				{
					currentLexeme += (char) i.next();
				}
				break;
			case State.s3:
				i.next();
				done = true;
				token = "MP_STRING_LIT";
				break;
		}
	}

	return token;
}
private String mp_Assign(Iterator i){
	currentLexeme ="";
	String token = "";

	//Console.WriteLine((char)s.Read());
	boolean done = false;
	//Set start state
	State state = State.s0;
	while(!done){
		switch(state){
			case State.s0:
				switch((char) i.next()){
					case ':':
						state = State.s1;
						currentLexeme = currentLexeme + (char)i.next();
						break;
				}
				break;

			case State.s1:
				switch((char) i.next()){
					//assign_op token has been scanned; scanning stops
					case '=':
						state = State.s2;
						break;
					//colon token and one extra character where scanned - handle error
					default:
						token = "MP_COLON";
						done = true;
						break;
				}
				break;
			//an assignment operator has been scanned
			case State.s2:
				currentLexeme += (char) i.next();
				token = "MP_ASSIGN";
				done = true;
				break;
		}
	}
	return token;
}
private string mp_Plus(Iterator i){
		currentLexeme = "";
		String token ="";

		if((char) i.next() == '+'){
				currentLexeme += (char) i.next();
				token = "MP_PLUS";
		}
		return token;
}

private String mp_Minus(Iterator i){
		currentLexeme = "";
		String token ="";

		if((char) i.next() == '-'){
				currentLexeme += (char) i.next();
				token = "MP_MINUS";
		}
		return token;
}

private String mp_Times(Iterator i){
		currentLexeme = "";
		String token ="";

		if((char) i.next() == '*'){
				currentLexeme += (char) i.next();
				token = "MP_TIMES";
		}
		return token;
}
private String mp_EOF(Iterator i){
	currentLexeme ="";
	String token = "";

	currentLexeme = "EOF";
	token = "MP_EOF";
	return token;
}

private String currentLexeme;
