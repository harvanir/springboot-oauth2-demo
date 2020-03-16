package org.harvanir.oauth2.server.oauth2.support;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

/**
 * @author Harvan Irsyadi
 */
public class DefaultUserDetailService extends JdbcDaoImpl {

    public static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled,client_id from users where username = ?";

    public DefaultUserDetailService(DataSource dataSource) {
        setDataSource(dataSource);
        setUsersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY);
    }

    private JdbcTemplate getJdbcTemplateInternal() {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        Assert.notNull(jdbcTemplate, () -> "No JdbcTemplate found.");
        return jdbcTemplate;
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplateInternal().query(getUsersByUsernameQuery(),
                new String[]{username}, (rs, rowNum) -> {
                    String username1 = rs.getString(1);
                    String password = rs.getString(2);
                    boolean enabled = rs.getBoolean(3);
                    String clientId = rs.getString(4);
                    return new ClientAwareUserDetails(new User(username1, password, enabled, true, true, true,
                            AuthorityUtils.NO_AUTHORITIES)).setClientId(clientId);
                });
    }

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
        UserDetails userDetails = super.createUserDetails(username, userFromUserQuery, combinedAuthorities);

        return new ClientAwareUserDetails(userDetails).setClientId(((ClientAwareUserDetails) userFromUserQuery).getClientId());
    }

    static class ClientAwareUserDetails implements UserDetails, CredentialsContainer {

        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        private final UserDetails user;

        private String clientId;

        public ClientAwareUserDetails(UserDetails user) {
            this.user = user;
        }

        @Override
        public void eraseCredentials() {
            if (user instanceof User) {
                ((User) user).eraseCredentials();
            }
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return user.getAuthorities();
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return user.isAccountNonExpired();
        }

        @Override
        public boolean isAccountNonLocked() {
            return user.isAccountNonLocked();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return user.isCredentialsNonExpired();
        }

        @Override
        public boolean isEnabled() {
            return user.isEnabled();
        }

        public String getClientId() {
            return clientId;
        }

        ClientAwareUserDetails setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }
    }
}
