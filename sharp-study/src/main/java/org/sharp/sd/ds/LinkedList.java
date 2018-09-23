package org.sharp.sd.ds;

public class LinkedList<T> implements IList<T>{

    private Node head;
    private int size;

    public LinkedList(){
        head = new Node(null,null,null);
        head.next = head.pre = head;
    }

    @Override
    public boolean add(T t) {
        addAfter(head,t);
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

    public void print(){
        Node p = head.next;
        while (p!=head){
            System.out.print(p.data+",");
            p = p.next;
        }
    }

    protected void addAfter(Node node,T t){
        Node newNode = new Node(node.pre,t,node);
        node.pre.next = newNode;
        node.pre = newNode;
    }

    class Node<T>{

        Node pre;
        T data;
        Node next;

        public Node(Node pre, T data, Node next) {
            this.pre = pre;
            this.data = data;
            this.next = next;
        }

        public Node() {
        }
    }

}
