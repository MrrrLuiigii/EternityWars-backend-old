package com.eternitywars.Models;

public class Card
{
    private int cardId;
    private String name;
    private int health;
    private int attack;
    private int blue_mana;
    private int death_essence;
    private boolean issleeping;

    public Card()
    {
        issleeping = true;
    }

    public boolean getIssleeping() {
        return issleeping;
    }

    public void setIssleeping(boolean issleeping) {
        this.issleeping = issleeping;
    }


    public int getCardId()
    {
        return cardId;
    }

    public void setCardId(int cardId)
    {
        this.cardId = cardId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getAttack()
    {
        return attack;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public int getBlue_mana()
    {
        return blue_mana;
    }

    public void setBlue_mana(int blue_mana)
    {
        this.blue_mana = blue_mana;
    }

    public int getDeath_essence()
    {
        return death_essence;
    }

    public void setDeath_essence(int death_essence)
    {
        this.death_essence = death_essence;
    }

}
