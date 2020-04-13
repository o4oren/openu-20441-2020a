/**
 * This class represents a mathematics polynomial and exposes some of the mathemtic operations you can perform on a
 * polynom.
 */
public class Polynom
{
    PolyNode _head;

    /**
     * Creates an empty Polynom object
     */
    public Polynom()
    {
        _head = null;
    }

    /**
     * Creates a new polynom object with the given PolyNode object at its head.
     * @param p
     */
    public Polynom (PolyNode p)
    {
        _head = p;
    }

    /**
     * Accepts a PolyNode object and adds it to the polynom in the correct place.
     * Explanation is inline,
     * Time complexity is O(n). We traverse the list n times (potentially) to find the right place to insert P.
     * Space complexity is also O(n) - although we potentially create n polynoms with multiple nodes,
     * We do not create more nodes on the stack.
     * @param p
     * @return this Polynom.
     */
    public Polynom addNode (PolyNode p)
    {
        // If the polynom is empty, p will be in it's head
        if (_head == null)
            _head = p;

        // If p's power is greater than _head's p will come as the first part of this polynom.
        else if(p.getPower() > _head.getPower())
        {
            p.setNext(_head);
            _head = p;
        }

        // If p's power is equals to _head's p's coefficiant will be added to _head's.
        else if(p.getPower() == _head.getPower())
        {
            _head.setCoefficient(_head.getCoefficient() + p.getCoefficient());
        }

        // If p's power is smaller than _head's it will come after the head.
        // Either at the end, added to the next node if their powers are equal or before the next node that has a smaller power
        else if(p.getPower() < _head.getPower())
        {
            PolyNode current = _head;
            while (current != null)
            {
                // If we're at the end of the polynom, add p at the end and return
                if (current.getNext() == null)
                {
                    current.setNext(p);
                    return this;
                }

                //otherwise, if the power is higher, but smaller than next
                if (p.getPower() > current.getNext().getPower())
                {
                    // place it here
                    p.setNext(current.getNext());
                    current.setNext(p);
                    return this;
                }
                else if (p.getPower() == current.getPower())
                    // sum the coefficients
                    current.setCoefficient(current.getCoefficient() + p.getCoefficient());
                else
                    // keep traversing
                    current = current.getNext();
            }
        }
        return this;
    }

    /**
     * A method that multiplies the polynom by a scalar and returns it.
     * @param num
     * @return Polynom
     * Time complexity is O(n) as it traverses all the PolyNodes once.
     * Space complexity O(1) as it creates its variables once, and reuses them. At no point do we hold
     * more than one num and nextNode;
     */
    public Polynom multByScalar (int num)
    {
        // If an empty polynom just return it
        if (_head == null)
            return this;

        PolyNode currentPolynode = _head;
        currentPolynode.setCoefficient(currentPolynode.getCoefficient() * num);

        if (currentPolynode.getNext() != null) {
            while (currentPolynode.getNext() != null) {
                currentPolynode.getNext().setCoefficient(currentPolynode.getNext().getCoefficient() * num);
                currentPolynode = currentPolynode.getNext();
            }
        }

        return this;
    }

    /**
     * Accepts a Polynom object and returns the sum of the two Polynoms
     * @param other Polynom
     * @return Polynom
     * Time complexity is O(n) because we traverse the two polynoms once.
     * Had I used the addNode method it would have been O(n^2) as we would have potentially traversed this N times for
     * each node in other.
     *
     * Space complexity is O(1). I create temp Polynode objects, but never keep them around.
     */
    public Polynom addPol (Polynom other)
    {
        // if the other polynom is empty, we return this
        if (other.getHead() == null)
            return this;

        // otherwise, if this._head is null, we set other's head here
        if (getHead() == null)
        {
            _head = other._head;
            return this;
        }

        // we'll use current node to traverse this polynom, and otherNode to traverse other.
        // Initially, set them to the first nodes of each polynom.
        PolyNode currentNode = _head;
        PolyNode otherNode = other.getHead();

        // now we traverse this polynom, and check where to place the next node of other for each node
        while (otherNode != null )
        {
            if (otherNode.getPower() > currentNode.getPower())
            {
                // We know that other is the next node and we need to place it before currentNode.
                // We'll copy it so we don't break the links of the other polynom and place it
                PolyNode newOtherNode = new PolyNode(otherNode);

                // If we are still in the head of current, we need to set the new node as _head
                if (currentNode == _head)
                    _head = newOtherNode;

                newOtherNode.setNext(currentNode);
            }
            else if (otherNode.getPower() == currentNode.getPower())
            {
                // If the powers are equal, we need to sum the coefficients
                currentNode.setCoefficient(currentNode.getCoefficient() + otherNode.getCoefficient());
            }
            else if (otherNode.getPower() < currentNode.getPower())
            {
                // If we are at the last node of this, we can add the rest of other here and return
                if (currentNode.getNext() == null)
                {
                    currentNode.setNext(otherNode);
                    return this;
                }

                // otherwise, if otherNode's power is less than currentNode's, we need to find where to place it after currentNode
                // we'll set a flang to indicate if we have placed otherNode yet
                boolean isOtherNodePlaced = false;
                while (currentNode.getNext() != null)
                {
                    if (!isOtherNodePlaced && otherNode.getPower() > currentNode.getPower())
                    {
                        // we place a copy here
                        PolyNode tempOther = otherNode;
                        tempOther.setNext(currentNode.getNext());
                        currentNode.setNext(tempOther);
                        isOtherNodePlaced = true;
                    }
                    else if (!isOtherNodePlaced && otherNode.getPower() == currentNode.getPower())
                    {
                        // we add otherNode to the coefficient here
                        currentNode.setCoefficient(currentNode.getCoefficient() + otherNode.getCoefficient());
                        isOtherNodePlaced = true;
                    }

                    // keep traversing...
                    currentNode = currentNode.getNext();
                }

                // if we reached the end of this polynom and haven't placed otherNode, we now place it here and
                // can return
                if (currentNode.getNext() == null && !isOtherNodePlaced)
                {
                    currentNode.setNext(otherNode);
                    return this;
                }
            }

            // next node in other
            otherNode = otherNode.getNext();
        }

        return this;
    }

