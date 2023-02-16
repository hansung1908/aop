package com.cos.aop.controller;

import com.cos.aop.dto.CommonDto;
import com.cos.aop.dto.JoinReqDto;
import com.cos.aop.dto.UpdateReqDto;
import com.cos.aop.domain.User;
import com.cos.aop.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor // final 변수 = 컴파일 시점에 초기화(런타임 시점 x), final이 붙은 변수의 생성자를 만듬.
@RestController
public class UserController {

    private final UserRepository userRepository;

//    DI = 의존성 주입
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @GetMapping("/user")
    public CommonDto<List<User>> findAll() {

        System.out.println("findAll()");
        return new CommonDto<>(HttpStatus.OK.value(), userRepository.findAll()); // MessageConverter (javaObject -> json String)
    }

    @GetMapping("/user/{id}")
    public CommonDto<User> findById(@PathVariable int id) {

        System.out.println("findById() : id : " + id);
        return new CommonDto<>(HttpStatus.OK.value(), userRepository.findById(id));
    }

    @CrossOrigin
    @PostMapping("/user")
    // x-www-form-urlencoded -> request.getParameter()
    // text/plain -> @RequestBody 어노테이션
    // application/json -> @RequestBody 어노테이션 + 오브젝트
    // <?>로 반환할때 인수의 타입을 결정
    public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult) {

        System.out.println("save()");
        System.out.println("user: " + dto);
        userRepository.save(dto);

//        System.out.println("data: " + data);
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
//        System.out.println("phone: " + phone);

        return new CommonDto<>(HttpStatus.CREATED.value(), "ok");
//        return new ResponseEntity<>("권한 없음", HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/user/{id}")
    public CommonDto delete(@PathVariable int id) {

        System.out.println("delete()");
        userRepository.delete(id);
        return new CommonDto<>(HttpStatus.OK.value());
    }

    @PutMapping("/user/{id}")
    public CommonDto update(@PathVariable int id,@Valid @RequestBody UpdateReqDto dto, BindingResult bindingResult) {

        System.out.println("update()");
        userRepository.update(id, dto);
        return new CommonDto<>(HttpStatus.OK.value());
    }
}
