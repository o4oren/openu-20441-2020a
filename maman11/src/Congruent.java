import java.util.Scanner;

/*
 * This program will accept the 3 vertices of two triangles, and calculate if the two are congruent.
 */
public class Congruent
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("This program determines if two triangles are congruent.");
        System.out.println("Please enter the coordinates for the two triangles vertices:");

        // Get the coordinates from input.
        double x11 = scan.nextDouble();
        double y11 = scan.nextDouble();
        double x12 = scan.nextDouble();
        double y12 = scan.nextDouble();
        double x13 = scan.nextDouble();
        double y13 = scan.nextDouble();
        double x21 = scan.nextDouble();
        double y21 = scan.nextDouble();
        double x22 = scan.nextDouble();
        double y22 = scan.nextDouble();
        double x23 = scan.nextDouble();
        double y23 = scan.nextDouble();

        // Calculate the side lengths - each side is calculated according to the formula
        // d(P, Q) = squareroot of (x2 − x1)^2 + (y2 − y1)^2
        double a1 = Math.sqrt(Math.pow((x11-x12), 2) + Math.pow((y11-y12), 2));
        double b1 = Math.sqrt(Math.pow((x11-x13), 2) + Math.pow((y11-y13), 2));
        double c1 = Math.sqrt(Math.pow((x12-x13), 2) + Math.pow((y12-y13), 2));

        double a2 = Math.sqrt(Math.pow((x21-x22), 2) + Math.pow((y21-y22), 2));
        double b2 = Math.sqrt(Math.pow((x21-x23), 2) + Math.pow((y21-y23), 2));
        double c2 = Math.sqrt(Math.pow((x22-x23), 2) + Math.pow((y22-y23), 2));

        /*
        To check if the triangles are congruent we will have to check all possible matches between
        side lengths manually - as we are not allowed to use loops and Collections.
        We have 3! possibilities for a match. If a1 equals one of the sides in triangle 2, a2 will need to be matched against
        the other 2 sides.
        We will store a boolean that will get true only of there are 3 length matches,
        and use it to determine if the triangles are congruent.
         */
        boolean isCongruent = false;

        if(a1==a2)
        {
            if(b1==b2)
            {
                if(c1==c2)
                    isCongruent = true;
            }
            else if(b1==c2)
            {
                if (c1==b2)
                    isCongruent = true;
            }
        }
        else if(a1==b2)
        {
            if(b1==a2)
            {
                if(c1==c2)
                    isCongruent = true;
            }
            else if(b1==c2)
            {
                if (c1==a2)
                    isCongruent = true;
            }
        }
        else if(a1==c2)
        {
            if(b1==b2)
            {
                if(c1==a2)
                    isCongruent = true;
            }
            else if(b1==a2)
            {
                if (c1==b2)
                    isCongruent = true;
            }
        }

        System.out.printf("The first triangle is (%f, %f) (%f, %f) (%f, %f) .\n", x11, y11, x12, y12, x13, y13);
        System.out.println("Its lengths are " + a1 + ", " + b1 + ", " + c1 + ".");
        System.out.printf("The second triangle is (%f, %f) (%f, %f) (%f, %f) .\n", x21, y21, x22, y22, x23, y23);
        System.out.println("Its lengths are " + a2 + ", " + b2 + ", " + c2 + ".");
        if(isCongruent)
            System.out.println("The triangles are congruent.");
        else
            System.out.println("The triangles are not congruent.");
    }
}
