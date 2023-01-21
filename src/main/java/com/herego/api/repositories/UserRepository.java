package com.herego.api.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.sql.Types;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herego.api.configurations.DriverConnection;
import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.dictionaries.StoreProcedureEnum;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.CrudException;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.Users;
import static com.herego.api.utils.DataFormat.formaterFun;
import static com.herego.api.utils.DataFormat.formaterSp;

@Dependent
public class UserRepository {
    @Inject
    DriverConnection driverConnection;
    @Inject
    Logger log;

    public List<Users> getUsers() {
        return null;
    }

    public Optional<Users> getUserById(String idUser) throws ConnectionException, SQLException, NotFoundException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        ResultSet resultSet = null;
        Optional<Users> responseUsers = Optional.empty();
        cstmt = connection.prepareCall(formaterFun(StoreProcedureEnum.S_USER_ID), ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        cstmt.setString(1, idUser);
        resultSet = cstmt.executeQuery();
        ResultSetMetaData md = resultSet.getMetaData();
        int columns = md.getColumnCount();
        HashMap<String, Object> row = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        while (resultSet.next()) {
            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i), resultSet.getObject(i));
            }
        }
        if (!row.isEmpty())
            responseUsers = Optional.ofNullable(mapper.convertValue(row, new TypeReference<Users>() {
            }));
        else {
            throw new NotFoundException(ResponseEnum.NOTFOUND);
        }
        return responseUsers;
    }

    public void createUser(Users newUser) throws SQLException, CrudException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        int code = 0;
        String msg = "";
        cstmt = connection.prepareCall(formaterSp(StoreProcedureEnum.I_USER_ID), ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        cstmt.setString(1, newUser.getName());
        cstmt.setString(2, newUser.getLastName());
        cstmt.setString(3, newUser.getUserType());
        cstmt.setDate(4, newUser.getBirthday());
        cstmt.setString(5, newUser.getPhone());
        cstmt.setString(6, newUser.getDni());
        cstmt.setString(7, newUser.getEmail());
        cstmt.setString(8, newUser.getUserState());
        cstmt.registerOutParameter(9, Types.VARCHAR);
        cstmt.registerOutParameter(10, Types.INTEGER);
        cstmt.execute();
        msg = cstmt.getString(9);
        code = cstmt.getInt(10);
        if (code != 0) {
            throw new CrudException(code, "Solicitud no puede ser procesada", msg, null);
        }
    }

    public boolean updateUser(Users editUser) {
        return false;
    }

    public boolean deleteUser(String idUser) {
        return false;
    }

}
