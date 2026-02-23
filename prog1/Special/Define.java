// Define -- Parse tree node strategy for printing the special form define

package Special;

import Tree.Node;
import Tree.Cons;
import Tree.Ident;

public class Define extends Special {

    public void print(Node t, int n, boolean p) {
        Cons c = (Cons) t;
        Node rest = c.getCdr();

        Node second = null;
        if (rest instanceof Cons)
            second = ((Cons) rest).getCar();

        if (second instanceof Ident) {
            spaces(n);
            printListInline((Cons) t, p);
            System.out.println();
            return;
        }

        spaces(n);
        if (!p)
            System.out.print("(");
        System.out.print("define");

        if (second != null) {
            System.out.print(" ");
            printInline(second);
        }
        System.out.println();

        if (rest instanceof Cons)
            rest = ((Cons) rest).getCdr();

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