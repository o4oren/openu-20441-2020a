public class FoodItemTester
{
    public static void main(String[] args) {
        System.out.println("********** Test FoodItem - Started **********");
        System.out.println("\n1. Testing Constructors and toString:");
        Date d1=new Date(14,12,2019);
        Date d2=new Date(21,12,2019);
        // test concstructor which takes 8 parameters
        FoodItem fi1 = new FoodItem(new String("milk"),111111,6,d1,d2, 10, 25,5);
        System.out.println("\tFoodItem 1:\n\t" + fi1);
        // test copy concstructor
        FoodItem fi2 = new FoodItem(fi1);
        System.out.println("\tFoodItem 2:\n\t" + fi2);
        System.out.println("\n2. Testing accessors and mutators:");
        // test getters
        System.out.println("\tFoodItem 1 name: " + fi1.getName());
        System.out.println("\tFoodItem 1 catalogue number: " + fi1.getCatalogueNumber());
        System.out.println("\tFoodItem 1 quantity: " + fi1.getQuantity());
        System.out.println("\tFoodItem 1 production date: " + fi1.getProductionDate());
        System.out.println("\tFoodItem 1 expiry date: " + fi1.getExpiryDate());
        System.out.println("\tFoodItem 1 min temperature: " + fi1.getMinTemperature());
        System.out.println("\tFoodItem 1 max temperature: " + fi1.getMaxTemperature());
        System.out.println("\tFoodItem 1 price: " + fi1.getPrice());

        // test setters   
        fi2.setQuantity(4);
        Date d3=new Date(18,12,2019);
        Date d4=new Date(28,12,2019);
        fi2.setProductionDate(d3);
        fi2.setExpiryDate(d4);
        fi2.setPrice(2);
        System.out.println("\tFoodItem 2:\n\t" + fi2);

        System.out.println("\n3. Testing comparison methods:");
        FoodItem fi3 = new FoodItem(new String("milk"),111111,6,d1,d2, 10, 25,5);
        System.out.println("\tFoodItem 1 and FoodItem 3 are equal: " + fi1.equals(fi3));
        System.out.println("\tFoodItem 1 is older than FoodItem 2: " + fi1.olderFoodItem(fi2));
        System.out.println("\tFoodItem 1 is fresh at "+d3+" : " + fi1.isFresh(d3)); 
        System.out.println("\tFoodItem 1 is cheaper than FoodItem 2: " + fi1.isCheaper(fi2));
        System.out.println("\n4. Testing howManyItems method :");
        int amount=10;
        System.out.println("\t"+ amount+" sheqels can buy "+ fi1.howManyItems(amount)+" of FoodItem 1");
        System.out.println("\t"+ amount+" sheqels can buy "+ fi2.howManyItems(amount)+" of FoodItem 2");

        
        System.out.println("\n********** Test FoodItem - Finished **********\n");
    }
}
