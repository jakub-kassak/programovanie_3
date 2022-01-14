import java.util.List;

enum Genre {
    COMEDY,
    HORROR,
    ACTION
}

public record Movie(String title, Enum<Genre> genre, int year, List<String> actors) {
}
