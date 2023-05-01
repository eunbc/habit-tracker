package com.yb.habittracker.domain.habit.dto;

import com.yb.habittracker.domain.habit.HabitEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HabitUpdateDto {
    private String habitId;
    private String name;
    public HabitEntity toEntity() {
        return HabitEntity.builder().habitId(habitId).name(name).build();
    }
}
