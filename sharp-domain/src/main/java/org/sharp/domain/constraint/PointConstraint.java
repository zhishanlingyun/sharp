package org.sharp.domain.constraint;

import jodd.vtor.ValidationConstraint;
import jodd.vtor.ValidationConstraintContext;
import org.sharp.domain.Point;

import java.lang.annotation.Annotation;

/**
 * 自定义对Point对象的约束
 * 一象限点的验证
 */
public class PointConstraint implements ValidationConstraint {

    //@Override
    public void configure(Annotation annotation) {

    }

    //@Override
    public boolean isValid(ValidationConstraintContext vcc, Object value) {
        if(!(value instanceof Point)){
            return false;
        }
        Point point = (Point) value;
        if(point.getX()>=0&&point.getY()>=0){
            return true;
        }
        return false;
    }
}
