public class Speed implements Visitor {
    public Integer visit(Cat cat) {
        return 48;
    }

    public Integer visit(Dog dog) {
        return 32;
    }
}
