import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by mrfiskerton on 08.12.17.
 */
public class Lexer {
    private InputStream is;
    private int curChar, curPos;
    private Token curToken;
    private int lastLetter;

    Lexer(InputStream input) throws ParseException {
        this.is = input;
        nextChar();
    }

    private boolean isBlank(int c) {
        return Character.isWhitespace(c);
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), curPos);
        }
    }

    void nextToken() throws ParseException {
        while (isBlank(curChar)) {
            nextChar();
        }

        if (Character.isLetter(curChar)) {
            lastLetter = curChar;
            nextChar();
            curToken = Token.LETTER;
            return;
        }

        switch (curChar) {
            case '(':
                nextChar();
                curToken = Token.LPAREN;
                break;
            case ')':
                nextChar();
                curToken = Token.RPAREN;
                break;
            case '|':
                nextChar();
                curToken = Token.VBAR;
                break;
            case '*':
                nextChar();
                curToken = Token.ASTERISK;
                break;
            case '+':
                nextChar();
                curToken = Token.PLUS;
                break;
            case '?':
                nextChar();
                curToken = Token.QUESTION;
                break;
            //case '$':
            case -1:
                curToken = Token.END;
                break;
            default:
                throw new ParseException("Illegal character " + (char) curChar + " at position ", curPos);
        }
    }

    Token getCurToken() {
        return curToken;
    }

    int getCurPos() {
        return curPos;
    }

    char getLetter() {
        return (char)lastLetter;
    }
}
