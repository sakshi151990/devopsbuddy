
package com.devopsbuddy.persistence;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan(basePackages = "com.devopsbuddy.config")
public interface PlanRepository extends CrudRepository<Plan, Integer> {

}
