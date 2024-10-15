import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultarMoneda consulta = new ConsultarMoneda();  // Asumo que tienes esta clase
        int opcion;

        do {
            // Llamar al menú
            opcion = menu();

            // Si no se selecciona la opción de salir (7), se procesa la conversión
            if (opcion != 7) {
                try {
                    // Llama al método convertir según la opción seleccionada
                    String resultadoConversion = convertir(opcion);
                    System.out.println(resultadoConversion);
                } catch (NumberFormatException e) {
                    System.out.println("Número no encontrado: " + e.getMessage());
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Finalizando la aplicación.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Finalizando la aplicación.");
            }

        } while (opcion != 7);  // Repetir el menú hasta que la opción sea 7 (Salir)

        lectura.close();  // Asegúrate de cerrar el scanner
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
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso Colombiano");
            System.out.println("6) Peso Colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija la opción válida:");
            System.out.println("************************************************************");

            if (lectura.hasNextInt()) {  // Verifica si la entrada es un número entero
                opcion = lectura.nextInt();
                if (opcion < 1 || opcion > 7) {
                    System.out.println("Por favor, elija una opción válida entre 1 y 7.");
                }
            } else {
                System.out.println("Entrada inválida. Debe ser un número.");
                lectura.next();
            }
        } while (opcion < 1 || opcion > 7);

        return opcion;
    }

    public static String convertir(int op) throws IOException {
        ConsultarMoneda consulta = new ConsultarMoneda();
        GeneradorDeInfo valorMoneda = new GeneradorDeInfo();
        Scanner lectura = new Scanner(System.in);
        String aliasMoneda = "";
        double resultado = 0;
        String respuesta = "";
        System.out.println("Ingrese el valor a convertir:");
        double cantidad = Double.parseDouble(lectura.nextLine());

        switch (op) {
            case 1:
                aliasMoneda = "ARS";
                Monedas moneda1 = consulta.consultarMoneda(aliasMoneda);
                double resl1 = valorMoneda.tomaValores(moneda1, aliasMoneda);
                resultado = cantidad * resl1;
                respuesta = "El valor de " + cantidad + " [USD] es " + resultado + " [" + aliasMoneda + "]";
                break;
            case 2:
                aliasMoneda = "ARS";
                Monedas moneda2 = consulta.consultarMoneda(aliasMoneda);
                double resl2 = valorMoneda.tomaValores(moneda2, aliasMoneda);
                resultado = cantidad / resl2;
                respuesta = "El valor de " + cantidad + " [ARS] es " + resultado + " [" + aliasMoneda + "]";
                break;
            case 3:
                aliasMoneda = "BRL";
                Monedas moneda3 = consulta.consultarMoneda(aliasMoneda);
                double resl3 = valorMoneda.tomaValores(moneda3, aliasMoneda);
                resultado = cantidad * resl3;
                respuesta = "El valor de " + cantidad + " [USD] es " + resultado + " [" + aliasMoneda + "]";
                break;
            case 4:
                aliasMoneda = "BRL";
                Monedas moneda4 = consulta.consultarMoneda(aliasMoneda);
                double resl4 = valorMoneda.tomaValores(moneda4, aliasMoneda);
                resultado = cantidad / resl4;
                respuesta = "El valor de " + cantidad + " [BRL] es " + resultado + " [" + aliasMoneda + "]";
                break;
            case 5:
                aliasMoneda = "COP";
                Monedas moneda5 = consulta.consultarMoneda(aliasMoneda);
                double resl5 = valorMoneda.tomaValores(moneda5, aliasMoneda);
                resultado = cantidad * resl5;
                respuesta = "El valor de " + cantidad + " [USD] es " + resultado + " [" + aliasMoneda + "]";
                break;
            case 6:
                aliasMoneda = "COP";
                Monedas moneda6 = consulta.consultarMoneda(aliasMoneda);
                double resl6 = valorMoneda.tomaValores(moneda6, aliasMoneda);
                resultado = cantidad / resl6;
                respuesta = "El valor de " + cantidad + " [COP] es " + resultado + " [" + aliasMoneda + "]";
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }

        return respuesta;
    }
}
