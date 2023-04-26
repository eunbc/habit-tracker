package com.yb.habittracker.domain.member;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class MemberEntity {
    @Id
    private String memberId;
    private String nickname;
    private String email;

}
