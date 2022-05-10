package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;


public class ReportToXML implements Report {

    Store store;

    public ReportToXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        JAXBContext context;
        StringBuilder xml = new StringBuilder();
        try {
            context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        for (Employee em : store.findBy(filter)) {
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(em, writer);
                xml.append(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml.toString();
    }
}
