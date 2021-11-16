import java.util.concurrent.TimeUnit;

public class MockTask implements Command{
    private int duration;
    private int id;

    public MockTask(int duration, int id) {
        this.duration = duration;
        this.id = id;
    }

    @Override
    public void execute() {
        try {
            TimeUnit.MILLISECONDS.sleep(duration);
        }catch (Exception e){
            System.out.println("Error");
        }finally {
            System.out.println(id);
        }
    }
}
