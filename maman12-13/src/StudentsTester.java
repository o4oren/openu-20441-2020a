
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StudentsTester
{
    public static void main(String[] args)
    {
        System.out.println("\n*********************** START OF STOCK TESTER**************************************");        
        // Stock        
        Date t1 = new Date(1,1,2000);
        Date t2 = new Date(1,1,2001);
        Date t3 = new Date(1,1,2002);
        FoodItem f1 = new FoodItem("Milk", 1111, 12, t1, t2, 7, 10, 5);
        FoodItem f2 = new FoodItem("Honey", 2222, 2, t1, t3, 6, 10, 20);
        FoodItem f3 = new FoodItem("PopCorn", 3333, 2, t1, t3, 6, 10, 12);

        Stock st = new Stock();
        st.addItem(f1);
        st.addItem(f2);
        st.addItem(f3);

        System.out.println("Testing method \"getNoOfItems\":");
        System.out.println("After adding 3 Food items to the Stock, the method \"getNoOfItems\" retuns: " + st.getNumOfItems()+"\n");//should print Honey and PopCorn 
        System.out.print("Testing method \"toString\" - ");
        System.out.println("the Stock looks like this:\n" + st);

        String list = st.order(5);
        System.out.println("This is the list to order (items quantity below 5) : " + list);//should print Honey and PopCorn                
        System.out.println("The number of items that can be store at 8 degrees are:  " + st.howMany(8));// should print 16
        System.out.println("the most expensive item on stock is:\n" + st.mostExpensive());// should print the Honey
        System.out.println("number of pieces in stock is: " + st.howManyPieces());// should print 16

        String[] updateList={"Milk","Milk"};
        System.out.println("\nUpdating Stock with {Milk,Milk}");
        st.updateStock(updateList);        
        System.out.println("list after update is (2 milks less in stock -> leaving 10 in the stock):\n"+ st);

        System.out.println("Min temperature of stock should be: " +st.getTempOfStock()); // should be 7

        Date t4 = new Date(1,6,2001);
        st.removeAfterDate (t4);
        System.out.println("deleting from stock all items with expiry date before (1/6/2001)\n"+
            "after deletion the stock looks like this (Milk should be deleted):\n" + st); 

        System.out.println("\n*********************** END OF STOCK TESTER**************************************");
        
        System.out.println("\n*********************** START OF MATRIX TESTER**************************************");
        
        // MATRIX
        System.out.println("\nTest Matrix:\n");
        System.out.println("for Matrix m2 = new Matrix(3, 3); the toString of m2 is:");
        Matrix m2 = new Matrix(3, 3);
        System.out.println(m2);

        System.out.println("\nfor a = { { 10, 30, 50 }, { 100, 150, 200 } };");
        int[][] a = { { 10, 30, 50 }, { 100, 150, 200 } };
        Matrix m1 = new Matrix(a);
        System.out.println("\nfor Matrix m1 = new Matrix(a); the toString of m1 is:");
        System.out.println(m1);
        Matrix temp;

        temp = m1.makeNegative();
        System.out.println("resuls of m1.makeNegative(); \n" + temp);

        temp = m1.imageFilterAverage();
        System.out.println("resuls of m1.imageFilterAverage(); \n" + temp);

        temp = m1.rotateClockwise();
        System.out.println("resuls of m1.rotateClockwise(); \n" + temp);

        temp = m1.rotateCounterClockwise();
        System.out.println("resuls of m1.rotateCounterClockwise(); \n" + temp);
        System.out.println("\n*********************** END OF MATRIX TESTER**************************************");

    }
}
