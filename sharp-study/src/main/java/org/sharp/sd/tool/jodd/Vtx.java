package org.sharp.sd.tool.jodd;

import com.alibaba.fastjson.JSON;
import jodd.vtor.Check;
import jodd.vtor.ValidationContext;
import jodd.vtor.Vtor;
import jodd.vtor.constraint.MaxConstraint;
import jodd.vtor.constraint.NotNullConstraint;
import org.sharp.domain.Bean;
import org.sharp.domain.Point;
import org.sharp.domain.constraint.PointConstraint;

public class Vtx {

    /**
     * 不使用注解的方式验证
     */
    public void check1(){
        Bean bean = new Bean();
        bean.setA(11);
        ValidationContext vct = new ValidationContext();
        Check check = new Check("a",new MaxConstraint(10));
        //check.setProfiles("create");
        Check nullcheck = new Check("point",new NotNullConstraint());
        nullcheck.setMessage("point 不能为空");
        Check ponitcheck = new Check("point",new PointConstraint());
        ponitcheck.setMessage("point 不在第一象限");
        vct.add(check);
        vct.add(nullcheck);
        vct.add(ponitcheck);
        Vtor vtor = new Vtor();
        //vtor.useProfile("create");
        vtor.validate(vct,bean);
        if(vtor.hasViolations()){
            vtor.getViolations().stream().forEach(e-> System.out.println(JSON.toJSON(e)));
        }else {
            System.out.println("验证通过");
        }
    }

    public void check2(){
        Bean bean = new Bean();
        bean.setA(12);
        Point point = new Point(-6,6);
        //bean.setPoint(point);
        Vtor vtor = new Vtor();
        vtor.setSeverity(3);
        vtor.validate(bean);
        if(vtor.hasViolations()){
            vtor.getViolations().stream().forEach(e-> System.out.println(JSON.toJSON(e)));
        }else {
            System.out.println("验证通过");
        }
    }


    public void foo(){
        Bean bean = new Bean();
        bean.setA(11);
        //bean.setB("ddd");
        ValidationContext vct = new ValidationContext();
        //Check check = new Check("a",new SizeConstraint(1,10));
        //check.setMessage("数字应在1 ^ 11");
        //vct.add(check);
        Vtor vtor = new Vtor();
        //vtor.useProfiles("p1");
        //vtor.useProfiles("default");
        vtor.validate(bean);
        /*violations.stream().forEach(v->{
            System.out.println(v);
        });*/
        System.out.println(vtor.hasViolations());
        if(vtor.hasViolations()){
            vtor.getViolations().stream().forEach(e->{
                System.out.println("字段+["+e.getCheck().getName()+"] = ["+e.getInvalidValue()+"]"+e.getCheck().getMessage());
            });
        }
    }

    public static void main(String[] args) {
        Vtx vtx = new Vtx();
        //vtx.check1();
        vtx.check2();
    }
}
