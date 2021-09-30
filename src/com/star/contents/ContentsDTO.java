package com.star.contents;

import java.util.Date;
import java.util.Objects;

public class ContentsDTO {
    private int cNO;
    private String cName;
    private Date cDate;
    private String cDirector;
    private String cActor;
    private String cGenre;
    private int cSerise;
    private int cRunningTime;
    private int cAgeLimit;
    private String cDetail;
    private int cRatingCount;
    private int cAccRating;
    private int cWishCount;
    private double GPA;

    public int getcNO() {
        return cNO;
    }

    public void setcNO(int cNO) {
        this.cNO = cNO;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public String getcDirector() {
        return cDirector;
    }

    public void setcDirector(String cDirector) {
        this.cDirector = cDirector;
    }

    public String getcActor() {
        return cActor;
    }

    public void setcActor(String cActor) {
        this.cActor = cActor;
    }

    public String getcGenre() {
        return cGenre;
    }

    public void setcGenre(String cGenre) {
        this.cGenre = cGenre;
    }

    public int getcSerise() {
        return cSerise;
    }

    public void setcSerise(int cSerise) {
        this.cSerise = cSerise;
    }

    public int getcRunningTime() {
        return cRunningTime;
    }

    public void setcRunningTime(int cRunningTime) {
        this.cRunningTime = cRunningTime;
    }

    public int getcAgeLimit() {
        return cAgeLimit;
    }

    public void setcAgeLimit(int cAgeLimit) {
        this.cAgeLimit = cAgeLimit;
    }

    public String getcDetail() {
        return cDetail;
    }

    public void setcDetail(String cDetail) {
        this.cDetail = cDetail;
    }

    public int getcRatingCount() {
        return cRatingCount;
    }

    public void setcRatingCount(int cRatingCount) {
        this.cRatingCount = cRatingCount;
    }

    public int getcAccRating() {
        return cAccRating;
    }

    public void setcAccRating(int cAccRating) {
        this.cAccRating = cAccRating;
    }

    public int getcWishCount() {
        return cWishCount;
    }

    public void setcWishCount(int cWishCount) {
        this.cWishCount = cWishCount;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public ContentsDTO() {
    }

    public ContentsDTO(int cNO, String cName, Date cDate, String cDirector, String cActor, String cGenre, int cSerise, int cRunningTime, int cAgeLimit, String cDetail, int cRatingCount, int cAccRating, int cWishCount) {
        this.cNO = cNO;
        this.cName = cName;
        this.cDate = cDate;
        this.cDirector = cDirector;
        this.cActor = cActor;
        this.cGenre = cGenre;
        this.cSerise = cSerise;
        this.cRunningTime = cRunningTime;
        this.cAgeLimit = cAgeLimit;
        this.cDetail = cDetail;
        this.cRatingCount = cRatingCount;
        this.cAccRating = cAccRating;
        this.cWishCount = cWishCount;

        if (cRatingCount != 0) {
            this.GPA = cAccRating / cRatingCount;
        } else this.GPA = 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[작품명] ").append(cName).append('\t').append('\t');
        sb.append("[출시일] ").append(cDate).append('\n');
        sb.append("[감  독] ").append(cDirector).append('\t').append('\t');
        sb.append("[출  연] ").append(cActor).append('\n');
        sb.append("[장  르] ").append(cGenre).append('\t').append('\t');
        sb.append("[재생시간] ").append(cRunningTime).append("분").append('\t').append('\t');
        sb.append("[연령제한] ").append(cAgeLimit).append("세 이상").append('\n');
        sb.append("[설  명] ").append(cDetail).append('\n');
        sb.append("[평가자수] ").append(cRatingCount).append("명").append('\t').append('\t');
        sb.append("[총  점] ").append(cAccRating).append("점").append('\t').append('\t');
        sb.append("[좋아요] ").append(cWishCount).append("개").append('\t').append('\t');
        sb.append("[평  점] ").append(GPA).append("점").append('\n');
        sb.append("===========================================================================");
        return sb.toString();
    }
}
