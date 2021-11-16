public class Sound implements Visitor {

    @Override
    public Void visit(Cat cat) {
        System.out.println("Meow");
        return null;
    }

    @Override
    public Void visit(Dog dog) {
        System.out.println("Woof");
        return null;
    }
    
}
