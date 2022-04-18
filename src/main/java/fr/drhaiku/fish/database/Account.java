package fr.drhaiku.fish.database;

import java.util.UUID;

public class Account {

    private final UUID uuid;
    private final String name;
    private final String lastName;
    private final int age;
    private final int size;
    private final String group;

    public Account(UUID uuid, String name, String lastName, int age, int size, String group) {
        this.uuid = uuid;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.size = size;
        this.group = group;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getSize() {
        return size;
    }

    public String getGroup() {
        return group;
    }
}
