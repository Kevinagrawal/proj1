// BooleanLit -- Parse tree node class for representing boolean literals

package Tree;

public class BooleanLit extends Node {
    private boolean boolVal;

    private static BooleanLit trueInstance = new BooleanLit(true);
    private static BooleanLit falseInstance = new BooleanLit(false);

    private BooleanLit(boolean b) {
        boolVal = b;
    }

    public static BooleanLit getInstance(boolean val) {
        if (val)
            return trueInstance;
        else
            return falseInstance;
    }

    public boolean getVal() {
        return boolVal;
    }

    public void print(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(" ");

        if (boolVal) {
            System.out.println("#t");
        } else {
            System.out.println("#f");
        }
    }
}