import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Test12
{
    @Test
    public void testDate()
    {
        //Test invalid date
        Date d0 = new Date(50,14,1000);
        assertTrue(d0.equals(new Date(Date.MIN_DAY, Date.FIRST_MONTH, Date.DEFAULT_YEAR)));
        //test before and after
        Date d1 = new Date(28,2, 2000);
        Date d2 = new Date(28, 2, 1000);
        Date d2equivalent = new Date(d2); // new date from constructor
        System.out.println("Before? " + d1.before(d2));
        assertFalse(d1.before(d2));
        System.out.println("After? " + d1.after(d2));
        assertTrue(d1.after(d2));
        assertFalse(d2.after(d2equivalent));
        assertFalse(d2.before(d2equivalent));
        assertTrue(d2.equals(d2equivalent));
        assertFalse(d2.equals(d1));

        //test february inleap year
        Date feb = new Date(29,2,2005);
        assertTrue(feb.equals(new Date(Date.MIN_DAY, Date.FIRST_MONTH, Date.DEFAULT_YEAR)));
        feb = new Date(29,2,2004);
        assertTrue(feb.getDay()==29);
        feb = new Date(29,2,2000);
        assertTrue(feb.getDay()==29);
        feb = new Date(29,2,1900);
        assertTrue(feb.equals(new Date(Date.MIN_DAY, Date.FIRST_MONTH, Date.DEFAULT_YEAR)));


        assertTrue(d2.tomorrow().after(d2equivalent));
        assertTrue(d2equivalent.before(d2.tomorrow()));

        assertTrue(d1.tomorrow().equals(new Date(29,2,2000)));
        assertTrue(d2.tomorrow().equals(new Date(1,3,1000)));

        Date last = new Date(31,12,Integer.MAX_VALUE);
        assertTrue(last.tomorrow().equals(new Date(Date.MIN_DAY,Date.FIRST_MONTH,Date.DEFAULT_YEAR)));
        System.out.println(d1.tomorrow());


        // test toString
        assertEquals(new Date(1,1,1000).toString(), "01/01/1000");
        assertEquals(new Date(12,12,1000).toString(), "12/12/1000");



        Date copiedDate = new Date(d1);
        assertTrue(copiedDate.equals(d1));
        assertNotEquals(System.identityHashCode(copiedDate), System.identityHashCode(d1));


//        System.out.println(d2.tomorrow());
//        System.out.println(d1.tomorrow());
//        System.out.println("Difference: " + d2.difference(d1));

        assertEquals(365242, d2.difference(d1));

//        System.out.println("Y: " + d2.getYear() % 100);d2
//        System.out.println("C: " + d2.getYear() / 100);

        System.out.println(d1.dayInWeek());
        assertEquals(2, d1.dayInWeek());
        System.out.println(d2.dayInWeek());
        assertEquals(6, d2.dayInWeek());

        assertEquals(4, new Date(1,1,2020).dayInWeek());
        assertEquals(0, new Date(1,2,2020).dayInWeek());
        assertEquals(1, new Date(1,3,2020).dayInWeek());

        // test setters
        d1.setDay(1);
        d1.setMonth(1);
        d1.setYear(1234);
        assertTrue(d1.equals(new Date(1,1,1234)));

    }

    @Test
    public void testFoodItem() {
        Date productionDate = new Date(2,2,2015);
        Date oldProductionDate = new Date(2,2,2014);
        Date newProductionDate = new Date(2,2,2020);


        Date dateBeforeProductionDate = new Date(1,2,2015);
        Date betweenProductionAndExpiryDate = new Date(2,3,2016);
        Date expiryDate = new Date(2,6,2019);
        FoodItem milk = new FoodItem("Milk", 123, 3, productionDate, expiryDate, 10, 20, 10);
        FoodItem milk2 = new FoodItem("Milks", 123, 10, productionDate, expiryDate, 10, 20, 10);
        FoodItem milk3 = new FoodItem("Milk", 123, 131, productionDate, expiryDate, 10, 20, 10);

        //test copy constructor
        FoodItem milkCopy = new FoodItem(milk);
        assertTrue(milkCopy.equals(milk));
        assertNotEquals(System.identityHashCode(milk), System.identityHashCode(milkCopy));

        System.out.println(milk);
        assertEquals("FoodItem: Milk\tProductionDate: 02/02/2015\tExpiryDate: 02/06/2019\tQuantity: 3", milk.toString());

        //Test equals
        assertTrue(milk.equals(milk3));
        assertFalse(milk.equals(milk2));

        // Test production date larger than expiry date
        FoodItem milk4 = new FoodItem("Milk", 123, 10, expiryDate, productionDate, 10, 20, 10);
        assertTrue(milk4.getProductionDate().tomorrow().equals(milk4.getExpiryDate())); // verify dates have changed places

        //test setter on milk3
        milk3.setExpiryDate(dateBeforeProductionDate);
        assertTrue(milk3.getExpiryDate().equals(expiryDate)); // verify dates have changed places

        //test older
        FoodItem olderItem = new FoodItem("Milk", 123, 11, oldProductionDate, expiryDate,  10, 20, 100);
        FoodItem newerItem = new FoodItem("Milk", 123, 11, newProductionDate, expiryDate, 10, 20, 100);

        assertFalse(milk.olderFoodItem(olderItem));
        assertTrue(milk.olderFoodItem(newerItem));

//        System.out.println(milk4.howManyItems(110));
        // howManyItems 10 items, 10 price so

        assertEquals(10, milk4.howManyItems(110));
        assertEquals(9, milk4.howManyItems(90));
        assertEquals(8, milk4.howManyItems(85));
        assertEquals(3, milk.howManyItems(85)); // quanity is 3
        assertEquals(8, milk3.howManyItems(85)); // quanity is 130

        // is fresh
        assertFalse(milk.isFresh(oldProductionDate));
        assertFalse(milk.isFresh(newProductionDate));
//        System.out.println(productionDate.before(betweenProductionAndExpiryDate));
//        System.out.println(expiryDate.after(betweenProductionAndExpiryDate));
        assertTrue(milk.isFresh(betweenProductionAndExpiryDate));

        FoodItem cheaper = new FoodItem("Milk", 123, 11, oldProductionDate, expiryDate, 10, 20, 100);
        FoodItem expensive = new FoodItem("Milk", 123, 11, oldProductionDate, expiryDate, 10, 20, 1000);

        assertTrue(cheaper.isCheaper(expensive));
        assertFalse(expensive.isCheaper(cheaper));
        assertFalse(cheaper.isCheaper(newerItem)); //equals

    }

}
