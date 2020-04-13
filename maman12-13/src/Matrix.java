public class Matrix
{
    public static final int MAX_VALUE = 255;
    int _array[][];

    /**
     * constructs a Matrix from two dimensional array; The dimensions as well as the values of this Matrix will be the same
     * as the dimensions and values of the two- dimensional array;
     * @param array
     */
    public Matrix(int[][] array)
    {
        _array = copyArray(array);
    }

    /**
     * Constructs a size1 by size2 matrix of zeroes
     * @param size1
     * @param size2
     */
    public Matrix(int size1, int size2)
    {
        _array = new int[size1][size2];
    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < _array.length; i++) // for each row we will traverse the values
        {
            for(int j = 0; j < _array[0].length; j++)
            {
                if(j!=0)
                    s+= "\t";
                s +=  _array[i][j];
                if(j==(_array[0].length -1) && ! (i== _array.length - 1)) //add \n at the end of the line, but not on the last line
                    s += "\n";
            }
        }
        return s;
    }

    public Matrix makeNegative()
    {
        int[][] newArray = new int[_array.length][_array[0].length];
        for (int i = 0; i < _array.length; i++) // for each row we will traverse the values
        {
            for(int j = 0; j< _array[0].length; j++)
            {
                newArray[i][j] =  (MAX_VALUE - _array[i][j]);
            }
        }
        return new Matrix(newArray);
    }

    /**
     * Returns a new "smoothed" image, by replacing each cell's average with its neighbor cells.
     * @return new Matrix
     */
    public Matrix imageFilterAverage()
    {
        int[][] newArray = new int[_array.length][_array[0].length];
        for (int i = 0; i < _array.length; i++) // for each row we will traverse the values
        {
            for(int j = 0; j< _array[0].length; j++)
            {
                newArray[i][j] =  calculateAverageWithNeighbors(i, j);
            }
        }
        return new Matrix(newArray);
    }

    /**
     * Returns a new Matrix which is rotated clockwise.
     * @return new Matrix
     */
    public Matrix rotateClockwise()
    {
        int[][] newArray = new int[_array[0].length][_array.length];
        for (int i = 0; i < _array.length; i++)
        {
            for(int j = 0; j < _array[0].length; j++)
            {
                newArray[j][_array.length - 1 - i] = _array[i][j];
            }
        }
        return new Matrix(newArray);
    }

    /**
     * Returns a new Matrix which is rotated counter-clockwise.
     * @return new Matrix
     */
    public Matrix rotateCounterClockwise()
    {
        int[][] newArray = new int[_array[0].length][_array.length];
        for (int i = 0; i < _array.length; i++)
        {
            for(int j = 0; j < _array[0].length; j++)
            {
                newArray[_array[0].length - 1 - j][i] = _array[i][j];
            }
        }
        return new Matrix(newArray);
    }

    private int calculateAverageWithNeighbors(int i, int j)
    {
        int counter = 0;
        int sum = 0;
        /*
        * i,j are cell coordinates.
        * If the row index is 0, i.e on the left edge, we will start calculating neighbors at 0.
        * But it it is not extreme right, we will start at i-1, to include the left neighbor.
        * On the other hand, if the row index is the last, we will stop calculating neighbor at that index.
        * For each row we follow the same logic with the column indexes.
        * This way we only process neighbors inside the matrix.
        */
        for (int row = (i == 0 ? 0 : i - 1); row <= (i == (_array.length - 1) ? i : i + 1); row++  )
        {
            for (int column = (j == 0 ? 0 : j - 1); column <= (j == (_array[0].length - 1) ? j : j + 1); column++  )
            {
                sum += _array[row][column];
                counter++;
            }
        }
        return sum / counter;
    }

    private int[][] copyArray(int[][] array)
    {
        int[][] newArray = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) // for each row we will traverse the values
        {
            for(int j = 0; j< array[0].length; j++)
            {
                newArray[i][j] =  array[i][j];
            }
        }
        return newArray;
    }
}
