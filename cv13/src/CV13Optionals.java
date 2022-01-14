import java.util.Optional;

public class CV13Optionals {

    record Person(String name, Optional<Car> car){}

    record Car(String brand, Optional<Insurance> insurance){}

    record Insurance(boolean exists, Optional<Integer> value){}

    static void printInsurance(Person person){
        System.out.println((person.car()
                .map(p -> {
                    System.out.print(person.name() + " ");
                    return p;
                })
                .map(p -> {
                    System.out.print(p.brand() + " ");
                    return p;
                })
                .flatMap(Car::insurance)
                .flatMap(Insurance::value)
                .map(ins -> "Vyska poistenia: " + ins)
                .orElse("vyska poistenia neexistuje")));
    }

    public static void main(String[] args) {
        Insurance insurance1 = new Insurance(true, Optional.of(10));
        Car car1 = new Car("mercedes", Optional.of(insurance1));
        Person person1 = new Person("Fero", Optional.of(car1));

        Car car2 = new Car("renault", Optional.empty());
        Person person2 = new Person("Jano", Optional.of(car2));

        Person person3 = new Person("Zuza", Optional.empty());
        printInsurance(person1);
        printInsurance(person2);
        printInsurance(person3);

    }
}
