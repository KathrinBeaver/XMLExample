package ru.mai.data;

import java.util.Map;

/**
 * Описание студента
 *
 * @author Beaver
 */
public class Student implements Comparable<Student> {

    /**
     * Идентификатор
     */
    private int id = 0;

    /**
     * Имя
     */
    private String name = "";

    /**
     * Возраст
     */
    private int age = 0;

    /**
     * Год рождения
     */
    private int year = 0;

    private Map<String, String> attributes;

    public Student() {

    }

    public Student(int id, String name, int age, int year) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.year = year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "id = " + id + "; " + name + "; age = " + age + "; year = " + year;
    }

    @Override
    public int compareTo(Student student) {
        if (age > student.getAge()) {
            return 1;
        } else if (age < student.getAge()){
            return -1;
        } else {
            return 0;
        }
    }
}
