import java.util.PriorityQueue;
import java.util.Queue;

public class Planner {
    private final Queue<Command> queue = new PriorityQueue<>();

    public void addCommand(Command command, int priority) {
        queue.add(new PriorityCommand(command, priority));
    }

    public void executeHighestPriority() {
        Command highest = queue.peek();
        highest.execute();
        queue.remove();
    }

    public void executeAll(){
        while (!queue.isEmpty()){
            executeHighestPriority();
        }
    }


    // the sorting by priority may be achieved in other fashions as well
    private class PriorityCommand implements Command, Comparable<PriorityCommand> {
        private final Command command;
        private final int priority;

        private PriorityCommand(Command command, int priority) {
            this.command = command;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityCommand o) {
            // command with higher priority will be executed sooner
            return o.priority - this.priority;
        }

        @Override
        public void execute() {
            command.execute();
        }
    }
}
