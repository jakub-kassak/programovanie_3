import workbalance.*;
import java.util.Random;

public class MyOrganization {

    public static void main(String[] args) {
        Random random = new Random();
        WorkUnit myOrg = new Team("MyOrg");
        for (int i = 1; i < 5; i++) {
            String teamName = "Team_" + i;
            WorkUnit team = new Team(teamName);
            for (int j = 10; j <= 30; j += 10) {
                String memberName = "Member_" + i + "-" + j;
                WorkUnit member = new Worker(memberName, 10 * i * j);
                member.addWorkload(random.nextInt(2));
                team.addMember(member);
            }
            myOrg.addMember(team);
        }

        System.out.println(myOrg.repr());

        for (int i = 0; i < 4; i++) {
            myOrg.addWorkload(10);
            System.out.println(myOrg.repr());
        }

    }
}
