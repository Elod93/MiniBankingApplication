package org.backend.Reporitory;

import org.backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(" select u from User u " +
            " where u.username = ?1")
    Optional<User> findUserWithName(String username);
    @Query(" select u from User u " +
            " where u.fullName = ?1")
    Optional<User> findUserWithFullName(String fullName);
}
