package sstutsman.com.learnrealm.model;

import io.realm.RealmObject;

/**
 * Sam Stutsman on April, 2015.
 */
public class Animal extends RealmObject {

    private String commonName;
    private String genus;
    private String species;
    private boolean isTerrestrial;

    // Make sure you have a zero-parameter, public constructor
    public Animal() {
    }

    public Animal(String commonName, String genus, String species, boolean isTerrestrial) {
        this.commonName = commonName;
        this.genus = genus;
        this.species = species;
        this.isTerrestrial = isTerrestrial;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isTerrestrial() {
        return isTerrestrial;
    }

    public void setIsTerrestrial(boolean isTerrestrial) {
        this.isTerrestrial = isTerrestrial;
    }
}
