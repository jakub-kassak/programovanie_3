public class InfiniteRange extends Range{

    public InfiniteRange(int start){
        super(start, Integer.MAX_VALUE);
    }
    public InfiniteRange(int start, int step){
        super(start, Integer.MAX_VALUE, step);
    }
}
