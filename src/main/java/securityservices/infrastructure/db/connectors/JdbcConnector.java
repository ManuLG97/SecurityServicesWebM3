package securityservices.infrastructure.db.connectors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.shared.responses.ResultRequest;

public class JdbcConnector implements PersistenceConnector {

    public JdbcConnector() {
    }

    //Creamos las variables
    private Connection connect = null;
    private Statement stat;
    private ResultSet result;
    private Boolean con = true;
    private ResultSetMetaData mData;
    private int cols;
    //Objetos Json
    Json jconnect = JsonObjectFactory.getInstance();
    Json jObject = JsonObjectFactory.getInstance();
    Json jArr = JsonObjectFactory.getInstance();
    Json jresp = JsonObjectFactory.getInstance();

    public JdbcConnector(Json dataconnex) {
        if (this.connect(dataconnex).failed()) {
            this.con = false;
        } else {
            this.con = true;
        }
    }

    @Override
    public ResultRequest<Json> connect(Json dataConnect) {
        if (dataConnect == null) {
            //error dataConnect
            return ResultRequest.fails("{\"Error\":\"Invalid dataConnect\"}");
        } else {
            try {
                //indicamos datos conexión
                String jdbc = dataConnect.get("jdbc");
                String serv = dataConnect.get("serv");
                String port = dataConnect.get("port");
                String db = dataConnect.get("db");
                String user = dataConnect.get("user");
                String pass = dataConnect.get("pass");
                //procesamos errores
                if (jdbc == null || jdbc.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Invalid Jdbc\"}");
                }
                if (serv == null || serv.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Invalid Server\"}");
                }
                if (port == null || port.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Invalid Port\"}");
                }
                if (db == null || db.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Invalid Database\"}");
                }
                if (user == null || user.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Invalid Username\"}");
                }
                if (pass == null || pass.trim().equals("")) {
                    return ResultRequest.fails("{\"Error\":\"Invalid Password\"}");
                }
                //"jdbc:postgresql://localhost:5432/securityservices?" + "user=securserv&password=Psg1234*"
                Class.forName("org." + jdbc + ".Driver");
                this.connect = DriverManager.getConnection("jdbc:" + jdbc + "://" + serv + ":" + port + "/"
                        + db + "?" + "user=" + user + "&password=" + pass + "");
                this.stat = connect.createStatement();
                jconnect.set("Connection", "done");
                return ResultRequest.done(jconnect);
            } catch (Exception ex) {
                //error de conexión
                return ResultRequest.fails("{\"Error\":\"Connection error\"}" + ex.getMessage());
            }
        }

    }

    @Override
    //indicamos si esta conectado o no
    public Boolean isConnect() {
        return con;
    }

    @Override
    //Lectura
    public ResultRequest<Json> readQuery(String query) {
        try {
            this.result = this.stat.executeQuery(query);
            this.mData = this.result.getMetaData();
            cols = mData.getColumnCount();

            while (result.next()) {
                jObject.removeAll();
                for (int i = 1; i <= cols; i++) {
                    jObject.set(mData.getColumnName(i), result.getString(i));
                }
                jArr.set("content", jObject);
            }
        } catch (Exception ex) {
            //error lectura
            return ResultRequest.fails("{\"Error\":\"Imposible to read\"}");
        }

        return ResultRequest.done(jArr);

    }

    @Override
    //Escritura
    public ResultRequest<Json> writeQuery(String query) {

        try {
            this.stat.executeUpdate(query);
            jresp.set("{\"Done\":\"Executed\"}");

            return ResultRequest.done(jresp);
        } catch (Exception ex) {
            //error escritura
            return ResultRequest.fails("{\"Error\":\"Can't write\"} " + ex.toString());
        }
    }

    //Desconectar sesión
    public ResultRequest<Json> unconnect() {
        try {
            jconnect.removeAll();
            jconnect.set("{\"Done\":\"Your session is closed\"}");
            this.connect.close();
            return ResultRequest.done(jconnect);
        } catch (Exception ex) {
            return ResultRequest.fails("{\"Error\":\"Unable to close your session\"}");
        }
    }

}
