// Let -- Parse tree node strategy for printing the special form let

package Special;

import Tree.Node;
import Tree.Cons;

public class Let extends Special {

    public void print(Node t, int n, boolean p) {
        Cons c = (Cons) t;

        spaces(n);
        if (!p)
            System.out.print("(");
        System.out.println("let");

        Node rest = c.getCdr();
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