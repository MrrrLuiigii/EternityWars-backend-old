package com.eternitywars.api.DAL.Repositories.User;

import com.eternitywars.api.Factories.User.UserContainerFactory;
import com.eternitywars.api.Factories.User.UserFactory;
import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    private UserRepository userRepository = new UserRepository(new UserFactory());
    private UserContainerRepository userContainerRepository = new UserContainerRepository(new UserContainerFactory());

    @Test
    void updateUsername() {
        User user = new User();
        user.setUserId(3);
        user.setUsername("Updated");
        userRepository.UpdateUsername(user);

        User updatedUser = userContainerRepository.GetUserById(user.getUserId());
        assertEquals(user.getUsername(), updatedUser.getUsername());
    }

    @Test
    void updateAccountStatus() {
        User user = new User();
        user.setUserId(7);
        user.setAccountStatus(AccountStatus.Offline);
        userRepository.UpdateAccountStatus(user);

        User updatedUser = userContainerRepository.GetUserById(user.getUserId());
        assertEquals(user.getAccountStatus(), updatedUser.getAccountStatus());
    }

    @Test
    void updatePackAmount() {
        User user = new User();
        user.setPackAmount(100);
        user.setUserId(4);
        userRepository.UpdatePackAmount(user);

        User updatedUser = userContainerRepository.GetUserById(user.getUserId());
        assertEquals(user.getPackAmount(), updatedUser.getPackAmount());
    }

    @Test
    void updateGold() {
        User user = new User();
        user.setGold(100);
        user.setUserId(4);
        userRepository.UpdateGold(user);

        User updatedUser = userContainerRepository.GetUserById(user.getUserId());
        assertEquals(user.getPackAmount(), updatedUser.getPackAmount());
    }

    @AfterAll
    void reset() {
        User user = new User();
        user.setUserId(3);
        user.setUsername("toUpdateUser");
        userRepository.UpdateUsername(user);

        User user2 = new User();
        user2.setGold(0);
        user2.setUserId(4);
        user2.setPackAmount(0);
        userRepository.UpdatePackAmount(user2);
        userRepository.UpdateGold(user2);

        User user3 = new User();
        user3.setAccountStatus(AccountStatus.Online);
        userRepository.UpdateAccountStatus(user3);
    }
}