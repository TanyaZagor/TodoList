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

    public DomainService(@NotNull final IProjectRepository<Project> projectRepository, @NotNull final ITaskRepository<Task> taskRepository, @NotNull final IUserRepository<User> userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void save() throws Exception {
        @NotNull final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());

        @NotNull final File file = new File("todoServer\\data\\file.txt");
        @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
    }

    public void load() throws Exception {
        @NotNull final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("todoServer\\data\\file.txt"));
        @NotNull final Domain domain = (Domain) inputStream.readObject();
        inputStream.close();
        projectRepository.setProjects(domain.getProjects());
        taskRepository.setTasks(domain.getTasks());
        userRepository.setUsers(domain.getUsers());
    }

    public void saveToJson() throws Exception {
        @NotNull final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());

        @NotNull final File file = new File("todoServer\\data\\fileFasterXml.json");
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
    }

    public void loadFromJson() throws Exception {
        @NotNull final File file = new File("todoServer\\data\\fileFasterXml.json");
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(file, Domain.class);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        @NotNull final Domain domain = mapper.readValue(file, Domain.class);
        projectRepository.setProjects(domain.getProjects());
        taskRepository.setTasks(domain.getTasks());
        userRepository.setUsers(domain.getUsers());
    }

    public void saveToXml() throws Exception {
        @NotNull final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());

        @NotNull final File file = new File("todoServer\\data\\fileFasterXml.xml");
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
    }

    public void loadFromXml() throws Exception {
        @NotNull final File file = new File("todoServer\\data\\fileFasterXml.xml");
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        @NotNull final Domain domain = xmlMapper.readValue(file, Domain.class);
        projectRepository.setProjects(domain.getProjects());
        taskRepository.setTasks(domain.getTasks());
        userRepository.setUsers(domain.getUsers());
    }

    public void saveToJsonJaxb() throws Exception {
        @NotNull final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());

        @NotNull final File file = new File("todoServer\\data\\fileJaxb.json");
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

        jaxbMarshaller.marshal(domain, file);
    }

    public void loadFromJsonJaxb() throws Exception {
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        jaxbUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        jaxbUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);

        @NotNull final Domain domain = (Domain) jaxbUnmarshaller.unmarshal(new File("todoServer\\data\\fileJaxb.json"));
        projectRepository.setProjects(domain.getProjects());
        taskRepository.setTasks(domain.getTasks());
        userRepository.setUsers(domain.getUsers());
    }

    public void saveToXmlJaxb() throws Exception {
        @NotNull final Domain domain = new Domain();
        domain.setUsers(userRepository.getUsers());
        domain.setProjects(projectRepository.getProjects());
        domain.setTasks(taskRepository.getTasks());

        @NotNull final File file = new File("todoServer\\data\\fileJaxb.xml");
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(domain, file);
    }

    public void loadFromXmlJaxb() throws Exception {
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        @NotNull final Domain domain = (Domain) jaxbUnmarshaller.unmarshal(new File("todoServer\\data\\fileJaxb.xml"));
        projectRepository.setProjects(domain.getProjects());
        taskRepository.setTasks(domain.getTasks());
        userRepository.setUsers(domain.getUsers());
    }
}
