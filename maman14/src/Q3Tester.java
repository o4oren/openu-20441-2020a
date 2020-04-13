public class Q3Tester
{
    public static void main(String[] args) {
        System.out.println("\n====================================\n\nTesting Question 3\n------------------------------------\n");
        boolean result=Ex14.isTrans("abbcd","aabbccdd");
        System.out.println("isTrans method returns " + result + " for abbcd and aabbccdd - should return true"); //expected true
        result=Ex14.isTrans("abbcd","abcd");
        System.out.println("isTrans method returns " + result + " for abbcd and abcd - should return false"); //expected true

        result=Ex14.isTrans("abbcd","aaccbbdd");
        System.out.println("isTrans method returns " + result + " for abbcd and abcd - should return false"); //expected true

        result=Ex14.isTrans("abbcd","abbcdddddd");
        System.out.println("isTrans method returns " + result + " for abbcd and abcd - should return false"); //expected true

        result=Ex14.isTrans("abbcd","abcd");
        System.out.println("isTrans method returns " + result + " for abbcd and abcd - should return false"); //expected true

    }
}
