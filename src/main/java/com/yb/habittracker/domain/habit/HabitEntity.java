package com.yb.habittracker.domain.habit;

import com.yb.habittracker.domain.member.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class HabitEntity {
    @Id
    private String habitId;
    @ManyToOne
    private MemberEntity member;
    private String name;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    @OneToMany(mappedBy = "habit")
    private List<RecordEntity> recordList = new ArrayList<>();

    protected HabitEntity() {
    }

    @Builder
    public HabitEntity(String habitId, MemberEntity member, String name, ZonedDateTime createdTime, ZonedDateTime updatedTime) {
        this.habitId = habitId;
        this.member = member;
        this.name = name;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
