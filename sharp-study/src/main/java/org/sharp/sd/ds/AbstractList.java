package org.sharp.sd.ds;

public abstract class AbstractList implements IList{

    protected boolean rangeCheck(int index){
        if(index<0){
            return false;
        }
        return true;
    }

}
