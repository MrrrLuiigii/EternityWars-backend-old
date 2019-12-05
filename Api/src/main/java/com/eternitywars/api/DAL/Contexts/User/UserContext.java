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

    @Override
    public void ChangeUserName( int userId, String username)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call ChangeUserName(?,?)}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, userId);
                cst.setString(2, username);
                cst.executeQuery();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void ChangeUserStatus(User user)
    {

    }
    
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
    
    public void UpdatePackAmount(User user)
    {
        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "update user set pack_amount = ? where id = ?;";
            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getPackAmount());
                pst.setInt(2, user.getId());
                pst.executeQuery(query);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void UpdateGold(User user)
    {
        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "update user set gold = ? where id = ?;";
            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getGold());
                pst.setInt(2, user.getId());
                pst.executeQuery(query);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
