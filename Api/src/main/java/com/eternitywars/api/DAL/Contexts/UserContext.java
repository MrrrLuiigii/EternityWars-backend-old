package com.eternitywars.api.DAL.Contexts;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.IUserContext;
import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UserContext implements IUserContext
{
    private DatabaseConnection dbc;

    public UserContext()
    {
        this.dbc = new DatabaseConnection();
    }

    @Override
    public void ChangeUserName( int userId, String username)
    {
        String yeet = null;

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call ChangeUserName(?,?)}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, userId);
                cst.setString(2, username);
                ResultSet rs = cst.executeQuery();
                try
                {
                    while (rs.next())
                    {
                        yeet = rs.getString("username");
                    }
                }
                catch (Exception e)
                {

                }

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void ChangeUserStatus(User user)
    {

    }
}
