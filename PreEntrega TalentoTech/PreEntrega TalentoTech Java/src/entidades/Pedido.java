package entidades;

import java.util.List;

public class Pedido {
    private static int contadorId = 1;

    private int id;
    private List<LineaPedido> lineas;

    public Pedido(List<LineaPedido> lineas) {
        this.id = contadorId++;
        this.lineas = lineas;
    }

    public int getId() {
        return id;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.calcularSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido ID: " + id + "\n");
        for (LineaPedido linea : lineas) {
            sb.append(" - ").append(linea).append("\n");
        }
        sb.append(String.format("Total: $%.2f", calcularTotal()));
        return sb.toString();
    }
}
