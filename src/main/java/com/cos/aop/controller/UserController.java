package com.cos.aop.controller;

import com.cos.aop.domain.User;
import com.cos.aop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public List<User> findAll() {
        System.out.println("findAll()");
        return userRepository.findAll(); // MessageConverter (javaObject -> json String)
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable int id) {
        System.out.println("findById() : id : " + id);
        return userRepository.findById(id);
    }

    @PostMapping("/user")
    // x-www-form-urlencoded -> request.getParameter()
    // text/plain -> @RequestBody 어노테이션
    // application/json -> @RequestBody 어노테이션 + 오브젝트
    public String save(@RequestBody User user) {
        System.out.println("save()");
        System.out.println("user: " + user);
        userRepository.save(user);

//        System.out.println("data: " + data);
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
//        System.out.println("phone: " + phone);

        return "ok";
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id) {
        System.out.println("delete()");
    }

    @PutMapping("/user/{id}")
    public void update(@PathVariable int id, String password, String phone) {
        System.out.println("update()");
    }
}
