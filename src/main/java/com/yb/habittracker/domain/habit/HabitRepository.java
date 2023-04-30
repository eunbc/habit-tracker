package com.yb.habittracker.domain.habit;

import com.yb.habittracker.domain.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, String> {

    List<HabitEntity> findAllByMember(MemberEntity member);
}
