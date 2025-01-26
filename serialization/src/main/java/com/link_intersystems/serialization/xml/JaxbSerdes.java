package com.link_intersystems.serialization.xml;

import com.link_intersystems.serialization.Serdes;
import com.link_intersystems.model.customer.Customer;
import jakarta.xml.bind.*;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JaxbSerdes implements Serdes {

    private final JAXBContext jaxbContext;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public JaxbSerdes() {
        try {
            jaxbContext = JAXBContext.newInstance(Customer.class);
            marshaller = jaxbContext.createMarshaller();
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public byte[] serialize(Object obj) throws IOException {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            JAXBElement<Customer> personElement = new JAXBElement<Customer>(new QName("customer"), Customer.class, (Customer) obj);
            marshaller.marshal(personElement, bout);
            return bout.toByteArray();
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        try {
            StreamSource streamSource = new StreamSource(new ByteArrayInputStream(bytes));
            JAXBElement<Customer> unmarshalled = unmarshaller.unmarshal(streamSource, Customer.class);
            return unmarshalled.getValue();
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }
}
