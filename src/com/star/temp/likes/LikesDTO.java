package com.star.temp.likes;

public class LikesDTO {
    private int mNO;
    private int cNO;

    public int getmNO() {
        return mNO;
    }

    public void setmNO(int mNO) {
        this.mNO = mNO;
    }

    public int getcNO() {
        return cNO;
    }

    public void setcNO(int cNO) {
        this.cNO = cNO;
    }

    public LikesDTO() {
    }

    public LikesDTO(int mNO, int cNO) {
        this.mNO = mNO;
        this.cNO = cNO;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LikeDTO{");
        sb.append("mNO=").append(mNO);
        sb.append(", cNO=").append(cNO);
        sb.append('}');
        return sb.toString();
    }
}
