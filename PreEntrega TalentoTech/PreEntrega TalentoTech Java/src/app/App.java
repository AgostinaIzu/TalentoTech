package app;

import java.util.Scanner;

import servicios.PedidoService;
import servicios.ProductoService;

public class App {
    private static ProductoService productoService = new ProductoService();
    private static PedidoService pedidoService = new PedidoService(productoService);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean salir = false;

        while (!salir) {
            System.out.println(
                    "\n=================================== SISTEMA DE GESTIÓN - BAZAR LICHIANDROS ==================================\n");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.print("\nElija una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> productoService.agregarProducto(scanner);
                case "2" -> productoService.listarProductos();
                case "3" -> productoService.actualizarProducto(scanner);
                case "4" -> productoService.eliminarProducto(scanner);
                case "5" -> pedidoService.crearPedido(scanner);
                case "6" -> pedidoService.listarPedidos();
                case "7" -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
