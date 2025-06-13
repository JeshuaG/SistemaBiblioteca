CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    salt VARCHAR(64) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    foto_perfil VARCHAR(255),
    fecha_registro DATE NOT NULL,
    recordar_sesion BOOLEAN DEFAULT FALSE
);

CREATE TABLE autores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    biografia TEXT,
    pais_origen VARCHAR(100),
    foto VARCHAR(255)
);

CREATE TABLE libros (
    isbn VARCHAR(20) PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(100),
    cantidad INT DEFAULT 1,
    autor_id INT,
    portada VARCHAR(255),
    anio_publicacion INT,
    editorial VARCHAR(100),
    idioma VARCHAR(50),
    rating_promedio DOUBLE DEFAULT 0,
    FOREIGN KEY (autor_id) REFERENCES autores(id)
);

CREATE TABLE prestamos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libro_isbn VARCHAR(20),
    usuario_id INT,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    fecha_devolucion_prevista DATE NOT NULL,
    devuelto BOOLEAN DEFAULT FALSE,
    multa DOUBLE DEFAULT 0,
    FOREIGN KEY (libro_isbn) REFERENCES libros(isbn),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE resenas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libro_isbn VARCHAR(20),
    usuario_id INT,
    texto TEXT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    fecha DATE NOT NULL,
    FOREIGN KEY (libro_isbn) REFERENCES libros(isbn),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

INSERT INTO usuarios (nombre, email, password, salt, rol, foto_perfil, fecha_registro, recordar_sesion)
VALUES 
('Admin', 'admin@biblioteca.com', 'WUpNn0lAL+AR/WiWTkXNqLk+NJfHvBinGWzBAOcg4d8=', 'K4CJ3E437atpMM6lDGivpw==', 'Administrador', NULL, '2024-01-01', FALSE),
('Biblio', 'biblio@biblioteca.com', '8/m/SrNuoJdAGMk3Ziu8oX7+4pbwT7OKG1LX6V/PzG8=', 'hhPQuXYDGdQUK/pEoMGVGQ==', 'Bibliotecario', NULL, '2024-02-01', FALSE),
('Miembro', 'miembro@biblioteca.com', '30o2IN/B/FN5ZVKKqh3unjWNvC8Pt98PQF7Z8+DBbwU=', 'MZj+fiZIJqoIDhrRZyd8vw==', 'Miembro', NULL, '2024-03-01', TRUE);

INSERT INTO autores (nombre, biografia, pais_origen, foto)
VALUES 
('Gabriel García Márquez', 'Escritor colombiano, autor de Cien Años de Soledad.', 'Colombia', NULL),
('Isabel Allende', 'Escritora chilena, autora de La Casa de los Espíritus.', 'Chile', NULL);

INSERT INTO libros (isbn, titulo, genero, cantidad, autor_id, portada, anio_publicacion, editorial, idioma, rating_promedio)
VALUES
('9788497592208', 'Cien Años de Soledad', 'Realismo Mágico', 3, 1, NULL, 1967, 'Sudamericana', 'Español', 4.5),
('9788408105824', 'La Casa de los Espíritus', 'Ficción', 2, 2, NULL, 1982, 'Plaza & Janés', 'Español', 4.2);

INSERT INTO prestamos (libro_isbn, usuario_id, fecha_prestamo, fecha_devolucion_prevista, devuelto, multa)
VALUES
('9788497592208', 3, '2025-06-01', '2025-06-15', FALSE, 0);

INSERT INTO resenas (libro_isbn, usuario_id, texto, rating, fecha)
VALUES
('9788497592208', 3, 'Una obra maestra del realismo mágico.', 5, '2025-06-05');

