package com.yb.habittracker.domain.habit;

import com.github.f4b6a3.ulid.UlidCreator;
import com.yb.habittracker.domain.member.MemberEntity;
import com.yb.habittracker.domain.member.MemberRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

@SpringBootTest
// @ActiveProfiles("local-mysql-test")
class HabitRepositoryTest {

    @Autowired
    HabitRepository habitRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager entityManager;

    private static HabitEntity habit;

    @BeforeAll
    public static void init() {
        habit = HabitEntity.builder().habitId(UlidCreator.getUlid().toString()).name("test").build();
    }

    @Test
    public void saveHabit() {
        //when
        HabitEntity entity = habitRepository.save(habit);

        //then
        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo("test");
    }

    @Test
    public void findAll() {
        //given
        habitRepository.save(habit);

        //when
        List<HabitEntity> result = habitRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).hasSize(1).extracting(HabitEntity::getName).containsExactly(habit.getName());
    }

    @Test
    @Transactional
    public void findAllByMember() {
        //given
        MemberEntity memberSave = MemberEntity.builder().memberId("test_id").email("test_email").nickname("test_nickname").build();
        entityManager.persist(memberSave);

        HabitEntity habit1 = HabitEntity.builder().habitId(UlidCreator.getUlid().toString()).name("test_habit1").build();
		habit1.setMember(memberSave);
        memberSave.getHabits().add(habit1);
        habitRepository.save(habit1);

		HabitEntity habit2 = HabitEntity.builder().habitId(UlidCreator.getUlid().toString()).name("test_habit2").build();
        habit2.setMember(memberSave);
        memberSave.getHabits().add(habit2);
		habitRepository.save(habit2);

        //when
        List<HabitEntity> habitEntityList = habitRepository.findAllByMember(memberSave);

        //then
        assertThat(habitEntityList).hasSize(2);
        assertThat(habitEntityList.get(0).getName()).isEqualTo(habit1.getName());
        assertThat(habitEntityList.get(1).getName()).isEqualTo(habit2.getName());
    }

    @Test
    public void updateHabit() {
        //given
        habitRepository.save(habit);
        String updated = "updated";
        HabitEntity entityUpdate = HabitDto.builder().habitId(habit.getHabitId()).name(updated).build().toEntity();

        //when
        HabitEntity save = habitRepository.save(entityUpdate);

        //then
        assertThat(save.getName()).isEqualTo(updated);
    }

    @Test
    public void deleteHabit() {
        //given
        HabitEntity entity = habitRepository.save(habit);

        //when
        habitRepository.deleteById(entity.getHabitId());

        //then
        Optional<HabitEntity> find = habitRepository.findById(habit.getHabitId());
        assertThat(find.isEmpty()).isTrue();
    }


}