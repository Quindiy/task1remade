package utils;

import geometry.*;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private static double E = 0.001;
    public static Point intersection(Segment s1, Segment s2){
        EquationLine l1 = new EquationLine(s1);
        EquationLine l2 = new EquationLine(s2);

        System.out.println("s1 = ");
        System.out.println(l1);

        System.out.println("s2 = ");
        System.out.println(l2);
        double y = (l2.getA()*l1.getC()/ l1.getA() - l2.getC())/(l2.getB()-l1.getB()* l2.getA()/ l1.getA());
        double x = -l1.getC()/ l1.getA() - l1.getB()*y/ l1.getA();
        Point p = new Point(x, y);
        if(isLiesOnLine(p, s1) && isLiesOnLine(p, s2))
            return p;
        return null;
    }

    public static boolean isLiesOnLine(Point p, Segment s){

        return isLiesOnLine(p, new EquationLine(s)) &&

                0 <= (p.getX() - s.getP1().getX())*(s.getP2().getX() - p.getX()) &&
                (p.getX() - s.getP1().getX())*(s.getP2().getX() - p.getX()) <= (s.getP1().getX() - s.getP2().getX())*(s.getP1().getX() - s.getP2().getX()) &&

                0 <= (p.getY() - s.getP1().getY())*(s.getP2().getY() - p.getY()) &&
                (p.getY() - s.getP1().getY())*(s.getP2().getY() - p.getY())  <= (s.getP1().getY()-s.getP2().getY())*(s.getP1().getY()-s.getP2().getY());

    }
    public static boolean isLiesOnLine(Point p, EquationLine e){
        return p.getX() * e.getA() + p.getY() * e.getB() + e.getC() <= E;
    }

    public static boolean isInsideFigure(Point p, Polygon polygon){
        List<Point> points = polygon.getPoints();
        boolean result = false;
        int j = polygon.getPoints().size() - 1;
        for (int i = 0; i < polygon.getPoints().size(); i++) {
            if ((points.get(i).getY() > p.getY()) != (points.get(j).getY() > p.getY()) &&
                    (p.getX() < points.get(i).getX() + (points.get(j).getX() - points.get(i).getX()) *
                            (p.getY() - points.get(i).getY()) / (points.get(j).getY() - points.get(i).getY()))) {
                result = !result;
            }
            j = i;
        }
        return result;
    }

    public static boolean isInsideFigure(Segment g, Polygon polygon){
        for (Point p: g.getPoints()) {
            if(!isInsideFigure(p, polygon))
                return false;
        }
        return true;
    }

    public static boolean isInsideFigure(Polygon inside, Polygon outside){//ѕроверка лежит ли лева€ фигура(inside) внутри правой(outside)
        for (Point p: inside.getPoints()) {
            if(!isInsideFigure(p, outside))
                return false;
        }
        return true;
    }

    public static Polygon intersection(Polygon p1, Polygon p2){
        List<Point> points1 = pointsInsidePolygon(p1, p2);
        List<Point> points2 = pointsInsidePolygon(p2, p1);
        List<Point> res = new ArrayList<>();

        if (points1.size() > points2.size()) {
            for (Point p : points1) {
                res.add(p);
            }
            res.addAll(getAllIntersection(p1, p2));
        } else if (points1.size() < points2.size()) {
            for (Point p : points2) {
                res.add(p);
            }
            res.addAll(getAllIntersection(p1, p2));
        }else {
            for (Point p : points1) {
                res.add(p);
            }
            List<Point> inters = getAllIntersection(p1, p2);
            res.add(inters.get(0));
            for (Point p : points2) {
                res.add(p);
            }
            res.add(inters.get(1));
        }
        return new Polygon(res);
    }

    public static Polygon unification(Polygon p1, Polygon p2){
        List<Point> points1 = pointsInsidePolygon(p1, p2);
        List<Point> points2 = pointsInsidePolygon(p2, p1);

        List<Point> res = new ArrayList<>();

        res.addAll(getNotContainsPoints(points1, p1));
        res.addAll(getNotContainsPoints(points2, p2));

        return new Polygon(res);
    }

    public static Polygon subtraction(Polygon p, Polygon deductible){
        List<Point> points = new ArrayList<>();
        points.addAll(getAllOccurrences(p, deductible, false));
        List<Point> inters = getAllIntersection(p, deductible);
        List<Point> occurence = getAllOccurrences(deductible, p, true);
        points.add(inters.get(0));
        points.addAll(occurence);
        points.add(inters.get(1));
        return  new Polygon(points);
    }
    private static List<Point> getNotContainsPoints(List<Point> inters, Polygon polygon){
        List<Point> res = new ArrayList<>();
        for (Point p:polygon.getPoints()) {
            if(!inters.contains(p))
                res.add(p);
        }
        return res;
    }

    private static List<Point> getAllOccurrences(Polygon p1, Polygon p2, boolean flag){
        List<Point> res = new ArrayList<>();
        for (Point p: p1.getPoints()) {
            if(isInsideFigure(p, p2) == flag)
                res.add(p);
        }
        return res;
    }
    private static List<Point> getAllIntersection(Polygon p1, Polygon p2){
        List<Segment> seg1 = p1.getSegments();
        List<Segment> seg2 = p1.getSegments();

        List<Point> inters = new ArrayList<>();

        for (Segment s1:seg1) {
            for (Segment s2:seg2) {
                Point i = intersection(s1, s2);
                if(i != null) {
                    inters.add(i);
                    continue;
                }
            }
        }
        return inters;
    }

    public static List<Point> pointsInsidePolygon(Polygon p1, Polygon p2){
        List<Point> l = new ArrayList<>();
        for (Point p:p1.getPoints()) {
            if(isInsideFigure(p, p2))
                l.add(p);
        }
        return l;
    }



}
