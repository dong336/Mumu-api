package io.Mumuapi.auth;

import io.Mumuapi.dto.common.ResCode;
import io.Mumuapi.entity.Member;
import io.Mumuapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public ResCode authAdminLogin(String userId, String userPw) {
        Member foundMember = memberRepository.findByUserId(userId);
        if(foundMember == null)
            return ResCode.FAIL_MEMBER_NO_ID;

        Member authenticedMember = memberRepository.findByUserIdAndUserPw(userId, userPw);
        if(authenticedMember == null)
            return ResCode.FAIL_MEMBER_INVALID_PW;

        return ResCode.SUCCESS;
    }

    public boolean authUserId(String userId) {
        Member member = memberRepository.findByUserId(userId);

        return member != null;
    }
}
