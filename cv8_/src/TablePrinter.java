import java.util.List;
import java.util.Map;

public interface TablePrinter {

    void openOutputFile(String filename);
    void closeOutputFile();
    void printHeader(List<String> header);
    void printRow(Map<String, Object> row);

}
