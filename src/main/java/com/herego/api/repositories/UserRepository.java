package com.herego.api.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.herego.api.controllers.dto.GetUsers;
import com.herego.api.controllers.dto.UpdateUser;
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

    public List<GetUsers> getUsers(String search, int page, int max)
            throws ConnectionException, SQLException, NotFoundException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        ResultSet resultSet = null;
        List<GetUsers> responseUsers = new ArrayList<>();
        cstmt = connection.prepareCall(formaterFun(StoreProcedureEnum.S_ALL_USER), ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        cstmt.setString(1, search);
        cstmt.setInt(2, page);
        cstmt.setInt(3, max);
        resultSet = cstmt.executeQuery();
        ResultSetMetaData md = resultSet.getMetaData();
        int columns = md.getColumnCount();
        HashMap<String, Object> row = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        while (resultSet.next()) {
            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i), resultSet.getObject(i));
            }
            if (!row.isEmpty()) {
                responseUsers.add(mapper.convertValue(row, new TypeReference<GetUsers>() {
                }));
            }
        }
        if (responseUsers.isEmpty())
            throw new NotFoundException(ResponseEnum.NOTFOUND);

        return responseUsers;
    }

    public Optional<GetUsers> getUserById(String idUser) throws ConnectionException, SQLException, NotFoundException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        ResultSet resultSet = null;
        Optional<GetUsers> responseUsers = Optional.empty();
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
            responseUsers = Optional.ofNullable(mapper.convertValue(row, new TypeReference<GetUsers>() {
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
        cstmt = connection.prepareCall(formaterSp(StoreProcedureEnum.I_USER), ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        cstmt.setString(1, newUser.getName());
        cstmt.setString(2, newUser.getLastName());
        cstmt.setInt(3, newUser.getUserType());
        cstmt.setDate(4, newUser.getBirthday());
        cstmt.setString(5, newUser.getPhone());
        cstmt.setString(6, newUser.getDni());
        cstmt.setString(7, newUser.getEmail());
        cstmt.registerOutParameter(8, Types.VARCHAR);
        cstmt.registerOutParameter(9, Types.INTEGER);
        cstmt.execute();
        msg = cstmt.getString(8);
        code = cstmt.getInt(9);
        if (code != 0) {
            throw new CrudException(code, msg, "Solicitud no puede ser procesada", null);
        }
    }

    public void updateUser(String phoneUser, UpdateUser editUser) throws SQLException, CrudException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        int code = 0;
        String msg = "";
        cstmt = connection.prepareCall(formaterSp(StoreProcedureEnum.U_USER_ID), ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        cstmt.setInt(1, editUser.getUserType());
        cstmt.setString(2, phoneUser);
        cstmt.setString(3, editUser.getEmail());
        cstmt.setInt(4, editUser.getUserState());
        cstmt.registerOutParameter(5, Types.VARCHAR);
        cstmt.registerOutParameter(6, Types.INTEGER);
        cstmt.execute();
        msg = cstmt.getString(5);
        code = cstmt.getInt(6);
        if (code != 0) {
            throw new CrudException(code, msg, "Solicitud no puede ser procesada", null);
        }
    }

    public void deleteUser(String phoneUser, Integer state) throws SQLException, CrudException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        int code = 0;
        String msg = "";
        cstmt = connection.prepareCall(formaterSp(StoreProcedureEnum.U_USER_STATE_ID), ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        cstmt.setString(1, phoneUser);
        cstmt.setInt(2, state);
        cstmt.registerOutParameter(3, Types.VARCHAR);
        cstmt.registerOutParameter(4, Types.INTEGER);
        cstmt.execute();
        msg = cstmt.getString(3);
        code = cstmt.getInt(4);
        if (code != 0) {
            throw new CrudException(code, msg, "Solicitud no puede ser procesada", null);
        }
    }

}
