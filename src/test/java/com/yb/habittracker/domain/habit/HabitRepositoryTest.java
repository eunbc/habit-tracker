package com.yb.habittracker.domain.habit;

import com.github.f4b6a3.ulid.UlidCreator;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@SpringBootTest
class HabitRepositoryTest {

    @Autowired
    HabitRepository habitRepository;

    private static HabitEntity habit;

    @BeforeAll
    public static void init() {
        habit = HabitEntity.builder()
                .habitId(UlidCreator.getUlid().toString())
                .name("test")
                .createdTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .updatedTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();
    }

    @Test
    public void saveHabit() {
        HabitEntity entity = habitRepository.save(habit);
        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo("test");
    }


}