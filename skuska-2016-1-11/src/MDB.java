import java.util.HashSet;
import java.util.Set;

public class MDB {
    private static volatile MDB instance = null;
    private Set<Movie> set;

    private MDB(Set<Movie> _set){
        set = _set;
    }

    public MDB createMDB(Set<Movie> set){
        if (instance == null){
            synchronized (this) {
                if (instance == null) {
                    instance = new MDB(set);
                }
            }
        }
        return instance;
    }

    public void addMovie(Movie m){
        set.add(m);
    }

    public void removeMovie(Movie m){
        set.remove(m);
    }

    public void printDB(){
        set.forEach(System.out::println);
    }

    public static class MDBMemento{
        private Set<Movie> set;

        public void save(MDB mdb){
            set = new HashSet<>(mdb.set);
        }

        public void restore(MDB mdb){
            mdb.set = set;
        }
    }
}


