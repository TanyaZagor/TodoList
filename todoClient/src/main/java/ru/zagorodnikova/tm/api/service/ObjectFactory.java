
package ru.zagorodnikova.tm.api.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.zagorodnikova.tm.api.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Data_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "data");
    private final static QName _FindAllProjects_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "findAllProjects");
    private final static QName _FindAllProjectsResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "findAllProjectsResponse");
    private final static QName _FindOneProject_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "findOneProject");
    private final static QName _FindOneProjectResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "findOneProjectResponse");
    private final static QName _MergeProject_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "mergeProject");
    private final static QName _MergeProjectResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "mergeProjectResponse");
    private final static QName _PersistProject_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "persistProject");
    private final static QName _PersistProjectResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "persistProjectResponse");
    private final static QName _Project_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "project");
    private final static QName _RemoveAllProjects_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "removeAllProjects");
    private final static QName _RemoveAllProjectsResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "removeAllProjectsResponse");
    private final static QName _RemoveProject_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "removeProject");
    private final static QName _RemoveProjectResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "removeProjectResponse");
    private final static QName _SortProjectsByDateCreated_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "sortProjectsByDateCreated");
    private final static QName _SortProjectsByDateCreatedResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "sortProjectsByDateCreatedResponse");
    private final static QName _SortProjectsByDateFinish_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "sortProjectsByDateFinish");
    private final static QName _SortProjectsByDateFinishResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "sortProjectsByDateFinishResponse");
    private final static QName _SortProjectsByDateStart_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "sortProjectsByDateStart");
    private final static QName _SortProjectsByDateStartResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "sortProjectsByDateStartResponse");
    private final static QName _SortProjectsByStatus_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "sortProjectsByStatus");
    private final static QName _SortProjectsByStatusResponse_QNAME = new QName("http://service.api.tm.zagorodnikova.ru/", "sortProjectsByStatusResponse");
    private final static QName _ProjectId_QNAME = new QName("", "id");
    private final static QName _ProjectUserId_QNAME = new QName("", "userId");
    private final static QName _ProjectName_QNAME = new QName("", "name");
    private final static QName _ProjectDescription_QNAME = new QName("", "description");
    private final static QName _ProjectDateFinish_QNAME = new QName("", "dateFinish");
    private final static QName _ProjectDateStart_QNAME = new QName("", "dateStart");
    private final static QName _ProjectDateCreate_QNAME = new QName("", "dateCreate");
    private final static QName _ProjectStatus_QNAME = new QName("", "status");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.zagorodnikova.tm.api.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindAllProjects }
     * 
     */
    public FindAllProjects createFindAllProjects() {
        return new FindAllProjects();
    }

    /**
     * Create an instance of {@link FindAllProjectsResponse }
     * 
     */
    public FindAllProjectsResponse createFindAllProjectsResponse() {
        return new FindAllProjectsResponse();
    }

    /**
     * Create an instance of {@link FindOneProject }
     * 
     */
    public FindOneProject createFindOneProject() {
        return new FindOneProject();
    }

    /**
     * Create an instance of {@link FindOneProjectResponse }
     * 
     */
    public FindOneProjectResponse createFindOneProjectResponse() {
        return new FindOneProjectResponse();
    }

    /**
     * Create an instance of {@link MergeProject }
     * 
     */
    public MergeProject createMergeProject() {
        return new MergeProject();
    }

    /**
     * Create an instance of {@link MergeProjectResponse }
     * 
     */
    public MergeProjectResponse createMergeProjectResponse() {
        return new MergeProjectResponse();
    }

    /**
     * Create an instance of {@link PersistProject }
     * 
     */
    public PersistProject createPersistProject() {
        return new PersistProject();
    }

    /**
     * Create an instance of {@link PersistProjectResponse }
     * 
     */
    public PersistProjectResponse createPersistProjectResponse() {
        return new PersistProjectResponse();
    }

    /**
     * Create an instance of {@link Project }
     * 
     */
    public Project createProject() {
        return new Project();
    }

    /**
     * Create an instance of {@link RemoveAllProjects }
     * 
     */
    public RemoveAllProjects createRemoveAllProjects() {
        return new RemoveAllProjects();
    }

    /**
     * Create an instance of {@link RemoveAllProjectsResponse }
     * 
     */
    public RemoveAllProjectsResponse createRemoveAllProjectsResponse() {
        return new RemoveAllProjectsResponse();
    }

    /**
     * Create an instance of {@link RemoveProject }
     * 
     */
    public RemoveProject createRemoveProject() {
        return new RemoveProject();
    }

    /**
     * Create an instance of {@link RemoveProjectResponse }
     * 
     */
    public RemoveProjectResponse createRemoveProjectResponse() {
        return new RemoveProjectResponse();
    }

    /**
     * Create an instance of {@link SortProjectsByDateCreated }
     * 
     */
    public SortProjectsByDateCreated createSortProjectsByDateCreated() {
        return new SortProjectsByDateCreated();
    }

    /**
     * Create an instance of {@link SortProjectsByDateCreatedResponse }
     * 
     */
    public SortProjectsByDateCreatedResponse createSortProjectsByDateCreatedResponse() {
        return new SortProjectsByDateCreatedResponse();
    }

    /**
     * Create an instance of {@link SortProjectsByDateFinish }
     * 
     */
    public SortProjectsByDateFinish createSortProjectsByDateFinish() {
        return new SortProjectsByDateFinish();
    }

    /**
     * Create an instance of {@link SortProjectsByDateFinishResponse }
     * 
     */
    public SortProjectsByDateFinishResponse createSortProjectsByDateFinishResponse() {
        return new SortProjectsByDateFinishResponse();
    }

    /**
     * Create an instance of {@link SortProjectsByDateStart }
     * 
     */
    public SortProjectsByDateStart createSortProjectsByDateStart() {
        return new SortProjectsByDateStart();
    }

    /**
     * Create an instance of {@link SortProjectsByDateStartResponse }
     * 
     */
    public SortProjectsByDateStartResponse createSortProjectsByDateStartResponse() {
        return new SortProjectsByDateStartResponse();
    }

    /**
     * Create an instance of {@link SortProjectsByStatus }
     * 
     */
    public SortProjectsByStatus createSortProjectsByStatus() {
        return new SortProjectsByStatus();
    }

    /**
     * Create an instance of {@link SortProjectsByStatusResponse }
     * 
     */
    public SortProjectsByStatusResponse createSortProjectsByStatusResponse() {
        return new SortProjectsByStatusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "data")
    public JAXBElement<AbstractEntity> createData(AbstractEntity value) {
        return new JAXBElement<AbstractEntity>(_Data_QNAME, AbstractEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllProjects }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "findAllProjects")
    public JAXBElement<FindAllProjects> createFindAllProjects(FindAllProjects value) {
        return new JAXBElement<FindAllProjects>(_FindAllProjects_QNAME, FindAllProjects.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllProjectsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "findAllProjectsResponse")
    public JAXBElement<FindAllProjectsResponse> createFindAllProjectsResponse(FindAllProjectsResponse value) {
        return new JAXBElement<FindAllProjectsResponse>(_FindAllProjectsResponse_QNAME, FindAllProjectsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "findOneProject")
    public JAXBElement<FindOneProject> createFindOneProject(FindOneProject value) {
        return new JAXBElement<FindOneProject>(_FindOneProject_QNAME, FindOneProject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "findOneProjectResponse")
    public JAXBElement<FindOneProjectResponse> createFindOneProjectResponse(FindOneProjectResponse value) {
        return new JAXBElement<FindOneProjectResponse>(_FindOneProjectResponse_QNAME, FindOneProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "mergeProject")
    public JAXBElement<MergeProject> createMergeProject(MergeProject value) {
        return new JAXBElement<MergeProject>(_MergeProject_QNAME, MergeProject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "mergeProjectResponse")
    public JAXBElement<MergeProjectResponse> createMergeProjectResponse(MergeProjectResponse value) {
        return new JAXBElement<MergeProjectResponse>(_MergeProjectResponse_QNAME, MergeProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "persistProject")
    public JAXBElement<PersistProject> createPersistProject(PersistProject value) {
        return new JAXBElement<PersistProject>(_PersistProject_QNAME, PersistProject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "persistProjectResponse")
    public JAXBElement<PersistProjectResponse> createPersistProjectResponse(PersistProjectResponse value) {
        return new JAXBElement<PersistProjectResponse>(_PersistProjectResponse_QNAME, PersistProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Project }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "project")
    public JAXBElement<Project> createProject(Project value) {
        return new JAXBElement<Project>(_Project_QNAME, Project.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllProjects }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "removeAllProjects")
    public JAXBElement<RemoveAllProjects> createRemoveAllProjects(RemoveAllProjects value) {
        return new JAXBElement<RemoveAllProjects>(_RemoveAllProjects_QNAME, RemoveAllProjects.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllProjectsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "removeAllProjectsResponse")
    public JAXBElement<RemoveAllProjectsResponse> createRemoveAllProjectsResponse(RemoveAllProjectsResponse value) {
        return new JAXBElement<RemoveAllProjectsResponse>(_RemoveAllProjectsResponse_QNAME, RemoveAllProjectsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "removeProject")
    public JAXBElement<RemoveProject> createRemoveProject(RemoveProject value) {
        return new JAXBElement<RemoveProject>(_RemoveProject_QNAME, RemoveProject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "removeProjectResponse")
    public JAXBElement<RemoveProjectResponse> createRemoveProjectResponse(RemoveProjectResponse value) {
        return new JAXBElement<RemoveProjectResponse>(_RemoveProjectResponse_QNAME, RemoveProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortProjectsByDateCreated }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "sortProjectsByDateCreated")
    public JAXBElement<SortProjectsByDateCreated> createSortProjectsByDateCreated(SortProjectsByDateCreated value) {
        return new JAXBElement<SortProjectsByDateCreated>(_SortProjectsByDateCreated_QNAME, SortProjectsByDateCreated.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortProjectsByDateCreatedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "sortProjectsByDateCreatedResponse")
    public JAXBElement<SortProjectsByDateCreatedResponse> createSortProjectsByDateCreatedResponse(SortProjectsByDateCreatedResponse value) {
        return new JAXBElement<SortProjectsByDateCreatedResponse>(_SortProjectsByDateCreatedResponse_QNAME, SortProjectsByDateCreatedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortProjectsByDateFinish }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "sortProjectsByDateFinish")
    public JAXBElement<SortProjectsByDateFinish> createSortProjectsByDateFinish(SortProjectsByDateFinish value) {
        return new JAXBElement<SortProjectsByDateFinish>(_SortProjectsByDateFinish_QNAME, SortProjectsByDateFinish.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortProjectsByDateFinishResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "sortProjectsByDateFinishResponse")
    public JAXBElement<SortProjectsByDateFinishResponse> createSortProjectsByDateFinishResponse(SortProjectsByDateFinishResponse value) {
        return new JAXBElement<SortProjectsByDateFinishResponse>(_SortProjectsByDateFinishResponse_QNAME, SortProjectsByDateFinishResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortProjectsByDateStart }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "sortProjectsByDateStart")
    public JAXBElement<SortProjectsByDateStart> createSortProjectsByDateStart(SortProjectsByDateStart value) {
        return new JAXBElement<SortProjectsByDateStart>(_SortProjectsByDateStart_QNAME, SortProjectsByDateStart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortProjectsByDateStartResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "sortProjectsByDateStartResponse")
    public JAXBElement<SortProjectsByDateStartResponse> createSortProjectsByDateStartResponse(SortProjectsByDateStartResponse value) {
        return new JAXBElement<SortProjectsByDateStartResponse>(_SortProjectsByDateStartResponse_QNAME, SortProjectsByDateStartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortProjectsByStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "sortProjectsByStatus")
    public JAXBElement<SortProjectsByStatus> createSortProjectsByStatus(SortProjectsByStatus value) {
        return new JAXBElement<SortProjectsByStatus>(_SortProjectsByStatus_QNAME, SortProjectsByStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortProjectsByStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.zagorodnikova.ru/", name = "sortProjectsByStatusResponse")
    public JAXBElement<SortProjectsByStatusResponse> createSortProjectsByStatusResponse(SortProjectsByStatusResponse value) {
        return new JAXBElement<SortProjectsByStatusResponse>(_SortProjectsByStatusResponse_QNAME, SortProjectsByStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id", scope = Project.class)
    public JAXBElement<String> createProjectId(String value) {
        return new JAXBElement<String>(_ProjectId_QNAME, String.class, Project.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "userId", scope = Project.class)
    public JAXBElement<String> createProjectUserId(String value) {
        return new JAXBElement<String>(_ProjectUserId_QNAME, String.class, Project.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name", scope = Project.class)
    public JAXBElement<String> createProjectName(String value) {
        return new JAXBElement<String>(_ProjectName_QNAME, String.class, Project.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description", scope = Project.class)
    public JAXBElement<String> createProjectDescription(String value) {
        return new JAXBElement<String>(_ProjectDescription_QNAME, String.class, Project.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dateFinish", scope = Project.class)
    public JAXBElement<XMLGregorianCalendar> createProjectDateFinish(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ProjectDateFinish_QNAME, XMLGregorianCalendar.class, Project.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dateStart", scope = Project.class)
    public JAXBElement<XMLGregorianCalendar> createProjectDateStart(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ProjectDateStart_QNAME, XMLGregorianCalendar.class, Project.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dateCreate", scope = Project.class)
    public JAXBElement<XMLGregorianCalendar> createProjectDateCreate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ProjectDateCreate_QNAME, XMLGregorianCalendar.class, Project.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Status }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "status", scope = Project.class)
    public JAXBElement<Status> createProjectStatus(Status value) {
        return new JAXBElement<Status>(_ProjectStatus_QNAME, Status.class, Project.class, value);
    }

}
