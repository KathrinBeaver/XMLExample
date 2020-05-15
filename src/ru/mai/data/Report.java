package ru.mai.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Данные для отчета
 */
public class Report {

    /**
     * Средний возраст студента
     */
    private double averageAge = 0;

    /**
     * Список имен студентов старше 1996 г.р.
     */
    private ArrayList<String> namesOlderList = new ArrayList<>();

    /**
     * Список студентов
     */
    private ArrayList<Student> studentsList = new ArrayList<>();

    /**
     * Список имен студентов
     */
    private ArrayList<String> namesList = new ArrayList<>();

    public double getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(double averageAge) {
        this.averageAge = averageAge;
    }

    public ArrayList<String> getNamesOlderList() {
        return namesOlderList;
    }

    public void setNamesOlderList(ArrayList<String> namesOlderList) {
        this.namesOlderList = namesOlderList;
    }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public ArrayList<String> getNamesList() {
        return namesList;
    }

    public void setNamesList(ArrayList<String> namesList) {
        this.namesList = namesList;
    }

    @Override
    public String toString() {
        return "Report{" +
                "averageAge=" + averageAge +
                ", namesOlderList=" + namesOlderList +
                ", studentsList=" + studentsList +
                ", namesList=" + namesList +
                '}';
    }

    public void sort() {
        Collections.sort(studentsList);
    }
}
