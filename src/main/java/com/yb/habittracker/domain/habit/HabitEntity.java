package com.yb.habittracker.domain.habit;

import com.yb.habittracker.domain.habit.dto.HabitUpdateDto;
import com.yb.habittracker.domain.member.MemberEntity;
import com.yb.habittracker.domain.record.RecordEntity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@EqualsAndHashCode
@Accessors(chain = true)
public class HabitEntity {
    @Id
    private String habitId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
    private String name;
    @OneToMany(mappedBy = "habit", fetch = FetchType.LAZY)
    private List<RecordEntity> recordList = new ArrayList<>();

    protected HabitEntity() {
    }

    @Builder
    public HabitEntity(String habitId, MemberEntity member, String name) {
        this.habitId = habitId;
        this.member = member;
        this.name = name;
    }

    public HabitEntity setMember(MemberEntity member) {
        this.member = member;
        member.getHabits().add(this);
        return this;
    }

    public void updateHabitName(HabitUpdateDto habitDto) {
        this.name = habitDto.getName();
    }
}
