package com.education.ztu.game;
import java.util.Collections;
import java.util.Comparator;

public class Game {
    public static void main(String[] args) {
        Schoolar schoolar1 = new Schoolar("Ivan", 13);
        Schoolar schoolar2 = new Schoolar("Mariya", 15);
        Student student1 = new Student("Mykola", 20);
        Student student2 = new Student("Viktoria", 21);
        Employee employee1 = new Employee("Andriy", 28);
        Employee employee2 = new Employee("Oksana", 25);

        Team<Schoolar> schoolarTeam = new Team<>("Dragon");
        schoolarTeam.addNewParticipant(schoolar1);
        schoolarTeam.addNewParticipant(schoolar2);

        Team<Student> studentTeam = new Team<>("Vpered");
        studentTeam.addNewParticipant(student1);
        studentTeam.addNewParticipant(student2);

        Team<Employee> employeeTeam = new Team<>("Robotyagi");
        employeeTeam.addNewParticipant(employee1);
        employeeTeam.addNewParticipant(employee2);

        Team<Schoolar> schoolarTeam2 = new Team<>("Rozumnyky");
        Schoolar schoolar3 = new Schoolar("Sergey", 12);
        Schoolar schoolar4 = new Schoolar("Olga", 14);
        schoolarTeam2.addNewParticipant(schoolar3);
        schoolarTeam2.addNewParticipant(schoolar4);

        schoolarTeam.playWith(schoolarTeam2);

        System.out.println("---- Cloning Demo ----");
        Team<Schoolar> clonedTeam = new Team<>(schoolarTeam);
        System.out.println("Original team: " + schoolarTeam.getParticipants());
        System.out.println("Cloned team:   " + clonedTeam.getParticipants());

        clonedTeam.getParticipants().get(0).setName("Ivan_Clone");
        System.out.println("After change:");
        System.out.println("Original team: " + schoolarTeam.getParticipants());
        System.out.println("Cloned team:   " + clonedTeam.getParticipants());

        System.out.println("---- Sorting Demo ----");
        System.out.println("Before sort: " + studentTeam.getParticipants());

        Collections.sort(studentTeam.getParticipants());
        System.out.println("Sorted by Name: " + studentTeam.getParticipants());

        Collections.sort(studentTeam.getParticipants(), new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
        System.out.println("Sorted by Age: " + studentTeam.getParticipants());

        Comparator<Participant> priorityComparator = Comparator.comparing(Participant::getName)
                .thenComparingInt(Participant::getAge);

        Collections.sort(studentTeam.getParticipants(), priorityComparator);
        System.out.println("Sorted by Name then Age: " + studentTeam.getParticipants());
    }
}
