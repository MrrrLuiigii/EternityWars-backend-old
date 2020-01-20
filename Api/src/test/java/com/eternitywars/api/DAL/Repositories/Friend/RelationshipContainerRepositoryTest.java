package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipContainerSqlContext;
import com.eternitywars.api.Factories.Friend.RelationshipContainerFactory;
import com.eternitywars.api.Factories.Friend.RelationshipFactory;
import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipContainerRepositoryTest {

    private RelationshipContainerRepository relationshipContainerRepository = new RelationshipContainerRepository(new RelationshipContainerFactory());

    @Test
    void addRelationship() {
        Relationship relationship = new Relationship();
        relationship.setFriendOneId(3);
        relationship.setFriendTwoId(4);
        relationshipContainerRepository.AddRelationship(relationship);
    }

    @Test
    void deleteRelationship() {
        Relationship relationship = new Relationship();
        relationship.setFriendOneId(3);
        relationship.setFriendTwoId(4);
        relationshipContainerRepository.DeleteRelationship(relationship);
    }

    @Test
    void getRelationships() {
        User user = new User();
        user.setUserId(5);
        relationshipContainerRepository.GetRelationships(user);
        assertEquals(1, relationshipContainerRepository.GetRelationships(user).getRelationships().size() );
    }
}