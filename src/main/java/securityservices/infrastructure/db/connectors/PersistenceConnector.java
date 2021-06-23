package securityservices.infrastructure.db.connectors;

import securityservices.core.shared.services.serializers.Json;
import securityservices.shared.responses.ResultRequest;


public interface PersistenceConnector {
 ResultRequest<Json> connect(Json dataconnex);
 Boolean isConnect();
 ResultRequest<Json> readQuery(String query);
 ResultRequest<Json> unconnect();
 ResultRequest<Json> writeQuery(String query);
}