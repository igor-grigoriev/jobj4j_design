package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int size;
    @XmlAttribute
    private boolean child;
    @XmlElementWrapper(name = "authors")
    @XmlElement(name = "author")
    private String[] authors;
    @XmlElement
    private Publisher publisher;

    public Book() {
    }

    public Book(String name, int size, boolean child, String[] authors, Publisher publisher) {
        this.name = name;
        this.size = size;
        this.child = child;
        this.authors = authors;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{name=" + name + ", size=" + size + ", child=" + child
                + ", authors=" + Arrays.toString(authors) + ", publisher=" + publisher + "}";
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isChild() {
        return child;
    }

    public String[] getAuthors() {
        return authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public static void main(String[] args) throws JAXBException, IOException {
        Book book = new Book("Kolobok", 9, true,
                new String[] {"Ivan", "Igor"}, new Publisher("Moscow", 2022));
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(book, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Book result = (Book) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        JSONObject jsonBook = new JSONObject("{\"size\":9,\"name\":\"Kolobok\",\"publisher\":\"Publisher{name=Moscow, year=2022}\",\"child\":true,\"authors\":[\"Ivan\",\"Igor\"]}");
        System.out.println(jsonBook);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", book.getName());
        jsonObject.put("size", book.getSize());
        jsonObject.put("child", book.isChild());
        jsonObject.put("authors", book.getAuthors());
        jsonObject.put("publisher", book.getPublisher());
        System.out.println(jsonObject);

        System.out.println(new JSONObject(book));
    }

    public static class Publisher {
        @XmlAttribute
        private String name;
        @XmlAttribute
        private int year;

        private Publisher() {
        }

        private Publisher(String name, int year) {
            this.name = name;
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public int getYear() {
            return year;
        }

        @Override
        public String toString() {
            return "Publisher{name=" + getName() + ", year=" + getYear() + "}";
        }
    }
}