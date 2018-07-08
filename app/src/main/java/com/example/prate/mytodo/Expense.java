package com.example.prate.mytodo;

public class Expense {
    private String tittle;
    private String discription ;
    private String date;
    private String time;
    private long id;


    public Expense(String tittle,String discription, String date,String time){
        this.time=time;
        this.discription=discription;
        this.date=date;
        this.tittle=tittle;
    }
    public Expense(String tittle,String discription){
        this.tittle=tittle;
        this.discription=discription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
