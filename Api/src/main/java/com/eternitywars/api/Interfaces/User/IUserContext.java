package com.eternitywars.api.Interfaces.User;

import com.eternitywars.api.Models.User;

public interface IUserContext
{
    boolean UpdateUsername(User user);

    boolean UpdateAccountStatus(User user);

    boolean UpdatePackAmount(User user);

    boolean UpdateGold(User user);
}
