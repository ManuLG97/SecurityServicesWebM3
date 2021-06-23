package securityservices.managment.catalogs.persistence;

//Classe que subministra el tipus específic de connexió que necessitem encapsulant els paràmetres que no es volen fer públics
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;

public class PersistenceConnectionFactory {

    private static Json postgreConnection() {
        Json jcon = JsonObjectFactory.getInstance();
        jcon.set("{"
                + "\"jdbc\":\"postgresql\","
                + "\"serv\":\"localhost\","
                + "\"port\":\"5432\","
                + "\"db\":\"securityservices\","
                + "\"user\":\"securserv\","
                + "\"pass\":\"Psg1234*\""
                + "}"
        );
        return jcon;
    }

    public static Json getDataConnection() {
        return postgreConnection();
    }
}
