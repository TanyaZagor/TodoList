package ru.zagorodnikova.tm.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IDomainService;
import ru.zagorodnikova.tm.entity.Domain;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class DomainService implements IDomainService {

    @NotNull private final IProjectRepository<Project> projectRepository;
    @NotNull private final ITaskRepository<Task> taskRepository;
    @NotNull private final IUserRepository<User> userRepository;

    public DomainService(@NotNull IProjectRepository<Project> projectRepository, @NotNull ITaskRepository<Task> taskRepository, @NotNull IUserRepository<User> userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void save(){
        final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());
        File file = new File("file.txt");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(domain);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("file.txt"));
            Domain domain = (Domain) inputStream.readObject();
            inputStream.close();
            projectRepository.setProjects(domain.getProjects());
            taskRepository.setTasks(domain.getTasks());
            userRepository.setUsers(domain.getUsers());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void saveToJson(){
        final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());
        File file = new File("fileFasterXml.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromJson(){
        File file = new File("fileFasterXml.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readValue(file, Domain.class);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Domain domain = mapper.readValue(file, Domain.class);
            projectRepository.setProjects(domain.getProjects());
            taskRepository.setTasks(domain.getTasks());
            userRepository.setUsers(domain.getUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveToXml() {
        final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());
        File file = new File("fileFasterXml.xml");
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromXml() {
        File file = new File("fileFasterXml.xml");
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            Domain domain = xmlMapper.readValue(file, Domain.class);
            projectRepository.setProjects(domain.getProjects());
            taskRepository.setTasks(domain.getTasks());
            userRepository.setUsers(domain.getUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveToJsonJaxb() {
        final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());
        File file = new File("fileJaxb.json");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

            jaxbMarshaller.marshal(domain, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void loadFromJsonJaxb() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            jaxbUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            jaxbUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);

            Domain domain = (Domain) jaxbUnmarshaller.unmarshal(new File("fileJaxb.json"));

            projectRepository.setProjects(domain.getProjects());
            taskRepository.setTasks(domain.getTasks());
            userRepository.setUsers(domain.getUsers());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveToXmlJaxb() {
        try {
            final Domain domain = new Domain();
            domain.setUsers(userRepository.getUsers());
            domain.setProjects(projectRepository.getProjects());
            domain.setTasks(taskRepository.getTasks());
            File file = new File("fileJaxb.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(domain, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void loadFromXmlJaxb() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Domain domain = (Domain) jaxbUnmarshaller.unmarshal(new File("fileJaxb.xml"));
            projectRepository.setProjects(domain.getProjects());
            taskRepository.setTasks(domain.getTasks());
            userRepository.setUsers(domain.getUsers());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
