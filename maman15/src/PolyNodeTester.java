public class PolyNodeTester
{
    
    /**
     * The Main method of this tester 
     */
    public static void main (String[] args)
    {
         PolyNode pn1=new PolyNode(3,2);
       
       
         System.out.println("pn1(3,2) \n" + pn1);
      
         pn1=new PolyNode(4,5);
         
         PolyNode pn11=new PolyNode(3,2,pn1);
             
         System.out.println("pn11(3,2,(4,5)) \n" + pn1);
         
         pn1=new PolyNode(4,5);
      
         PolyNode pn111=new PolyNode(pn1);
       
         System.out.println("copy constructor building pn111(4,5) \n" + pn111);
         pn1.setPower(7);
         pn1.setCoefficient(8);
         pn1.setNext(null);
         System.out.println(pn1.getPower());
         System.out.println(pn1.getCoefficient());
         System.out.println(pn1.getNext());
         pn1=new PolyNode(4,-3);
         System.out.println("pn1(4,-3) \n" + pn1);
         pn1=new PolyNode(4,-1);
         System.out.println("pn1(4,-1) \n" + pn1);
         pn1=new PolyNode(-4,-1);
         System.out.println("pn1(-4,-1) \n" + pn1);
         pn1=new PolyNode(1,1);
         System.out.println("pn1(1,1) \n" + pn1);
         pn1=new PolyNode(0,3);
         System.out.println("pn1(0,3) \n" + pn1);
         pn1=new PolyNode(0,1);
         System.out.println("pn1(0,1) \n" + pn1);
         pn1=new PolyNode(0,-1);
         System.out.println("pn1(0,-1) \n" + pn1);
         
         
         
      

    }
    
}