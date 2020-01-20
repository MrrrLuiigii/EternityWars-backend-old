package com.eternitywars.api.DAL.Contexts.User;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.IDatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;
import com.eternitywars.api.Interfaces.User.IUserContainerContext;
import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;

import java.sql.*;

public class UserContainerSqlContext implements IUserContainerContext
{
    private IDatabaseConnection dbc;

    public UserContainerSqlContext(DatabaseConnection dbc)
    {
        this.dbc = dbc;
    }

    public UserContainerSqlContext(TestDatabaseConnection dbc)
    {
        this.dbc = dbc;
    }



    public User GetUserById(int userId)
    {
        User user = new User();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `username`, `email`, `account_status`, `gold`, `pack_amount` " +
                    "from user " +
                    "where id = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, userId);

                try (ResultSet rs = pst.executeQuery())
                {
                    user = FillUser(rs);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return user;
    }

    public User GetUserByUsername(String username)
    {
        User user = new User();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `username`, `email`, `account_status`, `gold`, `pack_amount` " +
                    "from user " +
                    "where `username` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setString(1, username);

                try (ResultSet rs = pst.executeQuery())
                {
                    user = FillUser(rs);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return user;
    }

    public User GetUserByEmail(String email)
    {
        User user = new User();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `username`, `email`, `account_status`, `gold`, `pack_amount` " +
                    "from user " +
                    "where `email` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setString(1, email);

                try (ResultSet rs = pst.executeQuery())
                {
                    user = FillUser(rs);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return user;
    }

    public UserCollection GetUsers()
    {
        UserCollection userCollection = new UserCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `username`, `email`, `account_status`, `gold`, `pack_amount` from user;";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                try (ResultSet rs = cst.executeQuery())
                {
                    userCollection = FillUserCollection(rs);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return userCollection;
    }

    public User AddUser(User user)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call AddUser(?, ?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setString(1, user.getUsername());
                cst.setString(2, user.getEmail());

                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        user.setUserId(rs.getInt("id"));
                    }
                }

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        user.setAccountStatus(AccountStatus.Online);
        user.setGold(0);
        user.setPackAmount(0);

        return user;
    }

    public boolean DeleteUser(User user)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call DeleteUser(?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, user.getUserId());
                cst.executeQuery();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;
    }

    private User FillUser(ResultSet rs) throws SQLException
    {
        User user = new User();

        while (rs.next())
        {
            user.setUserId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setAccountStatus(AccountStatus.valueOf(rs.getString("account_status")));
            user.setGold(rs.getInt("gold"));
            user.setPackAmount(rs.getInt("pack_amount"));
        }

        return user;
    }

    private UserCollection FillUserCollection(ResultSet rs) throws SQLException
    {
        UserCollection userCollection = new UserCollection();

        while (rs.next())
        {
            User user = new User();
            user.setUserId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setAccountStatus(AccountStatus.valueOf(rs.getString("account_status")));
            user.setGold(rs.getInt("gold"));
            user.setPackAmount(rs.getInt("pack_amount"));
            userCollection.addUser(user);
        }

        return userCollection;
    }
}
