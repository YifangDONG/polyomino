import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

/**
 * Created by yifang on 5/9/2018.
 */

public class SolutionTest {

    Function<Solution, Integer> calculateWithElapsedTime = e -> {
        long startTime = System.nanoTime();
        int result = e.calculate();
        long endTime = System.nanoTime();
        System.out.println(String.format("execute time: %f ms", (float) (endTime - startTime) / 1_000_000));
        return result;
    };

    @Test
    public void level1() throws Exception {
        Solution basicSolution = new BasicSolution(1);
        assertEquals(1, calculateWithElapsedTime.apply(basicSolution).intValue());
        Solution secondSolution = new SecondSolution(1);
        assertEquals(1, calculateWithElapsedTime.apply(secondSolution).intValue());
    }

    @Test
    public void level2() throws Exception {
        Solution basicSolution = new BasicSolution(2);
        assertEquals(1, calculateWithElapsedTime.apply(basicSolution).intValue());
    }

    @Test
    public void level3() throws Exception {
        Solution basicSolution = new BasicSolution(3);
        assertEquals(2, calculateWithElapsedTime.apply(basicSolution).intValue());
    }

    @Test
    public void level4() throws Exception {
        Solution basicSolution = new BasicSolution(4);
        assertEquals(5, calculateWithElapsedTime.apply(basicSolution).intValue());
    }

    @Test
    public void level5() throws Exception {
        Solution basicSolution = new BasicSolution(5);
        assertEquals(12, calculateWithElapsedTime.apply(basicSolution).intValue());
    }

    @Test
    public void level6() throws Exception {
        Solution basicSolution = new BasicSolution(6);
        assertEquals(35, calculateWithElapsedTime.apply(basicSolution).intValue());
    }

    @Test
    public void level7() throws Exception {
        Solution basicSolution = new BasicSolution(7);
        assertEquals(108, calculateWithElapsedTime.apply(basicSolution).intValue());

    }

    @Test
    public void level8() throws Exception {
        Solution basicSolution = new BasicSolution(8);
        assertEquals(369, calculateWithElapsedTime.apply(basicSolution).intValue());
        Solution secondSolution = new SecondSolution(8);
        assertEquals(369, calculateWithElapsedTime.apply(secondSolution).intValue());

    }

    @Test
    public void level9() throws Exception {
        Solution secondSolution = new SecondSolution(9);
        assertEquals(1285, calculateWithElapsedTime.apply(secondSolution).intValue());
        Solution basicSolution = new BasicSolution(9);
        assertEquals(1285, calculateWithElapsedTime.apply(basicSolution).intValue());

    }

    @Test
    public void level10() throws Exception {
        Solution basicSolution = new BasicSolution(10);
        assertEquals(4655, calculateWithElapsedTime.apply(basicSolution).intValue());

    }

    @Test
    public void level11() throws Exception {
        Solution basicSolution = new BasicSolution(11);
        assertEquals(17073, calculateWithElapsedTime.apply(basicSolution).intValue());

    }

    @Test
    public void level12() throws Exception {
        Solution basicSolution = new BasicSolution(12);
        assertEquals(63600, calculateWithElapsedTime.apply(basicSolution).intValue());
    }
}
