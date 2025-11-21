package com.servers.surveying_supervising.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servers.surveying_supervising.model.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
    
}
