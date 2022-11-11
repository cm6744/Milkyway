package cm.milkywaygl.maths.check;

public class Ray
{

    Vec2 direction = Vec2.normal();
    Box4 oriXy = Box4.normal();
    Box4 endXy = Box4.normal();

    public static Ray of(double sx, double sy, Vec2 vec2)
    {
        Ray ray = normal();
        ray.direction().copy(vec2);
        ray.oriPos().loc(sx, sy);
        return ray;
    }

    public static Ray normal()
    {
        return new Ray();
    }

    public Box4 endPos()
    {
        endXy.loc(direction.applyX(oriXy.x()), direction.applyY(oriXy.y()));
        return endXy;
    }

    public Vec2 direction()
    {
        return direction;
    }

    public Box4 oriPos()
    {
        return oriXy;
    }

}
