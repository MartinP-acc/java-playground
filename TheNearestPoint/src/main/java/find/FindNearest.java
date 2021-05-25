package find;

import points.SinglePoint;

import java.util.Set;

public class FindNearest {

    private SinglePoint nearestPoint;
    private static FindNearest instance = new FindNearest();

    public void findPoint(Set<SinglePoint> points, SinglePoint center){
        for (SinglePoint p : points){
            if (nearestPoint!=null){
                if (getC(p,center)<getC(nearestPoint,center)) nearestPoint=p;
            } else nearestPoint=p;
        }
    }

    public int getC(SinglePoint p, SinglePoint cp){
        int a= p.getX()-cp.getX();
        int b= p.getY()-cp.getY();
        return (a*a) + (b*b);
    }

    public SinglePoint getNearestPoint() {
        return nearestPoint;
    }

    public static FindNearest getInstance() {
        return instance;
    }
}
