package io.backofficeApi.repository;

import io.backofficeApi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {

    Member findByUserId(String userId);
    Member findByUserIdAndUserPw(String userId, String userPw);
}
