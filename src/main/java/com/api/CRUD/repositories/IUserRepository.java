package com.api.CRUD.repositories;

import com.api.CRUD.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByFirstName(String firstName);
}
