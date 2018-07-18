package org.sharp.transfer;

import sun.dc.pr.PRError;

public class P {

    private String skuid;
    private String ps;

    public P(String skuid, String ps) {
        this.skuid = skuid;
        this.ps = ps;
    }

    public P() {
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof P)) return false;

        P p = (P) o;

        if (skuid != null ? !skuid.equals(p.skuid) : p.skuid != null) return false;
        return ps != null ? ps.equals(p.ps) : p.ps == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "P{" +
                "skuid='" + skuid + '\'' +
                ", ps='" + ps + '\'' +
                '}';
    }
}
