package com.star.rating;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RatingDTO {
    private int mNo;
    private int cNo;
    private int rScore;

    public int getmNo() {
        return mNo;
    }

    public void setmNo(int mNo) {
        this.mNo = mNo;
    }

    public int getcNo() {
        return cNo;
    }

    public void setcNo(int cNo) {
        this.cNo = cNo;
    }

    public int getrScore() {
        return rScore;
    }

    public void setrScore(int rScore) {
        this.rScore = rScore;
    }

    public RatingDTO() {
    }

    public RatingDTO(int mNo, int cNo, int rScore) {
        this.mNo = mNo;
        this.cNo = cNo;
        this.rScore = rScore;
    }
}
