package hannaeismant.m101j.user;

import hannaeismant.m101j.exceptions.TimeoutException;
import hannaeismant.m101j.exceptions.UnknownException;
import hannaeismant.m101j.exceptions.UserAlreadyExistException;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface UserService {

    User create(final @NotNull String _username, final @NotNull String _password)
            throws TimeoutException, UserAlreadyExistException, UnknownException;

    User find(final @NotNull String _username);

    User updatePassword(final @NotNull String _username, final @NotNull String _newPassword);

    boolean checkCredentials(final @NotNull String _username, final @NotNull String _password);
}
