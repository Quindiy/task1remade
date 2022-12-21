package geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point implements Geometry{
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public List<Point> getPoints() {
        List<Point> l = new ArrayList<>();
        l.add(this);
        return l;
    }

    @Override
    public List<Point> getAllPoints() {
        return getPoints();
    }

    @Override
    public List<Segment> getSegments() throws Exception {
        throw new  Exception("not subborted function");

    }

    @Override
    public List<Segment> getAllSegments() throws Exception {
        throw new  Exception("not subborted function");

    }

    @Override
    public List<Polygon> getPolygons() throws Exception {
        throw new  Exception("not subborted function");
    }
}
