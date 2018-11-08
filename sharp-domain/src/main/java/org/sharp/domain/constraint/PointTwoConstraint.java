package org.sharp.domain.constraint;

import jodd.vtor.ValidationConstraint;
import jodd.vtor.ValidationConstraintContext;
import org.sharp.domain.Point;


public class PointTwoConstraint implements ValidationConstraint<PointTwo> {

    private int x;
    private int y;

    //@Override
    public void configure(PointTwo annotation) {
        this.x = annotation.x();
        this.y = annotation.y();
    }

    //@Override
    public boolean isValid(ValidationConstraintContext vcc, Object value) {
        if(!(value instanceof Point)){
            return false;
        }
        Point point = (Point) value;
        if(point.getX()<0&&point.getX()>this.x&&point.getY()>0&&point.getY()<this.y){
           return true;
        }
        return false;
    }
}
