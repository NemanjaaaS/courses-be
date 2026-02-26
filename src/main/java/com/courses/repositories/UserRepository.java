package com.courses.repositories;

import com.courses.dto.CumulativeUserCountDTO;
import com.courses.models.User;
import com.courses.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findByRole( Role role);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    long countByRole(@Param("role") Role role);

    @Query(value = """
        SELECT 
            DATE(u.created_at) AS registrationDate,
            SUM(COUNT(*)) OVER (ORDER BY DATE(u.created_at)) AS cumulativeTotal
        FROM users u
        WHERE u.role = :role
        GROUP BY DATE(u.created_at)
        ORDER BY registrationDate ASC
        """, nativeQuery = true)
    List<CumulativeUserCountDTO> getCumulativeUserCountByRole(@Param("role") Role role);


}
