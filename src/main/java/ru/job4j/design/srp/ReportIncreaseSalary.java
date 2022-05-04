package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportIncreaseSalary implements Report {

    private Store store;

    private final double rate;

    public ReportIncreaseSalary(Store store, double rate) {
        this.store = store;
        this.rate = rate;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary; ");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() + (employee.getSalary() * this.rate)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
