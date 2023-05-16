package com.scaler.boookmyshowmay23.repositories;

import com.scaler.boookmyshowmay23.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
