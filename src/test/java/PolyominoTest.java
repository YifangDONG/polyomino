import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Created by yifang on 5/8/2018.
 */

public class PolyominoTest {

    private Polyomino polyomino;
    private Polyomino threePolyomino1;

    @Before
    public void setUp() throws Exception {
        polyomino = Polyomino.initPolyomino();
        List<Position> positions = Arrays.asList(new Position(0, -1), new Position(0, 0), new Position(1, 0));
        threePolyomino1 = new Polyomino(positions);
    }

    @Test
    public void addOneSquare() throws Exception {
        Position position = new Position(0, 1);
        System.out.println(polyomino);
        System.out.println(polyomino.addOneSquare(position));
        System.out.println(polyomino);
    }

    @Test
    public void rotate() throws Exception {
        System.out.println(threePolyomino1);
        System.out.println(threePolyomino1.rotate(90));
        System.out.println(threePolyomino1.rotate(180));
        System.out.println(threePolyomino1.rotate(270));
        assertTrue(threePolyomino1.equalTo(threePolyomino1.rotate(90)));
        assertTrue(threePolyomino1.equalTo(threePolyomino1.rotate(180)));
        assertTrue(threePolyomino1.equalTo(threePolyomino1.rotate(270)));
    }

    @Test
    public void reflect() throws Exception {
        System.out.println(threePolyomino1);
        System.out.println(threePolyomino1.reflect());
        assertTrue(threePolyomino1.equalTo(threePolyomino1.reflect()));
    }

    @Test
    public void getPossiblePositions() throws Exception {
        System.out.println(polyomino);
        System.out.println("possible positions: " + polyomino.getPossiblePositions());
        Position position = new Position(0, 1);
        System.out.println(polyomino.addOneSquare(position));
        System.out.println("possible positions: " + polyomino.addOneSquare(position).getPossiblePositions());
    }

}
