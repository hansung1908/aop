package com.cos.aop.repository;

import com.cos.aop.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "kim", "1234", "01012341234"));
        users.add(new User(2, "lee", "1234", "01012341234"));
        users.add(new User(3, "park", "1234", "01012341234"));
        return users;
    }

    public User findById(int id) {
        return new User(1, "kim", "1234", "01012341234");
    }

    public void save(User user) {
        System.out.println("DB에 insert하기");
    }
}
