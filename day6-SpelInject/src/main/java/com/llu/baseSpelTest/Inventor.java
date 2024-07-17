package com.llu.baseSpelTest;

import java.util.Date;

public class Inventor {
    private String name;
    private Date time;
    private String serbian;
    public Inventor() {
    }
    public Inventor(String name, Date time, String serbian) {
        this.name = name;
        this.time = time;
        this.serbian = serbian;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSerbian() {
        return serbian;
    }

    public void setSerbian(String serbian) {
        this.serbian = serbian;
    }
}
