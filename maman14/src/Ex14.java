public class Ex14
{

    /**
     * Accepts a string and a character and returns how many substrings exist whithin the string
     * that start and end with the character, and containing the character exactly one time in between.
     * @param s - String input
     * @param c - Character input
     * @return an int representing the number of substrings within s start and end with the character c, containing exactly
     * one 'c' between them.
     *
     * Time complexity is O(n) - the method depends directly and linearly on the length of the String.
     * Space complexity is O(1) - only storing cCount and i always.
     */
    public static int subStrC(String s, char c) {
        // count how many times character c is contained in s;
        int cCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c)
                cCount++;
        }
        // If c appears less than 3 times in the string, there are no matching substrings.
        if ( cCount < 3 )
            return 0;
        else
            // there are exactly cCount - 2 compatible substirngs - total number, minus the edge chars.
            return cCount - 2;
    }

    /**
     * Accepts a String, a character and and int and returns how many substrings exist whithin the string
     * that start and end with the character, and containing the k times the character in between.
     * @param s - String input
     * @param c - Character input
     * @param k - The number of times the character is allowed between the end chars.
     * @return an int representing the number of substrings within s start and end with the character c, containing exactly
     * k times 'c' between them.
     *
     * Time complexity is O(n) - the method depends directly and linearly on the length of the String.
     * A loop runs on the length of the string 2 times, so it is O(2n).
     * Space complexity is O(1) - only storing cCount, substrCount, i and j always.
     */
    public static int subStrMaxC(String s, char c, int k)
    {
        // count how many times the character c is contained in s;
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c)
                counter++;
        }

        // First, return 0 if less than 2 c chars in the string, as there is no substring enclosed by it.
        if (counter < 2)
            return 0;

        // This is needed for the case we have exactly 2 occurences of c
        if (counter == 2)
            return 1;

        // In order to avoid running a loop within a loop with a complexity of O(n^2), I searched for a pattern.
        // Using the arbitrary string abacadaeafag, with 6 'a' characters inside I noticed that if k=0,
        // there are 5 possible substrings: aba, aca, ada, aea, afa.
        // With k=1, there are the previous 5, plus 4 more - abaca, acada, adaea, aeafa
        // with k=2, there will be the previous 9, plus 3 more (abacada...)
        // So the pattern is that the number of substrings is (n-1) + (n-2) +...+(n-(k+1))
        // The stop condition is n (number of 'c') minus (k+1). Or 0 (if the remaining number of cs are less than k.
        // Therefore another loop on the number of found chars can give the result in O(n).
        int substrCount = 0;
        for (int j = counter - 1; j >= Math.max(counter - (k + 1), 0); j--) {
            substrCount += j;
        }
        return substrCount;
    }

    /**
     * This method will replace an array's contents with the distance of each cell's value from the nearest 0
     * @param a - int array filled with 0's and 1's.
     * If the resulting array is filled with -MAX_INTEGER, it means it was composed of only 1's.
     * Time complexity is O(n) we traverse the array 2n times.
     * Space complexity is O(1) - we don't store anything but the counters.
     */
    public static void zeroDistance (int [] a) {

        // traverse the array from left to right, and set distance from zero on all cells once I find a zero.
        // I will set the first cell to MAX_INTEGER if it is not 0, until we meet the first 0 so that the distance will
        // be overwritten later when traversing from right to left.
        if(a[0] != 0)
            a[0] = Integer.MAX_VALUE;

        // traverse and set MAX_VALUE if we haven't met a zero yet. After wards we replace content with the distance
        // from the previous zero we encountered.
        for (int i = 1; i < a.length; i++) {
            if ( a[i] != 0 )
                a[i] = a[i-1] == Integer.MAX_VALUE ? Integer.MAX_VALUE : a[i-1] + 1;
        }

        // Now we traverse from right to left, starting from the second cell.
        // Once we meet a zero we replace values that are closer to the zero to the right with that distance.
        for (int j = a.length - 2; j >= 0; j--) {
            if ( a[j] != 0 )
                a[j] = Math.min(a[j+1] + 1, a[j]);
        }
    }


    /**
     * A method that accepts two strings, and returns true if t is a transformation of s.
     * transformation is defined as having the same characters as s and in the same order, but each char can appear
     * in t multiple times.
     * @param s - original string
     * @param t - candidate transformed string
     * @return - true if t is a transformation of s, false otherwise.
     */
    public static boolean isTrans (String s, String t) {

        // If t is shorter than s, return false as it cannot have as many chars as s
        if (t.length() < s.length())
            return false;

        // when both strings only have one char, return true if it's the same - this is a stop condition
        if (s.length() == 1 && t.length() == 1)
            return s.charAt(0) == t.charAt(0);

        // if our remaining strings don't start with the same char, we return false.
        // If they do, then if s is it its last char, we need to call the recursive method again but remove t's first
        // char.
        // same if s's next char is different than the current one, but t's next char is the same.
        // Once we exhausted that - i.e. both s's and t's next char is different, we move forward in both strings.
        if (t.charAt(0) == s.charAt(0)) {
            // if s is in its last char, advance only t
            // same if check if the next char in s is the same, but not in s
            if (s.length() == 1 || s.charAt(0) != s.charAt(1) && t.charAt(0) == t.charAt(1)) {
                return isTrans(s, t.substring(1));
            }
            else
                return isTrans(s.substring(1), t.substring(1));
        } else {
            return false;
        }
    }


    /**
     * A method that traverses a 2 dimensional array by moving to a cell based on the current cell's
     * value, and counting the number of paths that can lead from coordinates 0,0 to the last cell in the array.
     * @param mat - a 2 dimentional array
     * @return number of possible paths
     */
    public static int countPaths (int [][] mat) {
        return countPaths(mat, 0, 0);
    }

        /**
         * A method that traverses a 2 dimensional array by moving to a cell based on the current cell's
         * value, and counting the number of paths that can lead from passed coordinates to the last cell in the array.
         * @param mat - a 2 dimentional array
         * @param row - row coordinate
         * @param col - column coordinate
         * @return number of possible paths from the current coordinate to the last cell
         */
    public static int countPaths (int [][] mat, int row, int col) {
        int numberOfPaths = 0;

        // if the passed coordinates point to outside the array - there are no more paths
        if (row > mat.length - 1 || col > mat[0].length - 1)
            return 0;

        // get the digits
        int unitsDigit = mat[row][col] % 10;
        int tensDigit = mat[row][col] / 10;

        // if units and tens digits are both 0 there are no paths
        if (unitsDigit == 0 && tensDigit == 0)
            return 0;

        // if going in one of the possible steps lands in the last cell, there is a single path from this cell
        if (row + unitsDigit == mat.length - 1 && col + tensDigit == mat[0].length - 1)
            return 1;

        if (row + tensDigit == mat.length - 1 && col + unitsDigit == mat[0].length - 1)
            return 1;

        // edge case - if unit and tens digits are equal, there is only one possible way out
        if (unitsDigit == tensDigit)
            numberOfPaths += countPaths(mat, row + tensDigit, col + unitsDigit);
        else
            numberOfPaths += countPaths(mat, row + tensDigit, col + unitsDigit)
                    + countPaths(mat, row + unitsDigit, col + tensDigit);

        return numberOfPaths;
    }
}
