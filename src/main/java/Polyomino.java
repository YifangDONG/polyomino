import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yifang on 5/8/2018.
 */
public class Polyomino {

    private List<Position> squares;
    private static final Comparator<Position> comparator = Comparator.comparing(Position::getX).thenComparing(Position::getY);

    public Polyomino(List<Position> squares) {
        this.squares = squares.stream().sorted(comparator).collect(Collectors.toList());
    }

    public static Polyomino initPolyomino() {
        return new Polyomino(Arrays.asList(new Position(0, 0)));
    }

    public Polyomino addOneSquare(final Position position) {
        if (!isTouched(position)) {
            throw new RuntimeException(String.format("position (%d,%d) can not be add to polyomino %s", position.getX(), position.getY(), this.toString()));
        }
        List<Position> squares = new ArrayList<>(this.squares);
        squares.add(position);
        squares.sort(comparator);
        return new Polyomino(squares);
    }

    public boolean equalTo(Polyomino polyomino) {
        return this.isTranslatedTo(polyomino) ||
                this.isTranslatedTo(polyomino.rotate(90)) ||
                this.isTranslatedTo(polyomino.rotate(180)) ||
                this.isTranslatedTo(polyomino.rotate(270)) ||
                this.isTranslatedTo(polyomino.reflect()) ||
                this.isTranslatedTo(polyomino.reflect().rotate(90)) ||
                this.isTranslatedTo(polyomino.reflect().rotate(180)) ||
                this.isTranslatedTo(polyomino.reflect().rotate(270));
    }

    public Polyomino rotate(int degree) {
        Function<Position, Position> rotate;
        switch (degree) {
            case 90: {
                rotate = e -> rotate(e, new Position(0, 1));
                break;
            }
            case 180: {
                rotate = e -> rotate(e, new Position(-1, 0));
                break;
            }
            case 270: {
                rotate = e -> rotate(e, new Position(0, -1));
                break;
            }
            default: {
                throw new RuntimeException(String.format("Can not rotate with degree: %d, accepted degree are 90, 180, 270", degree));
            }
        }
        return new Polyomino(this.getSquares().stream().map(rotate).sorted(comparator).collect(Collectors.toList()));
    }

    private static Position rotate(Position position, Position degree) {
        int x = position.getX() * degree.getX() - position.getY() * degree.getY();
        int y = position.getX() * degree.getY() + position.getY() * degree.getX();
        return new Position(x, y);
    }

    public Polyomino reflect() {
        return new Polyomino(this.getSquares().stream().map(Polyomino::reflect).sorted(comparator).collect(Collectors.toList()));
    }

    private static Position reflect(Position position) {
        return new Position(position.getX(), -position.getY());
    }

    public List<Position> getPossiblePositions() {
        return squares.stream()
                .flatMap(Polyomino::getTouchedPositions)
                .filter(e -> !isContained(e))
                .distinct()
                .collect(Collectors.toList());
    }

    private static Stream<Position> getTouchedPositions(Position center) {
        int x = center.getX();
        int y = center.getY();
        return Stream.of(
                new Position(x - 1, y),
                new Position(x + 1, y),
                new Position(x, y - 1),
                new Position(x, y + 1));
    }

    private boolean isTouched(Position position) {
        return squares.stream()
                .anyMatch(e -> Math.abs(e.getY() - position.getY()) + Math.abs(e.getX() - position.getX()) == 1);
    }

    private boolean isContained(Position position) {
        return squares.stream()
                .anyMatch(e -> e.getX() == position.getX() && e.getY() == position.getY());
    }

    public boolean isTranslatedTo(final Polyomino polyomino) {
        List<Position> targetSquares = polyomino.getSquares();
        int x = targetSquares.get(0).getX() - squares.get(0).getX();
        int y = targetSquares.get(0).getY() - squares.get(0).getY();
        Position dir = new Position(x, y);
        for (int i = 0; i < squares.size(); i++) {
            if (!isTranslatedTo(squares.get(i), targetSquares.get(i), dir)) {
                return false;
            }
        }
        return true;
    }

    private boolean isTranslatedTo(Position rsc, Position dst, Position dir) {
        return rsc.getX() + dir.getX() == dst.getX() && rsc.getY() + dir.getY() == dst.getY();
    }

    public List<Position> getSquares() {
        return squares;
    }

    public boolean hasSubset(Polyomino polyomino) {
        return this.squares.containsAll(polyomino.getSquares());
    }

    @Override
    public String toString() {
        return "Polyomino{" +
                "squares=" + squares +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polyomino polyomino = (Polyomino) o;
        return Objects.equals(squares, polyomino.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }
}
