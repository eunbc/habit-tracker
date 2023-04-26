package com.yb.habittracker.domain.member;

import com.yb.habittracker.domain.habit.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<HabitEntity, String> {
}
