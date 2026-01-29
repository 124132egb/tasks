import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        InputArgs params = parseCmdArgs(args);
        if (params == null) {
            System.err.println("Usage: java Main --input-file=path --output-file=path");
            System.exit(1);
        }

        try {
            List<List<Integer>> data = DataUtils.readDataFromFile(params.inputFile);
            if (!data.isEmpty()) {
                DataUtils.sort(data.get(0), 0, 3);
            }
            DataUtils.writeDataToFile(params.outputFile, data);
        } catch (IOException e) {
            System.err.println("Error reading/writing files: " + e.getMessage());
            System.exit(2);
        }
    }

    public static InputArgs parseCmdArgs(String[] args) {
        String inputFile = null;
        String outputFile = null;

        for (String arg : args) {
            if (arg.startsWith("--input-file=")) {
                inputFile = arg.substring("--input-file=".length());
            } else if (arg.startsWith("-i")) {
            } else if (arg.startsWith("--output-file=")) {
                outputFile = arg.substring("--output-file=".length());
            } else if (arg.startsWith("-o")) {
            }
        }
        if (inputFile == null || outputFile == null) {
            return null;
        }
        return new InputArgs(inputFile, outputFile);
    }
}