package ru.zagorodnikova.tm.api.service;

public interface IDomainService {

    void save();

    void load();

    void saveToJson();

    void loadFromJson();

    void saveToXml();

    void loadFromXml();

    void saveToJsonJaxb();

    void loadFromJsonJaxb();

    void saveToXmlJaxb();

    void loadFromXmlJaxb();
}
