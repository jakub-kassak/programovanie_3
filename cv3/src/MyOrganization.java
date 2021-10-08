import workbalance.*;
import java.util.Random;

public class MyOrganization {
    static Random rnd = new Random();
    static int counter = 0;


    static WorkUnit generateTree(int n, String name, WorkUnit parent){
        if (n == 1){
            Worker worker = new Worker("Member" + name + '.' + counter++, rnd.nextInt(10) * 10 + 100);
            worker.addWorkload(rnd.nextInt(3));
            return worker;
        }

        Team team = new Team("Team" + name);
        team.addMember(generateTree(n - 1, name+".l", team));
        team.addMember(generateTree(n -1, name+".r", team));
        return team;
    }

    public static void main(String[] args) {
        /*
        WorkUnit myOrg = new Team("MyOrg");
        for (int i = 1; i < 5; i++) {
            String teamName = "Team_" + i;
            WorkUnit team = new Team(teamName);
            for (int j = 10; j <= 30; j += 10) {
                String memberName = "Member_" + i + "-" + j;
                WorkUnit member = new Worker(memberName, 10 * i * j);
                member.addWorkload(rnd.nextInt(2));
                team.addMember(member);
            }
            myOrg.addMember(team);
        }
        */

        WorkUnit myOrg = generateTree(4, "MyOrg", null);

        System.out.println(myOrg.repr() + "\n");

        for (int i = 0; i < 4; i++) {
            myOrg.addWorkload(10);
            System.out.println(myOrg.repr()+ "\n");
        }


    }
}
