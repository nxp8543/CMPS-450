import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class SebestaScannerMain {

	/* Global declarations */
	/* Variables */
	static int charClass;
	static char lexeme[] =null;
	static char[] a;
	static char nextChar;
	static int lexLen;
	static int token;
	static int nextToken;
	
	
	
	/* Character classes */
	//defined as constants in c
	static final int LETTER = 0;
	static final int DIGIT = 1;
	static final int UNKNOWN = 99;
	/* Token codes */
	//defined as constants in c
	static final int INT_LIT = 10;
	static final int IDENT = 11;
	static final int ASSIGN_OP = 20;
	static final int ADD_OP = 21;
	static final int SUB_OP = 22;
	static final int MULT_OP = 23;
	static final int DIV_OP = 24;
	static final int LEFT_PAREN = 25;
	static final int RIGHT_PAREN = 26;
	static final int ENDOFFILE = -1;
	static FileReader reader = null;
	static BufferedReader br ;
	public static void main(String[] args)
	{
		SebestaScannerMain ss = new SebestaScannerMain();
		lexeme = new char[100];
		//Creates a FileReader Object
	      
		try {
			reader = new FileReader("front.in");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in file opening");
		} 
		
		
		if(reader == null)
		{
			System.out.println("Error can't open front.in");
		}
		else
		{
			getChar();
		//	System.out.println("after calling getChar() in main");
			do {
			//	System.out.println("before calling lex() in main");
				lex();
			} while (nextToken != ENDOFFILE);
		}
		
		
	}//end of main()
	
	/*****************************************************/
	/* lookup - a function to lookup operators and parentheses
	and return the token */
	static int lookup(char ch)
	{
		//System.out.println("in lookup");
		switch (ch) 
		{
			case '(':
			//	System.out.println("charcter read="+ch);
				addChar();
				nextToken = LEFT_PAREN;
				break;
			case ')':
			//	System.out.println("charcter read="+ch);
				addChar();
				nextToken = RIGHT_PAREN;
				break;
			case '+':
			//	System.out.println("charcter read="+ch);
				addChar();
				nextToken = ADD_OP;
				break;
			case '-':
			//	System.out.println("charcter read="+ch);
				addChar();
				nextToken = SUB_OP;
				break;
			case '*':
			//	System.out.println("charcter read="+ch);
				addChar();
				nextToken = MULT_OP;
				break;
			case '/':
			//	System.out.println("charcter read="+ch);
				addChar();
				nextToken = DIV_OP;
				break;
			default:
				//System.out.println("charcter read="+ch);
				addChar();
				nextToken = ENDOFFILE;
				lexeme[0] = 'E';
				lexeme[1] = 'O';
				lexeme[2] = 'F';
				lexeme[3] = 0;
				break;
		}//end of switch
		return nextToken;
	}//end of lookUp
	
	
	/*****************************************************/
	/* addChar - a function to add nextChar to lexeme */
	static void addChar() 
	{
		
		//System.out.println("in addChar()");
	//	System.out.println("nextChar in addChar="+nextChar);
			if (lexLen <= 98) {
			//	System.out.println("In if- addChar()");
				
				lexeme[lexLen++] = nextChar;
				//lexeme[lexLen] = 0;
			}
			else
				System.out.println("Error - lexeme is too long \n");
			//System.out.println("End of addChar()");
	}
	
	
	
	/*****************************************************/
	/* getChar - a function to get the next character of
	input and determine its character class */
	static void getChar() 
	{
		//System.out.println("in getChar()");
			try 
			{
				if ((nextChar =(char)reader.read()) != -1 && nextChar!='?')
				{
					if (Character.isLetter((nextChar)))
						charClass = LETTER;
					else if (Character.isDigit((nextChar)))
						charClass = DIGIT;
					else charClass = UNKNOWN;
					
				}
				else
				{
					System.out.println("Setting charClass as EOF");
					charClass = ENDOFFILE;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("charClass="+charClass);
			
	}
	/* Function declarations */
	
	/*****************************************************/
	/* getNonBlank - a function to call getChar until it
	returns a non-whitespace character */
	static void getNonBlank()
	{
		int count=0;
		//System.out.println("in getNonBlanck");
		while (Character.isWhitespace(nextChar))
		{
			count++;
		if(count<3)
			getChar();
		}
		
	}
	
	
	
	/*****************************************************/
	/* lex - a simple lexical analyzer for arithmetic
	expressions */
	static int lex()
	{
		//System.out.println("in Lex");
		lexLen = 0;
		getNonBlank();
			switch (charClass) 
			{
				/* Parse identifiers */
				case LETTER:
					addChar();
					getChar();
					while (charClass == LETTER || charClass == DIGIT) {
						addChar();
						getChar();
					}
					nextToken = IDENT;
					break;
				/* Parse integer literals */
				case DIGIT:
					addChar();
					getChar();
					while (charClass == DIGIT) {
						addChar();
						getChar();
					}
					nextToken = INT_LIT;
					break;
				/* Parentheses and operators */
				case UNKNOWN:
					lookup(nextChar);
					getChar();
					break;
				/* EOF */
				case ENDOFFILE:
					System.out.println("In EOF case");
					nextToken = ENDOFFILE;
					System.out.println("After assigning nextToken=EOF in lex");
					lexeme[0] = 'E';
					lexeme[1] = 'O';
					lexeme[2] = 'F';
					lexeme[3] = 0;
					break;
			} /* End of switch */
			System.out.println("Next token is:"+nextToken+" Next lexeme is:"+new String(lexeme).trim());
			//System.out.format("Next token is: %d, Next lexeme is %s\n",
			//		nextToken, new String(lexeme));
			Arrays.fill(lexeme,' ');
			return nextToken;
		} /* End of function lex */
}
