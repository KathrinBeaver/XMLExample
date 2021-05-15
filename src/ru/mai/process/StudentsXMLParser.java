package ru.mai.process;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.mai.data.Report;
import ru.mai.data.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс-парсер XML-документов с данными о студентах
 *
 * @author Beaver
 */
public class StudentsXMLParser {

    private static final int REPORT_YEAR = 1999;
    /**
     * Список имен студентов
     */
    private static final ArrayList<String> namesList = new ArrayList<>();

    /**
     * Список имен студентов старше 1999 г.р.
     */
    private static final ArrayList<String> namesOlderList = new ArrayList<>();

    /**
     * Список студентов
     */
    private static final ArrayList<Student> studentsList = new ArrayList<>();

    /**
     * Суммарный возраст всех студентов
     */
    private static double sumAge = 0;

    /**
     * Чтение и разбор XML-документа
     *
     * @param filePath путь к XML-документу
     */
    public void parseXmlFile(String filePath) {

        try {
            File students = new File(filePath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(students);
            doc.getDocumentElement().normalize();

            System.out.println("Root of xml file: " + doc.getDocumentElement().getNodeName());
            NodeList nodes = doc.getElementsByTagName("Student");

            searchByAge(doc);

            System.out.println("==========================");

            for (int i = 0; i < nodes.getLength(); i++) {

                Node node = nodes.item(i);

                System.out.println(node.getNodeName() + " : " + node.getNodeValue());

                NamedNodeMap attrMap = node.getAttributes();
                for (int j = 0; j < attrMap.getLength(); j++) {
                    System.out.println("Аттрибут:" + attrMap.item(j));
                }

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    NodeList children = element.getChildNodes();

                    String name = null;
                    Student student = new Student();

                    for (int j = 0; j < children.getLength(); j++) {
                        Node e = children.item(j);

                        if (e.getNodeType() == Node.ELEMENT_NODE) {

                            System.out.println(e.getNodeName() + ": " + getValue(e.getNodeName(), element));

                            if (e.getNodeName().equals("Name")) {
                                name = getValue(e.getNodeName(), element);
                                namesList.add(name);
                                student.setName(name);
                            } else if (e.getNodeName().equals("Age")) {
                                try {
                                    sumAge += Double.parseDouble(getValue(e.getNodeName(), element));
                                    student.setAge(Integer.parseInt(getValue(e.getNodeName(), element)));
                                } catch (NumberFormatException ex) {
                                    System.out.println("Ошибка преобразования возраста");
                                }
                            } else if (e.getNodeName().equals("Year")) {
                                try {
                                    int year = Integer.parseInt(getValue(e.getNodeName(), element));
                                    if (year < REPORT_YEAR) {
                                        namesOlderList.add(name);
                                    }
                                    student.setYear(Integer.parseInt(getValue(e.getNodeName(), element)));
                                } catch (NumberFormatException ex) {
                                    System.out.println("Ошибка преобразования года рождения");
                                }
                            } else if (e.getNodeName().equals("Id")) {
                                try {
                                    student.setId(Integer.parseInt(getValue(e.getNodeName(), element)));
                                } catch (NumberFormatException ex) {
                                    System.out.println("Ошибка преобразования возраста");
                                }
                            }
                        }
                    }

                    studentsList.add(student);
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(StudentsXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(StudentsXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentsXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Пример поиска с помощью xPath
     * https://msiter.ru/tutorials/xpath
     *
     * @param doc - объект XML-документа
     */
    private void searchByAge(Document doc) {
        System.out.println("Печать элементов Book у которых значение Cost > 4");
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xpath = pathFactory.newXPath();
        XPathExpression expr = null;
        try {
            expr = xpath.compile("MAI/Student[Age>22]");
        } catch (XPathExpressionException e) {
            Logger.getLogger(StudentsXMLParser.class.getName()).log(Level.SEVERE, null, e);
        }

        NodeList nodes = null;
        try {
            nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            Logger.getLogger(StudentsXMLParser.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            System.out.println("Value:" + n.getTextContent());
        }

        System.out.println();
    }

    private String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node.getNodeValue();
    }

    /**
     * Создание отчета
     *
     * @return данные отчета
     */
    public Report createReport() {
        Report report = new Report();
        report.setAverageAge(sumAge / namesList.size());
        report.setNamesList(namesList);
        report.setNamesOlderList(namesOlderList);
        report.setStudentsList(studentsList);
        return report;
    }
}
