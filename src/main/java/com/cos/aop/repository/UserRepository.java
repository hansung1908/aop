package com.cos.aop.repository;

import com.cos.aop.domain.JoinReqDto;
import com.cos.aop.domain.UpdateReqDto;
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

    public void save(JoinReqDto dto) {
        System.out.println("DB에 insert하기");
    }

    public void delete(int id) {
        System.out.println("DB에 삭제하기");
    }

    public void update(int id, UpdateReqDto dto) {
        // DAO 연결해서 실행하다가 익섹션 터짐
        throw new IllegalArgumentException("아규먼트를 잘못 넣음");
        // 알아서 에러요청으로 반환
        // System.out.println("DB에 수정하기");
    }
}
