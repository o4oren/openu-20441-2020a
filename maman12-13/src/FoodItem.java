/**
 * This class represents a ood item in the store.
 */
public class FoodItem
{
    private final String _name;
    private final long _catalogueNumber;
    private int _quantity, _price;
    private final int _minTemperature, _maxTemperature;
    private Date _productionDate, _expiryDate;

    private static final long MIN_CATALOGUE_NUMBER = 1;
    private static final int MIN_QUANTITY = 0;
    private static final int MIN_PRICE = 1;


    /**
     * Constructor for the food item accepts these parameters:
     * I included validations for minimum quantity, catalogue number and price though it was not requested.
     * Assuming that if a lower value is passed, the minimum value will be used.
     * If the parameters are legal this will not be an issue.
     * @param name            - product name
     * @param catalogueNumber -  Catalogue number. Must be a 3 digit number. If a negative number or 0 is passed, 1 will be set (as this is not defined in the instructions)
     * @param quantity        - he quantity of items of this type in the inventory. Non negative, so if a negative number is passed, 0 will be set.
     * @param minTemperature  - Minimum temperature for this item.
     * @param maxTemperature  - maximum temperature for this item.
     * @param price           - Item's price - positive number. If 0 or a negative int is passed, it will be treated as 1.
     * @param productionDate  - Production date
     * @param expiryDate      - Expiry date
     */
    public FoodItem(String name, long catalogueNumber, int quantity, Date productionDate, Date expiryDate, int minTemperature, int maxTemperature, int price)
    {
        this._name = name;
        this._catalogueNumber = ( catalogueNumber > 999 && catalogueNumber < 10000 ) ? catalogueNumber : 9999;
        this._quantity = Math.max(quantity, MIN_QUANTITY);
        this._minTemperature = Math.min(minTemperature, maxTemperature);
        this._maxTemperature = Math.max(minTemperature, maxTemperature);
        this._price = Math.max(price, MIN_PRICE);
        this._productionDate = productionDate;
        this._expiryDate = expiryDate.before(productionDate) ? productionDate.tomorrow() : expiryDate;
    }

    /**
     * A custructor that accpets a FoodItem object and copies it.
     *
     * @param other FoodItem
     */
    public FoodItem(FoodItem other)
    {
        this(other.getName(), other.getCatalogueNumber(), other.getQuantity(),  other.getProductionDate(), other.getExpiryDate(), other.getMinTemperature(),
                other.getMaxTemperature(), other.getPrice());
    }

    /**
     * Checks if the item is older than the passed item
     * @param other
     * @return true if the itme is older than the recieved item
     */
    public boolean olderFoodItem(FoodItem other)
    {
        if(other.getProductionDate().after(_productionDate))
            return true;
        return false;
    }

    /**
     * Accepts a sum of money and returns how many items can be purchased with that some
     * @return number of items
     */
    public int howManyItems(int amount)
    {
        return Math.min(amount / (_price), _quantity);
    }

    /**
     * Checks if this item will be fresh in a given date
     * @param d
     * @return true if date is between the production and expiry dates, inclusive.
     */
    public boolean isFresh(Date d)
    {
        if(!_productionDate.after(d) && !_expiryDate.before(d))
            return true;
        return false;
    }

    public boolean isCheaper(FoodItem other)
    {
        return _price < other.getPrice();
    }

    public boolean equals(FoodItem other)
    {
        if (other._name.equals(_name) && other._catalogueNumber == _catalogueNumber && other._minTemperature == _minTemperature && other._maxTemperature == _maxTemperature && other._price == _price && other._productionDate.equals(_productionDate) && other._expiryDate.equals(_expiryDate))
            return true;
        return false;
    }

    public String toString()
    {
        return ("FoodItem: " + _name
                + "\tCatalogueNumber: " + _catalogueNumber
                + "\tProductionDate: " + _productionDate
                + "\tExpiryDate: " + _expiryDate
                + "\tQuantity: " + _quantity
        );
    }


    // accessors and modifiers
    public String getName()
    {
        return _name;
    }

    public long getCatalogueNumber()
    {
        return _catalogueNumber;
    }

    public int getQuantity()
    {
        return _quantity;
    }

    public void setQuantity(int n)
    {
        if (n >= MIN_QUANTITY)
            _quantity = n;
    }

    public int getMinTemperature()
    {
        return _minTemperature;
    }

    public int getMaxTemperature()
    {
        return _maxTemperature;
    }

    public int getPrice()
    {
        return _price;
    }

    public void setPrice(int n)
    {
        if (n >= MIN_PRICE)
            this._price = n;
    }

    public Date getProductionDate()
    {
        return _productionDate;
    }

    public void setProductionDate(Date d)
    {
        // production date must be before expiry date
        if(d.before(_expiryDate))
            _productionDate = d;

    }

    public Date getExpiryDate()
    {
        return _expiryDate;
    }

    public void setExpiryDate(Date d)
    {
        // If given date is before the current production date, we will keep the current date - alternatively we can set to the dat after - but this was not the instruction.
        if(!d.before(_productionDate))
            _expiryDate =  d;
    }
}
