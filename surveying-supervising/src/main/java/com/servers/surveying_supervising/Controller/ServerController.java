package com.servers.surveying_supervising.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.servers.surveying_supervising.Model.Server;
import com.servers.surveying_supervising.Service.ServerService;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/server")
public class ServerController {
    
    private final ServerService serverService;
    
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @PostMapping
    public ResponseEntity<?> createServer( @RequestBody Server server) {
        try {
            Server createdServer = serverService.createServer(server);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdServer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                new ErrorResponse("Invalid server data: " + e.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Failed to create server"));
        }
    }

    @GetMapping
    public ResponseEntity<?> listServers() {
        try {
            List<Server> servers = serverService.Servers(); // Renommé pour plus de clarté
            return ResponseEntity.ok(servers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Failed to retrieve servers"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServerStatus(@PathVariable long id) {
        try {
            boolean status = serverService.getServerStatus(id);
            String statusMessage = status ? "Server is running" : "Server is stopped";
            return ResponseEntity.ok(new StatusResponse(status, statusMessage)); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error retrieving server status"));
        }
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<?> startServer(@PathVariable long id) {
        try {
            serverService.startServer(id);
            return ResponseEntity.ok(new MessageResponse("Server started successfully"));
        
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("Server is already running"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error when starting the server"));
        }
    }

    @PutMapping("/{id}/stop")
    public ResponseEntity<?> stopServer(@PathVariable long id) {
        try {
            serverService.stopServer(id);
            return ResponseEntity.ok(new MessageResponse("Server stopped successfully"));
        
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("Server is already stopped"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error when stopping the server"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServer(@PathVariable long id) {
        try {
            serverService.deleteServer(id);
            return ResponseEntity.ok(new MessageResponse("Server deleted successfully"));
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error when deleting the server"));
        }
    }
}

// Classes DTO pour les réponses standardisées
record ErrorResponse(String message, LocalDateTime timestamp) {
    public ErrorResponse(String message) {
        this(message, LocalDateTime.now());
    }
}

record MessageResponse(String message, LocalDateTime timestamp) {
    public MessageResponse(String message) {
        this(message, LocalDateTime.now());
    }
}

record StatusResponse(boolean status, String message, LocalDateTime timestamp) {
    public StatusResponse(boolean status, String message) {
        this(status, message, LocalDateTime.now());
    }
}