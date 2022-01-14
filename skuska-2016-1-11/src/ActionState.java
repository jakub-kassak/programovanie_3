public class ActionState implements PersonState{
    @Override
    public PersonState watchMovie(Movie m) {
        if (m.genre() == Genre.COMEDY)
            return new NormalState();
        return this;
    }
}
