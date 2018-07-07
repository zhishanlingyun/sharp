package org.sharp.research.algo.base;

public class E implements Comparable{

    private int value;

    public E() {
    }

    public E(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int compareTo(Object o) {
        return -1;
    }

    @Override
    public String toString() {
        return "E{" +
                "value=" + value +
                '}';
    }
}
