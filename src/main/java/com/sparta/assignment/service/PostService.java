package com.sparta.assignment.service;

import com.sparta.assignment.dto.PostDto;
import com.sparta.assignment.entity.Post;
import com.sparta.assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
// final 로 정의된 멤버변수 자동 생성 (@Autowired 를 포함한 생성자 생략 가능)
@Service
public class PostService {

    // 의존성 주입
    private final PostRepository postRepository;

    // 게시글 포스팅
    public PostDto.Simple createPost(PostDto.Request request) {
        // post 변수에 새 데이터를 dto 에 담아 저장.
        // entity 를 호출하는 방식으로 데이터를 저장하면 캡슐화가 깨진다.
        Post post = new Post(request);
        // post 를 repository 에 저장한다.
        postRepository.save(post);
        // post 를 매개변수로 받아 Response 객체 리턴
        return new PostDto.Simple(post);
    }

    // 게시글 전체 조회
    public List<PostDto.Simple> getPosts() {
        // findAll 함수 안에 Sort.by(Sort.Direction.DESC, "기준 데이터") 하면 정렬됨 (DESC -> Descending 내림차순)
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        // 게시글 없을 경우 NullPointerException 예외 던짐
        if (posts.size() == 0) throw new NullPointerException("게시글이 존재하지 않습니다.");
        // response 배열 선언(초기화 안함)
        List<PostDto.Simple> responses = new ArrayList<>();
        // for 문으로 Post 타입 배열 돌려서 Response 생성자에 매개변수로 넣어서 response 객체 초기화
        for (Post post : posts) {
            responses.add(new PostDto.Simple(post));
        }
        return responses;
    }

    // 특정 게시글 조회
    public PostDto.Detail getPost(Long id) {
        // id 체크 메서드 밑에 만들어 놓음
        Post post = checkId(id);
        return new PostDto.Detail(post);
    }

    // 비번 체크
    public boolean checkPw(Long id, String password) {
        // Post 객체에서 getPassword 메서드 호출해서 db에 저장된 비밀번호 savedPw 에 초기화
        String savedPw = checkId(id).getPassword();
        // 비번 두개 비교해서 boolean 리턴
        return savedPw.equals(password);
    }

    // 게시글 수정
    public PostDto.Detail updatePost(Long id, PostDto.Request request) {
        Post post = checkId(id);
        post.update(request);
        postRepository.save(post);
        return new PostDto.Detail(post);
    }

    // 게시글 삭제
    public PostDto.Detail deletePost(Long id) {
        Post post = checkId(id);
        postRepository.delete(post);
        return new PostDto.Detail(post);
    }

    // id 체크 로직 -> 많이 쓰여서 메서드로 뺌
    private Post checkId(Long id) {
        // 레포지토리에서 id 찾는데 id 유무 파악한 후 예외 던지고 있으면 Post 타입 변수에 저장
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        return post;
    }
}

