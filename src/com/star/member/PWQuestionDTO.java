package com.star.member;

public class PWQuestionDTO {
    private int pwNO;
    private String pwQuestion;

    public PWQuestionDTO(int pwNO, String pwQuestion) {
        this.pwNO = pwNO;
        this.pwQuestion = pwQuestion;
    }

    public PWQuestionDTO() {
    }

    public int getPwNO() {
        return pwNO;
    }

    public void setPwNO(int pwNO) {
        this.pwNO = pwNO;
    }

    public String getPwQuestion() {
        return pwQuestion;
    }

    public void setPwQuestion(String pwQuestion) {
        this.pwQuestion = pwQuestion;
    }

    @Override
    public String toString() {
        final String selectMessage = "       * ";
        final StringBuilder sb = new StringBuilder(selectMessage);
        sb.append("[").append(pwNO).append("] ").append(pwQuestion);
        return sb.toString();
    }
}
