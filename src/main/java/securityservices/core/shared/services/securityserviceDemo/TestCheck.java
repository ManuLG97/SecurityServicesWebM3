package securityservices.core.shared.services.securityserviceDemo;

import securityservices.core.shared.services.cheked.Checking;

public class TestCheck {

    public static void main(String[] args) {

        System.out.println("----Pruebas del DNI----");
        System.out.println();
        System.out.println("---DNI correcto---");
        int error = Checking.checkDNI("45126056X");
        switch (error) {
            case 0:
                System.out.println("DNI correcto.");
                break;
            case -1:
                System.out.println("DNI incorrecto, introduce bien los campos.");
                break;
            case -2:
                System.out.println("Patrón del DNI incorrecto.");
                break;
        }
        System.out.println();
        System.out.println("---DNI incorrecto---");
        int error2 = Checking.checkDNI("45126056Y");
        switch (error2) {
            case 0:
                System.out.println("DNI correcto.");
                break;
            case -1:
                System.out.println("DNI incorrecto, introduce bien los campos.");
                break;
            case -2:
                System.out.println("Patrón del DNI incorrecto.");
                break;
        }
        System.out.println();
        System.out.println("---DNI con letra que no existe---");
        int error3 = Checking.checkDNI("45126056o");
        switch (error3) {
            case 0:
                System.out.println("DNI correcto.");
                break;
            case -1:
                System.out.println("DNI incorrecto, introduce bien los campos.");
                break;
            case -2:
                System.out.println("Patrón del DNI incorrecto.");
                break;
        }
        System.out.println();
        System.out.println("---DNI vacío---");
        int error4 = Checking.checkDNI("");
        switch (error4) {
            case 0:
                System.out.println("DNI correcto.");
                break;
            case -1:
                System.out.println("DNI incorrecto, introduce bien los campos.");
                break;
            case -2:
                System.out.println("Patrón del DNI incorrecto.");
                break;
        }
        System.out.println();
        System.out.println("---DNI con letras/numeros de mas---");
        int error5 = Checking.checkDNI("4512688056po");
        switch (error5) {
            case 0:
                System.out.println("DNI correcto.");
                break;
            case -1:
                System.out.println("DNI incorrecto, introduce bien los campos.");
                break;
            case -2:
                System.out.println("Patrón del DNI incorrecto.");
                break;
        }

        System.out.println("______________________");
        System.out.println();
        System.out.println("----Pruebas de Fechas----");
        System.out.println();
        System.out.println("---Fecha correcta---");
        int error6 = Checking.checkDate("13/11/2020");
        switch (error6) {
            case 0:
                System.out.println("Fecha correcta.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            case -2:
                System.out.println("Patrón de la fecha incorrecto.");
                break;

        }
        System.out.println();
        System.out.println("---Fecha incorrecta---");
        int error7 = Checking.checkDate("33/11/2020");
        switch (error7) {
            case 0:
                System.out.println("Fecha correcta.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            case -2:
                System.out.println("Patrón de la fecha incorrecto.");
                break;

        }
        System.out.println();
        System.out.println("---Mes incorrecto---");
        int error8 = Checking.checkDate("13/13/2020");
        switch (error8) {
            case 0:
                System.out.println("Fecha correcta.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            case -2:
                System.out.println("Patrón de la fecha incorrecto.");
                break;

        }
        System.out.println();
        System.out.println("---Fecha bisiesta---");
        int error9 = Checking.checkDate("29/02/2020");
        switch (error9) {
            case 0:
                System.out.println("Fecha correcta.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            case -2:
                System.out.println("Patrón de la fecha incorrecto.");
                break;

        }
        System.out.println();
        System.out.println("---Fecha no bisiesta---");
        int error10 = Checking.checkDate("29/02/2021");
        switch (error10) {
            case 0:
                System.out.println("Fecha correcta.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            case -2:
                System.out.println("Patrón de la fecha incorrecto.");
                break;

        }
        System.out.println();
        System.out.println("---Fecha vacía---");
        int error11 = Checking.checkDate("");
        switch (error11) {
            case 0:
                System.out.println("Fecha correcta.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            case -2:
                System.out.println("Patrón de la fecha incorrecto.");
                break;

        }

        System.out.println("______________________");
        System.out.println();
        System.out.println("----Pruebas de Email----");
        System.out.println();
        System.out.println("---Email correcto---");
        int error12 = Checking.checkEmail("sandra1398DAW@gmail.com");
        switch (error12) {
            case 0:
                System.out.println("Email correcto.");
                break;
            case -1:
                System.out.println("Email incorrecto, introduce una correo válido.");
                break;

        }
        System.out.println();
        System.out.println("---Email vacío---");
        int error13 = Checking.checkEmail("");
        switch (error13) {
            case 0:
                System.out.println("Email correcto.");
                break;
            case -1:
                System.out.println("Email incorrecto, introduce una correo válido.");
                break;

        }
        System.out.println();
        System.out.println("---Email incorrecto---");
        int error14 = Checking.checkEmail("sandra1398DAWgmail.com");
        switch (error14) {
            case 0:
                System.out.println("Email correcto.");
                break;
            case -1:
                System.out.println("Email incorrecto, introduce una correo válido.");
                break;

        }
        System.out.println("______________________");
        System.out.println();
        System.out.println("----Pruebas de DiffDates----");
        System.out.println();
        System.out.println("---Diferencia con fechas correctas---");
        int error15 = Checking.diffDates("13/11/2020", "13/11/2021");
        switch (error15) {
            case -2:
                System.out.println("El patrón de alguna o ambas fechas incorrecto.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            default:
                System.out.println("Diferencia entre las dos fechas: " + error15);
        }

        System.out.println();
        System.out.println("---Diferencia con fechas incorrectas---");
        int error16 = Checking.diffDates("13/11/2020", "13/16/2021");
        switch (error16) {
            case -2:
                System.out.println("El patrón de alguna o ambas fechas incorrecto.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            default:
                System.out.println("Diferencia entre las dos fechas: " + error15);
        }

        System.out.println();
        System.out.println("---Diferencia con fechas vacías---");
        int error17 = Checking.diffDates("", "13/16/2021");
        switch (error17) {
            case -2:
                System.out.println("El patrón de alguna o ambas fechas incorrecto.");
                break;
            case -1:
                System.out.println("Fecha incorrecta, introduce una fecha válida.");
                break;
            default:
                System.out.println("Diferencia entre las dos fechas: " + error15);
        }

    }

}
