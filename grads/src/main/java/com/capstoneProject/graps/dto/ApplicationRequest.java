package com.capstoneProject.graps.dto;

import com.capstoneProject.graps.entity.ApplicationStatus;
import com.capstoneProject.graps.entity.RouteType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ApplicationRequest {

    private Integer universityId;
    private ApplicationStatus status;
    private LocalDate Deadline;
    private RouteType route;
    private BigDecimal fee;
}
