public class ScaredState implements PersonState{
    @Override
    public PersonState watchMovie(Movie m) {
        if (m.genre() == Genre.ACTION){
            return new ActionState();
        } else if (m.genre() == Genre.HORROR)
            return new NormalState();
        return this;
    }
}
