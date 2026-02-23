// Lambda -- Parse tree node strategy for printing the special form lambda

package Special;

import Tree.Node;
import Tree.Cons;

public class Lambda extends Special {

    public void print(Node t, int n, boolean p) {
        Cons c = (Cons) t;
        Node rest = c.getCdr();

        spaces(n);
        if (!p)
            System.out.print("(");
        System.out.print("lambda");

        Node params = null;
        if (rest instanceof Cons) {
            params = ((Cons) rest).getCar();
            rest = ((Cons) rest).getCdr();
        }

        if (params != null) {
            System.out.print(" ");
            printInline(params);
        }
        System.out.println();

        while (rest instanceof Cons) {
            Node e = ((Cons) rest).getCar();
            e.print(n + 2);
            rest = ((Cons) rest).getCdr();
        }

        spaces(n);
        System.out.println(")");
    }
}