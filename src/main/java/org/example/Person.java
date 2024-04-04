package org.example;

public class Person {
    private String id;
    private String name;
    private String city;
    private String age;


    public Person(String id, String name, String city, String age) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
    }


    public Person() {}


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAge() { return age; }
}
