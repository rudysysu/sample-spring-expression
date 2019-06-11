package com.rudysysu.sample.spring.expression;

import java.util.Date;

public class Inventor {
    private String name;
    private Date date;
    private String desc;

    public Inventor(String name, Date date, String desc) {
        this.name = name;
        this.date = date;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Inventor [name=" + name + ", date=" + date + ", desc=" + desc + "]";
    }
}
