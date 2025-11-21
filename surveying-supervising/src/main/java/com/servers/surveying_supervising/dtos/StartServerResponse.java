package com.servers.surveying_supervising.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "startServerResponse", namespace = ServerSoapNamespace.NAMESPACE_URI)
public class StartServerResponse {

    @XmlElement(required = true)
    private ServerPayload server;

    public ServerPayload getServer() {
        return server;
    }

    public void setServer(ServerPayload server) {
        this.server = server;
    }
}

