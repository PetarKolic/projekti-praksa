package mjcompiler;

import java_cup.runtime.Symbol;

%%
%{
    private Symbol newSymbol(int type){
        return new Symbol(type, yyline + 1, yycolumn);
    }

    private Symbol newSymbol(int type, Object value){
        return new Symbol(type, yyline + 1, yycolumn, value);
    }
%}
%cup
%line
%column

%xstate COMMENT_STATE


%eofval{
    return newSymbol(sym.EOF);
%eofval}

%%
" "    { }
"\b"   { }
"\t"   { }
"\r\n" { }
"\f"   { }

"program"  { return newSymbol(sym.PROGRAM, yytext());                  }
"print"    { return newSymbol(sym.PRINT, yytext());                    }
"read"     { return newSymbol(sym.READ, yytext());                     }
"void"     { return newSymbol(sym.VOID, yytext());                     }
"const"    { return newSymbol(sym.CONST, yytext());                    }
"new"      { return newSymbol(sym.NEW, yytext());                      }

"+"        { return newSymbol(sym.ADD, yytext());                      }
"-"        { return newSymbol(sym.SUB, yytext());                      }
"*"        { return newSymbol(sym.MUL, yytext());                      }
"/"        { return newSymbol(sym.DIV, yytext());                      }
"%"        { return newSymbol(sym.MOD, yytext());                      }
"="        { return newSymbol(sym.EQUAL, yytext());                    }
"++"       { return newSymbol(sym.INC, yytext());                      }
"--"       { return newSymbol(sym.DECR, yytext());                     }
";"        { return newSymbol(sym.SEMICOLN, yytext());                 }
","        { return newSymbol(sym.COMMA, yytext());                    }
"("        { return newSymbol(sym.LPAREN, yytext());                   }
")"        { return newSymbol(sym.RPAREN, yytext());                   }
"["        { return newSymbol(sym.LSQUARE, yytext());                  }
"]"        { return newSymbol(sym.RSQUARE, yytext());                  }
"{"        { return newSymbol(sym.LBRACE, yytext());                   }
"}"        { return newSymbol(sym.RBRACE, yytext());                   }
"+="       { return newSymbol(sym.ASSGN_ADD , yytext());               }
"-="       { return newSymbol(sym.ASSGN_SUB , yytext());               }
"*="       { return newSymbol(sym.ASSGN_MUL , yytext());               }
"/="       { return newSymbol(sym.ASSGN_DIV , yytext());               }
"-="       { return newSymbol(sym.ASSGN_SUB , yytext());               }
"%="       { return newSymbol(sym.ASSGN_MOD , yytext());               }
"?"        { return newSymbol(sym.QUESTMARK, yytext());                }
":"        { return newSymbol(sym.COLON, yytext());                    }
"=="       { return newSymbol(sym.EQUALS, yytext());                   }
"!="       { return newSymbol(sym.NEQUALS, yytext());                  }
">"        { return newSymbol(sym.GRT, yytext());                      }
">="       { return newSymbol(sym.GRTEQ, yytext());                    }
"<"        { return newSymbol(sym.LESS, yytext());                     }
"<="       { return newSymbol(sym.LESSEQ, yytext());                   }


[0-9]+                      { return newSymbol(sym.NUM_CONST, new Integer(yytext()));              }
"true"                      { return newSymbol(sym.BOOL_CONST, new Boolean(true));                 }
"false"                     { return newSymbol(sym.BOOL_CONST, new Boolean(false));                }
([a-zA-Z])([a-zA-Z0-9]|_)*  { return newSymbol(sym.IDENT, yytext());                               }
\'.\'                       { return newSymbol(sym.CHAR_CONST, new Character(yytext().charAt(1))); }


"//"                    { yybegin(COMMENT_STATE); }
<COMMENT_STATE> "\r\n"  { yybegin(YYINITIAL);     }
<COMMENT_STATE> .       { yybegin(COMMENT_STATE); }

. { System.err.println("Leksicka greska (" + yytext() + ") na liniji " + (yyline + 1)); }






