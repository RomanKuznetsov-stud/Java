
package com.education.ztu;


enum Gender {
    MALE, FEMALE, OTHER
}

enum Location {
    KIEV, ZHYTOMYR, VINNYTSYA, RIVNE
}

interface Human {
    void sayFullName();

    void sayAge();

    void sayLocation();

    void sayGender();

    default void whoIAm() {
        System.out.println("I am a human.");
    }
}

abstract class Person implements Human {
    private String firstName;
    private String lastName;
    protected int age;
    protected Gender gender;
    protected Location location;
    protected static int counter = 0;
    private boolean initializedByConstructor = false;

    {
        firstName = "Roman";
        lastName = "Kuznietsov";
        age = 19;
        gender = Gender.MALE;
        location = Location.ZHYTOMYR;
        if (!initializedByConstructor) {
            counter++;
        }
    }

    public Person() {
        initializedByConstructor = true;
    }

    public Person(String firstName, String lastName, int age, Gender gender, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.location = location;
        initializedByConstructor = true;
    }

    public static void showCounter() {
        System.out.println("Total persons: " + counter);
    }

    public abstract String getOccupation();

    public String getFullInfo() {
        return firstName + " " + lastName + ", Age: " + age + ", Gender: " + gender + ", Location: " + location + ", Occupation: " + getOccupation();
    }

    public void sayFullName() {
        System.out.println("Name: " + firstName + " " + lastName);
    }

    public void sayAge() {
        System.out.println("Age: " + age);
    }

    public void sayLocation() {
        System.out.println("Location: " + location);
    }

    public void sayGender() {
        System.out.println("Gender: " + gender);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

class Car {
    private String brand;
    private Engine engine;

    public Car(String brand) {
        this.brand = brand;
        this.engine = new Engine();
    }

    public void startCar() {
        engine.startEngine();
    }

    public void stopCar() {
        engine.stopEngine();
    }

    public boolean isEngineWorking() {
        return engine.isEngineWorks();
    }

    public String getBrand() {
        return brand;
    }

    class Engine {
        private boolean working = false;

        void startEngine() {
            working = true;
            System.out.println("Engine started");
        }

        void stopEngine() {
            working = false;
            System.out.println("Engine stopped");
        }

        boolean isEngineWorks() {
            return working;
        }
    }
}

class Student extends Person {
    private String university;
    private String specialty;
    private String course;


    public Student(String firstName, String lastName, int age, Gender gender, Location location) {
        super(firstName, lastName, age, gender, location);
        this.university = "Zhytomyr Polytechnic";
        this.specialty = "IT";
        this.course = "First";
    }

    @Override
    public String getOccupation() {
        return "Student";
    }

    public void study() {
        System.out.println("I am studying at " + university);
    }

    public void major() {
        System.out.println("I am studying " + specialty);
    }

    public void course() {
        System.out.println("I am in my " + course + " year at university. ");
    }
}

class Teacher extends Person {
    private String subject;
    private Car car;
    private String university;


    public Teacher(String firstName, String lastName, int age, Gender gender, Location location, Car car) {
        super(firstName, lastName, age, gender, location);
        this.subject = "biology";
        this.university = "Zhytomyr Polytechnic";
        this.car = car;
    }

    @Override
    public String getOccupation() {
        return "Teacher";
    }

    public void teach() {
        System.out.println("I teach " + subject);
    }

    public void teachPlace() {
        System.out.println("I teach at the university " + university);
    }

    public Car getCar() {
        return car;
    }

}

class Employee extends Person {
    private String company;
    private String position;
    private Car car;

    public Employee(String firstName, String lastName, int age, Gender gender, Location location, Car car) {
        super(firstName, lastName, age, gender, location);
        this.company = "SANA";
        this.position = "middle";
        this.car = car;
    }

    @Override
    public String getOccupation() {
        return "Employee";
    }

    public void work() {
        System.out.println("I work at " + company);
    }

    public void position() {
        System.out.println("My position is " + position);
    }

    public Car getCar() {
        return car;
    }
}