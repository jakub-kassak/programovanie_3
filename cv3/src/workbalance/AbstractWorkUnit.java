package workbalance;

public abstract class AbstractWorkUnit implements WorkUnit{
    WorkUnit parent = null;

    public void setParent(WorkUnit w){
        parent = w;
    }
}
