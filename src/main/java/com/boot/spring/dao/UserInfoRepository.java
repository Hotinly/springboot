package com.boot.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.spring.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	public UserInfo findByUsername(String username);
}
