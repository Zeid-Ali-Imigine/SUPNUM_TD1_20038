package com.servers.surveying_supervising.services;

import com.servers.surveying_supervising.model.Server;
import com.servers.surveying_supervising.repository.ServerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServerService {

    private final ServerRepository serverRepository;

    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public Server createServer(String name, String ipAddress, boolean status) {
        Server server = new Server(name, ipAddress, status);
        return serverRepository.save(server);
    }

    @Transactional(readOnly = true)
    public List<Server> getServers() {
        return serverRepository.findAll();
    }


    @Transactional(readOnly = true)
    public boolean getServerStatus(Long id) {
        Server server = loadServer(id);
        return server.isStatus();
    }

    public Server startServer(Long id) {
        Server server = loadServer(id);
        server.setStatus(true);
        return serverRepository.save(server);
    }

    public Server stopServer(Long id) {
        Server server = loadServer(id);
        server.setStatus(false);
        return serverRepository.save(server);
    }

    public void deleteServer(Long id) {
        Server server = loadServer(id);
        if (server.isStatus()) {
            throw new IllegalStateException("Un serveur en cours d'exécution ne peut pas être supprimé.");
        }
        serverRepository.delete(server);
    }

    private Server loadServer(Long id) {
        return serverRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Serveur introuvable avec l'id " + id));
    }
}
