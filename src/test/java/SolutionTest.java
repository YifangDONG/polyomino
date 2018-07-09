import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

/**
 * Created by yifang on 5/9/2018.
 */

public class SolutionTest {

    Solution solution = new SecondSolution();
    Function<Integer, Integer> calculateWithElapsedTime = level -> {
        long startTime = System.nanoTime();
        int result = solution.calculate(level);
        long endTime = System.nanoTime();
        System.out.println(String.format("execute time: %f ms", (float) (endTime - startTime) / 1_000_000));
        return result;
    };

    @Test
    public void level1() throws Exception {
        assertEquals(1, calculateWithElapsedTime.apply(1).intValue());
    }

    @Test
    public void level2() throws Exception {
        assertEquals(1, calculateWithElapsedTime.apply(2).intValue());
    }

    @Test
    public void level3() throws Exception {
        assertEquals(2, calculateWithElapsedTime.apply(3).intValue());
    }

    @Test
    public void level4() throws Exception {
        assertEquals(5, calculateWithElapsedTime.apply(4).intValue());
    }

    @Test
    public void level5() throws Exception {
        assertEquals(12, calculateWithElapsedTime.apply(5).intValue());
    }

    @Test
    public void level6() throws Exception {
        assertEquals(35, calculateWithElapsedTime.apply(6).intValue());
    }

    @Test
    public void level7() throws Exception {
        assertEquals(108, calculateWithElapsedTime.apply(7).intValue());
    }

    @Test
    public void level8() throws Exception {
        assertEquals(369, calculateWithElapsedTime.apply(8).intValue());
    }

    @Test
    public void level9() throws Exception {
        assertEquals(1285, calculateWithElapsedTime.apply(9).intValue());
    }

    @Test
    public void level10() throws Exception {
        assertEquals(4655, calculateWithElapsedTime.apply(10).intValue());
    }

    @Test
    public void level11() throws Exception {
        assertEquals(17073, calculateWithElapsedTime.apply(11).intValue());
    }

    @Test
    public void level12() throws Exception {
        assertEquals(63600, calculateWithElapsedTime.apply(12).intValue());
    }
}
