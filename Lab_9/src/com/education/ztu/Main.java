package com.education.ztu;
import java.lang.reflect.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {

        task2RegularExpressions();
        System.out.println("----------------------------------------");

        task3ReflectionAPI();
        System.out.println("----------------------------------------");

        task4CustomAnnotation();
    }

    private static void task2RegularExpressions() {
        System.out.println("Завдання 2: Робота з регулярними виразами");

        String text = "Козак Тарас, 33 роки, Менеджер з продажу, 7 років, Київ, t.kozak@sales.net, +380991234567, ДН: 12.03.1990.\n" +
                "Левченко Олена, 28 років, Бухгалтер, 5 років, Львів, elena.lev@fin.ua, 067-555-44-33, ДН: 25/07/1995.\n" +
                "Романенко Ігор, 45 років, Директор, 20 років, Одеса, director@office.com, +38 (048) 222-33-44, ДН: 1978-11-05.\n" +
                "Бойко Світлана, 30 років, Менеджер з продажу, 4 роки, Харків, s.boyko@market.ua, (050) 888 99 00, ДН: 15.01.1993.\n" +
                "Шевчук Дмитро, 24 роки, Стажер, 1 рік, Житомир, d.shevchuk@student.ztu, 063-111-22-33, ДН: 2000-09-10.";

        System.out.println("\n--- 2.1: Початковий текст ---");
        System.out.println(text);

        System.out.println("\n--- 2.2: Знайдені телефони та email ---");

        Pattern emailPattern = Pattern.compile("([\\w._-]+@[\\w._-]+\\.[a-zA-Z]{2,6})");
        Matcher emailMatcher = emailPattern.matcher(text);
        System.out.println("Emails:");
        while (emailMatcher.find()) {
            System.out.println("  - " + emailMatcher.group(1));
        }

        Pattern phonePattern = Pattern.compile("(\\+\\d{1,3}\\s?)?(\\(?\\d{2,4}\\)?[\\s\\.-]?)+(\\d[\\s\\.-]?){5,}");
        Matcher phoneMatcher = phonePattern.matcher(text);
        System.out.println("Телефони:");
        while (phoneMatcher.find()) {
            String phone = phoneMatcher.group(0).trim();
            if (phone.length() > 8 && !phone.contains("ДН")) {
                System.out.println("  - " + phone);
            }
        }

        System.out.println("\n--- 2.3: Зміна формату ДН (на 1978-11-05) ---");
        String dateRegex = "ДН:\\s*(\\d{2}[\\./]\\d{2}[\\./]\\d{4}|\\d{4}-\\d{2}-\\d{2}|\\d{2}\\/\\d{2}\\/\\d{4})";
        Pattern datePattern = Pattern.compile(dateRegex);
        Matcher dateMatcher = datePattern.matcher(text);
        StringBuffer resultBuffer = new StringBuffer();

        while (dateMatcher.find()) {
            String originalDate = dateMatcher.group(0);
            String datePart = originalDate.substring(4).trim();

            String newFormat;
            if (datePart.matches("\\d{2}\\.\\d{2}\\.\\d{4}|\\d{2}\\/\\d{2}\\/\\d{4}")) {
                String[] parts = datePart.split("[\\./]");
                newFormat = parts[2] + "-" + parts[1] + "-" + parts[0];
            } else if (datePart.matches("\\d{4}-\\d{2}-\\d{2}")) {
                newFormat = datePart;
            } else {
                newFormat = datePart;
            }
            dateMatcher.appendReplacement(resultBuffer, "ДН: " + newFormat);
        }
        dateMatcher.appendTail(resultBuffer);
        String textWithNewDates = resultBuffer.toString();
        System.out.println(textWithNewDates);

        System.out.println("\n--- 2.4: Зміна посад ---");
        String modifiedText = textWithNewDates
                .replaceAll("Менеджер з продажу", "Sales Manager")
                .replaceAll("Бухгалтер", "Фінансовий аналітик")
                .replaceAll("Стажер", "Молодший спеціаліст");
        System.out.println(modifiedText);
    }

    private static void task3ReflectionAPI() throws Exception {
        System.out.println("Завдання 3: Робота з користувацьким класом методами Reflection API");

        Class<Employee> clas1 = Employee.class;
        Class<?> clas2 = new Employee().getClass();
        Class<?> clas3 = Class.forName("com.education.ztu.Employee");

        System.out.println("\n--- 3.1: Об'єкти Class, отримані 3 способами ---");
        System.out.println("Спосіб 1 (.class): " + clas1.getName());
        System.out.println("Спосіб 2 (.getClass()): " + clas2.getName());
        System.out.println("Спосіб 3 (Class.forName): " + clas3.getName());


        System.out.println("\n--- 3.2: Поля, методи, конструктори (оголошені) ---");

        System.out.println("Поля (getDeclaredFields):");
        for (Field field : clas1.getDeclaredFields()) {
            System.out.printf("  - %s %s %s\n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());
        }

        System.out.println("Методи (getDeclaredMethods):");
        for (Method method : clas1.getDeclaredMethods()) {
            StringBuilder params = new StringBuilder();
            for (Parameter param : method.getParameters()) {
                params.append(param.getType().getSimpleName()).append(" ").append(param.getName()).append(", ");
            }
            String paramString = params.length() > 0 ? params.substring(0, params.length() - 2) : "";
            System.out.printf("  - %s %s %s(%s)\n", Modifier.toString(method.getModifiers()), method.getReturnType().getSimpleName(), method.getName(), paramString);
        }

        System.out.println("Конструктори (getDeclaredConstructors):");
        for (Constructor<?> constructor : clas1.getDeclaredConstructors()) {
            StringBuilder params = new StringBuilder();
            for (Parameter param : constructor.getParameters()) {
                params.append(param.getType().getSimpleName()).append(" ").append(param.getName()).append(", ");
            }
            String paramString = params.length() > 0 ? params.substring(0, params.length() - 2) : "";
            System.out.printf("  - %s %s(%s)\n", Modifier.toString(constructor.getModifiers()), constructor.getName(), paramString);
        }


        System.out.println("\n--- 3.3: Створення екземпляру класу та виклик методів ---");
        Constructor<Employee> constructor = clas1.getDeclaredConstructor(String.class, int.class);
        Employee employee = constructor.newInstance("Юрій", 20000);
        System.out.println("Створено об'єкт: " + employee);

        Method publicMethod = clas1.getDeclaredMethod("calculateAnnualSalary");
        Object result = publicMethod.invoke(employee);
        System.out.println("Виклик методу 'calculateAnnualSalary': " + result);


        System.out.println("\n--- 3.4: Робота з приватним полем ---");
        Field privateField = clas1.getDeclaredField("name");
        privateField.setAccessible(true);

        String currentName = (String) privateField.get(employee);
        System.out.println("Поточне значення приватного поля 'name': " + currentName);

        privateField.set(employee, "Юрій Новий");
        System.out.println("Встановлено нове значення: Юрій Новий");
        System.out.println("Перевірка об'єкта: " + employee);
    }

    private static void task4CustomAnnotation() throws Exception {
        System.out.println("Завдання 4: Створення власної анотації");

        System.out.println("\n--- 4.1: Отримання даних анотації з класу Employee ---");
        Class<Employee> clas = Employee.class;

        if (clas.isAnnotationPresent(CustomAnnotation.class)) {
            CustomAnnotation annotation = clas.getAnnotation(CustomAnnotation.class);
            System.out.println("Знайдено анотацію на КЛАСІ 'Employee':");
            System.out.println("  - Автор: " + annotation.author());
            System.out.println("  - Версія: " + annotation.version());
        }

        System.out.println("\n--- 4.2: Отримання даних анотації з методів класу Employee ---");
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CustomAnnotation.class)) {
                CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
                System.out.printf("Знайдено анотацію на МЕТОДІ '%s': Автор: %s, Версія: %s\n",
                        method.getName(), annotation.author(), annotation.version());
            }
        }
    }
}