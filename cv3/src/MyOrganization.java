import workbalance.*;
import java.util.Random;

public class MyOrganization {
    static int counter = 0;
    static Random rnd = new Random();

    static WorkUnit generateTree(int n, String name, WorkUnit parent){
        if (n == 1){
            Worker worker = new Worker("Member" + name + '_' + counter++, rnd.nextInt(10) * 10 + 100);
            worker.setParent(parent);
            worker.addWorkload(rnd.nextInt(3));
            return worker;
        }

        Team team = new Team("Team" + name);
        team.addMember(generateTree(n - 1, name+"_l", team));
        team.addMember(generateTree(n -1, name+"_r", team));
        team.setParent(parent);
        return team;
    }

    public static void main(String[] args) {
        WorkUnit myOrg = new Team("MyOrg");
        myOrg.addMember(generateTree(15, "Technology", myOrg));
        myOrg.addMember(generateTree(15, "Business", myOrg));

        System.out.println(myOrg.repr() + "\n");

        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            for (int j = 4; j <= 6; j += 2){
                myOrg.addWorkload(j);
            }
        }
        long end = System.nanoTime();
        System.out.println(myOrg.repr());

        System.out.println(end - start);

    }
}
