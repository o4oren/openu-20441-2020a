/**
 * This class represents a polynom node - a coefficient and power.
 * It contains a reference to the next node.
 * Altogether, a linked list of PolyNodes can represent a polynom.
 */
public class PolyNode
{
    private int _power;
    private double _coefficient;
    private PolyNode _next;

    /**
     * Constructor that accepts power and coefficient, and creates a PolyNode.
     * _next is initialuized to null;
     * Since power cannot be negative, a negative passed power will set both power and coefficient to 0.
     * @param power - power of the polynom node
     * @param coefficient - coefficient of the polynom node
     */
    public PolyNode (int power, double coefficient)
    {
        if(power < 0)
        {
            _power = 0;
            _coefficient = 0;
        }
        else
        {
            _power = power;
            _coefficient = coefficient;
        }
    }

    /**
     * Constructor that accepts power and coefficient, and creates a PolyNode.
     * _next is initialuized to null;
     * @param power - power of the polynom node
     * @param coefficient - coefficient of the polynom node
     * @param next - a reference to the next node in the polynom
     * Since power cannot be negative
     */
    public PolyNode(int power, double coefficient, PolyNode next)
    {
        if(power < 0)
        {
            _power = 0;
            _coefficient = 0;
        }
        else
        {
            _power = power;
            _coefficient = coefficient;
        }
        _next = next;
    }

    /**
     * A constructor that creates a new PolyNode object copied from the passed one.
     * @param p - a PolyNode object
     */
    public PolyNode(PolyNode p)
    {
        _power = p._power;
        _coefficient = p._coefficient;
        _next = p._next;
    }

    /**
     * @return int power of the polynode
     */
    public int getPower()
    {
        return _power;
    }

    /**
     * Sets the power of the PolyNode.
     * A negative value will be ignored
     * @param power - int power
     */
    public void setPower(int power)
    {
        if (power < 0)
            return;
        this._power = _power;
    }

    /**
     * Returns the coefficiant of the polynode
     * @return double coeeficient
     */
    public double getCoefficient()
    {
        return _coefficient;
    }

    /**
     * Sets the coeeficient
     * @param _coefficient
     */
    public void setCoefficient(double _coefficient)
    {
        this._coefficient = _coefficient;
    }

    /**
     * Get the next PolyNode
     * @return PolyNode
     */
    public PolyNode getNext()
    {
        return _next;
    }

    /**
     * Updates the next polynode in the polynom
     * @param _next
     */
    public void setNext(PolyNode _next)
    {
        this._next = _next;
    }

    /**
     * Returns a String representation of the node
     * @return
     */
    public String toString()
    {
        String s = "";

        // Return an empty string if coefficient is 0
        if (_coefficient == 0)
            return s;

        // Create the coefficient part of the string
        if (_coefficient == -1)
            s += "-";
        else if (_coefficient != 1)
            s += _coefficient;

        // Add the X variable as needed
        // If the power is 0, x is omitted, but if also the coefficient is 1 or -1 there is a special behavior.
        // If the power has an effect, we move on and handle that.
        if (_power == 0)
        {
            // if power is 0, we need to reintroduce the coefficient we did not include if it was 1 or -1
            // otherwise the stirng is what it should be.
            if ( Math.abs(_coefficient) == 1.0 )
                return s += Math.abs(_coefficient);
            else
                return s;
        }
        // If the power is a positive number, we add 'x'
        else
            s += 'x';

        // Add the power part of the string
        if (_power == 1)
            return s;

        s += "^" + _power;

        return s;
    }
}
