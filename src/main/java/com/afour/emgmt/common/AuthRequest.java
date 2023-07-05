/**
 * 
 */
package com.afour.emgmt.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthRequest {

    private String username ;
    private String password;
}