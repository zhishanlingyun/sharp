package org.sharp.sd.ds;

import org.junit.Test;

public class ListTest {

    @Test
    public void testlist(){
        IList<Integer> list = new LinkedList<>();
        for(int i=1;i<=10;i++){
            list.add(i);
        }
        ((LinkedList<Integer>) list).print();
    }

    @Test
    public void testlisted(){
        IList<Integer> list = new Linked<>();
        for(int i=1;i<=10;i++){
            list.add(i);
        }
        ((Linked<Integer>) list).print();
    }
}
