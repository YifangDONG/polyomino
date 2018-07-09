import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Created by yifang on 5/8/2018.
 */
public class Position implements Comparable {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(@NotNull Object o) {
        Position position = (Position) o;
        int compX = x > position.getX() ? 1 : x == position.getX() ? 0 : -1;
        int compY = y > position.getY() ? 1 : y == position.getY() ? 0 : -1;
        return compX != 0 ? compX : compY;
    }
}
