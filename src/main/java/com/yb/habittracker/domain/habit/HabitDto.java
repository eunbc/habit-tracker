package com.yb.habittracker.domain.habit;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HabitDto {
    private String habitId;
    private String name;

    public HabitEntity toEntity() {
        return HabitEntity.builder().habitId(habitId).name(name).build();
    }
}
