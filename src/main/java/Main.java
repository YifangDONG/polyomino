import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yifang on 5/8/2018.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage : java program integer");
            System.exit(0);
        }
        int level = Integer.valueOf(args[0]);
        if (level == 1) {
            System.out.println(1);
            System.exit(0);
        }
        List<Polyomino> polyominos = Arrays.asList(Polyomino.initPolyomino());
        for (int i = 1; i < level; i++) {
            List<Polyomino> newPolyominos = new ArrayList<>();
            for (int j = 0; j < polyominos.size(); j++) {
                Polyomino polyomino = polyominos.get(j);
                List<Position> positions = polyomino.getPossiblePositions();
                for (Position p : positions) {
                    Polyomino newPolymino = polyomino.addOneSquare(p);
                    boolean isNew = true;
                    for (Polyomino existPolyomino : newPolyominos) {
                        if (newPolymino.equalTo(existPolyomino)) {
                            isNew = false;
                            break;
                        }
                    }
                    if(isNew) {
                        newPolyominos.add(newPolymino);
                    }
                }
            }
            polyominos = newPolyominos;
        }
        System.out.println(polyominos.size());
    }
}
