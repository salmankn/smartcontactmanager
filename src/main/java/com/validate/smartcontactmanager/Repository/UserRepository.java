package com.validate.smartcontactmanager.Repository;


import com.validate.smartcontactmanager.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
