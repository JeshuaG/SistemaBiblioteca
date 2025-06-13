package Modelos;

import java.util.ArrayList;
import java.util.List;

public class Autor {
    private int id;
    private String nombre;
    private String biografia;
    private String paisOrigen;
    private String foto; 
    private List<Libro> librosEscritos;

    public Autor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.librosEscritos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Libro> getLibrosEscritos() {
        return librosEscritos;
    }

    public void agregarLibro(Libro libro) {
        this.librosEscritos.add(libro);
    }

    public void eliminarLibro(Libro libro) {
        this.librosEscritos.remove(libro);
    }
}