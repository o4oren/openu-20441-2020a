public class Q2Tester
{
    public static void main(String[] args) {
        System.out.println("\n====================================\n\nTesting Question 2\n------------------------------------\n");
        int[] a={0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1};
        Ex14.zeroDistance(a);
        System.out.println("zeroDistance method result : ");
        for (int i = 0; i < a.length - 1; i++)
            System.out.print(a[i] + ", ");
        System.out.println(a[a.length -1]);
        System.out.println("Should return : ");
        System.out.println("0, 1, 2, 3, 3, 2, 1, 0, 1, 2, 3, 2, 1, 0, 1, 2");

        int[] b={0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0};
        Ex14.zeroDistance(b);
        System.out.println("zeroDistance method result : ");
        for (int i = 0; i < b.length - 1; i++)
            System.out.print(b[i] + ", ");
        System.out.println(b[b.length -1]);
        System.out.println("Should return : ");
        System.out.println("2, 1, 0, 1, 2, 3, 2, 1, 0, 1, 2, 3, 3, 2, 1, 0");
    }
}
