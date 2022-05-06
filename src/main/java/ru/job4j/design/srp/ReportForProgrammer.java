package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForProgrammer implements Report {

    private final Store store;

    public ReportForProgrammer(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html lang=\"ru\">")
                .append("<head><meta charset=\"UTF-8\"><title>Сотрудники компании</title></head>")
                .append("<body><table>")
                .append("<tr><td>Name</td><td>Hired</td><td>Fired</td><td>Salary</td>")
                .append("<tr><td>");
        for (Employee em : store.findBy(filter)) {
               text.append(em.getName()).append("</td><td>")
                .append(em.getHired()).append("</td><td>")
                .append(em.getFired()).append("</td><td>")
                .append(em.getSalary()).append("</td></tr>")
                .append("</table></body></html>");
    }
        return text.toString();
    }
}
