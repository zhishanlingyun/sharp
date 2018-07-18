package org.sharp.transfer;

public class R {

    private String start;
    private int count;

    public R(String start, int count) {
        this.start = start;
        this.count = count;
    }

    public R() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
