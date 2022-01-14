package FSBT;

public class FBST {
    private final Integer value;
    private final FBST left, right;
    public FBST(Integer value, FBST left, FBST right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public Integer value() {return value;}
    public FBST left() {return left;}
    public FBST right() {return right;}
}
