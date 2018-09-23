package org.sharp.sd.ds;

public class Linked<T> implements IList<T>{

    private Node head,tail;
    private int size;

    @Override
    public boolean add(T t) {
        addAfter(t,tail);
        return true;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T remove(T t) {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T tail() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    private void addAfter(T data,Node<T> node){
        Node<T> p = node;
        Node<T> newNode = new Node<T>(data,null);
        if(p==null){
            head = newNode;
            tail = newNode;
        }else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;

    }

    public void print(){
        Node<T> p = head;
        while (p!=null){
            System.out.print(p.data+",");
            p = p.next;
        }
    }

    private class Node<T>{
        T data;
        Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

}
