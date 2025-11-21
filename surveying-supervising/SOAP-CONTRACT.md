## Contrat SOAP – Service de supervision des serveurs

- **Endpoint SOAP** : `POST http://localhost:8081/ws`
- **Namespace** : `http://server.com/servers`
- **WSDL** : `http://localhost:8081/ws/servers.wsdl`
- **Schéma XSD** : `src/main/resources/servers.xsd`

### Opérations disponibles

| Opération | Requête (localPart) | Description |
| --- | --- | --- |
| `createServer` | `createServerRequest` | Créer un serveur (nom, IP, statut initial). |
| `getAllServers` | `getAllServersRequest` | Lister tous les serveurs. |
| `renameServer` | `renameServerRequest` | Renommer un serveur existant. |
| `getServerStatus` | `getServerStatusRequest` | Connaître l’état (démarré/arrêté) d’un serveur. |
| `startServer` | `startServerRequest` | Démarrer un serveur (statut = `true`). |
| `stopServer` | `stopServerRequest` | Arrêter un serveur (statut = `false`). |
| `deleteServer` | `deleteServerRequest` | Supprimer un serveur (impossible si `status = true`). |

### Exemples

#### Créer un serveur
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://server.com/servers">
  <soapenv:Body>
    <ser:createServerRequest>
      <ser:name>srv-app-01</ser:name>
      <ser:ipAddress>10.10.0.5</ser:ipAddress>
      <ser:status>false</ser:status>
    </ser:createServerRequest>
  </soapenv:Body>
</soapenv:Envelope>
```

#### Réponse
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://server.com/servers">
  <soapenv:Body>
    <ser:createServerResponse>
      <ser:server>
        <ser:id>1</ser:id>
        <ser:name>srv-app-01</ser:name>
        <ser:ipAddress>10.10.0.5</ser:ipAddress>
        <ser:status>false</ser:status>
        <ser:createdAt>2025-11-21T10:00:00Z</ser:createdAt>
        <ser:updatedAt>2025-11-21T10:00:00Z</ser:updatedAt>
      </ser:server>
    </ser:createServerResponse>
  </soapenv:Body>
</soapenv:Envelope>
```

### Tester rapidement

1. Démarrer l’application : `./mvnw spring-boot:run`
2. Télécharger le WSDL : `curl http://localhost:8081/ws/servers.wsdl`
3. Envoyer une requête : `curl -d @request.xml -H "Content-Type:text/xml" http://localhost:8081/ws`

Le schéma XSD et les classes JAXB décrivent précisément tous les champs supportés.*** End Patch

