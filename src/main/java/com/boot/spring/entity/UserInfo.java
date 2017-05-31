package com.boot.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UserInfo {

	@Id
	@GeneratedValue
	private long uid;

	@Column(unique = true)
	private String username;

	private String name;

	private String password;

	private String salt;

	private byte state;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	private List<SysRole> roleList;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "UserInfo [uid=" + uid + ", username=" + username + ", name=" + name + ", password=" + password
				+ ", salt=" + salt + ", state=" + state + ", roleList="
				+ (roleList != null ? roleList.subList(0, Math.min(roleList.size(), maxLen)) : null) + "]";
	}

}
