package com.herego.api.configurations;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.logging.Logger;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.exceptions.ConnectionException;

import io.agroal.api.AgroalDataSource;

@Singleton
public class DriverConnection {
    @Inject
    AgroalDataSource postgresDataSource;
    @Inject
    Logger log;

    public Connection createConection() throws ConnectionException {
        Connection connection;
        try {
            connection = postgresDataSource.getConnection();
            if (connection.isClosed()) {
                throw new ConnectionException(ResponseEnum.NOTCONNECTION);
            }
            log.info("*****Inicializando llamada a base*****");
        } catch (SQLException e) {
            log.error("*****[ERROR]***** ", e);
            throw new ConnectionException(ResponseEnum.NOTCONNECTION, e);
        }
        return connection;
    }

}
