package com.eternitywars.api.DAL.Contexts.User;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.User.IUserContext;
import com.eternitywars.api.Models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

    @Override
    public int GetPackAmount(int userId)
    {
        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "SELECT pack_amount FROM user WHERE id = " + userId;
            try (CallableStatement cst = conn.prepareCall(query))
            {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next())
                {
                    int packamount = rs.getInt("pack_amount");
                    return packamount;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public void UpdatePackAmount(User user)
    {
        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "Update user SET pack_amount = " +  user.getPackAmount() + "WHERE id = " + user.getId();
            try (CallableStatement cst = conn.prepareCall(query))
            {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
