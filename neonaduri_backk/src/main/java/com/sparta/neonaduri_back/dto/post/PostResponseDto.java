package com.sparta.neonaduri_back.dto.post;

import com.sparta.neonaduri_back.model.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private String startDate;
    private String endDate;
    private int dateCnt;
    private String postTitle;
    private String location;
    private String theme;

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
        this.dateCnt = post.getDateCnt();
        this.postTitle = post.getTitle();
        this.location = post.getLocation();
        this.theme = post.getTheme();
    }
}
