package hannaeismant.m101j.session;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface SessionService {

    Session createSession(final @NotNull String username, final @NotNull String token);

    Session findSession(final @NotNull String token);

    void removeSession(final @NotNull String token);
}
