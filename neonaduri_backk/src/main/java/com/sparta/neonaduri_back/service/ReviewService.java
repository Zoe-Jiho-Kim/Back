package com.sparta.neonaduri_back.service;//package com.sparta.neonaduri.service;
//
///**
// * [Service] - 댓글 작성 Service
// *
// * @class   : ReviewService
// * @author  : 오예령
// * @since   : 2022.05.03
// * @version : 1.0
// *
// *   수정일     수정자             수정내용
// *  --------   --------    ---------------------------
// *
// */
//
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.sparta.neonaduri.dto.ReviewRequestDto;
//import com.sparta.neonaduri.dto.ReviewResponseDto;
//import com.sparta.neonaduri.model.Review;
//import com.sparta.neonaduri.model.User;
//import com.sparta.neonaduri.repository.ImageRepository;
//import com.sparta.neonaduri.repository.ReviewRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//@Service
//public class ReviewService {
//    private final ReviewRepository reviewRepository;
//    private final ImageRepository imageRepository;
//    private final AmazonS3Client amazonS3Client;
//    private final S3Uploader s3Uploader;
//
////    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto, User user) {
////
////    }
//}
