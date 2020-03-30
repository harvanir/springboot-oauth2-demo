package org.harvanir.oauth2.server.support;

import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

/**
 * @author Harvan Irsyadi
 */
public class DefaultUserDetailService extends JdbcDaoImpl {

    public DefaultUserDetailService(DataSource dataSource) {
        setDataSource(dataSource);
        setEnableAuthorities(true);
        setEnableGroups(true);
    }
}
