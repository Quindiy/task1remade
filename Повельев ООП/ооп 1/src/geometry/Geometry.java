package geometry;

import java.util.List;

public interface Geometry {

    List<Point> getPoints();
    List<Point> getAllPoints();
    List<Segment> getSegments() throws Exception;
    List<Segment> getAllSegments() throws Exception;
    List<Polygon> getPolygons() throws Exception;

    default boolean isItPoint(Geometry g){
        return Point.class.equals(g.getClass());
    }
    default boolean isItPolygon(Geometry g){
        return Polygon.class.equals(g.getClass());
    }
    default boolean isItSegment(Geometry g){
        return Segment.class.equals(g.getClass());
    }

}
