import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TextLogger {

    public static void main(String[] args) {
        TablePrinter printer = new TSVPrinter();
        Scanner sc = new Scanner(System.in);
        printer.openOutputFile("logs.txt");
        printer.printHeader(List.of("no", "text", "nieco"));
        int counter = 0;
        while (sc.hasNext()){
            printer.printRow(Map.of(
                    "no", counter++,
                    "text", sc.nextLine()));
        }
        printer.closeOutputFile();
    }
}
