package Modelos;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String isbn;
    private String titulo;
    private String genero;
    private int cantidad;
    private Autor autor;
    private String portada; // Ruta o bytes de la imagen
    private int anioPublicacion;
    private String editorial;
    private String idioma;
    private double ratingPromedio;
    private List<Resena> resenas;

    public Libro(String isbn, String titulo, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.resenas = new ArrayList<>();
        this.cantidad = 1; 
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public double getRatingPromedio() {
        return ratingPromedio;
    }

    public void setRatingPromedio(double ratingPromedio) {
        this.ratingPromedio = ratingPromedio;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void agregarResena(Resena resena) {
        this.resenas.add(resena);
        actualizarRatingPromedio();
    }

    public void eliminarResena(Resena resena) {
        this.resenas.remove(resena);
        actualizarRatingPromedio();
    }

    private void actualizarRatingPromedio() {
        if (resenas.isEmpty()) {
            ratingPromedio = 0;
            return;
        }
        double suma = 0;
        for (Resena resena : resenas) {
            suma += resena.getRating();
        }
        ratingPromedio = suma / resenas.size();
    }
}