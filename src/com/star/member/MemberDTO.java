package com.star.member;

import java.util.Objects;

public class MemberDTO {
    private int mNO;
    private String mID;
    private String mName;
    private String mPW;
    private String mPhone;
    private String mEmail;
    private int pwNO;
    private String pwAnswer;

    public MemberDTO() {
    }

    public MemberDTO(String mID, String mName, String mPW, String mPhone, String mEmail) {
        this.mNO = 0;
        this.mID = mID;
        this.mName = mName;
        this.mPW = mPW;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
    }

    public MemberDTO(int mNO, String mID, String mName, String mPW, String mPhone, String mEmail) {
        this.mNO = mNO;
        this.mID = mID;
        this.mName = mName;
        this.mPW = mPW;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
    }

    public MemberDTO(String mID, String mName, String mPW, String mEmail, String mPhone, int pwNO, String pwAnswer) {
        this.mNO = 0;
        this.mID = mID;
        this.mName = mName;
        this.mPW = mPW;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
        this.pwNO = pwNO;
        this.pwAnswer = pwAnswer;
    }

    public MemberDTO(int mNO, String mID, String mName, String mPW, String mPhone, String mEmail, int pwNO, String pwAnswer) {
        this.mNO = mNO;
        this.mID = mID;
        this.mName = mName;
        this.mPW = mPW;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
        this.pwNO = pwNO;
        this.pwAnswer = pwAnswer;
    }

    public int getmNO() {
        return mNO;
    }

    public void setmNO(int mNO) {
        this.mNO = mNO;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPW() {
        return mPW;
    }

    public void setmPW(String mPW) {
        this.mPW = mPW;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public int getPwNO() {
        return pwNO;
    }

    public void setPwNO(int pwNO) {
        this.pwNO = pwNO;
    }

    public String getPwAnswer() {
        return pwAnswer;
    }

    public void setPwAnswer(String pwAnswer) {
        this.pwAnswer = pwAnswer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SYSTEM>> ");
        sb.append(mName).append("(").append(mID).append(")").append("님의 회원정보입니다.").append('\n');
        sb.append("       * 회원번호 : ").append(mNO).append('\n');
        sb.append("       * 메일주소 : ").append(mEmail).append('\n');
        sb.append("       * 전화번호 : ").append(mPhone);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDTO memberDTO = (MemberDTO) o;
        return mID.equals(memberDTO.mID) && mPW.equals(memberDTO.mPW);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mID, mPW);
    }
}
