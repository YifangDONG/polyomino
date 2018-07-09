import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by yifang on 5/10/2018.
 */
public class SecondSolution implements Solution {

    private int level;

    public SecondSolution(int level) {
        this.level = level;
    }

    private List<Polyomino> constructNextLevel(Polyomino polyomino) {
        return polyomino.getPossiblePositions().stream()
                .parallel()
                .map(p ->
                {
                    Polyomino poly = new Polyomino(polyomino.addOneSquare(p).getSquares());
                    List<Polyomino> symmetricPolyominos = Polyomino.getSymmetricPolyominos(poly);
                    return symmetricPolyominos.stream().min(Polyomino::compareTo).get();
                })
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Polyomino> calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("calculate cannot be negative");
        }
        if (n == 0) {
            return new ArrayList<>();
        }
        if (n == 1) {
            return Arrays.asList(Polyomino.initPolyomino());
        }
        return calculate(n - 1).stream()
                .parallel()
                .flatMap(polyomino -> constructNextLevel(polyomino).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public int calculate() {
        return calculate(level).size();
    }
}
