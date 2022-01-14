import globalTesting.*;

public class Main {
    public static void main(String[] args) {
        try {
            assert false;
            System.out.println("ERROR! Add '-ea' flag to VM options to enable asserts");
        }
        catch (AssertionError e) {
            System.out.println("Asserts are active.");
        }
        // mozete si zakomentovat tie casti, za ktore nechcete byt hodnoteni
        partA_single_testing_team();
        partA2_composite_unit();
        partB_different_distribution_strategy();
        partC_builder();
        partD_visualisation();
        partD2_representation_progress();
    }

    static void partA_single_testing_team() {
        try {
            Unit team = new TestingTeam("team", 10);
            assert team.freePositions() == 10 :
                    "free positions should be equal to 10, got " + team.freePositions() + " instead";
            assert team.totalTeamsCount() == 1 :
                    "total team count should be equal to 1, got " + team.totalTeamsCount() + " instead";
            assert team.filledTeamsCount() == 0 :
                    "filled teams count should be equal to 0, got " + team.filledTeamsCount() + " instead";

            team.addPersonell(5);
            assert team.freePositions() == 5 :
                    "free positions should be equal to 5, got " + team.freePositions() + " instead";
            assert team.totalTeamsCount() == 1 :
                    "total team count should be equal to 1, got " + team.totalTeamsCount() + " instead";
            assert team.filledTeamsCount() == 0 :
                    "filled teams count should be equal to 0, got " + team.filledTeamsCount() + " instead";

            team.addPersonell(5);
            assert team.freePositions() == 0 :
                    "free positions should be equal to 0, got " + team.freePositions() + " instead";
            assert team.totalTeamsCount() == 1 :
                    "total team count should be equal to 1, got " + team.totalTeamsCount() + " instead";
            assert team.filledTeamsCount() == 1 :
                    "filled teams count should be equal to 1, got " + team.filledTeamsCount() + " instead";

            System.out.println("Part A: PASSED");
        }
        catch (AssertionError e) {
            System.out.println(e.getMessage());
            System.out.println("Part A: FAILED");
        }
    }

    static void partA2_composite_unit() {
        try {
            Unit t1 = new TestingTeam("t1", 4);
            Unit t2 = new TestingTeam("t2", 2);
            CompositeUnit c = new CompositeUnit("c");
            c.addSubunit(t1);
            c.addSubunit(t2);

            assert c.freePositions() == 6 :
                    "free positions should be equal to 6, got " + c.freePositions() + " instead";
            assert c.totalTeamsCount() == 2 :
                    "total team count should be equal to 2, got " + c.totalTeamsCount() + " instead";
            assert c.filledTeamsCount() == 0 :
                    "filled teams count should be equal to 0, got " + c.filledTeamsCount() + " instead";

            c.addPersonell(4);
            assert c.freePositions() == 2 :
                    "free positions should be equal to 2, got " + c.freePositions() + " instead";
            assert c.totalTeamsCount() == 2 :
                    "total team count should be equal to 2, got " + c.totalTeamsCount() + " instead";
            assert c.filledTeamsCount() == 0 :
                    "filled teams count should be equal to 0, got " + c.filledTeamsCount() + " instead";

            c.addPersonell(1);
            assert c.freePositions() == 1 :
                    "free positions should be equal to 1, got " + c.freePositions() + " instead";
            assert c.totalTeamsCount() == 2 :
                    "total team count should be equal to 2, got " + c.totalTeamsCount() + " instead";
            assert c.filledTeamsCount() == 1 :
                    "filled teams count should be equal to 1, got " + c.filledTeamsCount() + " instead";

            c.addPersonell(1);
            assert c.freePositions() == 0 :
                    "free positions should be equal to 0, got " + c.freePositions() + " instead";
            assert c.totalTeamsCount() == 2 :
                    "total team count should be equal to 2, got " + c.totalTeamsCount() + " instead";
            assert c.filledTeamsCount() == 2 :
                    "filled teams count should be equal to 2, got " + c.filledTeamsCount() + " instead";

            System.out.println("Part A2: PASSED");
        }
        catch (AssertionError e) {
            System.out.println(e.getMessage());
            System.out.println("Part A2: FAILED");
        }
    }


