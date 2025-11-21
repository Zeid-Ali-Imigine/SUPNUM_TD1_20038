package com.servers.surveying_supervising.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "server",
        namespace = ServerSoapNamespace.NAMESPACE_URI,
        propOrder = {"id", "name", "ipAddress", "status", "createdAt", "updatedAt"})
@XmlRootElement(name = "server", namespace = ServerSoapNamespace.NAMESPACE_URI)
public class ServerPayload {

    private Long id;
    private String name;
    @XmlElement(name = "ipAddress")
    private String ipAddress;
    private boolean status;

    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar createdAt;

    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public XMLGregorianCalendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(XMLGregorianCalendar createdAt) {
        this.createdAt = createdAt;
    }

    public XMLGregorianCalendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(XMLGregorianCalendar updatedAt) {
        this.updatedAt = updatedAt;
    }
}

