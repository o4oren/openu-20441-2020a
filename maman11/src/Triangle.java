import java.util.Scanner;

/*
 * This program calculates the area and perimeter of a given triangle (input side lengths from command line).
 */
public class Triangle
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("This program calculates the area "
                + "and the perimeter of a given triangle. ");
        System.out.println("Please enter the three lengths"
                + "of the triangle's sides:");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        /*
        The length of every two sides of a triangle must be more than the length of the third.
        We will verify that, and print the results. Otherwise, we will print a message.
        We are assuming here that the input was whole positive numbers, as the question states.
        If this is not the case, we will need more validations.
        I added a non negativity or zero check even though it was not stated in the exercise as a safe guard.
        */
        if (a < 1 || b < 1 || c < 1)
        {
            System.out.println("At least one of the triangle's side's length is negative or zero!"
                    + " Please try again!");
        }
        else if ( a + b > c && a + c > b && b + c > a)
        {
            // Perimeter is calculated by summing the side legnths
            double perimeter = a + b + c;

            // Area is calculated by Heron's formula - the square root of s(s-a)(s-b)(s-c) where is is half the perimeter
            double s = perimeter / 2;
            double aa = s-a;
            double ab = s-b;
            double ac = s-c;

            double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));

            // Print the results
            System.out.println("The perimeter of the triangle is " + perimeter);
            System.out.println("The area of the triangle is " + area);
        }
        else
        {
            System.out.println("One of the triangle's side's length is greater than the sum of the other two!"
                + "Please try again!");

            //Just fiddling with printf
            System.out.printf("Side a's length: %d\nSide b's length: %d\nSide c's length: %d", a, b, c);
        }
    }
}
