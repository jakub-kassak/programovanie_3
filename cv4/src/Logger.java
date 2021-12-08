import java.io.*;

public class Logger extends ShelfDecorator {
    String fileName;

    public Logger(Shelf shelf, String fileName) {
        super(shelf);
        this.fileName = fileName;
    }

    private void logToFile(String log)  {
        try{
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName, true), "UTF-8"));
            writer.write(log + '\n');
            writer.close();
        } catch (IOException e) {
            System.out.println("Zapis sa nepodaril");
        }

    }

    @Override
    public Coffee createEspresso() {
        logToFile("createEspresso");
        return super.createEspresso();
    }

    @Override
    public Coffee addMilk(Coffee basis) {
        logToFile("addMilk");
        return super.addMilk(basis);
    }

    @Override
    public Coffee addMilkFoam(Coffee basis) {
        logToFile("addMilkFoam");
        return super.addMilkFoam(basis);
    }

    @Override
    public Coffee addWhiskey(Coffee basis) {
        logToFile("addWhiskey");
        return super.addWhiskey(basis);
    }

    @Override
    public Coffee addWater(Coffee basis) {
        logToFile("addWater");
        return super.addWater(basis);
    }

    @Override
    public Coffee addWhippedCream(Coffee basis) {
        logToFile("addWhippedCream");
        return super.addWhippedCream(basis);
    }
}
