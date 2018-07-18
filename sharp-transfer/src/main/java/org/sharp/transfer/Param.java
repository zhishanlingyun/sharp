package org.sharp.transfer;

public class Param {

    private String domain;
    private String sql;
    private String dbname;

    public Param(String domain, String sql, String dbname) {
        this.domain = domain;
        this.sql = sql;
        this.dbname = dbname;
    }

    public Param() {
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
}
