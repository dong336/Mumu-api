package io.Mumuapi.entity;

import io.Mumuapi.entity.vo.RoleType;
import io.Mumuapi.entity.vo.Timestamps;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member", indexes = {
        @Index(name="IDX_MEMBER_1", columnList = "userId"),
        @Index(name="IDX_MEMBER_2", columnList = "roleType"),
        @Index(name="IDX_MEMBER_3", columnList = "userId, roleType")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String userId;

    @Column(nullable = false, length = 255)
    private String userPw;

    @Column(length = 50)
    private String userName;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Embedded
    private Timestamps timestamps;

    @Builder
    public Member(String userId, String userPw, String userName, RoleType roleType) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.roleType = roleType;
    }

    // 비즈니스 로직

    public static Member createAdminMember(String userId, String userPw, String userName) {

        return new Member(userId, userPw, userName, RoleType.ADMIN);
    }
}
