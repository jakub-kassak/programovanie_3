package Graphs;

public class TableGraph implements Graph{
    final boolean[][] matrix;
    final int count;

    public TableGraph(int count){
        this.count = count;
        matrix = new boolean[count][count];
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitTableGraph(this);
    }
}
