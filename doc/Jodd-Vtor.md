# Jodd 之 Vtor - 校验框架
*原文参考[jodd-vtor](https://jodd.org/vtor/)*
##概念
* ValidationContext
* ValidationConstraint
* Check
* Vtor


##校验模板代码
* 1.无注解方式
>>>>**ValidationContext vct = new ValidationContext();**<br>
    **Check check = new Check("a",new MaxConstraint(10));**<br>
    //check.setProfiles("create");<br>
    Check nullcheck = new Check("point",new NotNullConstraint());<br>
    nullcheck.setMessage("point 不能为空");<br>
    Check ponitcheck = new Check("point",new PointConstraint());<br>
    ponitcheck.setMessage("point 不在第一象限");<br>
    **vct.add(check);**<br>
    vct.add(nullcheck);<br>
    vct.add(ponitcheck);<br>
    **Vtor vtor = new Vtor();**<br>
    //vtor.useProfile("create");<br>
    **vtor.validate(vct,bean);**<br>
    if(vtor.hasViolations()){<br>
        vtor.getViolations().stream().forEach(e-> System.out.println(JSON.toJSON(e)));<br>
    }else {<br>
        System.out.println("验证通过");<br>
    }<br>
* 2.有注解方式<br>
>>>>Bean bean = new Bean();<br>
    bean.setA(12);<br>
    Point point = new Point(-6,6);<br>
    //bean.setPoint(point);<br>
    **Vtor vtor = new Vtor();**<br>
    vtor.setSeverity(3);<br>
    **vtor.validate(bean);**<br>
    if(vtor.hasViolations()){<br>
        vtor.getViolations().stream().forEach(e-> System.out.println(JSON.toJSON(e)));<br>
    }else {<br>
        System.out.println("验证通过");<br>
    }<br>