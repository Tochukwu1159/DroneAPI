
package com.musalasoft.drone.repositories;

import com.musalasoft.drone.models.User;
import com.musalasoft.drone.utils.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String username);

}
