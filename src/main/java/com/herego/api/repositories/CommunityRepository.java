package com.herego.api.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.sql.Types;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.herego.api.configurations.DriverConnection;
import com.herego.api.controllers.dto.UpdateUser;
import com.herego.api.dictionaries.StoreProcedureEnum;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.CrudException;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.Community;
import com.herego.api.models.Users;
import static com.herego.api.utils.DataFormat.formaterSp;

@Dependent
public class CommunityRepository {
    @Inject
    DriverConnection driverConnection;
    @Inject
    Logger log;

    public List<Users> getUsers(int page, int max) throws ConnectionException, SQLException, NotFoundException {
        return null;
    }

    public Optional<Users> getUserById(String idUser) throws ConnectionException, SQLException, NotFoundException {
        return null;
    }

    public void createCommunity(Community newCommunity, String phone) throws SQLException, CrudException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        int code = 0;
        String msg = "";
        cstmt = connection.prepareCall(formaterSp(StoreProcedureEnum.I_COMMUNITY_ACCOUNT),
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        cstmt.setString(1, newCommunity.getName());
        cstmt.setString(2, newCommunity.getDescription());
        cstmt.setInt(3, newCommunity.getType());
        cstmt.setString(4, phone);
        cstmt.registerOutParameter(5, Types.VARCHAR);
        cstmt.registerOutParameter(6, Types.INTEGER);
        cstmt.execute();
        msg = cstmt.getString(5);
        code = cstmt.getInt(6);
        if (code != 0) {
            throw new CrudException(code, msg, "Solicitud no puede ser procesada", null);
        }
    }

    public void updateUser(String phoneUser, UpdateUser editUser) throws SQLException, CrudException {
    }

    public void deleteUser(String phoneUser, Integer state) throws SQLException, CrudException {
    }

}
