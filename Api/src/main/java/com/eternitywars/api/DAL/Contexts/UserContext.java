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

    public UserContext(){
        dbc = new DatabaseConnection();
    }


}
