package com.yb.habittracker.domain.habit;

import com.github.f4b6a3.ulid.UlidCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


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
                .build();
    }

    @Test
    public void saveHabit() {
        HabitEntity entity = habitRepository.save(habit);

        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo("test");
    }

    @Test
    public void updateHabit() {
        habitRepository.save(habit);
        String updated = "updated";
        HabitEntity entityUpdate = HabitDto.builder().habitId(habit.getHabitId()).name(updated).build().toEntity();

        HabitEntity save = habitRepository.save(entityUpdate);

        assertThat(save.getName()).isEqualTo(updated);
    }

    @Test
    public void deleteHabit() {
        HabitEntity entity = habitRepository.save(habit);

        habitRepository.deleteById(entity.getHabitId());

        Optional<HabitEntity> find = habitRepository.findById(habit.getHabitId());
        assertThat(find.isEmpty()).isTrue();
    }




}