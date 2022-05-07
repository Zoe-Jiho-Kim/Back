package com.sparta.neonaduri_back.repository;

import com.sparta.neonaduri_back.model.Post;
import com.sparta.neonaduri_back.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    //내가 찜한 여행 목록 조회
    List<Post> findAllByUserOrderByModifiedAtDesc(User user);

    Optional<Post> findByPostId(Long postId);

    Page<Post> findAllByPostIdandUserId(Pageable pageable, Long postId, Long id);
}
