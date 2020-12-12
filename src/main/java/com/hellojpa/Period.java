package com.hellojpa;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