    private static void partB_different_distribution_strategy() {
        try {
            Unit t1 = new TestingTeam("t1", 4);
            Unit t2 = new TestingTeam("t2", 2);
            Unit t3 = new TestingTeam("t3", 10);

            CompositeUnit c_sub = new CompositeUnit("c_sub");
            c_sub.addSubunit(t2);
            c_sub.addSubunit(t3);

            CompositeUnit c = new CompositeUnit("c", new MostCompleteByOne());
            c.addSubunit(c_sub);
            c.addSubunit(t1);

            assert c.freePositions() == 16 :
                    "free positions should be equal to 16, got " + c.freePositions() + " instead";
            assert c.filledTeamsCount() == 0 :
                    "filled teams count should be equal to 0, got " + c.filledTeamsCount() + " instead";

            c.addPersonell(4);
            assert c.freePositions() == 12 :
                    "free positions should be equal to 12, got " + c.freePositions() + " instead";
            assert c.filledTeamsCount() == 1 :
                    "filled teams count should be equal to 1, got " + c.filledTeamsCount() + " instead";

            c.addPersonell(10);
            assert c.freePositions() == 2 :
                    "free positions should be equal to 2, got " + c.freePositions() + " instead";
            assert c.filledTeamsCount() == 1 :
                    "filled teams count should be equal to 1, got " + c.filledTeamsCount() + " instead";

            c.addPersonell(1);
            assert c.freePositions() == 1 :
                    "free positions should be equal to 1, got " + c.freePositions() + " instead";
            assert c.filledTeamsCount() == 2 :
                    "filled teams count should be equal to 2, got " + c.filledTeamsCount() + " instead";

            c.addPersonell(1);
            assert c.freePositions() == 0 :
                    "free positions should be equal to 0, got " + c.freePositions() + " instead";
            assert c.filledTeamsCount() == 3 :
                    "filled teams count should be equal to 3, got " + c.filledTeamsCount() + " instead";

            System.out.println("Part B: PASSED");
        }
        catch (AssertionError e) {
            System.out.println(e.getMessage());
            System.out.println("Part B: FAILED");
        }
    }

private static void partC_builder() {
    try {
        UnitBuilder builder = new UnitBuilder();
        Unit small = builder.createSmallTestingSite("smallSite");
        assert small.freePositions() == 4 :
                "smallSite doesn't have 4 free positions, got " + small.freePositions() + " instead";
        small.addPersonell(2);
        assert small.filledTeamsCount() == 0:
                "smallSite doesn't have 0 filled teams, got " + small.filledTeamsCount() + " instead";

        Unit medium = builder.createMediumTestingSite("mediumSite");
        medium.addPersonell(9);
        assert medium.filledTeamsCount() == 1 :
                "mediumSite doesn't have 1 filled team, got " + medium.filledTeamsCount() + " instead";
        medium.addPersonell(1);
        assert medium.filledTeamsCount() == 2 :
                "mediumSite doesn't have 2 filled teams, got " + medium.filledTeamsCount() + " instead";

        Unit large = builder.createLargeTestingSite("largeSite");
        large.addPersonell(25);
        assert large.filledTeamsCount() == 1 :
                "largeSite doesn't have 1 filled team, got " + large.filledTeamsCount() + " instead";
        large.addPersonell(3);
        assert large.filledTeamsCount() == 4 :
                "largeSite doesn't have 4 filled teams, got " + large.filledTeamsCount() + " instead";

        Unit vysoke_tatry = builder.createStructureForSettlement("Vysoké Tatry", 4070);
        assert vysoke_tatry.totalTeamsCount() == 10 :
                "Vysoke Tatry (4070) should have 10 teams, not " + vysoke_tatry.totalTeamsCount();
        assert  vysoke_tatry.freePositions() == 20 :
                "Vysoke Tatry (4070) should have 20 positions, not " + vysoke_tatry.freePositions();

        Unit partizanske = builder.createStructureForSettlement("Partizánske", 22653);
        assert partizanske.totalTeamsCount() == 23 * 2 + 3 * 4 :
                "Partizanske (22653) should have 58 teams, not " + partizanske.totalTeamsCount();

        Unit kosice = builder.createStructureForSettlement("Košice", 239095);
        assert kosice.totalTeamsCount() == 240 * 2 + 24 * 4 + 3 * 8:
                "Kosice (239095) should have 600 teams, not " + kosice.totalTeamsCount();

        System.out.println("Part C: PASSED");
    }
    catch (AssertionError e) {
        System.out.println(e.getMessage());
        System.out.println("Part C: FAILED");
    }
}


