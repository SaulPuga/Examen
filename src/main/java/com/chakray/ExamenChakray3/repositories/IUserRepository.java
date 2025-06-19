package com.chakray.ExamenChakray3.repositories;

import com.chakray.ExamenChakray3.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository  extends JpaRepository<UserModel, Long> {
}
