package ru.mai;

import ru.mai.data.Report;
import ru.mai.process.ReportCreator;
import ru.mai.process.StudentsXMLParser;

/**
 * Пример работы с XML
 */
public class XMLExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentsXMLParser parser = new StudentsXMLParser();
        parser.parseXmlFile("testXml.xml");
        Report report = parser.createReport();
        ReportCreator.displayReport(report);
    }
}
