package com.education.ztu.game;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Schoolar scholar1 = new Schoolar("Ivan", 13);
        Schoolar scholar2 = new Schoolar("Mariya", 15);

        Team<Schoolar> scholarTeam = new Team<>("Dragon");
        scholarTeam.addNewParticipant(scholar1);
        scholarTeam.addNewParticipant(scholar2);

        Team<Schoolar> clonedTeam = new Team<>(scholarTeam);
        System.out.println("Original: " + scholarTeam.getParticipants());
        System.out.println("Clone: " + clonedTeam.getParticipants());

        Collections.sort(scholarTeam.getParticipants());
        System.out.println("Sorted by Name: " + scholarTeam.getParticipants());

        Comparator<Participant> ageComparator = Comparator.comparingInt(Participant::getAge);
        Collections.sort(scholarTeam.getParticipants(), ageComparator);
        System.out.println("Sorted by Age: " + scholarTeam.getParticipants());

        Comparator<Participant> priorityComparator = Comparator.comparing(Participant::getName)
                .thenComparingInt(Participant::getAge);
        Collections.sort(scholarTeam.getParticipants(), priorityComparator);
        System.out.println("Sorted by Name then Age: " + scholarTeam.getParticipants());
    }
}