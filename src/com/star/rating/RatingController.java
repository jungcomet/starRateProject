package com.star.rating;

import com.star.contents.ContentsService;

public class RatingController {
    RatingService ratingService = new RatingService();
    ContentsService contentsService = new ContentsService();

    public void makeRating(int cNO) {
        if (cNO == 0) {
            System.out.println("평가할 작품을 선택하지 않았습니다.");
        } else {
            ratingService.makeRating(cNO);
        }
    }
}
