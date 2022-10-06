package com.sparta.assignment.dto;

import com.sparta.assignment.entity.Post;
import lombok.Getter;
import java.time.LocalDateTime;

public class PostDto { // inner class 로 requestDto 와 responseDto 를 만들었다.
    @Getter
    public static class Request {
        private String name;
        private String title;
        private String content;
        private String password;
    }

    @Getter
    public static class Simple {
        private String name;
        private String title;

        // 원래 Post 리턴할 때엔 날짜 출력됐는데 Response 리턴하니까 날짜 null 로 뜸
        // sol) 날짜 변수 선언 후, 생성자에서 post 객체의 getter 로 날짜 데이터 부름.
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        // Dto 생성자를 만들고 매개변수로 Post 객체를 받아 getter 로 초기화
        public Simple(Post post) {
            this.name = post.getName();
            this.title = post.getTitle();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }

    @Getter
    public static class Detail {
        private String name;
        private String title;
        private String content;

        // 날짜 변수 선언 후, 생성자에서 post 객체의 getter 로 날짜 데이터 부름.
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public Detail(Post post) {
            this.name = post.getName();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}
