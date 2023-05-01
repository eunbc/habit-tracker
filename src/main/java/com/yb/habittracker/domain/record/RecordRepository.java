package com.yb.habittracker.domain.record;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yb.habittracker.domain.habit.HabitEntity;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, String> {

    List<RecordEntity> findAllByHabit(HabitEntity habit);
}
