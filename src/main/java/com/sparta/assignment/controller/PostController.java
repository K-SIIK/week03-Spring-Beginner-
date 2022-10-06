package com.sparta.assignment.controller;

import com.sparta.assignment.dto.PostDto;
import com.sparta.assignment.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final 로 정의된 멤버 변수 자동 생성
@RestController
public class PostController {

    private final PostService postService;

    // 클라이언트가 POST 방식이면서 URL 이 /api/posts 에 데이터를 요청하면 이 메서드가 호출된다.
    @PostMapping("/api/posts")
    public PostDto.Simple createPost(@RequestBody PostDto.Request request) {
            // Service 에서 로직을 구현할 수 있도록 데이터 매개변수에 넣음
        return postService.createPost(request);
    }

    @GetMapping("/api/posts")
    public List<PostDto.Simple> getPosts() {
        return postService.getPosts();
    }

    // 오타조심 url posts 말고 post 라고 써서 30분 까먹음!!!!!!!!!!!!!!!!!!!!!
    @GetMapping("/api/posts/{id}")
    public PostDto.Detail getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping("/api/posts/{id}") // password requestBody or requestParam??????????????????????????
    public boolean checkPw(@PathVariable Long id, @RequestBody String password) {
        return postService.checkPw(id, password);
    }

    // @PathVariable -> url 경로에 데이터도 같이 넣어줌!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @PutMapping("/api/posts/{id}")
    public PostDto.Detail updatePost(@PathVariable Long id, @RequestBody PostDto.Request request) {
        return postService.updatePost(id, request);
    }

    @DeleteMapping("/api/posts/{id}")
    public PostDto.Detail deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
