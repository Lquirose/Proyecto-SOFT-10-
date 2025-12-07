import java.util.*;

public class Grafo {
    private static final Map<String, List<Arista>> adj;

    static {
        adj = new HashMap<>();
    }


    public void agregarVertice(String v) {
        if (v == null || v.trim().isEmpty()) return;
        adj.putIfAbsent(v.trim(), new ArrayList<>());
    }


    public void agregarArista(String a, String b, int peso) {
        agregarVertice(a);
        agregarVertice(b);
        adj.get(a).add(new Arista(b, peso));
        adj.get(b).add(new Arista(a, peso)); // grafo no dirigido
    }

    // Métodos de existencia — compatibles con diferentes nombres usados en el proyecto
    public static boolean existeVertice(String v) {
        return adj.containsKey(v);
    }


    // Listado de vértices (útil para mostrar en menú)
    public Set<String> obtenerVertices() {
        return Collections.unmodifiableSet(adj.keySet());
    }

    // Obtener adyacencias (copia) para mostrar/depurar
    public List<String> listarAdyacencias(String v) {
        List<String> res = new ArrayList<>();
        List<Arista> lista = adj.get(v);
        if (lista == null) return res;
        for (Arista a : lista) {
            res.add(a.destino + " (" + a.peso + ")");
        }
        return res;
    }

    // Dijkstra: devuelve ruta y distancia; si no existe ruta, ruta = null y distancia = Integer.MAX_VALUE
    public static ResultadoDijkstra dijkstra(String inicio, String destino) {
        if (!adj.containsKey(inicio) || !adj.containsKey(destino)) {
            return new ResultadoDijkstra(null, Integer.MAX_VALUE);
        }

        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Par> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.dist));

        for (String v : adj.keySet()) dist.put(v, Integer.MAX_VALUE);
        dist.put(inicio, 0);
        pq.add(new Par(inicio, 0));

        while (!pq.isEmpty()) {
            Par cur = pq.poll();
            if (cur.dist > dist.get(cur.vertice)) continue;
            if (cur.vertice.equals(destino)) break;

            List<Arista> vecinos = adj.get(cur.vertice);
            if (vecinos == null) continue;

            for (Arista ar : vecinos) {
                int nd = dist.get(cur.vertice) + ar.peso;
                if (nd < dist.get(ar.destino)) {
                    dist.put(ar.destino, nd);
                    prev.put(ar.destino, cur.vertice);
                    pq.add(new Par(ar.destino, nd));
                }
            }
        }

        if (dist.get(destino) == Integer.MAX_VALUE) {
            return new ResultadoDijkstra(null, Integer.MAX_VALUE);
        }

        LinkedList<String> ruta = new LinkedList<>();
        String u = destino;
        while (u != null) {
            ruta.addFirst(u);
            u = prev.get(u);
        }

        return new ResultadoDijkstra(ruta, dist.get(destino));
    }

    public static boolean estaConectado(String a, String b) {
        ResultadoDijkstra r = dijkstra(a, b);
        return r.ruta != null;
    }

    // Clases auxiliares
    private static class Arista {
        final String destino;
        final int peso;
        Arista(String destino, int peso) { this.destino = destino; this.peso = peso; }
    }

    private static class Par {
        final String vertice;
        final int dist;
        Par(String v, int d) { vertice = v; dist = d; }
    }

    public static class ResultadoDijkstra {
        public final List<String> ruta;
        public final int distancia;
        public ResultadoDijkstra(List<String> ruta, int distancia) {
            this.ruta = ruta;
            this.distancia = distancia;
        }
    }

    public void mostrarGrafo() {
        System.out.println("\n===== GRAFO DE UBICACIONES =====");

        for (String v : obtenerVertices()) {
            System.out.print(v + " -> ");
            List<String> ady = listarAdyacencias(v);

            if (ady.isEmpty()) {
                System.out.println("Sin conexiones");
            } else {
                System.out.println(String.join(", ", ady));
            }
        }
    }

}
