public class NormalState implements PersonState{
    @Override
    public PersonState watchMovie(Movie m) {
        if (m.genre() == Genre.HORROR)
            return new ScaredState();
        if (m.genre() == Genre.ACTION)
            return new ActionState();
        if (m.genre() == Genre.COMEDY)
            return new FunState();
        return this;
    }
}
