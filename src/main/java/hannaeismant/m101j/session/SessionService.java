package hannaeismant.m101j.session;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface SessionService {

    Session create(final @NotNull String _username, final @NotNull String _token);

    Session find(final @NotNull String _token);

    void remove(final @NotNull String _token);

    boolean check(final @NotNull String _token);
}
