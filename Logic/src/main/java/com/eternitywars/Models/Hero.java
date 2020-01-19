package com.eternitywars.Models;

public class Hero {
    private int hp;
    private int deathessence;
    private int mana;
    private int maxMana;
    private int maxDeathessence;


    public Hero() {
        this.hp = 30;
        this.maxDeathessence = 0;
        this.maxMana = 1;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDeathessence() {
        return deathessence;
    }

    public void setDeathessence(int deathessence) {
        this.deathessence = deathessence;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getMaxDeathessence() {
        return maxDeathessence;
    }

    public void setMaxDeathessence(int maxDeathessence) {
        this.maxDeathessence = maxDeathessence;
    }

}
