package amparo.refactor.bien;

public class document {
    public static void main(String[] args) {

        printBanner();

        printResultado("11111111H");
        printResultado("24324356A");
        printResultado("X0932707B");
        printResultado("Z2691139Z");



    }

    static void printBanner(){
        System.out.println("=====================");
        System.out.println("Vamos a refactorizar!");
        System.out.println("=====================");

    }

    static void printResultado(String documento){

        // faltaria implemetar un printer por tipo
        IValidadorDocumento miValidador = ValidadorFactory.getValidador(documento);

        if(miValidador != null && miValidador.validar() == true) {

            System.out.println("El DNI "+documento+" es: true");
        } else {
            System.out.println("El DNI " +documento +" es: false");
        }
    }
}
