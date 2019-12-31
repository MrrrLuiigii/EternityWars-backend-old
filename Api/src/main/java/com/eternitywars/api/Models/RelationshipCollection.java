package com.eternitywars.api.Models;

import java.util.ArrayList;
import java.util.List;

public class RelationshipCollection
{
    private List<Relationship> relationships;

    public RelationshipCollection()
    {
        relationships = new ArrayList<>();
    }

    public RelationshipCollection(List<Relationship> relationships)
    {
        this.relationships = relationships;
    }

    public List<Relationship> getRelationships()
    {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships)
    {
        this.relationships = relationships;
    }
}
