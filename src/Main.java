import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Libro {
    private String titulo;
    private String autor;
    private String ISBN;
    private String editorial;
    private int añoPublicacion;
    private double precio;

    public Libro(String titulo, String autor, String ISBN, String editorial, int añoPublicacion, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.editorial = editorial;
        this.añoPublicacion = añoPublicacion;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Libro: " + titulo + " - Autor: " + autor + " - ISBN: " + ISBN + " - Editorial: " + editorial +
                " - Año de Publicación: " + añoPublicacion + " - Precio: $" + precio;
    }
}

class Libreria {
    private HashMap<String, Libro> inventario;
    private HashMap<String, List<Libro>> librosPorAutor;

    public Libreria() {
        this.inventario = new HashMap<>();
        this.librosPorAutor = new HashMap<>();
    }

    public void agregarLibro(Libro libro) {
        if (!inventario.containsKey(libro.getTitulo())) {
            inventario.put(libro.getTitulo(), libro);
            librosPorAutor.computeIfAbsent(libro.getAutor(), k -> new ArrayList<>()).add(libro);
            System.out.println("Libro agregado al inventario.");
        } else {
            System.out.println("El libro ya está en el inventario.");
        }
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        return inventario.get(titulo);
    }

    public List<Libro> buscarLibrosPorAutor(String autor) {
        return librosPorAutor.getOrDefault(autor, new ArrayList<>());
    }

    public void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario:");
            for (Libro libro : inventario.values()) {
                System.out.println(libro);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Libreria biblioteca = new Libreria();
        Scanner scanner;
        String opcion;

        do {
            scanner = new Scanner(System.in);
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar Nuevo Libro");
            System.out.println("2. Buscar Libro por Título");
            System.out.println("3. Buscar Libros por Autor");
            System.out.println("4. Mostrar Inventario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese el título del libro (solo letras): ");
                    String titulo = scanner.nextLine();
                    if (!titulo.matches("[a-zA-Z ]+")) {
                        System.out.println("Error: El título solo puede contener letras y espacios.");
                        break;
                    }

                    System.out.print("Ingrese el autor del libro (solo letras): ");
                    String autor = scanner.nextLine();
                    if (!autor.matches("[a-zA-Z ]+")) {
                        System.out.println("Error: El autor solo puede contener letras y espacios.");
                        break;
                    }

                    System.out.print("Ingrese el ISBN del libro (solo números): ");
                    String ISBN = scanner.nextLine();
                    if (!ISBN.matches("\\d+")) {
                        System.out.println("Error: El ISBN solo puede contener números.");
                        break;
                    }

                    System.out.print("Ingrese la editorial del libro (solo letras): ");
                    String editorial = scanner.nextLine();
                    if (!editorial.matches("[a-zA-Z ]+")) {
                        System.out.println("Error: La editorial solo puede contener letras y espacios.");
                        break;
                    }

                    System.out.print("Ingrese el año de publicación del libro (solo números): ");
                    String añoPublicacionStr = scanner.nextLine();
                    if (!añoPublicacionStr.matches("\\d+")) {
                        System.out.println("Error: El año de publicación solo puede contener números.");
                        break;
                    }
                    int añoPublicacion = Integer.parseInt(añoPublicacionStr);

                    System.out.print("Ingrese el precio del libro (solo números): ");
                    String precioStr = scanner.nextLine();
                    if (!precioStr.matches("\\d+(\\.\\d+)?")) {
                        System.out.println("Error: El precio solo puede contener números y opcionalmente un punto decimal.");
                        break;
                    }
                    double precio = Double.parseDouble(precioStr);

                    biblioteca.agregarLibro(new Libro(titulo, autor, ISBN, editorial, añoPublicacion, precio));
                    break;
                case "2":
                    System.out.print("Ingrese el título del libro a buscar: ");
                    String tituloBuscar = scanner.nextLine();
                    Libro libroEncontrado = biblioteca.buscarLibroPorTitulo(tituloBuscar);
                    if (libroEncontrado != null) {
                        System.out.println("Libro encontrado:");
                        System.out.println(libroEncontrado);
                    } else {
                        System.out.println("No se encontró ningún libro con el título \"" + tituloBuscar + "\".");
                    }
                    break;
                case "3":
                    System.out.println("\nBuscar Libros por Autor:");
                    System.out.print("Autor: ");
                    String autorBuscar = scanner.nextLine();
                    List<Libro> librosAutor = biblioteca.buscarLibrosPorAutor(autorBuscar);
                    if (!librosAutor.isEmpty()) {
                        System.out.println("Libros encontrados del autor " + autorBuscar + ":");
                        for (Libro libro : librosAutor) {
                            System.out.println(libro);
                        }
                    } else {
                        System.out.println("No se encontraron libros del autor " + autorBuscar + ".");
                    }
                    break;
                case "4":
                    biblioteca.mostrarInventario();
                    break;
                case "5":
                    System.out.println("Saliendo... ¡Adiós!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (!opcion.equals("5"));

        scanner.close();
    }
}
