package com.boot.spring.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boot.spring.dao.UserInfoRepository;
import com.boot.spring.entity.UserInfo;
import com.boot.spring.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo findByUsername(String username) {
		System.out.println(this.getClass().getName());
		return userInfoRepository.findByUsername(username);
	}

}
