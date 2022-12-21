package geometry;

import jdk.nashorn.internal.objects.annotations.Function;
import utils.Util;

import java.util.ArrayList;
import java.util.List;

public class GeometricGroup implements Geometry {
    private List<Geometry> objects = null;

    public GeometricGroup(List<Geometry> objects) {
        this.objects = new ArrayList<>();
        this.objects = objects;
    }
    public GeometricGroup(Geometry... object) {
        this.objects = new ArrayList<>();
        add(object);
    }
    public GeometricGroup() {
        this.objects = new ArrayList<>();
    }

    public void add(Geometry object){
        objects.add(object);
    }
    public void add(Geometry... object){

        for (Geometry o: object) {
            this.objects.add(o);
        }

    }
    public List<Geometry> getObjects() {
        return objects;
    }

    public void setObjects(List<Geometry> objects) {
        this.objects = objects;
    }

    @Override
    public List<Point> getPoints(){
        List<Point> l = new ArrayList<>();
        for (Geometry g:this.objects) {
            if(isItPoint(g))
                l.add((Point) g);
        }
        return l;
    }

    @Override
    public List<Point> getAllPoints(){
        List<Point> l = new ArrayList<>();
        for (Geometry g:this.objects) {
            if(isItPoint(g))
                l.addAll(((Point) g).getPoints());
            else if(isItPolygon(g)){
                l.addAll(((Polygon)g).getPoints());
            }else{
                l.addAll(((Segment)g).getPoints());
            }
        }
        return l;
    }
    @Override
    public List<Segment> getSegments(){
        List<Segment> l = new ArrayList<>();
        for (Geometry g:this.objects) {
            if(isItSegment(g))
                l.add((Segment) g);
        }
        return l;
    }

    @Override
    public List<Segment> getAllSegments(){
        List<Segment> l = new ArrayList<>();
        for (Geometry g:this.objects) {
            if(isItPolygon(g)){
                l.addAll(((Polygon)g).getSegments());
            }else if(isItSegment(g)){
                l.add((Segment)g);
            }
        }
        return l;
    }

    @Override
    public List<Polygon> getPolygons(){
        List<Polygon> l = new ArrayList<>();
        for (Geometry g:this.objects) {
            if(isItPolygon(g))
                l.add((Polygon) g);
        }
        return l;
    }

}
