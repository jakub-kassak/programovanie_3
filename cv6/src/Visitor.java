public interface Visitor {

    <T> T visit(Cat cat);
    <T> T visit(Dog dog);

}
