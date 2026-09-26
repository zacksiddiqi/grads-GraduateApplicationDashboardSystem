package com.capstoneProject.graps.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Application")

public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.not_started;

    private LocalDate deadline;

    @Column(name = "submitted_date")
    private LocalDate submittedDate;

    @Column(name = "decision_date")
    private LocalDate decisionDate;

    @Enumerated(EnumType.STRING)
    private RouteType route;

    @Column(precision = 5, scale = 2)
    private BigDecimal fee;

    @Column(columnDefinition = "text")
    private String notes;

}
