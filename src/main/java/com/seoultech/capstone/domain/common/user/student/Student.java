package com.seoultech.capstone.domain.common.user.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seoultech.capstone.domain.common.group.Group;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, length = 255)
    @JsonIgnore
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private LocalDate birth;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Group group;

    @Column(name = "profile_url", length = 255)
    private String profileUrl;

    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt;

    @Column(nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public void updatePassword(String newPassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(newPassword);
    }

}

