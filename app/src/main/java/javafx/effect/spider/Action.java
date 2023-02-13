package javafx.effect.spider;

class Action {
    public static void checkCollision(Point lhs, Point rhs) {
        if (EMath.distance(lhs.getX(), lhs.getY(), rhs.getX(), rhs.getY()) <= lhs.getR() + rhs.getR()) {
            lhs.setDx(rhs.getDx());
            lhs.setDy(rhs.getDy());

            lhs.setX(lhs.getX() + lhs.getDx());
            lhs.setY(lhs.getY() + lhs.getDy());
        }
    }
}
