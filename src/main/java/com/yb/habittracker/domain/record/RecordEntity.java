package com.yb.habittracker.domain.record;

import com.yb.habittracker.domain.habit.HabitEntity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Getter
@Entity
public class RecordEntity {
    @Id
    private String recordId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private HabitEntity habit;
    private ZonedDateTime recordDate;

    protected RecordEntity() {}

    @Builder
    public RecordEntity(String recordId, HabitEntity habit, ZonedDateTime recordDate) {
        this.recordId = recordId;
        this.habit = habit;
        this.recordDate = recordDate;
    }

    public void setHabit(HabitEntity habit) {
        this.habit = habit;
        habit.getRecordList().add(this);
    }
}
