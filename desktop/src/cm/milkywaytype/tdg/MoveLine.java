package cm.milkywaytype.tdg;

import cm.milkywaygl.maths.VMaths;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.maths.check.Ray;
import cm.milkywaygl.util.container.List;

public class MoveLine
{

    List<Ray> rays = new List<>();

    public void add(Ray ray)
    {
        rays.add(ray);
    }

    public boolean isEndVec(Box4 check, int index)
    {
        return VMaths.degreeBetweenAB(rays.get(index).endPos(), check) != rays.get(index).direction().degree();
    }

}
