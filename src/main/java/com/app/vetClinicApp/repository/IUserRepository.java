package com.app.vetClinicApp.repository;

import com.app.vetClinicApp.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    User getUsersByEmail(@Param("email") String email);

}
