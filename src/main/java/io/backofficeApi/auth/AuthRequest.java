package io.backofficeApi.auth;

public record AuthRequest(
        String username,
        String password) {
}
