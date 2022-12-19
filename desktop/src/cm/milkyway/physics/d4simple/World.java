package cm.milkyway.physics.d4simple;

import cm.milkyway.lang.container.List;
import cm.milkyway.physics.shapes.Rect;

public class World
{

    List<Rect> bounds = new List<>();

    public List<Rect> bounds()
    {
        return bounds;
    }

    public boolean isPointInSolid(double x, double y)
    {
        for(int i = 0; i < bounds.size(); i++) {
            if(bounds.get(i).contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public void dim(double x, double y, double w, double h)
    {
        bounds.add(Rect.offset(x, y, w, h));
    }

    public void vet(double x, double y, double x2, double y2)
    {
        dim(x, y, x2 - x, y2 - y);
    }

}
