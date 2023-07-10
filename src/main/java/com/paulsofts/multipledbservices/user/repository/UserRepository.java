package com.paulsofts.multipledbservices.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulsofts.multipledbservices.user.data.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
