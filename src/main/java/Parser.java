import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by mrfiskerton on 08.12.17.
 */
public class Parser {
    private Lexer lexer;

    public Tree parse(InputStream input) throws ParseException {
        lexer = new Lexer(input);
        lexer.nextToken();
        return RE();
    }

    private Tree RE() throws ParseException {
        debug_message("<RE>");
        switch (lexer.getCurToken()) {
            case LETTER:
            case LPAREN:
                return new Tree("<RE>", concatenation(), RE_p());
            default:
                throw new AssertionError();
        }
    }

    private Tree RE_p() throws ParseException {
        debug_message("<RE_p>");
        switch (lexer.getCurToken()) {
            case VBAR:
                lexer.nextToken();
                return new Tree("<RE_p>", new Tree("|"), concatenation(), RE_p());
            case END:
            case RPAREN://
                return new Tree("<RE_p>");
            default:
                throw new AssertionError();
        }
    }

    private Tree concatenation() throws ParseException {
        debug_message("<concatenation>");
        switch (lexer.getCurToken()) {
            case LETTER:
            case LPAREN:
                return new Tree("<concat>", kleene(), concatenation_p());
            default:
                throw new AssertionError();
        }
    }

    private Tree concatenation_p() throws ParseException {
        debug_message("<concatenation_p>");
        switch (lexer.getCurToken()) {
            case LETTER:
            case LPAREN:
                return new Tree("<concat_p>", kleene(), concatenation_p());
            case VBAR:
            case END:
            case RPAREN:
                return new Tree("<concat_p>", new Tree("ε"));
            default:
                throw new AssertionError();
        }
    }

    private Tree kleene() throws ParseException {
        debug_message("<kleene>");
        switch (lexer.getCurToken()) {
            case LETTER:
                String letter = Character.toString(lexer.getLetter());
                skip(Token.LETTER);
                return new Tree("<kleene>", new Tree(letter), kleene_p());
            case LPAREN:
                lexer.nextToken();
                Tree REinParens = RE();
                skip(Token.RPAREN);
                return new Tree("<kleene>", new Tree("("), REinParens, new Tree(")"), kleene_p());
            default:
                throw new AssertionError();
        }
    }

    private Tree kleene_p() throws ParseException {
        debug_message("<kleene_p>");
        if (compareAndSkip(Token.ASTERISK)) return new Tree("<kleene_p>", new Tree("*"), kleene_p());
        return new Tree("<kleene_p>", new Tree("ε"));
    }

    private void skip(Token expected_token) throws ParseException {
        if (expected_token != lexer.getCurToken())
            throw new ParseException("Unexpected token. Expected " + expected_token + ", got " + lexer.getCurToken()
                    + "at position ", lexer.getCurPos());
        lexer.nextToken();
    }

    private boolean compareAndSkip(Token expected_token) throws ParseException {
        if (expected_token == lexer.getCurToken()) {
            lexer.nextToken();
            return true;
        }
        return false;
    }

    private void debug_message(String where) {
        //System.out.println(where + " found: " + lexer.getCurToken() + " at position " + lexer.getCurPos());
    }

    final static String[] NON_TERMINALS =
            {"<RE>", "<RE_p>", "<concat>", "<concat_p>", "<kleene>", "<kleene_p>"};
}
