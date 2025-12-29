import java.util.Scanner;

public class task6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите x (–1<x<1): ");
        double x = sc.nextDouble();
        System.out.print("Введите n (количество членов): ");
        int n = sc.nextInt();
        System.out.print("Введите ε: ");
        double eps = sc.nextDouble();

        double a = 1.0;
        double sumAll = 0.0;
        double sumGreaterE = 0.0;
        double sumGreaterE10 = 0.0;//

        for (int k = 0; k < n; k++) {
            sumAll += a;
            if (Math.abs(a) > eps) {
                sumGreaterE += a;
            }
            if (Math.abs(a) > eps / 10.0) {
                sumGreaterE10 += a;
            }
            if (k + 1 < n) {
                if (k == 0) {
                    // из вида a₁ = –(2*3)/2 * x = –3 x
                    a = -3.0 * x;
                } else {
                    a = a * (-(k + 2.0) / k) * x;
                }
            }
        }

        double exact = 1.0 / Math.pow(1.0 + x, 3);

        System.out.println("Сумма первых n членов:              " + sumAll);
        System.out.println("Сумма членов с |a_k| > ε:           " + sumGreaterE);
        System.out.println("Сумма членов с |a_k| > ε/10:        " + sumGreaterE10);
        System.out.println("Точное значение 1/(1+x)^3:         " + exact);

        sc.close();
    }
}