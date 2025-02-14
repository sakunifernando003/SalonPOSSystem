package org.example.demo.dao.custom.Impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.UserDAO;
import org.example.demo.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");
        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()){
            users.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return users;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('1', LPAD(IFNULL(MAX(SUBSTRING(user_id, 2)) + 1, 1), 3, '0')) AS next_user_id FROM user;");
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(Integer userId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM user WHERE user_id=?", userId);
    }

    @Override

    public boolean save(User user) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user VALUES(?,?,?,?,?)",
                user.getName(),
                user.getId(),
                user.getEmail(),
                user.getRole(),
               user.getPassword());
    }



    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET name=?, email=?, role=?, password=? WHERE user_id=?",
                user.getName(),
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getPassword());

    }


}
