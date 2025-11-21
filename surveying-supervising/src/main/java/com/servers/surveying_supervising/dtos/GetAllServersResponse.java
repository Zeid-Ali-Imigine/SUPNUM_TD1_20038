package com.servers.surveying_supervising.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getAllServersResponse", namespace = ServerSoapNamespace.NAMESPACE_URI)
public class GetAllServersResponse {

    @XmlElement(name = "server")
    private final List<ServerPayload> servers = new ArrayList<>();

    public List<ServerPayload> getServers() {
        return servers;
    }
}

