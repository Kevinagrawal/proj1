// Special -- Parse tree node strategy for printing special forms

package Special;

import Tree.Node;
import Tree.Nil;
import Tree.Cons;
import Tree.Ident;
import Tree.IntLit;
import Tree.StrLit;
import Tree.BooleanLit;

// There are several different approaches for how to implement the Special
// hierarchy.  We'll discuss some of them in class.  The easiest solution
// is to not add any fields and to use empty constructors.

abstract public class Special {
    abstract public void print(Node t, int n, boolean p);

    protected void spaces(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(" ");
    }

    protected void printInline(Node x) {
        if (x == null) {
            System.out.print("()");
            return;
        }

        if (x instanceof Nil) {
            System.out.print("()");
            return;
        }

        if (x instanceof BooleanLit) {
            System.out.print(((BooleanLit) x).getVal() ? "#t" : "#f");
            return;
        }

        if (x instanceof IntLit) {
            System.out.print(((IntLit) x).getVal());
            return;
        }

        if (x instanceof StrLit) {
            System.out.print("\"" + ((StrLit) x).getVal() + "\"");
            return;
        }

        if (x instanceof Ident) {
            System.out.print(((Ident) x).getName());
            return;
        }

        if (x instanceof Cons) {
            printListInline((Cons) x, false);
            return;
        }

        x.print(0);
    }

    protected void printListInline(Cons c, boolean openAlreadyPrinted) {
        if (!openAlreadyPrinted)
            System.out.print("(");

        Node cur = c;
        boolean first = true;

        while (cur instanceof Cons) {
            Cons cc = (Cons) cur;

            if (!first)
                System.out.print(" ");

            printInline(cc.getCar());
            cur = cc.getCdr();
            first = false;
        }

        if (!(cur instanceof Nil)) {
            System.out.print(" . ");
            printInline(cur);
        }

        System.out.print(")");
    }
}