import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yifang on 5/10/2018.
 */
public class SecondSolution implements Solution {

    private int level;

    public SecondSolution(int level) {
        this.level = level;
    }

    @Override
    public int calculate() {
        if (level == 1) {
            return 1;
        } else {
            List<Polyomino> fatherPalominos = Arrays.asList(Polyomino.initPolyomino());
            for (int i = 1; i < level; i++) {
                List<Polyomino> newPolyominos = new ArrayList<>();
                for (int j = 0; j < fatherPalominos.size(); j++) {
                    List<Position> positions = fatherPalominos.get(j).getPossiblePositions();
                    List<Polyomino> subPolyominos = new ArrayList<>();
                    for (Position p : positions) {
                        Polyomino newPolyomino = fatherPalominos.get(j).addOneSquare(p);
                        boolean isNew = true;
                        boolean isNeed = true;
                        for(int k = 0; k < j;k++) {
                            if(newPolyomino.hasSubset(fatherPalominos.get(k))){
                                isNeed = false;
                            }
                        }
                        if(!isNeed) {
                            break;
                        }
                        for (Polyomino existPolyomino : subPolyominos) {
                            if (newPolyomino.equalTo(existPolyomino)) {
                                isNew = false;
                                break;
                            }
                        }
                        if (isNew) {
                            subPolyominos.add(newPolyomino);
                        }
                    }
                    for (Polyomino newPolyomino : subPolyominos) {
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
                fatherPalominos = newPolyominos;
            }
            return fatherPalominos.size();
        }
    }
}
