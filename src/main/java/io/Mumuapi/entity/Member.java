package io.Mumuapi.entity;

import io.Mumuapi.entity.vo.RoleType;
import io.Mumuapi.entity.vo.Timestamps;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member", indexes = {
        @Index(name="IDX_MEMBER_1", columnList = "userId"),
        @Index(name="IDX_MEMBER_2", columnList = "roleType")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String userId;

    @Column(length = 255)
    private String userPw;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Embedded
    private Timestamps timestamps;

    public Member(String userId, String userPw, String name, RoleType roleType) {
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.roleType = roleType;
    }

    // 비즈니스 로직

    public static Member createAdminMember(String userId, String userPw, String name) {

        return new Member(userId, userPw, name, RoleType.ADMIN);
    }
}
