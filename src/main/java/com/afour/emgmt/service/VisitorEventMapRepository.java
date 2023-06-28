/**
 * 
 */
package com.afour.emgmt.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afour.emgmt.entity.VisitorEventMap;

/**
 * 
 */
@Repository
public interface VisitorEventMapRepository extends JpaRepository<VisitorEventMap, Integer>{

}
