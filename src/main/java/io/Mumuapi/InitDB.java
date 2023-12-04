package io.Mumuapi;

import io.Mumuapi.entity.Member;
import io.Mumuapi.entity.vo.RoleType;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initMember();
    }

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void initMember() {
            Member admin = Member.builder()
                    .userName("관리자")
                    .roleType(RoleType.ADMIN)
                    .userId("admin")
                    .userPw("0000")
                    .build();

            em.persist(admin);
        }
    }
}
