package com.servers.surveying_supervising.endpoints;

import com.servers.surveying_supervising.dtos.CreateServerRequest;
import com.servers.surveying_supervising.dtos.CreateServerResponse;
import com.servers.surveying_supervising.dtos.DeleteServerRequest;
import com.servers.surveying_supervising.dtos.DeleteServerResponse;
import com.servers.surveying_supervising.dtos.GetAllServersRequest;
import com.servers.surveying_supervising.dtos.GetAllServersResponse;
import com.servers.surveying_supervising.dtos.GetServerStatusRequest;
import com.servers.surveying_supervising.dtos.GetServerStatusResponse;
import com.servers.surveying_supervising.dtos.ServerPayloadMapper;
import com.servers.surveying_supervising.dtos.ServerSoapNamespace;
import com.servers.surveying_supervising.dtos.StartServerRequest;
import com.servers.surveying_supervising.dtos.StartServerResponse;
import com.servers.surveying_supervising.dtos.StopServerRequest;
import com.servers.surveying_supervising.dtos.StopServerResponse;
import com.servers.surveying_supervising.model.Server;
import com.servers.surveying_supervising.services.ServerService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ServerEndpoint {
    private final ServerService serverService;

    public ServerEndpoint(ServerService service) {
        this.serverService = service;
    }

    @PayloadRoot(namespace = ServerSoapNamespace.NAMESPACE_URI, localPart = "createServerRequest")
    @ResponsePayload
    public CreateServerResponse createServer(@RequestPayload CreateServerRequest request) {
        Server created = serverService.createServer(request.getName(), request.getIpAddress(), request.isStatus());
        CreateServerResponse response = new CreateServerResponse();
        response.setServer(ServerPayloadMapper.toPayload(created));
        return response;
    }

    @PayloadRoot(namespace = ServerSoapNamespace.NAMESPACE_URI, localPart = "getAllServersRequest")
    @ResponsePayload
    public GetAllServersResponse getServers(@RequestPayload GetAllServersRequest request) {
        GetAllServersResponse response = new GetAllServersResponse();
        serverService
                .getServers()
                .stream()
                .map(ServerPayloadMapper::toPayload)
                .forEach(response.getServers()::add);
        return response;
    }

    @PayloadRoot(namespace = ServerSoapNamespace.NAMESPACE_URI, localPart = "getServerStatusRequest")
    @ResponsePayload
    public GetServerStatusResponse getServerStatus(@RequestPayload GetServerStatusRequest request) {
        boolean running = serverService.getServerStatus(request.getId());
        GetServerStatusResponse response = new GetServerStatusResponse();
        response.setRunning(running);
        return response;
    }

    @PayloadRoot(namespace = ServerSoapNamespace.NAMESPACE_URI, localPart = "startServerRequest")
    @ResponsePayload
    public StartServerResponse startServer(@RequestPayload StartServerRequest request) {
        Server server = serverService.startServer(request.getId());
        StartServerResponse response = new StartServerResponse();
        response.setServer(ServerPayloadMapper.toPayload(server));
        return response;
    }

    @PayloadRoot(namespace = ServerSoapNamespace.NAMESPACE_URI, localPart = "stopServerRequest")
    @ResponsePayload
    public StopServerResponse stopServer(@RequestPayload StopServerRequest request) {
        Server server = serverService.stopServer(request.getId());
        StopServerResponse response = new StopServerResponse();
        response.setServer(ServerPayloadMapper.toPayload(server));
        return response;
    }

    @PayloadRoot(namespace = ServerSoapNamespace.NAMESPACE_URI, localPart = "deleteServerRequest")
    @ResponsePayload
    public DeleteServerResponse deleteServer(@RequestPayload DeleteServerRequest request) {
        DeleteServerResponse response = new DeleteServerResponse();
        try {
            serverService.deleteServer(request.getId());
            response.setSuccess(true);
            response.setMessage("Serveur supprimé avec succès.");
        } catch (IllegalStateException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}
