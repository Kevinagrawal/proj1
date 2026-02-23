// Cons -- Parse tree node class for representing a Cons node

package Tree;

import Special.Begin;
import Special.Cond;
import Special.Define;
import Special.If;
import Special.Lambda;
import Special.Let;
import Special.Quote;
import Special.Regular;
import Special.Set;
import Special.Special;

public class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;

    public Cons(Node a, Node d) {
        car = a;
        cdr = d;
        parseList();
    }

    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form. It would be possible to fully parse
    // special forms at this point. Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    void parseList() {
        if (car instanceof Ident) {
            String s = ((Ident) car).getName();

            if (s.equals("quote"))
                form = new Quote();
            else if (s.equals("lambda"))
                form = new Lambda();
            else if (s.equals("begin"))
                form = new Begin();
            else if (s.equals("if"))
                form = new If();
            else if (s.equals("let"))
                form = new Let();
            else if (s.equals("cond"))
                form = new Cond();
            else if (s.equals("define"))
                form = new Define();
            else if (s.equals("set!"))
                form = new Set();
            else
                form = new Regular();
        } else {
            form = new Regular();
        }
    }


    // TODO: Add any helper functions for parseList
    // to the class hierarchy as needed.

    public void print(int n) {
        form.print(this, n, false);
    }

    public void print(int n, boolean p) {
        form.print(this, n, p);
    }
}