package com.scaler.boookmyshowmay23.repositories;

import com.scaler.boookmyshowmay23.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByEmail(String email);

    @Override
    User save(User user);
}
