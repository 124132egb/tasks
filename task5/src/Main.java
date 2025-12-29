public class Main {
    public static void main(String[] args) {
        printFigure(8);
    }

    static void printFigure(int s) {
        if (s < 3) {
            throw new IllegalArgumentException("s must be >= 3");
        }

        for (int j = 0; j < s; j++) {
            System.out.print('*');
        }
        System.out.println();

        for (int i = 1; i <= s - 2; i++) {
            System.out.print('*');

            for (int j = 1; j <= s - 2; j++) {
                if (i == 1 || i == s - 2) {
                    System.out.print('#');
                }
                else if (j == i || j == (s - 1 - i)) {
                    System.out.print('#');
                }
                else {
                    System.out.print(' ');
                }
            }

            System.out.print('*');
            System.out.println();
        }

        for (int j = 0; j < s; j++) {
            System.out.print('*');
        }
        System.out.println();
    }
}