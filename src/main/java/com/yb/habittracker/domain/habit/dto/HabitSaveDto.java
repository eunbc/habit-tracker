package com.yb.habittracker.domain.habit.dto;

import com.yb.habittracker.domain.habit.HabitEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Builder
@Accessors(chain = true)
public class HabitSaveDto {
    private String habitId;
    private String name;
    public HabitEntity toEntity() {
        return HabitEntity.builder().habitId(habitId).name(name).build();
    }
}
