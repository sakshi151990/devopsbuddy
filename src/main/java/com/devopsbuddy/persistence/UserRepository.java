package com.devopsbuddy.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String name);

	public User findByEmail(String email);

	@Transactional
	@Modifying
	@Query("update User u set u.password=:password where u.id=:userId")
	void updateUserPassword(@Param("userId") long userId, @Param("password") String password);

}
