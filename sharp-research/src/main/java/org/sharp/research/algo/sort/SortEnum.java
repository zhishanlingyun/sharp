package org.sharp.research.algo.sort;

public enum SortEnum {

    ACS(0),DESC(1);
    private int code;

    SortEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
