package securityservices.core.shared.services.securityserviceDemo;
/*
import securityservices.core.order.domain.model.Order;
import securityservices.operations.Stock;

public class SecurityServices {

    public static void main(String[] args) {

        Order o = new Order();

        System.out.println("----AÑADIMOS DETALLES----");
        System.out.println();

        //Añadimos un detalle correcto --> Retorna 0, es decir inserción correcta
        System.out.println("Detail -> " + o.setDetail("AR03;3;10.0") + "(Inserción correcta)");
        System.out.println("getDetail by String -> " + o.getDetail("AR03"));
        System.out.println("getDetail by int -> " + o.getDetail(1));
        System.out.println("--------");
        //Añadimos un detalle sin referencia --> Retorna -1, falta de referencia
        System.out.println("Detail -> " + o.setDetail(";3;10.0") + "(Campos incorrectos)");
        System.out.println("--------");
        //Añadimos un detall sin precio --> Retorna -1, campos incorrectos
        System.out.println("Detail -> " + o.setDetail("AR03;3;") + "(Campos incorrectos)");
        System.out.println("--------");
        //Añadimos un detalle sin cantidad
        System.out.println("Detalle añadido:" + o.setDetail("AR03;;10.0") + "(Campos incorrectos)");
        //Añadimos un detalle vacío --> Retorna -1, campos incorrectos
        System.out.println("Detail -> " + o.setDetail("") + "(Campos incorrectos)");
        System.out.println("--------");
        //Añadimos un detalle correcto y luego el mismo detalle con diferente cantidad --> Retornan 0, inserción correcta
        System.out.println("Detail -> " + o.setDetail("AR04;3;10.0") + "(Inserción correcta)");
        System.out.println("getDetail by String -> " + o.getDetail("AR04"));
        System.out.println("getDetail by int -> " + o.getDetail(2));
        System.out.println("Detail -> " + o.setDetail("AR04;5;10.0") + "(Inserción correcta + cantidad sumada)");
        System.out.println("getDetail by String -> " + o.getDetail("AR04"));
        System.out.println("getDetail by int -> " + o.getDetail(2));
        System.out.println("--------");

        System.out.println();
        System.out.println("----SUMAMOS DETALLES----");
        System.out.println();
        //Añado 3 detalles mas que se sumarán con los dos anteriores, por lo tanto el resultado será 5
        System.out.println("Detail -> " + o.setDetail("AR06;3;10.0") + " -> " + o.getDetail("AR06") + "(Inserción correcta)");
        System.out.println("Detail -> " + o.setDetail("AR07;3;10.0") + " -> " + o.getDetail("AR07") + "(Inserción correcta)");
        System.out.println("Detail -> " + o.setDetail("AR08;3;10.0") + " -> " + o.getDetail("AR08") + "(Inserción correcta)");

        System.out.println("Cantidad -> " + o.getNumDetails());

        System.out.println();
        System.out.println("----RECOGEMOS LOS DETALLES----");
        System.out.println();

        //Insertamos un nuevo detalle correcto
        System.out.println("Detail -> " + o.setDetail("AR10;3;10.0") + " (Inserción correcta)");
        //Recogemos los datos del detalle a partir de un String --> Lo retornará correctamente
        System.out.println("getDetail by String -> " + o.getDetail("AR10") + " / (Dato correcto)");
        //Recogemos los datos del detalle a partir de un int --> Lo retornará correctamente
        System.out.println("getDetail by int -> " + o.getDetail(6) + " /(Dato correcto)");
        //Recogemos los datos del detalle a partir de un String que no existe --> Retornará null
        System.out.println("getDetail by String -> " + o.getDetail("AR11") + " / (Dato incorrecto)");
        //Recogemos los datos del detalle a partir de un int que no existe --> Retornará null
        System.out.println("getDetail by int -> " + o.getDetail(7) + " / Dato incorrecto)");
        //Recogemos los datos del detalle a partir de un String vacío --> Retornará null
        System.out.println("getDetail by String -> " + o.getDetail("") + " / (Dato incorrecto)");

        System.out.println();
        System.out.println("----ACTUALIZAMOS LOS DETALLES----");
        System.out.println();

        //Insertamos un nuevo detalle correcto
        System.out.println("Detail -> " + o.setDetail("AR13;3;10.0") + " (Inserción correcta)");
        //Recordar que únicamente se puede modificar la cantidad, ya que supuestamente esto es el carrito de un cliente
        //Hacemos un update con String --> Retornará 0, es correcto
        System.out.println("getDetail by String -> " + o.updateDetail("AR13", "4") + " -> " + o.getDetail("AR13") + " / (Actualización correcta)");
        //Hacemos un update con int --> Retornará 0, es correcto
        System.out.println("getDetail by int -> " + o.updateDetail(7, "4") + " -> " + o.getDetail(7) + " / (Actualización correcta)");
        //Hacemos un update con String que no existe --> Retornará -2, es posición no encontrada
        System.out.println("update String -> " + o.updateDetail("AR20", "4") + " / (Actualización incorrecta)");
        //Hacemos un update con int que no existe--> Retornará -1, es posición no encontrada
        System.out.println("update int -> " + o.updateDetail(8, "4") + " / (Actualización incorrecta)");
        //Hacemos un update con String pasando campos vacíos --> Retornará -2, incorrecto
        System.out.println("update String -> " + o.updateDetail("", "") + " / (Actualización incorrecta)");
        //Hacemos un update con int pasando campos vacíos --> Retornará -1, incorrecto
        System.out.println("update int -> " + o.updateDetail(7, "") + " / (Actualización incorrecta)");

        System.out.println();
        System.out.println("----ELIMINAMOS LOS DETALLES----");
        System.out.println();

        //Insertamos un nuevo detalle correcto
        System.out.println("Detail -> " + o.setDetail("AR15;3;10.0") + " (Inserción correcta)");
        //Eliminamos el detalle por String --> Retornará 0, se ha eliminado correctamente
        System.out.println("Detail -> " + o.deleteDetail("AR15") + " (Eliminado correctamente)");
        //Insertamos un nuevo detalle correcto
        System.out.println("Detail -> " + o.setDetail("AR15;3;10.0") + " (Inserción correcta)");
        //Eliminamos el detalle por int --> Retornará 0, se ha eliminado correctamente
        System.out.println("Detail -> " + o.deleteDetail(7) + " (Eliminado correctamente)");
        //Insertamos un nuevo detalle correcto
        System.out.println("Detail -> " + o.setDetail("AR15;3;10.0") + " (Inserción correcta)");
        //Eliminamos el detalle por String pasando uno incorrecto --> Retornará -1, error
        System.out.println("Detail -> " + o.deleteDetail("AR18") + " (Error)");
        //Insertamos un nuevo detalle correcto
        System.out.println("Detail -> " + o.setDetail("AR15;3;10.0") + " (Inserción correcta)");
        //Eliminamos el detalle por int pasando uno incorrecto --> Retornará -1, error
        System.out.println("Detail -> " + o.deleteDetail(8) + " (Error)");
        //Insertamos un nuevo detalle correcto
        System.out.println("Detail -> " + o.setDetail("AR15;3;10.0") + " (Inserción correcta)");
        //Eliminamos el detalle por String vacío --> Retornará -1, error
        System.out.println("Detail -> " + o.deleteDetail("") + " (Error)");

        System.out.println();
        System.out.println("----CALCULAMOS EL VALOR DE TODOS LOS DETALLES----");
        System.out.println();

        //Calculamos el total de todos los details
        System.out.println("Detail -> " + o.getValue() + "€");

        System.out.println();
        System.out.println("----AÑADIMOS STOCK----");
        System.out.println();

        Stock s = new Stock();

        //Añadimos 3 productos junto a su stock --> Retornan 0, actualizados correctamente
        System.out.println(s.updateStock("PS5", 5) + " (Actualizado correctamente)");
        System.out.println(s.updateStock("PS4", 10) + " (Actualizado correctamente)");
        System.out.println(s.updateStock("PS3", 20) + " (Actualizado correctamente)");
        System.out.println("--------");
        //Imprimimos la cantidad de referencías que hay en el array
        System.out.println("Cantidad de referencias -> " + s.getNumLines());
        System.out.println("--------");
        //Mostramos la cantidad de cada producto
        System.out.println("Hay en stock: " + s.getAmount("PS5") + " unidades");
        System.out.println(s.getAmount("PS4") + " unidades");
        System.out.println(s.getAmount("PS3") + " unidades");
        System.out.println("--------");
        //Eliminamos una producto
        System.out.println(s.delStock("PS5") + " (Eliminado correctamente)");
        System.out.println("Cantidad de referencias -> " + s.getNumLines());
        System.out.println("--------");
        //Mostramos los productos con su cantidad
        String[] stk = s.getLines();
        for (int i = 0; i < s.getNumLines(); i++) {
            System.out.println(stk[i]);
        }
        System.out.println("--------");

        //Añadimos 2 productos mas, que vamos a actualizar
        s.updateStock("PS2", 5);
        s.updateStock("PS1", 5);
        //Los actualizamos, a "PS2" le añadimos 100 unidades, a "PS1" le quitamos 1
        System.out.println(s.updateStock("PS2", 100) + " (Actualizado correctamente)");
        System.out.println(s.updateStock("PS1", -1) + " (Actualizado correctamente)");
        System.out.println("--------");
        //Vemos la cantidad de estos dos para ver si se han actualizado bien
        System.out.println(s.getAmount("PS2") + " unidades");
        System.out.println(s.getAmount("PS1") + " unidades");

        System.out.println();
        System.out.println("----INTERFAZ BILLABLE----");
        System.out.println();

        /*Order newo = new Order("AR33", 13, 10.10, 1.1, "Venta",
                "Ended", "Non additional info", "09/12/2020-23:23:23", "10/12/2020-'17:17:17",
                "Paypal", "09/12/2020-23:23:23");
        System.out.println("Code: " + newo.getCode() + ";"
                + " Type: " + newo.getType() + ";"
                + " Client: " + newo.getClient() + ";"
                + " BeginDate: " + newo.getBeginDate() + ";"
                + " FinishDate: " + newo.getFinishDate() + ";");
*/
/*
    }
}*/
