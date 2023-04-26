package com.yb.habittracker.domain.habit;

import com.yb.habittracker.domain.habit.HabitEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Getter
@Entity
public class RecordEntity {
    @Id
    private String recordId;
    @ManyToOne
    private HabitEntity habit;
    private ZonedDateTime recordDate;
}
