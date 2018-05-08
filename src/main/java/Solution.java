import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by yifang on 5/8/2018.
 */
public class Solution {
    private int level;

    public Solution(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int calculate() {
        if (level == 1) {
            return 1;
        } else {
            List<Polyomino> polyominos = Arrays.asList(Polyomino.initPolyomino());
            for (int i = 1; i < level; i++) {
                List<Polyomino> newPolyominos = new ArrayList<>();
                for (int j = 0; j < polyominos.size(); j++) {
                    Polyomino polyomino = polyominos.get(j);
                    List<Position> positions = polyomino.getPossiblePositions();
                    for (Position p : positions) {
                        Polyomino newPolyomino = polyomino.addOneSquare(p);
                        boolean isNew = true;
                        for (Polyomino existPolyomino : newPolyominos) {
                            if (newPolyomino.equalTo(existPolyomino)) {
                                isNew = false;
                                break;
                            }
                        }
                        if (isNew) {
                            newPolyominos.add(newPolyomino);
                        }
                    }
                }
                polyominos = newPolyominos;
            }
            return polyominos.size();
        }
    }
}
