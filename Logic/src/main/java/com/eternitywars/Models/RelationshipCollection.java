package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class RelationshipCollection {
    private List<Relationship> relationships = new ArrayList<>();

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
