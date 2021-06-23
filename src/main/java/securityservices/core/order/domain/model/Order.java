package securityservices.core.order.domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.UUID;
import securityservices.core.shared.operation.Billable;
import securityservices.core.shared.operation.Operation;
import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public class Order extends Operation implements Billable {

    protected UUID orderId;
    protected String paymentType;
    protected LocalDateTime paymentDate;
    protected ArrayList<OrderDetail> details = new ArrayList();

    public Order() {
         this.setOrderId();
    }

     public Order getInstance() {
        return new Order();
    }
    
    public static ResultRequest<Order> getInstance(String code, Integer creator, Double value, Double surcharges, String type,
            String status, String additonalInfo, String beginDate, String finishDate,
            String paymentType, String paymentDate){
        
        Order order = new Order();
        
        ResultRequest result = order.setCode(code);
        if (result.failed()) {
            return result;
        }
        result = order.setCreator(creator);
        if (result.failed()) {
            return result;
        }
        result = order.setValue(value);
        if (result.failed()) {
            return result;
        }
        result = order.setSurcharges(surcharges);
        if (result.failed()) {
            return result;
        }
       
        result = order.setType(type);
        if (result.failed()) {
            return result;
        }
        result = order.setStatus(status);
        if (result.failed()) {
            return result;
        }
        result = order.setAdditonalInfo(additonalInfo);
        if (result.failed()) {
            return result;
        }
        result = order.setBeginDate(beginDate);
        if (result.failed()) {
            return result;
        }
        result = order.setFinishDate(finishDate);
        if (result.failed()) {
            return result;
        }
        result = order.setPaymentType(paymentType);
        if (result.failed()) {
            return result;
        }
        result = order.setPaymentDate(paymentDate);
        if (result.failed()) {
            return result;
        }
        return ResultRequest.done(order);

    }

    public String getOrderId() {
        return orderId.toString();
    }

    protected void setOrderId() {
        this.orderId = UUID.randomUUID();
    }
    public String getPaymentType() {
        return paymentType;
    }
    public ResultRequest setPaymentType(String paymentType) {
        if (paymentType == null || paymentType.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid paymentType\"");
        }
        this.paymentType = paymentType;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getPaymentDate() {
        if (this.paymentDate != null) {
            return this.paymentDate.format(this.dateTimeFormatter);
        }
        return "";
    }
    public ResultRequest setPaymentDate(String paymentDate) {
        try {
            this.paymentDate = LocalDateTime.parse(paymentDate, this.dateTimeFormatter);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid paymentDate: " + e.getMessage() + "\"");
        }
    }
    
    //getNumDetails()-> Esta función va a devolver la cantidad de detalles añadidos
    public int getNumDetails() {
        int detS = details.size();
        return detS; //guardaremos en la variable detS la cantidad de detalles
    }

    //position(String n) --> Esta función nos va a devolver la posición del detalle indicado
    protected int position(String n) {
        int position = -1; //indicamos el error
        for (int i = 0; i < getNumDetails(); i++) {
            if (details.get(i).getRef().equals(n)) { //aqui va a comparar hasta que encuentre la posición que coinicide con la referencia
                position = i; //la posicion se guarda en la variable position
            }
        }
        return position; //si no da error devuelve la posición
    }

    //setDetail(String detail) -> Con esta función podremos añadir un nuevo detalle
    public ResultRequest setDetail(String detail) {

        String[] camps = detail.split(";"); //indicamos que los campos del array se separan por un ";"

        if (camps.length != 3) { //si hay menos o mas de tres campos error
             return ResultRequest.fails("\"Error\":\"Hay mas o menos de 3 campos\"");
        }
        if (camps[0].equals("") || camps[1].equals("") || camps[2].equals("")) { 
            return ResultRequest.fails("\"Error\":\"Alguno o todos los campos estan vacíos\"");
        }
        String ref = camps[0];
        int amount = Integer.valueOf(camps[1]);
        Double price = Double.valueOf(camps[2]);

        int pos = position(ref);

        if (pos >= 0 && amount > 0 && price > 0) {
            amount += details.get(pos).getAmount();
            details.get(pos).setAmount(amount);
        } else {
            details.add(new OrderDetail(ref, amount, price));
        }

        return ResultRequest.done(ResultResponses.SUCCESS);
    }

   
    //getDetail(int n) -> Recoger el detalle a partir de un valor int
    public String getDetail(int n) {
        n--; //le restamos una posición para que comapre correctamente con el array
        for (int i = 0; i < getNumDetails(); i++) {
            if (i == n) {
                String detail = (details.get(i).getRef() + ";"
                        + details.get(i).getAmount() + ";"
                        + details.get(i).getPrice());
                return detail;
            }
        }
        return null;
    }

    //getDetail(String n) -> Recoger el detalle a partir de un valor String
    public String getDetail(String n) {
        for (int i = 0; i < getNumDetails(); i++) {
            if (details.get(i).getRef().equals(n) == true) {
                String detail = (details.get(i).getRef() + ";"
                        + details.get(i).getAmount() + ";"
                        + details.get(i).getPrice());
                return detail;
            }
        }
        return null;
    }

    //public int updateDetail(int n, String newdetail) -> actualizar la cangtidad a partir de valor int
    public ResultRequest updateDetail(int n, String newdetail) {
        if (n > getNumDetails() || newdetail.length() <= 0 || newdetail.length() > 1) {
            return ResultRequest.fails("\"Error\":\"El detalle no existe\"");
        }
        n--;
        if (n < 0) {
            return ResultRequest.fails("\"Error\":\"No existe un detalle menor a 0\"");
        }
        int amount = Integer.valueOf(newdetail);
        if (amount > 0) {
            details.get(n).setAmount(amount);
        }

        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    //public int updateDetail(String n, String newdetail) -> actualizar la cangtidad a partir de valor String
    public ResultRequest updateDetail(String n, String newdetail) {

        if (newdetail.length() < 0 || newdetail.length() > 1) {
            return ResultRequest.fails("\"Error\":\"\"");
        }
        int pos = position(n);
        if (pos >= 0) {
            int amount = Integer.valueOf(newdetail);
            if (amount > 0) {
                details.get(pos).setAmount(amount);
            }
        } else {
            return ResultRequest.fails("\"Error\":\"No existe la posición\"");
        }
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

//public int deleteDetail(int n) -> Borrar un detalle a partir de un valor int
    public ResultRequest deleteDetail(int n) {
        if (n > getNumDetails()) {
             return ResultRequest.fails("\"Error\":\"No existe el detalle\"");
        }
        n--;
        if (n <= 0) {
             return ResultRequest.fails("\"Error\":\"No existe el detalle\"");
        }
        details.remove(n);

        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    //public int deleteDetail(String n) -> Borrar un detalle a partir de un valor String
    public ResultRequest deleteDetail(String n) {
        int pos = position(n);
        if (pos >= 0) {
            details.remove(pos);
        } else {
           return ResultRequest.fails("\"Error\":\"No existe el detalle\"");
        }
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    //public double getValue()-> Precio total del carrito
    @Override
    public double getTotalValue() {
        double total = 0;
        for (int i = 0; i < getNumDetails(); i++) {
            total += (details.get(i).getPrice() * details.get(i).getAmount());
        }
        return total;
    }

    @Override
    public int getClient() {
        return creator;
    }

}
