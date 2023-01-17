package com.herego.api.repositories.contracts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.herego.api.configurations.DriverConnection;
import com.herego.api.dictionaries.StoreProcedureEnum;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.models.Users;
import com.herego.api.repositories.implementations.UserImpl;
import static com.herego.api.utils.DataFormat.formaterSp;

@Dependent
public class UserRepository implements UserImpl {
    @Inject
    DriverConnection driverConnection;
    @Inject
    Logger log;

    @Override
    public List<Users> getUsers() {
        return null;
    }

    @Override
    public Optional<Users> getUserById(String idUser) throws ConnectionException, SQLException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        ResultSet resultSet = null;
        Optional<Users> responseUsers = Optional.empty();
        try {
            cstmt = connection.prepareCall(formaterSp(StoreProcedureEnum.C_USER_ID), ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = cstmt.executeQuery();
            while (resultSet.next()) {
                responseUsers = Optional.ofNullable(new Users(resultSet));
            }
        } catch (Exception e) {
            log.error("****[ERROR]****", e);
        }
        return responseUsers;
    }

    @Override
    public boolean createUser(Users newUser) {
        return false;
    }

    @Override
    public boolean updateUser(Users editUser) {
        return false;
    }

    @Override
    public boolean deleteUser(String idUser) {
        return false;
    }

}
