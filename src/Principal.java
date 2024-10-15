import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar el menú
            opcion = menu();

            if (opcion != 7) {  // Si no es la opción de salir
                try {
                    // Llamar al método convertir según la opción seleccionada
                    String resultadoConversion = procesarConversion(opcion);
                    System.out.println(resultadoConversion);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Finalizando la aplicación.");
            }

        } while (opcion != 7);

        lectura.close();  // Cerrar el scanner
    }

    public static int menu() {
        Scanner lectura = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("************************************************************");
            System.out.println("Sean bienvenidos al conversor de monedas");
            System.out.println("1) Dólar => Peso Argentino");
            System.out.println("2) Peso Argentino => Dólar");
            System.out.println("3) Dólar => Real Brasileño");
            System.out.println("4) Real Brasileño => Dólar");
            System.out.println("5) Dólar => Peso Colombiano");
            System.out.println("6) Peso Colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("************************************************************");

            if (lectura.hasNextInt()) {  // Verificar si la entrada es un número entero
                opcion = lectura.nextInt();
                if (opcion < 1 || opcion > 7) {
                    System.out.println("Por favor, elija una opción válida entre 1 y 7.");
                }
            } else {
                System.out.println("Entrada inválida. Debe ser un número.");
                lectura.next();  // Limpiar la entrada
            }
        } while (opcion < 1 || opcion > 7);

        return opcion;
    }

    public static String procesarConversion(int op) throws IOException {
        Scanner lectura = new Scanner(System.in);
        ConsultarMoneda consulta = new ConsultarMoneda();
        GeneradorDeInfo valorMoneda = new GeneradorDeInfo();

        String aliasMoneda = obtenerAlias(op);
        if (aliasMoneda.isEmpty()) {
            return "Opción no válida.";
        }

        System.out.println("Ingrese el valor a convertir:");
        double cantidad = Double.parseDouble(lectura.nextLine());

        Monedas moneda = consulta.consultarMoneda(aliasMoneda);
        double tasaConversion = valorMoneda.tomaValores(moneda, aliasMoneda);

        // Determinar si se debe multiplicar o dividir según la opción
        boolean esConversionDirecta = (op % 2 == 1);
        double resultado = esConversionDirecta ? cantidad * tasaConversion : cantidad / tasaConversion;

        String monedaOriginal = esConversionDirecta ? "USD" : aliasMoneda;
        String monedaDestino = esConversionDirecta ? aliasMoneda : "USD";

        return "El valor de " + cantidad + " [" + monedaOriginal + "] es " + resultado + " [" + monedaDestino + "]";
    }

    // Método auxiliar para obtener el alias de la moneda según la opción seleccionada
    public static String obtenerAlias(int opcion) {
        switch (opcion) {
            case 1:
            case 2:
                return "ARS";  // Peso Argentino
            case 3:
            case 4:
                return "BRL";  // Real Brasileño
            case 5:
            case 6:
                return "COP";  // Peso Colombiano
            default:
                return "";  // Opción inválida
        }
    }
}
