import java.util.Scanner;

public class task4 {

    public static boolean isNumberMatching(int number) {
        int evenCount = 0;
        int oddCount = 0;
        int temp = number;

        if (temp < 0) {
            temp = -temp;
        }

        if (temp == 0) {
            evenCount = 1;
            oddCount = 0;
        }

        while (temp > 0) {
            int digit = temp % 10;
            if (digit % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
            temp /= 10;
        }

        return evenCount == oddCount;
    }

    public static int sumMatchingNumbers(int A, int B) {
        int sum = 0;
        int start = A;
        int end = B;

        if (A > B) {
            start = B;
            end = A;
        }

        for (int num = start; num <= end; num++) {
            if (isNumberMatching(num)) {
                sum += num;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число A: ");
        int A = scanner.nextInt();
        System.out.print("Введите число B: ");
        int B = scanner.nextInt();

        int result = sumMatchingNumbers(A, B);
        System.out.println("Сумма подходящих чисел: " + result);
    }
}