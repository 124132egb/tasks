import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            InputArgs params = parseCmdArgs(args);
            if (params == null) {
                System.err.println("Некорректные параметры. Используйте --input-file=FILE --output-file=FILE");
                System.exit(1);
            }

            int[][] array = logic.readArrayFromFile(params.inputFile);
            if (!logic.isRectangular(array)) {
                System.err.println("Массив не является прямоугольным");
                System.exit(2);
            }

            int result = logic.countElementsSumOfTwoOther(array);
            System.out.println("Результат: " + result);

            logic.writeArrayToFile(array, params.outputFile);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении/записи файла: " + e.getMessage());
            System.exit(3);
        } catch (NumberFormatException e) {
            System.err.println("Некорректное число в файле");
            System.exit(4);
        }
    }

    public static InputArgs parseCmdArgs(String[] args) {
        String inputFile = null;
        String outputFile = null;
        for (String arg : args) {
            if (arg.startsWith("--input-file=")) {
                inputFile = arg.substring("--input-file=".length());
            } else if (arg.startsWith("--output-file=")) {
                outputFile = arg.substring("--output-file=".length());
            } else if (arg.startsWith("-i")) {
                inputFile = arg.substring("-i".length()).trim();
            } else if (arg.startsWith("-o")) {
                outputFile = arg.substring("-o".length()).trim();
            }
        }
        if (inputFile == null || outputFile == null) {
            return null;
        }
        return new InputArgs(inputFile, outputFile);
    }
}