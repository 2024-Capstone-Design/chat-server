package com.seoultech.capstone.domain.common.user.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {


    Optional<Student> findByUsernameAndGroupIdAndActiveTrue(String username, Integer groupId);
    boolean existsByUsername(String username);


}
