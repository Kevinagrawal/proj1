// Regular -- Parse tree node stratagy for printing regular lists

package Special;

import Tree.Node;
import Tree.Cons;
import Tree.Ident;
import Tree.Nil;

public class Regular extends Special {

    private boolean isSpecialForm(Node x) {
        if (!(x instanceof Cons))
            return false;

        Node a = ((Cons) x).getCar();
        if (!(a instanceof Ident))
            return false;

        String s = ((Ident) a).getName();

        return s.equals("lambda") || s.equals("if") || s.equals("cond") ||
               s.equals("let") || s.equals("begin") || s.equals("define") ||
               s.equals("set!") || s.equals("quote");
    }

    public void print(Node t, int n, boolean p) {
        Cons c = (Cons) t;

        Node first = c.getCar();
        Node rest = c.getCdr();

        if (!isSpecialForm(first)) {
            spaces(n);
            printListInline(c, p);
            System.out.println();
            return;
        }

        spaces(n);
        if (!p)
            System.out.print("(");

        ((Cons) first).print(0);

        while (rest instanceof Cons) {
            Node e = ((Cons) rest).getCar();
            spaces(n + 2);
            printInline(e);
            System.out.println();
            rest = ((Cons) rest).getCdr();
        }

        spaces(n);
        System.out.println(")");
    }
}