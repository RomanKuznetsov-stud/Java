package com.education.ztu;

public class Main {
    public static void main(String[] args) {

        Student student = new Student("Sofia", "Tkachuk", 18, Gender.FEMALE, Location.RIVNE);
        Teacher teacher = new Teacher("Yurii", "Sydorenko", 55, Gender.MALE, Location.VINNYTSYA, new Car("Toyota"));
        Employee employee = new Employee("Olena", "Danylko", 44, Gender.FEMALE, Location.KIEV, new Car("BMW"));

        divFunc();
        student.sayFullName();
        student.sayAge();
        student.sayLocation();
        student.sayGender();
        student.whoIAm();
        student.study();
        student.major();
        student.course();
        System.out.println(student.getFullInfo());
        divFunc();

        teacher.sayFullName();
        teacher.sayAge();
        teacher.sayLocation();
        teacher.sayGender();
        teacher.teach();
        teacher.teachPlace();
        teacher.getCar().startCar();
        System.out.println("Car brand: " + teacher.getCar().getBrand());
        System.out.println("Engine running: " + teacher.getCar().isEngineWorking());
        divFunc();

        employee.sayFullName();
        employee.sayAge();
        employee.sayLocation();
        employee.sayGender();
        employee.work();
        employee.position();
        System.out.println("Car brand: " + employee.getCar().getBrand());
        employee.getCar().startCar();
        employee.getCar().stopCar();
        System.out.println("Engine running: " + employee.getCar().isEngineWorking());
        divFunc();

        Person.showCounter();
        divFunc();
        if (student instanceof Person) {
            System.out.println("Student is a Person.");
        }

        if (teacher instanceof Human) {
            System.out.println("Teacher is a Human.");
        }

    }

    public static void divFunc() {
        System.out.println("\u001B[32m----------------------------------------\u001B[0m");
    }
}