    /**
     * Multiplies this polynom by other
     * Basically we're summing the multiplication of each node in this by all the nodes in other,
     * which is similar to multiplication by scalar - but with adding the power of the node to each node in other.
     * The result is a new polynom that in turn should be added to the other multiplications.
     * I will use a helper method to calculate each node in this polynom by other.
     * @param other Polynom
     * @return this
     * Time complexity is O(n^2) as we call multPolByNode n times, and its own complexity is O(n) as
     * it traverses all nodes in other.
     * Space complexity is O(n) as we keep Polynom sun in memory throughout the call, and it grows proportionally to
     * the number of nodes.
     */
    public Polynom multPol (Polynom other)
    {
        // if other is null we will return an empty polynom as multiplying by nothing is not defined;
        if (other.getHead() == null)
        {
            this._head = null;
            return this;
        }

        // We will use multPolByNode to get a result polynom of multiplying other by the current node
        // and add it to a sumPolynom.
        Polynom sum = new Polynom();
        PolyNode current = _head;

        while (current != null)
        {
            Polynom add = multPolByNode(other, current);
            sum.addPol(add);
            current = current.getNext();
        }
        this.setHead(sum.getHead());
        return this;
    }

    // private method that returns a multiplication of a polynom by a single pylonode
    private Polynom multPolByNode(Polynom other, PolyNode n)
    {
        // for each node in the result polynom, we will multiply the coefficient with n's, and add n's power to the
        // node's and add a new node to the result polynom;
        Polynom resultPolynom = new Polynom();
        PolyNode current = other.getHead();

        while (current != null)
        {
            double newCoefficient = n.getCoefficient() * current.getCoefficient();
            int newPower = n.getPower() + current.getPower();
            PolyNode resultPolyNode = new PolyNode(newPower, newCoefficient);
            resultPolynom.addNode(resultPolyNode);
            current = current.getNext();
        }
        return resultPolynom;
    }

    /**
     * Returns the polynom's differential
     * @return
     * Time complexity O(n) as we traverse all nodes.
     * Space complexity O(1)
     */
    public Polynom differential ()
    {
        PolyNode current = _head;
        while (current != null) {
            double newCoefficient = current.getCoefficient() * current.getPower();
            int newPower = current.getPower() - 1;
            current.setCoefficient(newCoefficient);
            current.setPower(newPower);

            // if the power of the next node is 0, we don't have that node in the differential and can exit
            if (current.getNext() != null && current.getNext().getPower() == 0)
                current.setNext(null);
            current = current.getNext();
        }
        return this;
    }


    /**
     * Returns the head object
     * @return
     */
    public PolyNode getHead()
    {
        return _head;
    }

    /**
     * Sets the head to the give PolyNode object
     * @param p
     */
    public void setHead(PolyNode p)
    {
        _head = p;
    }

    /**
     * Prints a string representation of the polynom.
     * @return String
     * Space complexity O(1) as it creates its variables once, and reuses them. At no point do we hold
     * more than one num and nextNode;
     */
    public String toString()
    {
        String s = "";
        if (_head == null)
            return s;

        s += _head;

        PolyNode nextNode = _head;
        while (nextNode.getNext() != null) {
            if (nextNode.getNext().getCoefficient() > 0)
                s += "+";
            s += nextNode.getNext();
            nextNode = nextNode.getNext();
        }
        return s;
    }
}
