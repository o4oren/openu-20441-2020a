public class Q1Tester
{
    public static void main(String[] args) {
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

        s = "abcbcabcacabac";
        k = 3;
        System.out.println("subStrMaxC method return: " + Ex14.subStrMaxC(s,c,k) + " for " + s + " and " + c + " and " + k + ". Should return 10"); // should return 6

        c='a';
        s = "abacadaeafag";
        k = 2;
        System.out.println("subStrMaxC method return: " + Ex14.subStrMaxC(s,c,k) + " for " + s + " and " + c + " and " + k + ". Should return 12"); // should return 6
        s = "abacadaeafag";
        k = 3;
        System.out.println("subStrMaxC method return: " + Ex14.subStrMaxC(s,c,k) + " for " + s + " and " + c + " and " + k + ". Should return 14"); // should return 6

    }
}
