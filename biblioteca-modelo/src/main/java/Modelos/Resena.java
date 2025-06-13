package Modelos;

import java.time.LocalDate;

public class Resena {
    private int id;
    private Libro libro;
    private Usuario usuario;
    private String texto;
    private int rating; 
    private LocalDate fecha;

    public Resena(int id, Libro libro, Usuario usuario, String texto, int rating) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.texto = texto;
        this.rating = rating;
        this.fecha = LocalDate.now();
        
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("El rating debe estar entre 1 y 5");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("El rating debe estar entre 1 y 5");
        }
        this.rating = rating;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}