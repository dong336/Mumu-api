package io.Mumuapi;

import io.Mumuapi.auth.AuthUtil;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

@SpringBootTest
class JwtTests {
	@Autowired
    AuthUtil jwtUtil;

	@Test
	void testJwtUtil() {
		String token = jwtUtil.generateJws("kdw123", "ADMIN");
		String username = jwtUtil.getUsernameFromToken(token);
		String role = jwtUtil.getRoleFromToken(token);

		System.out.println("testJwt: " + username);
		System.out.println("testJwt: " + role);

		assert jwtUtil.validateToken(token);
	}

	@Test
	void jjwt() {
		SecretKey key = Jwts.SIG.HS256.key().build();

		String jws = Jwts.builder().subject("Joe").signWith(key).compact();

		assert Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject().equals("Joe");
	}

}
