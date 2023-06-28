/**
 * 
 */
package com.afour.emgmt.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorRegistrationDTO {
	
	private Integer visitorId;
	
	private Set<Integer> eventIds;

}