    static void partD_visualisation() {
        try {
            Unit unit1 = new TestingTeam("team1", 5);
            String my_repr1 = unit1.accept(new Visualisation());

            String repr1 = "team1: 0/5 filled";
            assert repr1.equals(my_repr1) :
                    "Incorrect representation 1: " + my_repr1;

            Unit t1 = new TestingTeam("t1", 4);
            Unit t2 = new TestingTeam("t2", 2);
            CompositeUnit c = new CompositeUnit("c");
            c.addSubunit(t1);
            c.addSubunit(t2);
            String my_repr2 = c.accept(new Visualisation());

            String repr2 = "c: 0/6 filled positions, 0/2 filled teams\n" +
                    "  t1: 0/4 filled\n" +
                    "  t2: 0/2 filled";
            assert repr2.equals(my_repr2) :
                    "Incorrect representation 2: " + my_repr2;

            Unit vysoke_tatry = (new UnitBuilder()).createStructureForSettlement("Vysoké Tatry", 4070);
            String my_repr3 = vysoke_tatry.accept(new Visualisation());

            String repr3 = "Vysoké Tatry: 0/20 filled positions, 0/10 filled teams\n" +
                    "  Vysoké Tatry_0: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Vysoké Tatry_0_1: 0/2 filled\n" +
                    "    Vysoké Tatry_0_2: 0/2 filled\n" +
                    "  Vysoké Tatry_1: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Vysoké Tatry_1_1: 0/2 filled\n" +
                    "    Vysoké Tatry_1_2: 0/2 filled\n" +
                    "  Vysoké Tatry_2: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Vysoké Tatry_2_1: 0/2 filled\n" +
                    "    Vysoké Tatry_2_2: 0/2 filled\n" +
                    "  Vysoké Tatry_3: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Vysoké Tatry_3_1: 0/2 filled\n" +
                    "    Vysoké Tatry_3_2: 0/2 filled\n" +
                    "  Vysoké Tatry_4: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Vysoké Tatry_4_1: 0/2 filled\n" +
                    "    Vysoké Tatry_4_2: 0/2 filled";
            assert repr3.equals(my_repr3) :
                    "Incorrect representation 3: \n" + my_repr3;

            Unit sturovo = (new UnitBuilder()).createStructureForSettlement("Štúrovo", 10390);
            String my_repr4 = sturovo.accept(new Visualisation());

            String repr4 = "Štúrovo: 0/68 filled positions, 0/30 filled teams\n" +
                    "  Štúrovo_0: 0/12 filled positions, 0/4 filled teams\n" +
                    "    Štúrovo_0_1: 0/3 filled\n" +
                    "    Štúrovo_0_2: 0/3 filled\n" +
                    "    Štúrovo_0_3: 0/3 filled\n" +
                    "    Štúrovo_0_4: 0/3 filled\n" +
                    "  Štúrovo_1: 0/12 filled positions, 0/4 filled teams\n" +
                    "    Štúrovo_1_1: 0/3 filled\n" +
                    "    Štúrovo_1_2: 0/3 filled\n" +
                    "    Štúrovo_1_3: 0/3 filled\n" +
                    "    Štúrovo_1_4: 0/3 filled\n" +
                    "  Štúrovo_2: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_2_1: 0/2 filled\n" +
                    "    Štúrovo_2_2: 0/2 filled\n" +
                    "  Štúrovo_3: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_3_1: 0/2 filled\n" +
                    "    Štúrovo_3_2: 0/2 filled\n" +
                    "  Štúrovo_4: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_4_1: 0/2 filled\n" +
                    "    Štúrovo_4_2: 0/2 filled\n" +
                    "  Štúrovo_5: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_5_1: 0/2 filled\n" +
                    "    Štúrovo_5_2: 0/2 filled\n" +
                    "  Štúrovo_6: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_6_1: 0/2 filled\n" +
                    "    Štúrovo_6_2: 0/2 filled\n" +
                    "  Štúrovo_7: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_7_1: 0/2 filled\n" +
                    "    Štúrovo_7_2: 0/2 filled\n" +
                    "  Štúrovo_8: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_8_1: 0/2 filled\n" +
                    "    Štúrovo_8_2: 0/2 filled\n" +
                    "  Štúrovo_9: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_9_1: 0/2 filled\n" +
                    "    Štúrovo_9_2: 0/2 filled\n" +
                    "  Štúrovo_10: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_10_1: 0/2 filled\n" +
                    "    Štúrovo_10_2: 0/2 filled\n" +
                    "  Štúrovo_11: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_11_1: 0/2 filled\n" +
                    "    Štúrovo_11_2: 0/2 filled\n" +
                    "  Štúrovo_12: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Štúrovo_12_1: 0/2 filled\n" +
                    "    Štúrovo_12_2: 0/2 filled";

            assert repr4.equals(my_repr4) :
                    "Incorrect representation 4: " + my_repr4;

            System.out.println("Part D: PASSED");
        }
        catch (AssertionError e) {
            System.out.println(e.getMessage());
            System.out.println("Part D: FAILED");
        }
    }

