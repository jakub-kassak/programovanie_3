import static java.lang.Math.pow;

public class Part1 {

    interface Alcohol {
        double volume();
        double alcoholContent();
        default double alcoholVolume(){
            return volume() * alcoholContent();
        }
        static String warning(){return "Alcohol is bad for health.";}
    }

    public static void main(String[] args) {
        class Burger{
            private final double mass;
            private final String type;

            public Burger(double mass, String type) {
                this.mass = mass;
                this.type = type;
            }

            public double getMass() {
                return mass;
            }

            public String getType() {
                return type;
            }

        }
        class Beer implements Alcohol{
            private final double volume;
            private final double alcoholContent;

            public Beer(double volume, double alcoholContent) {
                this.volume = volume;
                this.alcoholContent = alcoholContent;
            }

            public double volume() {
                return volume;
            }

            public double alcoholContent() {
                return alcoholContent;
            }
        }

        Burger b = new Burger(10, "small");
        System.out.println(b.getMass());
        System.out.println(b.getType());

        Beer beer = new Beer(.75, .12);
        System.out.println(beer.volume() + " " + beer.alcoholContent());

        Alcohol absinth = new Alcohol() {
            @Override
            public double volume() {
                return 1;
            }

            @Override
            public double alcoholContent() {
                return 0.4;
            }
        };

        System.out.println(absinth.alcoholVolume());
        System.out.println(pow(2, 8));
        System.out.println(Alcohol.warning());
    }
}
