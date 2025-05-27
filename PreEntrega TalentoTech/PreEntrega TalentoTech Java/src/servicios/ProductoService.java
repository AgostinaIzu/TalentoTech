package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Producto;

public class ProductoService {
    
  private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Scanner scanner) {
        try {
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());
            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());
            productos.add(new Producto(nombre, precio, stock));

            System.out.println("Producto agregado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese números válidos para precio y stock.");
        }
    }

    public void listarProductos() {
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\n--- Lista de Productos ---");
        
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public void actualizarProducto(Scanner scanner) {
        listarProductos();
        System.out.print("Ingrese ID del producto a actualizar: ");
        
        int id = Integer.parseInt(scanner.nextLine());
        Producto producto = buscarProductoPorId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Nuevo precio: ");
        double nuevoPrecio = Double.parseDouble(scanner.nextLine());
        System.out.print("Nuevo stock: ");
        int nuevoStock = Integer.parseInt(scanner.nextLine());
        
        producto.setPrecio(nuevoPrecio);
        producto.setStock(nuevoStock);
        
        System.out.println("Producto actualizado exitosamente.");
    }

    public void eliminarProducto(Scanner scanner) {
        listarProductos();
        try {
            System.out.print("Ingrese ID del producto a eliminar: ");
            
            int id = Integer.parseInt(scanner.nextLine());
            Producto producto = buscarProductoPorId(id);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                return;
            }

            productos.remove(producto);
            System.out.println("Producto eliminado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // public Producto buscarProductoPorNombre(String nombre) {
    //     for (Producto p : productos) {
    //         if (p.getNombre().equalsIgnoreCase(nombre)) return p;
    //     }
    //     return null;
    // }

    public List<Producto> getProductos() {
        return productos;
    }
}
