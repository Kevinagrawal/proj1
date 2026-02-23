// Quote -- Parse tree node strategy for printing the special form quote

package Special;

import Tree.Node;
import Tree.Cons;
import Tree.Nil;

public class Quote extends Special {

    public void print(Node t, int n, boolean p) {
        Cons c = (Cons) t;
        Node args = c.getCdr();

        spaces(n);

        if (!p)
            System.out.print("'");

        if (args instanceof Cons) {
            Node x = ((Cons) args).getCar();
            printInline(x);
        } else {
            System.out.print("()");
        }

        System.out.println();
    }
}