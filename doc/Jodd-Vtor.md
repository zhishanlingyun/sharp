# Jodd 之 Vtor - 校验框架
*原文参考 [jodd-vtor](https://jodd.org/vtor/)*
##概念
* ValidationContext
  >验证器载体(上下文),用来存放 Check(检查点)
  
* Check
  >检查点, 包括name(待校验字段名称) 和 ValidationConstraint(验证器)

* ValidationConstraint
  >验证器,是一个接口，作为扩展点，由不同子类实现验证的方法
  该接口有两个方法<br>
  **void configure(A annotation)**<br>
  接收与之配套的注解里的参数，可以做一些初始化操作<br>
  **boolean isValid(ValidationConstraintContext vcc, Object value)**<br>
  校验逻辑<br>
  
* Vtor
  >校验客户端,该类属于非线程安全的,禁止使用单例
  


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