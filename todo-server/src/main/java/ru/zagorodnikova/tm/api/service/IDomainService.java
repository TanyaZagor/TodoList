package ru.zagorodnikova.tm.api.service;

public interface IDomainService {

    void save() throws Exception;

    void load() throws Exception;

    void saveToJson() throws Exception;

    void loadFromJson() throws Exception;

    void saveToXml() throws Exception;

    void loadFromXml() throws Exception;

    void saveToJsonJaxb() throws Exception;

    void loadFromJsonJaxb() throws Exception;

    void saveToXmlJaxb() throws Exception;

    void loadFromXmlJaxb() throws Exception;
}
