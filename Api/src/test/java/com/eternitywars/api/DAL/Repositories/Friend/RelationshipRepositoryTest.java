package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Repositories.Lobby.LobbyRepository;
import com.eternitywars.api.Factories.Friend.RelationshipFactory;
import com.eternitywars.api.Factories.Lobby.LobbyFactory;
import com.eternitywars.api.Models.Enums.FriendStatus;
import com.eternitywars.api.Models.Relationship;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RelationshipRepositoryTest {

    private RelationshipRepository relationshipRepository = new RelationshipRepository(new RelationshipFactory());

    @Test
    void updateRelationship() {
        Relationship relationship = new Relationship();
        relationship.setFriendOneId(5);
        relationship.setFriendTwoId(6);
        relationship.setFriendStatus(FriendStatus.Accepted);
        assertEquals(true, relationshipRepository.UpdateRelationship(relationship));
    }

    @AfterAll
    void reset(){
        Relationship relationship = new Relationship();
        relationship.setFriendOneId(5);
        relationship.setFriendTwoId(6);
        relationship.setFriendStatus(FriendStatus.Blocked);
        assertEquals(true, relationshipRepository.UpdateRelationship(relationship));
    }
}