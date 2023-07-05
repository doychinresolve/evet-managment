/**
 * 
 */
package com.afour.emgmt.common;

/**
 * 
 */
public enum RoleEnum {
	
	ORGANIZER(1),
	VISITOR(2);
	
	private Integer roleId;

	public Integer getRoleId() {
		return this.roleId;
	}
	
	private RoleEnum(int value) {
        this.roleId = value;
    }
}