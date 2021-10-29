public interface Animal {

    <T> T accept(Visitor visitor);
}
