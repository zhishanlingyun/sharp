package org.sharp.research.algo.ds;


import java.util.List;

public class ArrayList<T> {

    private Object[] array;

    private int size;

    private static int threshold = 3;

    private static int DEF_SIZE = 10;

    private int capacity = DEF_SIZE;

List l;
    public ArrayList() {
        this(DEF_SIZE);
    }

    public ArrayList(int size) {
        this.capacity = size;
        array = new Object[this.capacity];
    }

    public void add(T t){
        if(isNull(t)){
            return;
        }
        if(isGrew()){
            rebuild();
        }
        array[size++] = t;

    }

    public T remove(T t){
        if(isNull(t)){
            return null;
        }
        T old = null;
        int i = indexOf(t);
        if(i != -1){
            old = (T)array[i];
            shiftLeft(i);
            array[size--] = null;
        }
        return old;
    }

    public T get(int index){
        if(index>=0 && index < size){
            return (T)array[index];
        }else {
            throw new IllegalArgumentException("非法参数");
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int indexOf(T t){
        int index = -1;
        if(isNull(t)){
            return index;
        }

        for(int i=0;i<size;i++){
            if(t.equals(array[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    private void rebuild(){
        capacity = 2 * capacity;
        Object[] newData = new Object[capacity];
        System.arraycopy(array,0,newData,0,size);
        array = null;
        array = newData;
    }

    private boolean isGrew(){
        if((capacity-size)<=threshold){
            return true;
        }
        return false;
    }

    private boolean isNull(Object obj){
        return obj == null;
    }

    private void shiftLeft(int index){
        for(int i=index;i<size-1;i++){
            array[i] = array[i+1];
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<100;i++){
            list.add(i);
        }
        System.out.println("size is "+list.size());
        list.remove(10);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println("size is "+list.size());
    }

}
