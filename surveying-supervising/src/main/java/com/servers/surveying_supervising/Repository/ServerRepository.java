package com.servers.surveying_supervising.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.servers.surveying_supervising.Model.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {


}
