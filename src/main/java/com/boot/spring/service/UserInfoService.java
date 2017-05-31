package com.boot.spring.service;

import com.boot.spring.entity.UserInfo;

public interface UserInfoService {

	public UserInfo findByUsername(String username);
}
