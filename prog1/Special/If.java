// If -- Parse tree node strategy for printing the special form if

package Special;

import Tree.Node;
import Tree.Cons;
import Tree.Nil;

public class If extends Special {

    public void print(Node t, int n, boolean p) {
        Cons c = (Cons) t;
        Node rest = c.getCdr();

        spaces(n);
        if (!p)
            System.out.print("(");
        System.out.print("if");

        Node test = null;
        Node conseq = null;
        Node alt = null;

        if (rest instanceof Cons) {
            test = ((Cons) rest).getCar();
            rest = ((Cons) rest).getCdr();
        }
        if (rest instanceof Cons) {
            conseq = ((Cons) rest).getCar();
            rest = ((Cons) rest).getCdr();
        }
        if (rest instanceof Cons) {
            alt = ((Cons) rest).getCar();
        }

        if (test != null) {
            System.out.print(" ");
            printInline(test);
        }
        System.out.println();

        if (conseq != null) {
            spaces(n + 2);
            printInline(conseq);
            System.out.println();
        }

        if (alt != null) {
            spaces(n + 2);
            printInline(alt);
            System.out.println();
        }

        spaces(n);
        System.out.println(")");
    }
}