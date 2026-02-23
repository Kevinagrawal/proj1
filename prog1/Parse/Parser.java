// Parser -- the parser for the Scheme printer and interpreter
//
// Defines
//
//   class Parser;
//
// Parses the language
//
//   exp  ->  ( rest
//         |  #f
//         |  #t
//         |  ' exp
//         |  integer_constant
//         |  string_constant
//         |  identifier
//    rest -> )
//         |  exp+ [. exp] )
//
// and builds a parse tree.  Lists of the form (rest) are further
// `parsed' into regular lists and special forms in the constructor
// for the parse tree node class Cons.  See Cons.parseList() for
// more information.
//
// The parser is implemented as an LL(0) recursive descent parser.
// I.e., parseExp() expects that the first token of an exp has not
// been read yet.  If parseRest() reads the first token of an exp
// before calling parseExp(), that token must be put back so that
// it can be reread by parseExp() or an alternative version of
// parseExp() must be called.
//
// If EOF is reached (i.e., if the scanner returns a NULL) token,
// the parser returns a NULL tree.  In case of a parse error, the
// parser discards the offending token (which probably was a DOT
// or an RPAREN) and attempts to continue parsing with the next token.

package Parse;

import Tree.Node;

public class Parser {
    private Scanner scanner;

    public Parser(Scanner s) {
        scanner = s;
    }

    public Node parseExp() {
        // TODO: write code for parsing an exp
        Tokens.Token t = scanner.getNextToken();

        if (t == null)
            return null;

        Tokens.TokenType tt = t.getType();

        if (tt == Tokens.TokenType.LPAREN)
            return parseRest();

        if (tt == Tokens.TokenType.TRUE)
            return Tree.BooleanLit.getInstance(true);

        if (tt == Tokens.TokenType.FALSE)
            return Tree.BooleanLit.getInstance(false);

        if (tt == Tokens.TokenType.QUOTE) {
            Node e = parseExp();
            if (e == null)
                return null;

            return new Tree.Cons(
                    new Tree.Ident("quote"),
                    new Tree.Cons(e, Tree.Nil.getInstance())
            );
        }

        if (tt == Tokens.TokenType.INT)
            return new Tree.IntLit(t.getIntVal());

        if (tt == Tokens.TokenType.STRING)
            return new Tree.StrLit(t.getStrVal());

        if (tt == Tokens.TokenType.IDENT)
            return new Tree.Ident(t.getName());

        return parseExp();
    }

    protected Node parseRest() {
        // TODO: write code for parsing rest
        Tokens.Token t = scanner.getNextToken();

        if (t == null)
            return null;

        if (t.getType() == Tokens.TokenType.RPAREN)
            return Tree.Nil.getInstance();

        java.util.ArrayList<Node> elems = new java.util.ArrayList<>();
        Node dottedTail = null;

        while (true) {
            Tokens.TokenType tt = t.getType();

            if (tt == Tokens.TokenType.RPAREN)
                break;

            if (tt == Tokens.TokenType.DOT) {
                dottedTail = parseExp();
                t = scanner.getNextToken();
                if (t == null)
                    return null;
                break;
            }

            if (tt == Tokens.TokenType.LPAREN) {
                Node e = parseRest();
                if (e == null)
                    return null;
                elems.add(e);
            } else if (tt == Tokens.TokenType.TRUE) {
                elems.add(Tree.BooleanLit.getInstance(true));
            } else if (tt == Tokens.TokenType.FALSE) {
                elems.add(Tree.BooleanLit.getInstance(false));
            } else if (tt == Tokens.TokenType.QUOTE) {
                Node e = parseExp();
                if (e == null)
                    return null;
                elems.add(new Tree.Cons(
                        new Tree.Ident("quote"),
                        new Tree.Cons(e, Tree.Nil.getInstance())
                ));
            } else if (tt == Tokens.TokenType.INT) {
                elems.add(new Tree.IntLit(t.getIntVal()));
            } else if (tt == Tokens.TokenType.STRING) {
                elems.add(new Tree.StrLit(t.getStrVal()));
            } else if (tt == Tokens.TokenType.IDENT) {
                elems.add(new Tree.Ident(t.getName()));
            } else {
                t = scanner.getNextToken();
                if (t == null)
                    return null;
                continue;
            }

            t = scanner.getNextToken();
            if (t == null)
                return null;
        }

        Node list = (dottedTail != null) ? dottedTail : Tree.Nil.getInstance();
        for (int i = elems.size() - 1; i >= 0; i--)
            list = new Tree.Cons(elems.get(i), list);

        return list;
    }

    // TODO: Add any additional methods you might need.
}