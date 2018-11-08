package org.sharp.domain;


import jodd.vtor.constraint.NotNull;
import org.sharp.domain.constraint.PointTwo;

public class Bean {

    private int a;
    private Integer objA;
    @NotNull(message = "不能为空",profiles = {"p1"})
    private String b;
    @NotNull(message = "point不能为空")
    @PointTwo(x=-10,y=10,severity = 6,message = "point 点只能在(-10,0),(0,10)区域")
    private Point point;

    private static int count;
    static {
        System.out.println("Bean static {}");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Integer getObjA() {
        return objA;
    }

    public void setObjA(Integer objA) {
        this.objA = objA;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
