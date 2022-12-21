import geometry.*;
import utils.Util;

public class Main {

    public static void main(String[] args) throws Exception {
        Point p = new Point(-1, -1);
        p.getSegments();
        EquationLine l = new EquationLine(1, 2, 3);
        System.out.println(l);
        Geometry g = new Point(1, 2);

        Polygon polygon = new Polygon(new Point(0, 0),
                                new Point(0, 10),
                                new Point(12, 10),
                                new Point(20, 0));
        Point p1 = new Point(0, 2);

        System.out.println(Util.isInsideFigure(p1, polygon)   );
    }
}
