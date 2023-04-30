package com.yb.habittracker.domain.habit;

import com.yb.habittracker.domain.member.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class HabitEntity {
    @Id
    private String habitId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
    private String name;
    @OneToMany(mappedBy = "habit")
    private List<RecordEntity> recordList = new ArrayList<>();

    protected HabitEntity() {
    }

    @Builder
    public HabitEntity(String habitId, MemberEntity member, String name) {
        this.habitId = habitId;
        this.member = member;
        this.name = name;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }



}
