import org.junit.Assert;
import org.junit.Test;

public class Test13
{
    FoodItem banana = new FoodItem("Banana", 1000, 1, new Date(1,1,2019), new Date(1,2,2019), 10, 20, 5);
    FoodItem apple = new FoodItem("Apple", 2000, 1, new Date(1,1,2019), new Date(1,2,2019), 9, 20, 3);
    FoodItem cream = new FoodItem("Cream", 3000, 1, new Date(1,1,2019), new Date(1,3,2019), -2, 10, 3);
    FoodItem dbeer = new FoodItem("Drought beer", 4000, 10, new Date(1,1,2019), new Date(4,6,2019), -3, 10, 10);
    FoodItem milk = new FoodItem("Milk", 5000, 1, new Date(1,1,2019), new Date(1,5,2019), 0, 11, 3);
    FoodItem milk2 = new FoodItem("Milk", 5000, 6, new Date(2,1,2019), new Date(1,3,2021), 2, 10, 3);
    FoodItem otherMilk = new FoodItem("Milk", 6000, 6, new Date(2,1,2019), new Date(1,3,2021), 2, 10, 3);


    @Test
    public void testStock()
    {
        Stock stock = new Stock();
        Assert.assertTrue(stock.addItem(banana));
        Assert.assertTrue(stock._stock[0].equals(banana));
        stock.addItem(banana);
        Assert.assertTrue(stock._stock[0].getQuantity() == 2);
        stock.addItem(milk);
        stock.addItem(apple);
        Assert.assertTrue(stock._stock[2].equals(milk));
        Assert.assertTrue(stock._stock[1].equals(apple));
        Assert.assertTrue(stock._stock[3]==(null));

        // add an item before
        stock.addItem(dbeer);
        Assert.assertTrue(stock._stock[2]==(dbeer));
        Assert.assertTrue(stock._stock[3]==(milk));

        stock.addItem(cream);
        Assert.assertTrue(stock._stock[2].equals(cream));
        Assert.assertTrue(stock._stock[4].equals(milk));
        Assert.assertEquals(stock.getNumOfItems(), 5);

        System.out.println(stock.order(3));
        Assert.assertEquals("Banana, Apple, Cream, Drought beer, Milk", stock.order(11));
        Assert.assertEquals("Banana, Apple, Cream, Milk", stock.order(3));
        Assert.assertEquals("", stock.order(1));

        // check order summing
        stock.addItem(milk2);
        Assert.assertEquals(stock.getNumOfItems(), 6);
        stock.order(3);
        Assert.assertEquals("Banana, Apple, Cream", stock.order(3));
        Assert.assertEquals("Banana, Apple, Cream, Milk", stock.order(8));


        System.out.println(stock);
    }

    @Test
    public void testStockMaxSize()
    {
        Stock stock = new Stock();
        String name = "name";
        Date date = new Date(1,1,1000);
        for (int i = 0; i < 100; i++)
        {
            FoodItem item = new FoodItem(name + i,(long)i, 10, date, date.tomorrow(), 20, 30, 40);
            boolean success = stock.addItem(item);
            Assert.assertTrue("Item " + item.getName(),success);
        }
        FoodItem item = new FoodItem("last",100, 10, date, date.tomorrow(), 20, 30, 40);
        Assert.assertFalse(stock.addItem(item));
        System.out.println("");
    }

    @Test
    public void testMaxNumberOfItems()
    {
        Stock stock = new Stock();
        int i = 0;
        boolean success = false;
        do {
            FoodItem item = new FoodItem("item" + i, i, i, new Date(1,1,2000 + i), new Date(2,2,2010 + i), 5, 15, 100);
            success = stock.addItem(item);
            i++;
        } while (success);
        Assert.assertEquals(100, stock.getNumOfItems());

    }

    @Test
    public void testHowMany()
    {
        Stock stock = createStock();
        Assert.assertEquals(2 ,stock.howMany(18));
        Assert.assertEquals(10 ,stock.howMany(-2));
        Assert.assertEquals(12 ,stock.howMany(2));
        Assert.assertEquals(24 ,stock.howMany(3));

        Assert.assertEquals(26, stock.howManyPieces());
        Stock stock2 = new Stock();
        Assert.assertEquals(0, stock2.howManyPieces());
        System.out.println(stock);
    }

    @Test
    public void testRemoveAfterDelete()
    {
        Stock stock = new Stock();
        stock.addItem(banana);
        stock.addItem(cream);
        stock.addItem(dbeer);
        stock.addItem(milk);
        stock.addItem(apple);
        stock.addItem(milk2);
        stock.addItem(otherMilk);

        stock.removeAfterDate(new Date(3,2,2019));
        Assert.assertEquals(5, stock._noOfItems);
        Assert.assertTrue(stock._stock[0].equals(cream));
        Assert.assertTrue(stock._stock[3].equals(milk2));
        Assert.assertTrue(stock._stock[5]==null);
        stock.removeAfterDate(new Date(2,3,5000));
        Assert.assertEquals(0, stock._noOfItems);
    }

    @Test
    public void testMostExpensive()
    {
        Stock stock = createStock();
        FoodItem i = stock.mostExpensive();
        Assert.assertEquals(dbeer, stock.mostExpensive());

        Stock stock2 = new Stock();
        Assert.assertTrue(stock2.mostExpensive()==null);
    }

    @Test
    public void testUpdateStock()
    {
        Stock stock = createStock();
        FoodItem threeItem = new FoodItem("3 items", 7000, 3, new Date(2,1,2019), new Date(1,3,2019), 2, 10, 3);
        FoodItem sixItem = new FoodItem("6 items", 8000, 3, new Date(2,1,2019), new Date(1,3,2019), 2, 10, 3);
        stock.addItem(threeItem);
        stock.addItem(sixItem);
        String[] s = new String[] { "3 items", "6 items", "Apple"};
        stock.updateStock(s);
        Assert.assertEquals(cream, stock._stock[1]);

        s = new String[] { "6 items", "Cream", "6 items"};
        stock.updateStock(s);
        Assert.assertEquals(threeItem, stock._stock[5]);
        Assert.assertEquals(6, stock.getNumOfItems());
    }

    @Test
    public void testGetTempOfStock()
    {
        Stock stock = createStock();
        Assert.assertEquals(10, stock.getTempOfStock());
        stock.addItem(new FoodItem("too hot", 11, 11, new Date(1,1,2019), new Date(1,2,2019), 50, 60, 5) );
        Assert.assertEquals(Integer.MAX_VALUE, stock.getTempOfStock());


    }

    private Stock createStock()
    {
        Stock stock = new Stock();
        stock.addItem(banana);
        stock.addItem(apple);
        stock.addItem(cream);
        stock.addItem(dbeer);
        stock.addItem(milk);
        stock.addItem(milk2);
        stock.addItem(otherMilk);
        return stock;
    }

}
