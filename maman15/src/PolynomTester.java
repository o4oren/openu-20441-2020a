
public class PolynomTester
{

    /**
     * The Main method of this tester
     */
    public static void main (String[] args)
    {
        // Create two Polynoms
        Polynom p1 = new Polynom();
        p1.addNode(new PolyNode(0,2));
        p1.addNode(new PolyNode(2,4));
        System.out.println("\nP1:");
        System.out.println(p1);

        Polynom p2 = new Polynom(new PolyNode(0,2));
        p2.addNode(new PolyNode(2,-1));
        p2.addNode(new PolyNode(4,5));
        System.out.println("\nP2:");
        System.out.println(p2);

        Polynom p3 = new Polynom(new PolyNode(0,2));
        p3.addNode(new PolyNode(2,-1));
        p3.addNode(new PolyNode(4,-5));
        System.out.println("\nP3:");
        System.out.println(p3);

        // A few simple tests
        System.out.println("\n2*P1 + P2:");
        System.out.println(p2.addPol(p1.multByScalar(2)));

        System.out.println("\nP1 * P2:");
        //System.out.println(p1 + "\t\t* \n" + p2 + "\t=\n" + p1.multPol(p2));
        System.out.println(p1 + "\t\t*");
        System.out.println(p2 + "\t=");
        System.out.println(p1.multPol(p2));

        System.out.println("\nDifferential of P2:");
        System.out.println(p2.differential());

    }

}
