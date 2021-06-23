package securityservices.core.shared.services.cheked;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checking {

    //DNI
    public static int checkDNI(String DNI) {
        //en el patter ya se le indica que iouñ no pueden ser letras válidas en un DNI
        Pattern patro = Pattern.compile("(\\d[0-9]{7,8})([ -]?)([a-z A-Z&&[^IOUÑ iouñ]])$"); //exept ^I,O,U,i,o,u
        Matcher mymatcher = patro.matcher(DNI);
        //Creamos un array con las letras
        String[] letters = new String[]{"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D",
            "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E", "T"};

        //si el patrón no se cumple error
        if (mymatcher.matches() == false) {
            return -2;
        }

        //printamos los campos
        System.out.println("Numbers: " + mymatcher.group(1));
        System.out.println("Letter: " + mymatcher.group(3));

        //guardamos en nums los numeros del DNI
        int nums = Integer.valueOf(mymatcher.group(1));
        //guardamos en letter la letra del DNI
        String letter = mymatcher.group(3);
        //tenemos que calcular el resto, por lo tanto dividimos entre 23 los numeros del DNI
        int ok = nums % 23;

        //hacemos un if con equals para comprobar, si la letra no coincide dará error
        if (!letter.equals(letters[ok])) {
            return -1;
        }
        return 0;

    }

    //DATE
    public static int checkDate(String Date) {
        int day, month, year;

        //Indicamos el pattern
        Pattern pattern = Pattern.compile("(\\d{1,2})[ -/](\\d{1,2})[ -/](\\d{2,4})$");
        Matcher mymatcher = pattern.matcher(Date);
        //creamos un array del año bisiesto
        int[] finalDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        //si el patrón no se cumple error
        if (mymatcher.matches() == false) {
            return -2;
        }

        //printamos los datos
        System.out.println("Day: " + mymatcher.group(1));
        System.out.println("Month: " + mymatcher.group(2));
        System.out.println("Year: " + mymatcher.group(3));

        day = Integer.valueOf(mymatcher.group(1));
        month = Integer.valueOf(mymatcher.group(2));
        year = Integer.valueOf(mymatcher.group(3));

        //Si el dia/mes es menor o mayor a los numeros indicamos dará error
        if (day < 1 || day > 31 || month < 1 || month > 12) {
            return -1;
        }
        //si el mes es 2 y es bisisesto
        if (month == 2 && (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
            finalDays[1] = 29;
        }
        //comprobar dias
        if (day > finalDays[month - 1]) {
            return -1;
        }
        return 0;
    }

    //DIFFDATES 
    //Importante: Se que no es la manera adecuada de hacerlo, pero no he sabido hacerlo de otra manera.
    public static int diffDates(String fDate1, String fDate2) {
        //indicamos el pattern
        Pattern pattern = Pattern.compile("(\\d{1,2})/(\\d{1,2})/(\\d{2,4})$");
        Matcher mymatcher1 = pattern.matcher(fDate1);
        Matcher mymatcher2 = pattern.matcher(fDate2);
        
        //hacemos variables de las fechas y el formatter
        int day1, month1, day2, month2;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
        //si no coinciden las fechas con el pattern error
        if (mymatcher1.matches() == false || mymatcher2.matches() == false) {
            return -2;
        }
        
        //printamos las fechas
        System.out.println("Date 1: " + mymatcher1.group(1) + "/" + mymatcher1.group(2) + "/" + mymatcher1.group(3));
        System.out.println("Date 2: " + mymatcher2.group(1) + "/" + mymatcher2.group(2) + "/" + mymatcher2.group(3));

        day1 = Integer.valueOf(mymatcher1.group(1));
        month1 = Integer.valueOf(mymatcher1.group(2));
        day2 = Integer.valueOf(mymatcher2.group(1));
        month2 = Integer.valueOf(mymatcher2.group(2));

        //Si el dia/mes es menor o mayor a los numeros indicados dará error
        if ((day1 < 1 || day2 < 1) || (day1 > 31 || day2 > 31) || (month1 < 1 || month2 < 1) || (month1 > 12 || month2 > 12)) {
            return -1;
        }
        
        //convertimos las fechas en LocalDate
        LocalDate date1 = LocalDate.parse(fDate1, formatter);
        LocalDate date2 = LocalDate.parse(fDate2, formatter);
        
        //calcular diferencia entre las dos fechas
        long diffDays = ChronoUnit.DAYS.between(date1, date2);
        //convertimos el long en integer
        int diffDay = (int) diffDays;
  
        return diffDay;
    }

    //EMAIL
    public static int checkEmail(String Email) {
        Pattern pattern = Pattern.compile("(\\w{5,30})(@{1})(\\w{5,20})+(\\.[a-zA-Z]{2,6})$");
        Matcher mymatcher = pattern.matcher(Email);

        //si el email no cumple el pattern error
        if (mymatcher.matches() == false) {
            return -1;
        }
        System.out.println("Inicio correo: " + mymatcher.group(1));
        System.out.println("Tipo: " + mymatcher.group(3));
        System.out.println("Dominio: " + mymatcher.group(4));
        return 0;
    }

}
