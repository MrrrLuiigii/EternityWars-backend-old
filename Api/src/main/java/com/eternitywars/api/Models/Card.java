package com.eternitywars.api.Models;

public class Card
{
    private int cardId;
    private String name;
    private int health;
    private int attack;
    private int blue_mana;
    private int death_essence;
    private boolean taunt;
    private boolean isSleeping;



    public Card()
    {
        isSleeping = true;
    }

    public Card(int cardId)
    {
        this.cardId = cardId;
    }

    public boolean getSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.isSleeping = sleeping;
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

    public boolean getTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }
}