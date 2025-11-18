package com.servers.surveying_supervising.Service;

import com.servers.surveying_supervising.Model.Server;
import com.servers.surveying_supervising.Repository.ServerRepository;
import java.util.List;


import org.springframework.stereotype.Service;

@Service
public class ServerService {

    private final ServerRepository serverRepository;

    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public Server createServer(Server server) {
        Server createdServer = serverRepository.save(server);
        return createdServer;
    }

    public List<Server> Servers() {
        return serverRepository.findAll();
    }

    public boolean getServerStatus(long id) {
        boolean serverStatus = serverRepository.findById(id)
                .map(Server::getStatus)
                .orElse(false);
        return serverStatus;
    }

    public void startServer(long id) {
        Server server = serverRepository.findById(id).orElse(null);
        server.setStatus(true);
        serverRepository.save(server);

    }

    public void stopServer(long id) {
        Server server = serverRepository.findById(id).orElse(null);
        server.setStatus(false);
        serverRepository.save(server);

    }

    public void deleteServer(long id) {
        serverRepository.deleteById(id);
    }
}
