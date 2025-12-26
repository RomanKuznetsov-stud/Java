package com.education.ztu.game;
import java.util.Collections;
import java.util.Comparator;

public class Main2 {
    public static void main(String[] args) {
        Team<Employee> employeeTeam = new Team<>("IT-Specialists");
        Employee emp1 = new Employee("Vlad", 28);
        Employee emp2 = new Employee("Anton", 24);
        Employee emp3 = new Employee("Dmitry", 35);

        employeeTeam.addNewParticipant(emp1);
        employeeTeam.addNewParticipant(emp2);
        employeeTeam.addNewParticipant(emp3);

        System.out.println("Before sorting: " + employeeTeam.getParticipants());

        Collections.sort(employeeTeam.getParticipants());
        System.out.println("Sorted by Name (Comparable): " + employeeTeam.getParticipants());

        Comparator<Participant> ageComparator = new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        };

        Collections.sort(employeeTeam.getParticipants(), ageComparator);
        System.out.println("Sorted by Age (Comparator): " + employeeTeam.getParticipants());

        Comparator<Participant> priorityComparator = Comparator.comparing(Participant::getName)
                .thenComparingInt(Participant::getAge);

        Collections.sort(employeeTeam.getParticipants(), priorityComparator);
        System.out.println("Sorted by Name then Age: " + employeeTeam.getParticipants());
    }
}