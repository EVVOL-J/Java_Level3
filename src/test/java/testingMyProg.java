
import Lesson6.TestArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class testingMyProg {



    private int[] in;
    private int[] out;

    public testingMyProg(int[] in, int[] out) {
        this.in = in;
        this.out = out;
    }



    private TestArray testArray;


    @Before
    public void init() {
        testArray=new TestArray();
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 6, 5, 4, 2, 3, 3}, new int[]{2, 3, 3}},
                {new int[]{4, 3, 7, 5, 4, 4, 3, 2}, new int[]{3, 2}},
                {new int[]{4, 3, 7, 5, 4, 4, 3, 4}, null}
        });
    }

    @Test
    public void testAfterLast4() {
        Assert.assertArrayEquals(out, testArray.lastNumbersAfterFour(in));
    }


    @Test(expected = RuntimeException.class)
    public void testAfterLast4Ex() {
        testArray.lastNumbersAfterFour(new int[]{0, 3, 7, 5, 0, 0, 3, 2});
    }
}


