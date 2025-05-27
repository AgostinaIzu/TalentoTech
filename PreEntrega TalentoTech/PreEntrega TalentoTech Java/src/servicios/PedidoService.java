package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.LineaPedido;
import entidades.Pedido;
import entidades.Producto;
import excepciones.StockInsuficienteException;

 
public class PedidoService {
    private List<Pedido> pedidos = new ArrayList<>();
    private ProductoService productoService;

    public PedidoService(ProductoService servicioProducto) {
        this.productoService = servicioProducto;
    }

    public void crearPedido(Scanner scanner) {
        List<LineaPedido> lineas = new ArrayList<>();

        while (true) {
            productoService.listarProductos();
            System.out.print("Ingrese ID del producto (o 0 para finalizar): ");
            int id = Integer.parseInt(scanner.nextLine());
            if (id == 0) break;

            Producto producto = productoService.buscarProductoPorId(id);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            try {
                if (producto.getStock() < cantidad) {
                    throw new StockInsuficienteException("Stock insuficiente para el producto " + producto.getNombre());
                }
                producto.setStock(producto.getStock() - cantidad);
                lineas.add(new LineaPedido(producto, cantidad));
                System.out.println("Producto agregado al pedido.");
            } catch (StockInsuficienteException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!lineas.isEmpty()) {
            Pedido pedido = new Pedido(lineas);
            pedidos.add(pedido);
            System.out.println("Pedido creado. Total: $" + pedido.calcularTotal());
        }
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        System.out.println("\n--- Lista de Pedidos ---");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }
}
