package securityservices.core.shared.services.serializers;

import securityservices.core.shared.services.serializers.jsonapis.JavaJson;
import securityservices.core.shared.services.serializers.Json;

/*
CLASSE AUXILIAR QUE PERMITIRA ENCAPSULAR EL TIPO DE LIBRERIA U OBJETO QUE SE UTILIZARA
INTERNAMENTE PARA LA GESTION DE LA INFORMACION DE TIPO JSON
SIRVE DE EJEMPLO PARA COMPROBAR LA POTENCIA DE LAS INTERFACES, QUE NOS PERMITEN
AISLARNOS DE LAS IMPLEMENTACIONES FINALES Y APORTAN UNA GRAN FLEXIBILIDAD ANTE LOS CAMBIOS
*/
public class JsonObjectFactory {    
    public static Json getInstance(){
        return new JavaJson();
    }  
    
    public static Json getInstance(String selectAdapter){
        return new JavaJson();
        //create map of jsonadapters and use here
    }
}