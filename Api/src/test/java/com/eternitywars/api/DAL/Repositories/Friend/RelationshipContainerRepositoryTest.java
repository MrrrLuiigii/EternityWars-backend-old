package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipContainerSqlContext;
import com.eternitywars.api.Factories.Friend.RelationshipContainerFactory;
import com.eternitywars.api.Factories.Friend.RelationshipFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipContainerRepositoryTest {

    private RelationshipContainerRepository relationshipContainerRepository = new RelationshipContainerRepository(new RelationshipContainerFactory());

    @Test
    void addRelationship() {
    }

    @Test
    void deleteRelationship() {
    }

    @Test
    void getRelationships() {
    }
}