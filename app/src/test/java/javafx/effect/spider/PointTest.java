package javafx.effect.spider;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    @Test
    public void updatePosition() {
        var point = new Point(0, 0, 1, 1, 10);
        for (int i = 0; i < 10000; ++i) {
            point.updatePosition(100, 100, 1);
        }
    }
}
