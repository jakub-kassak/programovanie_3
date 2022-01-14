public class Person {
    private PersonState st = new NormalState();

    public void watchMovie(Movie m){
        st = st.watchMovie(m);
    }
}
