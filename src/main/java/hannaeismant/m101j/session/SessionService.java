package hannaeismant.m101j.session;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface SessionService {

    Session createSession(final @NotNull String _username, final @NotNull String _token);

    Session findSession(final @NotNull String _token);

    void removeSession(final @NotNull String _token);
}
