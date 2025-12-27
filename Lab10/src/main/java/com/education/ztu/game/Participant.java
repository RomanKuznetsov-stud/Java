package com.education.ztu.game;
import java.io.Serializable;
import java.util.Objects;

public abstract class Participant implements Cloneable, Comparable<Participant>, Serializable {
    private String name;
    private int age;

    public Participant() {}

    public Participant(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттери та сеттери (необхідні для JSON/XML парсерів)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Participant{name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() { return Objects.hash(name, age); }

    @Override
    public Participant clone() {
        try {
            return (Participant) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(Participant other) {
        return this.name.compareTo(other.name);
    }
}
