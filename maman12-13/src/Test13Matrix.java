import org.junit.Assert;
import org.junit.Test;

public class Test13Matrix
{
    @Test
    public void testConstructors()
    {
        Matrix m = new Matrix(3,2);
        Assert.assertEquals("0\t0\n" +
                "0\t0\n" +
                "0\t0", m.toString());

        int[][] array = new int[4][5];
        array[0][0] = 1;
        array[1][1] = 2;
        array[2][2] = 3;
        array[3][3] = 4;
        array[3][4] = 5;

        Matrix n = new Matrix(array);
        Assert.assertEquals("1\t0\t0\t0\t0\n" +
                "0\t2\t0\t0\t0\n" +
                "0\t0\t3\t0\t0\n" +
                "0\t0\t0\t4\t5", n.toString());

//        System.out.println(n);
//        System.out.println(n.rotateCounterClockwise());
    }

    @Test
    public void testMakeNegative()
    {
        Matrix m = createMatrix();
        Matrix n = m.makeNegative();
        Assert.assertEquals("19\t124\t28\t35\t38\n" +
                "115\t22\t25\t230\t31\n" +
                "9\t21\t22\t249\t230\n" +
                "0\t6\t9\t232\t255\n" +
                "2\t5\t10\t116\t129", m.toString());
        Assert.assertEquals("236\t131\t227\t220\t217\n" +
                "140\t233\t230\t25\t224\n" +
                "246\t234\t233\t6\t25\n" +
                "255\t249\t246\t23\t0\n" +
                "253\t250\t245\t139\t126", n.toString());
    }

    @Test
    public void testImageFilterAverage()
    {
        Matrix m = createMatrix();
        Matrix n = m.imageFilterAverage();
        Assert.assertEquals("70\t55\t77\t64\t83\n" +
                "51\t42\t84\t98\t135\n" +
                "28\t25\t90\t142\t204\n" +
                "7\t9\t74\t139\t201\n" +
                "3\t5\t63\t125\t183", n.toString());

        int[][] arr = new int[1][1];
        arr[0][0] = 8;
        Matrix one = new Matrix(arr);
        Matrix oneAveraga = one.imageFilterAverage();
        Assert.assertEquals("8", one.toString());
        Assert.assertEquals("8", oneAveraga.toString());
    }

    @Test
    public void testRotateClockWise()
    {
        Matrix m = createMatrix();
        Matrix n = m.rotateClockwise();
        System.out.println(m + "\n");

        System.out.println(n);
        Assert.assertEquals("2\t0\t9\t115\t19\n" +
                "5\t6\t21\t22\t124\n" +
                "10\t9\t22\t25\t28\n" +
                "116\t232\t249\t230\t35\n" +
                "129\t255\t230\t31\t38", n.toString());

    }

    @Test
    public void testRotateCounterClockWise()
    {
        Matrix m = createMatrix();
        Matrix n = m.rotateCounterClockwise();
        System.out.println(m + "\n");

        System.out.println(n);
        Assert.assertEquals("38\t31\t230\t255\t129\n" +
                "35\t230\t249\t232\t116\n" +
                "28\t25\t22\t9\t10\n" +
                "124\t22\t21\t6\t5\n" +
                "19\t115\t9\t0\t2", n.toString());
    }

    private Matrix createMatrix()
    {
        int[][] array = new int[5][5];
        array[0][0] = 19;
        array[0][1] = 124;
        array[0][2] = 28;
        array[0][3] = 35;
        array[0][4] = 38;

        array[1][0] = 115;
        array[1][1] = 22;
        array[1][2] = 25;
        array[1][3] = 230;
        array[1][4] = 31;

        array[2][0] = 9;
        array[2][1] = 21;
        array[2][2] = 22;
        array[2][3] = 249;
        array[2][4] = 230;

        array[3][0] = 0;
        array[3][1] = 6;
        array[3][2] = 9;
        array[3][3] = 232;
        array[3][4] = 255;

        array[4][0] = 2;
        array[4][1] = 5;
        array[4][2] = 10;
        array[4][3] = 116;
        array[4][4] = 129;

        Matrix m = new Matrix(array);
        return m;

    }
}
