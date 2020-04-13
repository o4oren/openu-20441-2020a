public class Ex14StudentTester {

    public static void main(String[] argv) {

        System.out.println("\n====================================\n\nTesting Question 1\n------------------------------------\n");

        String s;
        char c;
        int k;
        s = "abcbcabcacabcc";
        c = 'c';
        System.out.println("subStrC method return: " + Ex14.subStrC(s,c) + " for " + s + " and " + c + ". Should return 4"); // should return 4

        s = "abcbc";
        k = 0;
        System.out.println("subStrMaxC method return: " + Ex14.subStrMaxC(s,c,k) + " for " + s + " and " + c + " and " + k + ". Should return 1"); // should return 1

        s = "abcbcabcacab";
        k = 2;
        System.out.println("subStrMaxC method return: " + Ex14.subStrMaxC(s,c,k) + " for " + s + " and " + c + " and " + k + ". Should return 6"); // should return 6

        k = 3;
        System.out.println("subStrMaxC method return: " + Ex14.subStrMaxC(s,c,k) + " for " + s + " and " + c + " and " + k + ". Should return 6"); // should return 6

        s = "abc";
        k = 2;
        System.out.println("subStrMaxC method return: " + Ex14.subStrMaxC(s,c,k) + " for " + s + " and " + c + " and " + k + ". Should return 0"); // should return 0

        System.out.println("\n====================================\n\nTesting Question 2\n------------------------------------\n");
        int[] a={0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1};
        Ex14.zeroDistance(a);
        System.out.println("zeroDistance method result : ");
        for (int i = 0; i < a.length - 1; i++)
            System.out.print(a[i] + ", ");
        System.out.println(a[a.length -1]);
        System.out.println("Should return : ");
        System.out.println("0, 1, 2, 3, 3, 2, 1, 0, 1, 2, 3, 2, 1, 0, 1, 2");

        System.out.println("\n====================================\n\nTesting Question 3\n------------------------------------\n");
        boolean result=Ex14.isTrans("abbcd","aabbccdd");
        System.out.println("isTrans method returns " + result + " for abbcd and aabbccdd - should return true"); //expected true
        result=Ex14.isTrans("abbcd","abcd");
        System.out.println("isTrans method returns " + result + " for abbcd and abcd - should return false"); //expected true

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
