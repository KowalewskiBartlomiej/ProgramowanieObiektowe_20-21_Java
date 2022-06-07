package main;

import java.io.Serializable;
import java.util.Comparator;

public class VolleyballPlayer implements Serializable {
    String name;
    String surname;
    String nationality;
    String position;
    int age;
    int length_of_contract;
    int number_on_shirt;
    int attack;
    int block;
    int serve;
    int speed;
    int positioning;
    int receiving;
    int technique;
    int decisions;

    public VolleyballPlayer () {};

    public VolleyballPlayer (String name, String surname, String nationality, String position, int age,
                             int length_of_contract, int number_on_shirt, int attack, int block, int serve,
                             int speed, int positioning, int receiving, int technique, int decisions)
    {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.position = position;
        this.age = age;
        this.length_of_contract = length_of_contract;
        this.number_on_shirt = number_on_shirt;
        this.attack = attack;
        this.block = block;
        this.serve = serve;
        this.speed = speed;
        this.positioning = positioning;
        this.receiving = receiving;
        this.technique = technique;
        this.decisions = decisions;
    }

    public int getNumber_on_shirt() {
        return number_on_shirt;
    }

    public int getAge() {
        return age;
    }

    public int getLength_of_contract() {
        return length_of_contract;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getSurname() {
        return surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLength_of_contract(int length_of_contract) {
        this.length_of_contract = length_of_contract;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNumber_on_shirt(int number_on_shirt) {
        this.number_on_shirt = number_on_shirt;
    }

    public int getReceiving() {
        return receiving;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPositioning() {
        return positioning;
    }

    public int getAttack() {
        return attack;
    }

    public int getBlock() {
        return block;
    }

    public int getServe() {
        return serve;
    }

    public int getDecisions() {
        return decisions;
    }

    public int getTechnique() {
        return technique;
    }

    public String getPosition() {
        return position;
    }

    public void setReceiving(int receiving) {
        this.receiving = receiving;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPositioning(int positioning) {
        this.positioning = positioning;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public void setServe(int serve) {
        this.serve = serve;
    }

    public void setDecisions(int decisions) {
        this.decisions = decisions;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTechnique(int technique) {
        this.technique = technique;
    }

}