    static void partD2_representation_progress() {
        try {
            Unit unit1 = new TestingTeam("team1", 5);
            unit1.addPersonell(4);
            String my_repr1 = unit1.accept(new Visualisation());

            String repr1 = "team1: 4/5 filled";
            assert repr1.equals(my_repr1) :
                    "Incorrect representation 1: " + my_repr1;

            Unit t1 = new TestingTeam("t1", 4);
            Unit t2 = new TestingTeam("t2", 2);
            CompositeUnit c = new CompositeUnit("c");
            c.addSubunit(t1);
            c.addSubunit(t2);
            c.addPersonell(4);
            String my_repr2 = c.accept(new Visualisation());

            String repr2 = "c: 4/6 filled positions, 0/2 filled teams\n" +
                    "  t1: 3/4 filled\n" +
                    "  t2: 1/2 filled";
            assert repr2.equals(my_repr2) :
                    "Incorrect representation 2: " + my_repr2;

            Unit vysoke_tatry = (new UnitBuilder()).createStructureForSettlement("Vysoké Tatry", 4070);
            vysoke_tatry.addPersonell(15);
            String my_repr3 = vysoke_tatry.accept(new Visualisation());

            String repr3 = "Vysoké Tatry: 15/20 filled positions, 7/10 filled teams\n" +
                    "  Vysoké Tatry_0: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Vysoké Tatry_0_1: 2/2 filled\n" +
                    "    Vysoké Tatry_0_2: 2/2 filled\n" +
                    "  Vysoké Tatry_1: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Vysoké Tatry_1_1: 2/2 filled\n" +
                    "    Vysoké Tatry_1_2: 2/2 filled\n" +
                    "  Vysoké Tatry_2: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Vysoké Tatry_2_1: 2/2 filled\n" +
                    "    Vysoké Tatry_2_2: 2/2 filled\n" +
                    "  Vysoké Tatry_3: 3/4 filled positions, 1/2 filled teams\n" +
                    "    Vysoké Tatry_3_1: 2/2 filled\n" +
                    "    Vysoké Tatry_3_2: 1/2 filled\n" +
                    "  Vysoké Tatry_4: 0/4 filled positions, 0/2 filled teams\n" +
                    "    Vysoké Tatry_4_1: 0/2 filled\n" +
                    "    Vysoké Tatry_4_2: 0/2 filled";
            assert repr3.equals(my_repr3) :
                    "Incorrect representation 3: " + my_repr3;

            Unit sturovo = (new UnitBuilder()).createStructureForSettlement("Štúrovo", 10390);
            sturovo.addPersonell(50);
            String my_repr4 = sturovo.accept(new Visualisation());

            String repr4 = "Štúrovo: 50/68 filled positions, 22/30 filled teams\n" +
                    "  Štúrovo_0: 6/12 filled positions, 0/4 filled teams\n" +
                    "    Štúrovo_0_1: 2/3 filled\n" +
                    "    Štúrovo_0_2: 2/3 filled\n" +
                    "    Štúrovo_0_3: 1/3 filled\n" +
                    "    Štúrovo_0_4: 1/3 filled\n" +
                    "  Štúrovo_1: 0/12 filled positions, 0/4 filled teams\n" +
                    "    Štúrovo_1_1: 0/3 filled\n" +
                    "    Štúrovo_1_2: 0/3 filled\n" +
                    "    Štúrovo_1_3: 0/3 filled\n" +
                    "    Štúrovo_1_4: 0/3 filled\n" +
                    "  Štúrovo_2: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_2_1: 2/2 filled\n" +
                    "    Štúrovo_2_2: 2/2 filled\n" +
                    "  Štúrovo_3: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_3_1: 2/2 filled\n" +
                    "    Štúrovo_3_2: 2/2 filled\n" +
                    "  Štúrovo_4: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_4_1: 2/2 filled\n" +
                    "    Štúrovo_4_2: 2/2 filled\n" +
                    "  Štúrovo_5: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_5_1: 2/2 filled\n" +
                    "    Štúrovo_5_2: 2/2 filled\n" +
                    "  Štúrovo_6: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_6_1: 2/2 filled\n" +
                    "    Štúrovo_6_2: 2/2 filled\n" +
                    "  Štúrovo_7: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_7_1: 2/2 filled\n" +
                    "    Štúrovo_7_2: 2/2 filled\n" +
                    "  Štúrovo_8: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_8_1: 2/2 filled\n" +
                    "    Štúrovo_8_2: 2/2 filled\n" +
                    "  Štúrovo_9: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_9_1: 2/2 filled\n" +
                    "    Štúrovo_9_2: 2/2 filled\n" +
                    "  Štúrovo_10: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_10_1: 2/2 filled\n" +
                    "    Štúrovo_10_2: 2/2 filled\n" +
                    "  Štúrovo_11: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_11_1: 2/2 filled\n" +
                    "    Štúrovo_11_2: 2/2 filled\n" +
                    "  Štúrovo_12: 4/4 filled positions, 2/2 filled teams\n" +
                    "    Štúrovo_12_1: 2/2 filled\n" +
                    "    Štúrovo_12_2: 2/2 filled";

            assert repr4.equals(my_repr4) :
                    "Incorrect representation 4: " + my_repr4;

            System.out.println("Part D2: PASSED");
        }
        catch (AssertionError e) {
            System.out.println(e.getMessage());
            System.out.println("Part D2: FAILED");
        }
    }


}

