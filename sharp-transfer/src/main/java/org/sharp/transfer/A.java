package org.sharp.transfer;

import java.io.IOException;
import java.io.InputStream;

public class A {

    public static void main(String[] args) {
        ClassLoader loader = new ClassLoader(){
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String classfile = name.substring(name.lastIndexOf(".")+1)+".class";
                System.out.println("path: "+classfile);
                InputStream in = getClass().getResourceAsStream(classfile);
                if(null == in){
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[in.available()];
                    in.read(b);
                    return this.defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };
        try {
            Class clazz = loader.loadClass("org.sharp.transfer.B");
            Object obj1 = loader.loadClass("org.sharp.transfer.A");
            Object a1 = clazz.newInstance();
            Object a2 = clazz.newInstance();
            B b = new B();
            A a3 = new A();
            System.out.println("obj "+clazz.getClass()+",classloader is "+clazz.getClass().getClassLoader());
            System.out.println("obj1 "+obj1.getClass()+",classloader is "+obj1.getClass().getClassLoader());
            System.out.println("b "+b.getClass()+",classloader is "+b.getClass().getClassLoader());
            System.out.println(a1 instanceof org.sharp.transfer.B);
            System.out.println(clazz==obj1);
            System.out.println(a1==a2);
            System.out.println(a1.getClass().getClassLoader());
            System.out.println(a3.getClass().getClassLoader());
            System.out.println(b.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
