package com.education.ztu;
import com.education.ztu.game.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Програма розпочала роботу");

        Team<Student> team = new Team<>("Майстри Java");
        team.addNewParticipant(new Student("Олександр", 20));

        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            File jsonFile = new File("team.json");
            jsonMapper.writeValue(jsonFile, team);
            logger.info("Об'єкт успішно серіалізовано в JSON: " + jsonFile.getAbsolutePath());

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File("team.xml"), team);
            logger.info("Об'єкт успішно серіалізовано в XML");

            Team readTeam = jsonMapper.readValue(jsonFile, Team.class);
            System.out.println("Прочитана назва команди з файлу: " + readTeam.getName());

        } catch (Exception e) {
            logger.error("Сталася помилка під час серіалізації: " + e.getMessage());
        }
    }
}
