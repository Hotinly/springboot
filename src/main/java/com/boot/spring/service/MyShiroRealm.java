package com.boot.spring.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.spring.entity.SysPermission;
import com.boot.spring.entity.SysRole;
import com.boot.spring.entity.UserInfo;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserInfoService userInfoService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("com.boot.spring.service.MyShiroRealm.doGetAuthorizationInfo(PrincipalCollection)");

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();

		if (userInfo != null) {
			for (SysRole role : userInfo.getRoleList()) {
				authorizationInfo.addRole(role.getRole());
				for (SysPermission permission : role.getPermissions()) {
					authorizationInfo.addStringPermission(permission.getPermission());
				}
			}
			return authorizationInfo;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("com.boot.spring.service.MyShiroRealm.doGetAuthenticationInfo(AuthenticationToken)");

		String username = (String) token.getPrincipal();
		System.out.println(token.getCredentials());

		UserInfo userInfo = userInfoService.findByUsername(username);
		// System.out.println(userInfo.toString());

		if (userInfo != null) {
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
					userInfo,
					userInfo.getPassword(),
					// ByteSource.Util.bytes(userInfo.getCredentialsSalt()),  //密码匹配？
					getName());
			return authenticationInfo;
		}
		return null;
	}

}
