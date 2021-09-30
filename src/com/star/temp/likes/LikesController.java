package com.star.temp.likes;

public class LikesController {
    static LikesService likesService = new LikesService();
    public static void main(String[] args) {
        likesService.selectLikesBymNO(22);
        likesService.deleteLikesBycNO(22, 1);
    }
}
