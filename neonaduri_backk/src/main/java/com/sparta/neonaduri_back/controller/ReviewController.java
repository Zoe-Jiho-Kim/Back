package com.sparta.neonaduri_back.controller;//package com.sparta.neonaduri.controller;
//
///**
// * [controller] - ReviewController
// *
// * @class   : ReviewController
// * @author  : 오예령
// * @since   : 2022.05.03
// * @version : 1.0
// *
// *   수정일     수정자             수정내용
// *  --------   --------    ---------------------------
// *
// */
//
//import com.sparta.neonaduri.dto.ReviewRequestDto;
//import com.sparta.neonaduri.security.UserDetailsImpl;
//import com.sparta.neonaduri.service.ReviewService;
//import com.sparta.neonaduri.service.S3Uploader;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@RestController
//public class ReviewController {
//    private final ReviewService reviewService;
//
////    @PostMapping("/api/detail/reviews/{postId}")
////    public ResponseEntity<String> createReview(@PathVariable Long postId,
////                                               @RequestParam("reviewContetns") String reviewContents,
////                                               @RequestParam(value = "reviewImgUrl") MultipartFile multipartFile,
////                                               @AuthenticationPrincipal UserDetailsImpl userDetails
////    ) throws IOException {
////
////        String reviewImgUrl = S3Uploader.upload(multipartFile, "static");
////
////        ReviewRequestDto reviewRequestDto = new ReviewRequestDto(reviewContents, reviewImgUrl);
////
////        reviewService.createReview(reviewRequestDto, userDetails.getUser());
////        return ResponseEntity.status(201)
////                .header("status", "201")
////                .body("{ status : 201 }");
////    }
//}
