// Set -- Parse tree node strategy for printing the special form set!

package Special;

import Tree.Node;
import Tree.Cons;

public class Set extends Special {

    public void print(Node t, int n, boolean p) {
        spaces(n);
        printListInline((Cons) t, p);
        System.out.println();
    }
}