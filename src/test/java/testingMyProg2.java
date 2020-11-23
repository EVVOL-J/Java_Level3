import Lesson6.TestArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class testingMyProg2 {
    private TestArray testArray;

    private int[] in;
    private boolean out;

    public testingMyProg2(int[] in, boolean out) {
        this.in = in;
        this.out = out;
    }

    @Before
    public void init() {
        testArray=new TestArray();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 1, 4, 4, 4, 1, 4, 1}, true},
                {new int[]{8, 8, 7, 7, 7, 3, 5, 0}, false},
                {new int[]{1, 4, 8, 7}, true}
        });
    }

    @Test
    public void testAfterLast4() {
        Assert.assertEquals(testArray.isOneOrFourInArray(in),out);
    }
}
