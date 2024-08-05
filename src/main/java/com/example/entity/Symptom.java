package com.example.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "symptom")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "USER_ID")
    private String user_id;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Pattern")
    private  String pattern;

    @Column(name = "Severity")
    private Integer severity;
    @Column(name = "Location")
    private String location;
    @Column(name = "Triggers")
    private String triggers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTriggers() { return triggers; }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }
}
