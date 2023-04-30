package com.yb.habittracker.domain.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.yb.habittracker.domain.habit.HabitEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class MemberEntity {
    @Id
    private String memberId;
    private String nickname;
    private String email;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<HabitEntity> habits = new ArrayList<>();

    protected MemberEntity() {}

    @Builder
    public MemberEntity(String memberId, String nickname, String email) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.email = email;
    }


}
