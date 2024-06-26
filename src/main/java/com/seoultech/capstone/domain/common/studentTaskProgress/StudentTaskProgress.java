package com.seoultech.capstone.domain.common.studentTaskProgress;

import com.seoultech.capstone.domain.common.task.Task;
import com.seoultech.capstone.domain.common.user.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "student_task_progress")
public class StudentTaskProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "completed", nullable = false)
    private Boolean completed = false;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Column(name = "base_img_url", nullable = false)
    private String baseImgUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

