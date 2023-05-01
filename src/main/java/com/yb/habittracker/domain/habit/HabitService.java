package com.yb.habittracker.domain.habit;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yb.habittracker.domain.habit.dto.HabitListDto;
import com.yb.habittracker.domain.habit.dto.HabitSaveDto;
import com.yb.habittracker.domain.habit.dto.HabitUpdateDto;
import com.yb.habittracker.domain.member.MemberEntity;
import com.yb.habittracker.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class HabitService {

	private final HabitRepository habitRepository;
	private final MemberRepository memberRepository;

	/**
	 * 습관 생성
	 */
	public void save(HabitSaveDto habitDto, String memberId) {
		//todo : member 조회 실패 예외 추가
		MemberEntity member = memberRepository.findById(memberId).orElseThrow();
		HabitEntity habit = habitDto.toEntity().setMember(member);
		habitRepository.save(habit);
	}

	/**
	 * 습관 목록 조회
	 */
	public List<HabitListDto> getHabitListByMember(String memberId) {
		MemberEntity member = memberRepository.findById(memberId).orElseThrow();
		return habitRepository.findAllByMember(member)
			.stream()
			.map(HabitListDto::new)
			.collect(Collectors.toList());
	}

	/**
	 * 습관 수정
	 */
	public void updateHabitName(HabitUpdateDto habitDto) {
		HabitEntity entity = habitRepository.findById(habitDto.getHabitId()).orElseThrow();
		entity.updateHabitName(habitDto);
	}

	/**
	 * 습관 삭제
	 */
	public void deleteHabit(String habitId) {
		HabitEntity entity = habitRepository.findById(habitId).orElseThrow();
		habitRepository.deleteById(entity.getHabitId());
	}

}
