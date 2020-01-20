package com.eternitywars.api.DAL.Repositories.User;

import com.eternitywars.api.Factories.User.UserContainerFactory;
import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserContainerRepositoryTest
{
    private UserContainerRepository userContainerRepository = new UserContainerRepository(new UserContainerFactory());

    private User SetupExpectedUser()
    {
        User expectedUser = new User();
        expectedUser.setUserId(1);
        expectedUser.setEmail("getUser@byEmail.nl");
        expectedUser.setUsername("expectedUser");
        expectedUser.setAccountStatus(AccountStatus.Online);
        expectedUser.setGold(250);
        expectedUser.setPackAmount(3);
        return expectedUser;
    }

    private UserCollection SetupExpectedUserCollection()
    {
        UserCollection expectedUserCollection = new UserCollection();

        User expectedUserOne = new User();
        expectedUserOne.setUserId(1);
        expectedUserOne.setEmail("getUser@byEmail.nl");
        expectedUserOne.setUsername("expectedUser");
        expectedUserOne.setAccountStatus(AccountStatus.Online);
        expectedUserOne.setGold(250);
        expectedUserOne.setPackAmount(3);
        expectedUserCollection.addUser(expectedUserOne);

        User expectedUserTwo = new User();
        expectedUserTwo.setUserId(2);
        expectedUserTwo.setEmail("getUserTwo@byEmail.nl");
        expectedUserTwo.setUsername("expectedUserTwo");
        expectedUserTwo.setAccountStatus(AccountStatus.Offline);
        expectedUserTwo.setGold(300);
        expectedUserTwo.setPackAmount(2);
        expectedUserCollection.addUser(expectedUserTwo);

        return expectedUserCollection;
    }

    private User SetupNewUser()
    {
        User newUser = new User();

        newUser.setUserId(0);
        newUser.setEmail("newUser@mail.nl");
        newUser.setUsername("newUser");
        newUser.setAccountStatus(AccountStatus.InGame);
        newUser.setGold(100);
        newUser.setPackAmount(0);

        return newUser;
    }

    @Test
    void getUserById() {
        User expectedUser = SetupExpectedUser();

        User user = userContainerRepository.GetUserById(expectedUser.getUserId());

        assertEquals(expectedUser.getUserId(), user.getUserId());
        assertEquals(expectedUser.getEmail(), user.getEmail());
        assertEquals(expectedUser.getUsername(), user.getUsername());
        assertEquals(expectedUser.getAccountStatus(), user.getAccountStatus());
        assertEquals(expectedUser.getGold(), user.getGold());
        assertEquals(expectedUser.getPackAmount(), user.getPackAmount());
    }

    @Test
    void getUserByUsername() {
        User expectedUser = SetupExpectedUser();

        User user = userContainerRepository.GetUserByEmail(expectedUser.getEmail());

        assertEquals(expectedUser.getUserId(), user.getUserId());
        assertEquals(expectedUser.getEmail(), user.getEmail());
        assertEquals(expectedUser.getUsername(), user.getUsername());
        assertEquals(expectedUser.getAccountStatus(), user.getAccountStatus());
        assertEquals(expectedUser.getGold(), user.getGold());
        assertEquals(expectedUser.getPackAmount(), user.getPackAmount());
    }

    @Test
    void getUserByEmail() {
        User expectedUser = SetupExpectedUser();

        User user = userContainerRepository.GetUserByEmail(expectedUser.getEmail());

        assertEquals(expectedUser.getUserId(), user.getUserId());
        assertEquals(expectedUser.getEmail(), user.getEmail());
        assertEquals(expectedUser.getUsername(), user.getUsername());
        assertEquals(expectedUser.getAccountStatus(), user.getAccountStatus());
        assertEquals(expectedUser.getGold(), user.getGold());
        assertEquals(expectedUser.getPackAmount(), user.getPackAmount());
    }

    @Test
    void getUsers() {
        UserCollection expectedUserCollection = SetupExpectedUserCollection();

        UserCollection userCollection = userContainerRepository.GetUsers();

        for (int i = 0; i < expectedUserCollection.getUsers().size(); i++)
        {
            assertEquals(expectedUserCollection.getUsers().get(i).getUserId(), userCollection.getUsers().get(i).getUserId());
            assertEquals(expectedUserCollection.getUsers().get(i).getEmail(), userCollection.getUsers().get(i).getEmail());
            assertEquals(expectedUserCollection.getUsers().get(i).getUsername(), userCollection.getUsers().get(i).getUsername());
            assertEquals(expectedUserCollection.getUsers().get(i).getAccountStatus(), userCollection.getUsers().get(i).getAccountStatus());
            assertEquals(expectedUserCollection.getUsers().get(i).getGold(), userCollection.getUsers().get(i).getGold());
            assertEquals(expectedUserCollection.getUsers().get(i).getPackAmount(), userCollection.getUsers().get(i).getPackAmount());
        }
    }

    @Test
    void addUser() {
        User newUser = SetupNewUser();

        int userCount = userContainerRepository.GetUsers().getUsers().size();

        User user = userContainerRepository.AddUser(newUser);

        int newUserCount = userContainerRepository.GetUsers().getUsers().size();

        assertEquals(userCount + 1, newUserCount);
        assertEquals(user.getUsername(), user.getUsername());
        assertEquals(user.getEmail(), newUser.getEmail());
        assertEquals(user.getGold(), newUser.getGold());
        assertEquals(user.getPackAmount(), newUser.getPackAmount());

        DeleteAddedUser(user);
    }

    private void DeleteAddedUser(User user)
    {
        userContainerRepository.DeleteUser(user);
    }
}