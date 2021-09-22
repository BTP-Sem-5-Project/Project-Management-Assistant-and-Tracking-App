package com.example.p_mat.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commit {
    String name;
    String date;
    String msg;
    String url;

    public Commit(String name, String date, String msg) throws ParseException {
        this.name = name;
        this.date = dateFormatter(date);
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getMsg() {
        return msg;
    }
    public String dateFormatter(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy, HH:mm aa");
        Date d = sdf.parse(time);
        String formattedTime = output.format(d);
        return formattedTime;
    }

}
