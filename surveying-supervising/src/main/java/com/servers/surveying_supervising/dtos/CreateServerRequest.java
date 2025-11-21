package com.servers.surveying_supervising.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "createServerRequest", namespace = ServerSoapNamespace.NAMESPACE_URI)
public class CreateServerRequest {

    @XmlElement(name = "name", namespace = ServerSoapNamespace.NAMESPACE_URI, required = true)
    private String name;

    @XmlElement(name = "ipAddress", namespace = ServerSoapNamespace.NAMESPACE_URI, required = true)
    private String ipAddress;

    @XmlElement(name = "status", namespace = ServerSoapNamespace.NAMESPACE_URI)
    private boolean status;

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
}
