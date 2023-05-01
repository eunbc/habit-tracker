package com.yb.habittracker.domain.habit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yb.habittracker.domain.habit.dto.HabitListDto;
import com.yb.habittracker.domain.habit.dto.HabitSaveDto;
import com.yb.habittracker.domain.habit.dto.HabitUpdateDto;
import com.yb.habittracker.domain.member.MemberEntity;
import com.yb.habittracker.domain.member.MemberRepository;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {

	@InjectMocks
	private HabitService habitService;

	@Mock
	private HabitRepository habitRepository;
	@Mock
	private MemberRepository memberRepository;

	@Test
	void savehabit_success() {
		//given
		HabitSaveDto dto = HabitSaveDto.builder().habitId("test-habit-id").name("test-name").build();
		MemberEntity member = getMember();
		when(memberRepository.findById(member.getMemberId())).thenReturn(Optional.of(member));

		//when
		habitService.save(dto, member.getMemberId());

		//then
		verify(habitRepository, times(1)).save(dto.toEntity().setMember(member));
	}

	@Test
	void getHabitListByMember() {
		//given
		MemberEntity member = getMember();
		when(memberRepository.findById(member.getMemberId())).thenReturn(Optional.of(member));
		List<HabitEntity> list = new ArrayList<>();
		list.add(HabitEntity.builder().habitId("test-habit_id").name("test-name").build());
		when(habitRepository.findAllByMember(member)).thenReturn(list);

		//when
		List<HabitListDto> habitListDtos = habitService.getHabitListByMember(member.getMemberId());

		//then
		assertEquals(1, habitListDtos.size());
	}

	private MemberEntity getMember() {
		return MemberEntity.builder().memberId("test-member").build();
	}

	@Test
	void updateHabitName() {
		//given
		HabitUpdateDto habitUpdateDto = HabitUpdateDto.builder().habitId("test-habit-id").build();
		when(habitRepository.findById(habitUpdateDto.getHabitId())).thenReturn(Optional.of(habitUpdateDto.toEntity()));

		//when
		habitService.updateHabitName(habitUpdateDto);

		//then
		verify(habitRepository, times(1)).findById(habitUpdateDto.getHabitId());
	}

	@Test
	void delete() {
		//given
		HabitEntity entity = HabitEntity.builder().habitId("test-habit-id").name("test-name").build();
		when(habitRepository.findById(entity.getHabitId())).thenReturn(Optional.of(entity));

		//when
		habitService.deleteHabit(entity.getHabitId());

		//then
		verify(habitRepository, times(1)).deleteById(entity.getHabitId());
	}
}