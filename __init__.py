#from array import array

def getChar():
    try:
        SebestaScanner.nextChar = SebestaScanner.f.read(1)
        #print SebestaScanner.nextChar
                
        if  SebestaScanner.nextChar != '' and SebestaScanner.nextChar != '?':
            
            if SebestaScanner.nextChar.isalpha():
                SebestaScanner.charClass = SebestaScanner.LETTER
            elif SebestaScanner.nextChar.isdigit():
                SebestaScanner.charClass = SebestaScanner.DIGIT
            else:
                SebestaScanner.charClass = SebestaScanner.UNKNOWN
        else:
            SebestaScanner.charClass = SebestaScanner.ENDOFFILE
    except IOError:
            print 'Error in getting nextChar'
def getNonBlank():
    #print 'In getNonBlank()'
    while SebestaScanner.nextChar.isspace():
        #print 'count: ', SebestaScanner.count
        SebestaScanner.count  = SebestaScanner.count+ 1
        if SebestaScanner.count<3:
            getChar();
        else:
            exit
    
        
def addChar():
    #print 'In addChar()'
    if SebestaScanner.lexLen <= 98:
        SebestaScanner.lexeme.append(str(SebestaScanner.nextChar))
    else:
        print 'Error - lexeme is too long'    
           
def lookup(ch):
    #print 'in lookup()'
    #global lexeme
    #global LEFT_PAREN
    #global RIGHT_PAREN
    #global ADD_OP
    #global SUB_OP
    #global MULT_OP
    #global DIV_OP
    
    
    if ch== '(':
        addChar();
        SebestaScanner.nextToken = SebestaScanner.LEFT_PAREN
    elif ch == ')':
        addChar();
        SebestaScanner.nextToken = SebestaScanner.RIGHT_PAREN
    elif ch == '+':
        addChar();
        SebestaScanner.nextToken = SebestaScanner.ADD_OP
    elif ch == '-':
        addChar();
        SebestaScanner.nextToken = SebestaScanner.SUB_OP
    elif ch == '*':
        addChar();
        SebestaScanner.nextToken = SebestaScanner.MULT_OP
    elif ch == '/':
        addChar();
        SebestaScanner.nextToken = SebestaScanner.DIV_OP
    else:
        addChar();
        SebestaScanner.nextToken =SebestaScanner.ENDOFFILE
        SebestaScanner.lexeme.append('E','O','F','0')# adding EOF0 to lexeme
    return SebestaScanner.nextToken; 
            
def lex():
    #print 'In lex()'
    SebestaScanner.lexLen = 0
    getNonBlank()
    if SebestaScanner.charClass == SebestaScanner.LETTER:
        addChar();
        getChar();
        while SebestaScanner.charClass == SebestaScanner.LETTER or SebestaScanner.charClass == SebestaScanner.DIGIT :
            addChar();
            getChar();
        SebestaScanner.nextToken = SebestaScanner.IDENT
               
    elif SebestaScanner.charClass == SebestaScanner.DIGIT:
        addChar();
        getChar();
        while SebestaScanner.charClass == SebestaScanner.DIGIT:
            addChar();
            getChar();
        SebestaScanner.nextToken = SebestaScanner.INT_LIT
        
          
    elif SebestaScanner.charClass == SebestaScanner.UNKNOWN:
        lookup(SebestaScanner.nextChar);
        getChar();

    elif SebestaScanner.charClass == SebestaScanner.ENDOFFILE:
        
        SebestaScanner.nextToken = SebestaScanner.ENDOFFILE;
        SebestaScanner.lexeme.append('EOF')
    print 'Next token is:'+ str(SebestaScanner.nextToken) + ' Next lexeme is :', ''.join(SebestaScanner.lexeme)
    SebestaScanner.lexeme=[]
    SebestaScanner.count=0
    #ystem.out.println("Next token is:"+nextToken+" Next lexeme is:"+new String(lexeme).trim());
    return SebestaScanner.nextToken;

class SebestaScanner(object):
   
    charClass = None
    lexeme = []
    a = []
    nextChar = None
    lexLen = 0
    token = None
    nextToken = None
    count=0

    
    
    #  /* Character classes */
    # //defined as constants in c
    LETTER = 0;
    DIGIT = 1;
    UNKNOWN = 99;
    #  /* Token codes */
    #   //defined as constants in c
    INT_LIT = 10;
    IDENT = 11;
    ASSIGN_OP = 20;
    ADD_OP = 21;
    SUB_OP = 22;
    MULT_OP = 23;
    DIV_OP = 24;
    LEFT_PAREN = 25;
    RIGHT_PAREN = 26;
    ENDOFFILE = -1;
    
    
    
    
                
    def start(self):
        #print 'in _str_(-) method'
        try:
            SebestaScanner.f = open('E:\\workspace\\CMPS450\\Assinment1\\front.txt', 'r')
            if SebestaScanner.f == '':
                print 'Error: can not open front.in'
            else:
                getChar()
                while (SebestaScanner.nextToken != '' and SebestaScanner.nextToken!= -1 ):
                    lex();  
        except IOError:
            print 'Error in file opening : front.in'
        SebestaScanner.f.close()
        

SebestaScanner = SebestaScanner()
SebestaScanner.start()
    
  