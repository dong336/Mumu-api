package io.Mumuapi.auth;

import io.Mumuapi.controller.api.dto.common.ResponseCode;
import io.Mumuapi.controller.api.dto.common.ResponseWrapper;
import io.Mumuapi.entity.Member;
import io.Mumuapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public ResponseCode authAdminLogin(String userId, String userPw) {
        Member foundMember = memberRepository.findByUserId(userId);
        if(foundMember == null)
            return ResponseCode.FAIL_MEMBER_NO_ID;

        Member authenticedMember = memberRepository.findByUserIdAndUserPw(userId, userPw);
        if(authenticedMember == null)
            return ResponseCode.FAIL_MEMBER_INVALID_PW;

        return ResponseCode.SUCCESS;
    }

    public boolean authUserId(String userId) {
        Member member = memberRepository.findByUserId(userId);

        return member != null;
    }
}
