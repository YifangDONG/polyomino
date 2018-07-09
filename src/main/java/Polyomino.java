import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yifang on 5/8/2018.
 */
public class Polyomino implements Comparable {

    private List<Position> squares;
    private static final Comparator<Position> comparator = Comparator.comparing(Position::getX).thenComparing(Position::getY);

    public Polyomino(List<Position> squares) {
        this.squares = squares.stream().sorted(comparator).collect(Collectors.toList());
        Position dir = this.squares.get(0);
        this.squares.replaceAll(position -> translate(position, dir));
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
        return new Polyomino(squares);
    }

    public static List<Polyomino> getSymmetricPolyominos(Polyomino polyomino) {
        List<Polyomino> polyominos = new ArrayList<>();
        polyominos.add(polyomino);
        polyominos.add(polyomino.rotate(90));
        polyominos.add(polyomino.rotate(180));
        polyominos.add(polyomino.rotate(270));
        polyominos.add(polyomino.reflect());
        polyominos.add(polyomino.reflect().rotate(90));
        polyominos.add(polyomino.reflect().rotate(180));
        polyominos.add(polyomino.reflect().rotate(270));
        return polyominos;
    }

    public boolean equalTo(Polyomino polyomino) {
        return this.equals(polyomino) ||
                this.equals(polyomino.rotate(90)) ||
                this.equals(polyomino.rotate(180)) ||
                this.equals(polyomino.rotate(270)) ||
                this.equals(polyomino.reflect()) ||
                this.equals(polyomino.reflect().rotate(90)) ||
                this.equals(polyomino.reflect().rotate(180)) ||
                this.equals(polyomino.reflect().rotate(270));
    }

    public Polyomino rotate(int degree) {
        UnaryOperator<Position> rotate;
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
        return new Polyomino(this.getSquares().stream().map(rotate).collect(Collectors.toList()));
    }

    private static Position rotate(Position position, Position degree) {
        int x = position.getX() * degree.getX() - position.getY() * degree.getY();
        int y = position.getX() * degree.getY() + position.getY() * degree.getX();
        return new Position(x, y);
    }

    private static Position translate(Position position, Position dir) {
        return new Position(position.getX() - dir.getX(), position.getY() - dir.getY());
    }

    public Polyomino reflect() {
        return new Polyomino(this.getSquares().stream().map(Polyomino::reflect).collect(Collectors.toList()));
    }

    private static Position reflect(Position position) {
        return new Position(position.getX(), -position.getY());
    }

    public List<Position> getPossiblePositions() {
        return squares.stream()
                .flatMap(Polyomino::getTouchedPositions)
                .filter(e -> !squares.contains(e))
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

    public List<Position> getSquares() {
        return squares;
    }

    public boolean hasSubset(Polyomino polyomino) {
        return this.squares.contains(polyomino.getSquares());
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

    @Override
    public int compareTo(@NotNull Object o) {
        Polyomino polyomino = (Polyomino) o;
        if (squares.size() != polyomino.getSquares().size()) {
            return squares.size() > polyomino.getSquares().size() ? 1 : -1;
        } else {
            int i = 0;
            while (squares.get(i).compareTo(polyomino.getSquares().get(i)) == 0 && i < squares.size()-1) {
                i++;
            }
            return squares.get(i).compareTo(polyomino.getSquares().get(i));
        }
    }
}
