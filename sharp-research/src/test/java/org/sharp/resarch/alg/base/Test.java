package org.sharp.resarch.alg.base;

import org.sharp.research.algo.base.E;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;

public class Test {


    @Test
    public void compare(){

        E e1 = new E(1);
        E e2 = new E(5);
        E e3 = new E(3);

        ArrayList<E> list = new ArrayList<E>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        Collections.sort(list);

    }


}
