package ru.job4j.design.srp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(em -> true), expect.toString());
    }

    @Test
    public void whenToHtmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForProgrammer(store);
        StringBuilder expect = new StringBuilder()
                .append("<html lang=\"ru\">")
                .append("<head><meta charset=\"UTF-8\"><title>Сотрудники компании</title></head>")
                .append("<body><table>")
                .append("<tr><td>Name</td><td>Hired</td><td>Fired</td><td>Salary</td>")
                .append("<tr><td>")
                .append(worker.getName()).append("</td><td>")
                .append(worker.getHired()).append("</td><td>")
                .append(worker.getFired()).append("</td><td>")
                .append(worker.getSalary()).append("</td></tr>")
                .append("</table></body></html>");
        assertEquals(engine.generate(em -> true), expect.toString());
    }

    @Test
    public void whenReportForAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportIncreaseSalary(store, 0.5);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() + (worker.getSalary() * 0.5)).append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(em -> true), expect.toString());
    }

    @Test
    public void whenReportForHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vovan", now, now, 110);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportForHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(em -> true), expect.toString());
    }

    @Test
    public void whenToJsonGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", new GregorianCalendar(2021, 5, 7),
                new GregorianCalendar(2022, 5, 7), 100);
        store.add(worker);
        StringBuilder expect = new StringBuilder()
                .append("{\"name\":\"").append(worker.getName()).append("\",")
                .append("\"salary\":").append(worker.getSalary())
                .append(",\"hired\":")
                .append("{\"year\":2021,\"month\":5,\"dayOfMonth\":7,\"hourOfDay\":0,\"minute\":0,\"second\":0}")
                .append(",")
                .append("\"fired\":")
                .append("{\"year\":2022,\"month\":5,\"dayOfMonth\":7,\"hourOfDay\":0,\"minute\":0,\"second\":0}}");


        Report engine = new ReportToJson(store);

        assertEquals(engine.generate(em -> true), expect.toString());
    }

    @Test
    public void whenToXmlGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", new GregorianCalendar(2021, 5, 7),
                new GregorianCalendar(2022, 5, 7), 100);
        store.add(worker);
        Report engine = new ReportToXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employee name=\"").append(worker.getName()).append("\" ")
                .append("salary=\"").append(worker.getSalary()).append(("\" "))
                .append("hired=\"2021-06-07T00:00:00+03:00\" ")
                .append("fired=\"2022-06-07T00:00:00+03:00\"/>\n");
        assertEquals(engine.generate(em -> true), expect.toString());
    }
}