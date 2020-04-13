/**
 * This sclass represents a the store's stock.
 */
public class Stock
{
    FoodItem[] _stock;
    int _noOfItems;

    static final int MAX_STOCK = 100;

    public Stock()
    {
        this._stock = new FoodItem[MAX_STOCK];
    }

    public boolean addItem(FoodItem newItem)
    {
        // If the array is emply - add the first item and update number of Items
        if(_noOfItems==0) {
            _stock[_noOfItems] = newItem;
            _noOfItems++;
            return true;
        }

        // If the array is not empty, check if the item exists in the array, and if yes - update quantity
        // Otherwise, add the item at the correct place in the array.
        int sameItemIndex = findFirstExistingItemIndex(newItem);
        if (sameItemIndex != -1)
        {
            _stock[sameItemIndex].setQuantity(_stock[sameItemIndex].getQuantity() + newItem.getQuantity());
            return true;
        }
        else
        {
            // if we are not updating quantity of an existing item, we need to check if we have room to add
            // check if there is room to shift the cells
            if (_noOfItems == 100)
                return false;

            // if an item with same name and cat number but different dates exists, add the item before it.
            int sameNameItemIndex = findExistingNameAndCatNumButNotIdenticalDates(newItem);
            if(sameItemIndex != -1)
            {
                shiftArrayValuesFromIndex(sameNameItemIndex);
                _stock[sameNameItemIndex] = newItem;
                _noOfItems++;
                return true;
            }
            else
            {
                // Find the correct place to add the item based on catalogue number
                int index = findNewItemIndex(newItem);
                shiftArrayValuesFromIndex(index);
                _stock[index] = newItem;
                _noOfItems++;
                return true;
            }
        }
    }

    /**
     * Returns a list of items to order
     * An Item will be included in the list if it's quantity is lower than the given amount.
     * @param amount
     * @return
     */
    public String order(int amount)
    {
        String s = "";
        int i = 0;
        FoodItem item = _stock[i];
        do
        {
            if( getTotalQuantity(item) < amount && !s.contains(item.getName())) // check if item is below quantity, but also if it is not already in the string - so it doesn't appear twice.
                s += ", " + item.getName();
            i++;
            item = _stock[i];
        }
        while (item != null);
        return s.replaceFirst(", ", "");
    }

    /**
     * Returns the number of items that cam move to a refrigerator with the given temperature
     * @param temp
     * @return
     */
    public int howMany(int temp)
    {
        int num = 0;
        for(int i = 0; i < _noOfItems; i++)
        {
            if( temp > _stock[i].getMinTemperature() && temp < _stock[i].getMaxTemperature() )
                num += _stock[i].getQuantity();
        }
        return num;
    }

    /**
     * Will remove expired items from stock.
     * The method will set such items to null in the array, and will then run  updateArray() to close the gaps
     * @param d
     */
    public void removeAfterDate(Date d)
    {
        int originalNumberOfItems = _noOfItems;
        for(int i = 0; i < originalNumberOfItems; i++)
        {
            if( d.after(_stock[i].getExpiryDate()))
            {
                _stock[i]=null;
                _noOfItems--;
            }
        }
        updateArray(originalNumberOfItems);
    }

    public FoodItem mostExpensive()
    {
        FoodItem mostExpensive = _stock[0]; // if the array is empty this will be null; We'll compate the rest to it.
        for (int i = 1; i < _noOfItems; i++)
        {
            if(_stock[i].getPrice() > mostExpensive.getPrice())
                mostExpensive=_stock[i];
        }
        return mostExpensive;
    }

    public int howManyPieces()
    {
        int thisMany = 0;
        for (int i = 0; i < _noOfItems; i++)
        {
            thisMany += _stock[i].getQuantity();
        }
        return thisMany;
    }

    /**
     * Accepts a list of items to remove from stock
     * @param itemsList
     */
    public void updateStock(String[] itemsList)
    {
        int originalNumberOfItems = _noOfItems;
        for(String name : itemsList)
        {
            int index = findItemIndexByName(name);
            int quantity = _stock[index].getQuantity();
            // if quantity is 2 or more we can reduce it by 1. But if it is 1, when reduced the item will be removed.
            if(quantity>1)
                _stock[index].setQuantity(quantity-1);
            else
            {
                _stock[index] = null; // nullify the item if the last one has been removed.
                _noOfItems--;
                updateArray(originalNumberOfItems);
            }
        }
    }

