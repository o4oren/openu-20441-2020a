public class Q4Tester
{
    public static void main(String[] args) {
        System.out.println("\n====================================\n\nTesting Question 4\n------------------------------------\n");

        int [][] a1 = { { 12, 22, 23, 54 }, { 43, 35, 21, 20 },
                { 34, 21, 43, 21 }, { 25, 30, 0, 20 }, { 0, 22, 10, 10 },
                { 20, 13, 3, 45 } };

        int studentRes = Ex14.countPaths(a1);
        int expectedRes = 3;
        System.out.println("array : ");
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) {
                System.out.print(a1[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("expected=" + expectedRes + " : student="+ studentRes);
        if (studentRes != expectedRes)
            System.out.println("Ex14.countPaths(a1) FAILED ");
        else
            System.out.println("Ex14.countPaths(a1) PASSED ");

    }
}
