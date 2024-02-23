import java.util.Scanner;

class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public void mostrarDato() {
        System.out.print(dato + " ");
    }
}

class Lista {
    Nodo primero;
    boolean ordenAscendente;

    public Lista() {
        primero = null;
        ordenAscendente = true;
    }

    public void setOrdenAscendente(boolean ascendente) {
        this.ordenAscendente = ascendente;
        if (!ascendente) {
            desordenarLista();
        }
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public void agregar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (ordenAscendente) {
            agregarOrdenado(nuevo);
        } else {
            agregarAlFinal(nuevo);
        }
    }

    private void agregarOrdenado(Nodo nuevo) {
        if (primero == null || primero.dato >= nuevo.dato) {
            nuevo.siguiente = primero;
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null && actual.siguiente.dato < nuevo.dato) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }

    private void agregarAlFinal(Nodo nuevo) {
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public void eliminar(int posicion) {
        if (posicion == 0) {
            primero = primero.siguiente;
            return;
        }

        Nodo anterior = null;
        Nodo actual = primero;
        int indice = 0;
        while (actual != null && indice < posicion) {
            anterior = actual;
            actual = actual.siguiente;
            indice++;
        }

        if (actual != null) {
            anterior.siguiente = actual.siguiente;
        }
    }

    public int obtenerTamaño() {
        int tamaño = 0;
        Nodo actual = primero;
        while (actual != null) {
            tamaño++;
            actual = actual.siguiente;
        }
        return tamaño;
    }

    public void mostrarElementos() {
        Nodo actual = primero;
        while (actual != null) {
            actual.mostrarDato();
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public void mostrarPosicion(int posicion) {
        Nodo actual = primero;
        int indice = 0;
        while (actual != null) {
            if (indice == posicion) {
                System.out.println("El elemento en la posición " + posicion + " es: " + actual.dato);
                return;
            }
            actual = actual.siguiente;
            indice++;
        }
        System.out.println("No se encontró ningún elemento en la posición " + posicion);
    }

    public void mostrarNodosSuperiores(int valor) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.dato > valor) {
                actual.mostrarDato();
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }

    private void desordenarLista() {
        int tamaño = obtenerTamaño();
        int[] elementos = new int[tamaño];
        Nodo actual = primero;
        for (int i = 0; i < tamaño; i++) {
            elementos[i] = actual.dato;
            actual = actual.siguiente;
        }
        primero = null;
        for (int i = 0; i < tamaño; i++) {
            agregar(elementos[i]);
        }
    }
}

public class Principal {
    public static void main(String[] args) {
        Lista lista = new Lista();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Crear una lista enlazada de números enteros");
            System.out.println("2. Consultar si la lista será ordenada");
            System.out.println("3. Agregar nodos(elementos) a la lista");
            System.out.println("4. Indicar si la lista está vacía");
            System.out.println("5. Eliminar nodo de la lista por posición");
            System.out.println("6. Devolver el número de nodos de la Lista Enlazada");
            System.out.println("7. Mostrar todos los elementos de la lista");
            System.out.println("8. Mostrar por pantalla la posición de un nodo especificado por teclado");
            System.out.println("9. Mostrar todos los nodos que superen un valor determinado, solicitado por teclado");
            System.out.println("10. Cambiar orden de la lista");
            System.out.println("11. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Lista creada.");
                    break;
                case 2:
                    System.out.println("¿Desea que la lista esté ordenada? (1: Sí, 0: No)");
                    int opcionOrden = scanner.nextInt();
                    if (opcionOrden == 1) {
                        System.out.println("¿Orden ascendente o descendente? (1: Ascendente, 0: Descendente)");
                        int orden = scanner.nextInt();
                        lista.setOrdenAscendente(orden == 1);
                    }
                    break;
                case 3:
                    System.out.print("Ingrese un número entero para agregar a la lista: ");
                    int num = scanner.nextInt();
                    lista.agregar(num);
                    break;
                case 4:
                    if (lista.estaVacia()) {
                        System.out.println("La lista está vacía.");
                    } else {
                        System.out.println("La lista no está vacía.");
                    }
                    break;
                case 5:
                    if (lista.estaVacia()) {
                        System.out.println("La lista está vacía, no se puede eliminar ningún nodo.");
                    } else {
                        System.out.print("Ingrese la posición del nodo que desea eliminar: ");
                        int posEliminar = scanner.nextInt();
                        lista.eliminar(posEliminar);
                    }
                    break;
                case 6:
                    System.out.println("El número de nodos de la lista es: " + lista.obtenerTamaño());
                    break;
                case 7:
                    System.out.println("Elementos de la lista:");
                    lista.mostrarElementos();
                    break;
                case 8:
                    System.out.print("Ingrese la posición del nodo que desea mostrar: ");
                    int posicion = scanner.nextInt();
                    lista.mostrarPosicion(posicion);
                    break;
                case 9:
                    System.out.print("Ingrese el valor que deben superar los nodos a mostrar: ");
                    int valor = scanner.nextInt();
                    System.out.println("Nodos que superan el valor " + valor + ":");
                    lista.mostrarNodosSuperiores(valor);
                    break;
                case 10:
                    lista.setOrdenAscendente(!lista.ordenAscendente);
                    System.out.println("Orden de la lista cambiado.");
                    break;
                case 11:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 11);

        scanner.close();
    }
}
