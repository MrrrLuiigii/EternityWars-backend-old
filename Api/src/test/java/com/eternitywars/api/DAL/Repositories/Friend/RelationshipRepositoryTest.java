package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Repositories.Lobby.LobbyRepository;
import com.eternitywars.api.Factories.Friend.RelationshipFactory;
import com.eternitywars.api.Factories.Lobby.LobbyFactory;
import com.eternitywars.api.Models.Relationship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipRepositoryTest {

    private RelationshipRepository relationshipRepository = new RelationshipRepository(new RelationshipFactory());

    @Test
    void updateRelationship() {
    }
}