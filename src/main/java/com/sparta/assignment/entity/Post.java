package com.sparta.assignment.entity;

import com.sparta.assignment.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본 생성자
@Getter // 게터 자동 생성
@Entity // 개체로 등록. 테이블과 1대1 매칭
public class Post extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO) // 1씩 자동 증가
    @Id // sql 에서 primary key 역할
    private Long id;

    @Column(nullable = false)     // 반드시 값을 가지도록 함
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;     // int 로 받으려다 문자열 들어왔을 때의 예외 처리를 해야함



    public Post(PostDto.Request request) {
        this.name = request.getName();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.password = request.getPassword();
    }


    public void update(PostDto.Request request) {
        this.name = request.getName();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.password = request.getPassword();
    }
}