    /**
     * Returns an integer representing the temperature at which all products can be stored.
     * Otherwise, return Integer.MAX_VALUE;
     * @return
     */
    public int getTempOfStock()
    {
        int loMax = Integer.MAX_VALUE;
        int hiMin = -Integer.MAX_VALUE;
        // find highest minimum and lowest maximum temperature of all items
        for(int i = 0; i < _noOfItems; i++)
        {
            loMax = Math.min(loMax, _stock[i].getMaxTemperature());
            hiMin = Math.max(hiMin, _stock[i].getMinTemperature());
        }

        // If the lowest max temperature is above or equals the highest min temperature then there is a possibility to store all products
        if( loMax >= hiMin)
            return hiMin;

        return Integer.MAX_VALUE;
    }

    /**
     * will clear the nulls in the array and move items to the lest to close the gaps, and update the number of items.
     */
    private void updateArray(int originalNumberOfItems)
    {
        int readIndex = 0, writeIndex = 0;
        while( readIndex < originalNumberOfItems )
        {
            if(_stock[readIndex]!=null)
            {
                // if readIndex and writeIndex are equal it means we haven't found a null yet. So we do nothing.
                // but if they are different, we will write the value at readIndex to writeIndex, and then nullify the cell at readIndex - so the item will no longer be there.
                if(readIndex > writeIndex)
                {
                    _stock[writeIndex] = _stock[readIndex];
                    _stock[readIndex] = null;
                }
                writeIndex++;
            }
            readIndex++;
        }
    }

    /**
     * Returns total quantity of given item - i.e. if the same item exists in a different cell, their quantities will be summed
     * @param item
     * @return
     */
    private int getTotalQuantity(FoodItem item)
    {
        int total = 0;
        for(int i=0; i < _noOfItems ; i++)
        {
            if(item.getName().equals(_stock[i].getName()) && item.getCatalogueNumber()==_stock[i].getCatalogueNumber())
            {
                total+=_stock[i].getQuantity();
            }
        }
        return total;
    }

    /**
     * Finds the index a new food item should have in the current array
     * With binary search we will find the index of the item that should be immediately before the current item
     * @param food
     * @return
     */
    private int findIndexForNewItem(FoodItem food)
    {
        int i = 0;
        while(i < _noOfItems && _stock[i].getCatalogueNumber() < food.getCatalogueNumber() )
        {
            i++;
        }
        return i;
    }

    /**
     * Shifts all array values to the right, starting at the given index
     * We are going from the end towards the beginning, so we do not overwrite values.
     * @param index
     */
    private void shiftArrayValuesFromIndex(int index)
    {
        for(int i = _noOfItems - 1; i >= index ; i--)
        {
            _stock[i+1] = _stock[i];
        }
    }

    /**
     * Returns the index of the existing given food item, and -1 if it is not in stock
     * This method does not use the FoodItem.equals() method, as the only relevant properties here are the name, catalogueNumber and manifacture and expiration dates
     * @param food
     * @return
     */
    private int findFirstExistingItemIndex(FoodItem food)
    {
        for(int i = 0; i < _noOfItems; i++)
        {
            if (food.getName().equals(_stock[i].getName()) &&
                    food.getCatalogueNumber() == (_stock[i].getCatalogueNumber()) &&
                    food.getProductionDate().equals(_stock[i].getProductionDate()) &&
                    food.getExpiryDate().equals(_stock[i].getExpiryDate()))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the first existing food item, with the same name and cat number as the given item and -1 if
     * it is not in stock.
     * @param food
     * @return
     */
    private int findExistingNameAndCatNumButNotIdenticalDates(FoodItem food)
    {
        for(int i = 0; i < _noOfItems; i++)
        {
            if (food.getName().equals(_stock[i].getName()) &&
                    food.getCatalogueNumber() == (_stock[i].getCatalogueNumber()) &&
                    (!food.getProductionDate().equals(_stock[i].getProductionDate()) ||
                    !food.getExpiryDate().equals(_stock[i].getExpiryDate())))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the location of item that should follow the given item based on catalogue number
     * it is not in stock.
     * @param food
     * @return
     */
    private int findNewItemIndex(FoodItem food)
    {
        // in case the array is empty we should return 0. This should not happen, as we test it in the calling
        // method, but it is a safeguard.
        if (_noOfItems == 0)
            return 0;

        // check what is the first location of an item with a higher cat number
        int i = 0;
        while(i < _noOfItems && _stock[i].getCatalogueNumber() <= food.getCatalogueNumber()) {
            i++;
        }
        return i;
    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < _noOfItems; i++) {
            s += _stock[i] + "\n";
        }
        return s;
    }


    /**
     * Returns the index of a food item with the given name, and -1 if it is not in stock
     * @param name
     * @return
     */
    private int findItemIndexByName(String name)
    {
        for(int i = 0; i < _noOfItems; i++)
        {
            if (name.equals(_stock[i].getName()))
                return i;
        }
        return -1;
    }

    public int getNumOfItems()
    {
        return this._noOfItems;
    }
}
