package ru.zagorodnikova.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractEntity implements Serializable {

    @NotNull
    private String id = UUID.randomUUID().toString();

}
