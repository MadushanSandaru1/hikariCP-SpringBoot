package com.example.hikari.dao;

import com.example.hikari.beans.User;
import com.example.hikari.utils.UserConst;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    private final HikariDataSource hikariDataSource;

    public UserDAO(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        try(Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UserConst.GET_ALL_USERS_QUERY);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(rs.getInt(UserConst.TBL_COL_1), rs.getString(UserConst.TBL_COL_2)));
                }
            } catch (SQLException e) {
                logger.error("Error Occurred when executing query. "+e.getMessage());
                throw new IllegalStateException("Error occurred when executing query");
            }

        } catch (SQLException e) {
            logger.error("Error Occurred when establish database connection. "+e.getMessage());
            throw new IllegalStateException("Error Occurred when establish database connection.");
        }

        return users;
    }

    public User getUserById(Integer id) {

        User user = null;

        try(Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UserConst.GET_USER_BY_ID_QUERY);
            ps.setString(1, id.toString());

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(rs.getInt(UserConst.TBL_COL_1), UserConst.TBL_COL_2);
                } else {
                    logger.error("Record not found in the table");
                }

            } catch (SQLException e) {
                logger.error("Error Occurred when executing query. "+e.getMessage());
                throw new IllegalStateException("Error occurred when executing query");
            }

        } catch (SQLException e) {
            logger.error("Error Occurred when establish database connection. "+e.getMessage());
            throw new IllegalStateException("Error Occurred when establish database connection.");
        }

        return user;
    }

}
