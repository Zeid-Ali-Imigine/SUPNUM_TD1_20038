package com.servers.surveying_supervising.dtos;

import com.servers.surveying_supervising.model.Server;


public final class ServerPayloadMapper {


    private ServerPayloadMapper() {
    }

    public static ServerPayload toPayload(Server server) {
        ServerPayload payload = new ServerPayload();
        payload.setId(server.getId());
        payload.setName(server.getName());
        payload.setIpAddress(server.getIpAddress());
        payload.setStatus(server.isStatus());
        return payload;
    }


}

