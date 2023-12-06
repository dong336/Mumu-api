package io.Mumuapi.auth;

import io.Mumuapi.controller.api.admin.dto.common.ResCode;
import io.Mumuapi.controller.api.admin.dto.common.ResWrapper;
import io.Mumuapi.entity.Member;
import io.Mumuapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public ResWrapper<AuthResponse> authAdminLogin(String userId, String userPw) {
        Member foundMember = memberRepository.findByUserId(userId);
        if(foundMember == null)
            return new ResWrapper<>(ResCode.FAIL_MEMBER_NO_ID);

        Member authenticedMember = memberRepository.findByUserIdAndUserPw(userId, userPw);
        if(authenticedMember == null)
            return new ResWrapper<>(ResCode.FAIL_MEMBER_INVALID_PW);

        String id = authenticedMember.getUserId();
        String name = authenticedMember.getUserName();

        return new ResWrapper<>(ResCode.SUCCESS, new AuthResponse(id, name));
    }

    public boolean authUserId(String userId) {
        Member member = memberRepository.findByUserId(userId);

        return member != null;
    }
}
