package com.eternitywars.api.DAL.Contexts;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.IUserContainerContext;
import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserContainerContext implements IUserContainerContext
{
    private DatabaseConnection dbc;

    public UserContainerContext(){
        dbc = new DatabaseConnection();
    }

    @Override
    public List<User> GetUsers()
    {
        List<User> users = new ArrayList<>();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call GetUsers()}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setGoogleId(rs.getString("google_id"));
                        user.setEmail(rs.getString("email"));
                        user.setUsername(rs.getString("username"));
                        user.setAccountStatus(AccountStatus.valueOf(rs.getString("account_status")));
                        user.setGold(rs.getInt("gold"));
                        user.setPackAmount(rs.getInt("pack_amount"));

                        users.add(user);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return users;
    }
}
