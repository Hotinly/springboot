package com.boot.spring.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SysRole {

	@Id
	@GeneratedValue
	private Long id;

	private String role;

	private String description;

	private Boolean availiable = Boolean.FALSE;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SysRolePermission", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
			@JoinColumn(name = "permissionId") })
	private List<SysPermission> permissions;

	@ManyToMany
	@JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
			@JoinColumn(name = "uid") })
	private List<UserInfo> userInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailiable() {
		return availiable;
	}

	public void setAvailiable(Boolean availiable) {
		this.availiable = availiable;
	}

	public List<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}

	public List<UserInfo> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<UserInfo> userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "SysUserRole [id=" + id + ", role=" + role + ", description=" + description + ", availiable="
				+ availiable + ", permissions="
				+ (permissions != null ? permissions.subList(0, Math.min(permissions.size(), maxLen)) : null)
				+ ", userInfo=" + (userInfo != null ? userInfo.subList(0, Math.min(userInfo.size(), maxLen)) : null)
				+ "]";
	}

}
