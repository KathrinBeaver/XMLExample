package ru.mai.process;

import ru.mai.data.Report;

/**
 * Класс для реализации разных методов вывода отчетов
 */
public class ReportCreator {

    /**
     * Вывод отчета в консоль
     * @param report - данные отчета
     */
    public static void displayReport(Report report) {
        System.out.println("\n ************** REPORT ******************* ");
        System.out.println("\nИмена студентов:");
        System.out.println(report.getNamesList());
        System.out.println("\nСредний возраст = " + report.getAverageAge());
        System.out.println("\nСтуденты старше 1997 г.р.:");
        System.out.println(report.getNamesOlderList());
        System.out.println("\nОбщий список студентов:");
        report.sort();
        System.out.println(report.getStudentsList());
    }
}
