import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Printer implements  TablePrinter {
    private FileWriter writer;
    private List<String> header;
    private boolean open;
    private final String delimiter;

    public Printer(String delimiter){
        this.delimiter = delimiter;
    }

    @Override
    public void openOutputFile(String filename) {
        try {
            if (!open) {
                writer = new FileWriter(filename);
                open = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeOutputFile() {
        try {
            if (open) {
                writer.close();
                open = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printHeader(List<String> header) {
        if (open){
            try {
                this.header = header;
                writer.write(header.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(delimiter)) + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void printRow(Map<String, Object> row) {
        try {
            writer.write(header.stream()
                    .map(key -> Optional
                            .ofNullable(row.get(key))
                            .orElse("null"))
                    .map(Object::toString)
                    .collect(Collectors.joining(delimiter)) + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
