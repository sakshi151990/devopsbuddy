
package com.devopsbuddy.web;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devopsbuddy.persistence.Plan;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer> {

}
