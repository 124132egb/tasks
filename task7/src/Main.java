import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите размер массива:");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int count = countElementsSumOfTwoOther(arr);
        System.out.println("Количество элементов, которые можно получить сложением двух других: " + count);
    }

    public static int countElementsSumOfTwoOther(int[] arr) {
        int n = arr.length;
        int result = 0;

        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        for (int i = 0; i < n; i++) {
            boolean canBeFormed = false;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                for (int k = j + 1; k < n; k++) {
                    if (i == k) continue;
                    if (arr[j] + arr[k] == arr[i]) {
                        canBeFormed = true;
                        break;
                    }
                }
                if (canBeFormed) break;
            }
            if (canBeFormed) {
                result++;
            }
        }
        return result;
    }
}