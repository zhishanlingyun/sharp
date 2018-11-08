package org.sharp.sd.base;

import org.sharp.domain.Bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateClass {

    public void create() {
        Bean b1 = new Bean();
        System.out.println(b1);
        try {
            Bean b2 = (Bean) Class.forName("org.sharp.domain.Bean").newInstance();
            System.out.println(b2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        Bean b3 = null;
        try {
            b3 = Bean.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println(b3);

        try {
            Constructor<Bean> constructor = Bean.class.getConstructor(null);
            Bean b5 = constructor.newInstance(null);
            System.out.println(b5);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Bean b6 = null;
        try {
            Class<?> aClass = classLoader.loadClass("org.sharp.domain.Bean");
            b6 = (Bean) aClass.newInstance();
            System.out.println(b6);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        CreateClass cc = new CreateClass();
        cc.create();
    }

}
