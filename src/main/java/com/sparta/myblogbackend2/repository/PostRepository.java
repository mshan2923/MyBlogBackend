package com.sparta.myblogbackend2.repository;

import com.sparta.myblogbackend2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findAllByContentsContainsOrderByCreatedAtDesc(String keyword);

}
