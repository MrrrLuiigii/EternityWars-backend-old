package com.eternitywars.api.DAL.Contexts.User;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.User.IUserContext;
import com.eternitywars.api.Models.User;

import java.sql.*;

public class UserContext implements IUserContext
{
    private DatabaseConnection dbc;

    public UserContext()
    {
        this.dbc = new DatabaseConnection();
    }

    public User UpdateUsername(User user)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call ChangeUserName(?,?)}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, user.getUserId());
                cst.setString(2, user.getUsername());

                try(ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        user.setUsername(rs.getString("username"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return user;
    }

    public boolean UpdateAccountStatus(User user)
    {
        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "update user set account_status = ? where id = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setString(1, user.getAccountStatus().toString());
                pst.setInt(2, user.getUserId());
                pst.executeUpdate();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }


        return true;
    }

    public boolean UpdatePackAmount(User user)
    {
        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "update user set pack_amount = ? where id = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getPackAmount());
                pst.setInt(2, user.getUserId());
                pst.executeUpdate();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;
    }
}
