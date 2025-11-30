package jaiz.bigfoot.entity.bigfoot;

import java.util.Arrays;
import java.util.Comparator;

public enum BigFootVariant {
    BIGGY(0),
    FOOTY(1),
    CHUNKY(2),
    MONK(3),
    BUZZLIGHTYEAR(4);

    private static final BigFootVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BigFootVariant::getId)).toArray(BigFootVariant[]::new);
    private final int id;

    BigFootVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BigFootVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
