
-- ==========================
-- TIPOS
-- ==========================
INSERT INTO tipo (nombre) VALUES 
  ('Música'), 
  ('Libro'), 
  ('Película'), 
  ('Revista'),
  ('Videojuego');

-- ==========================
-- FORMATOS
-- ==========================
INSERT INTO formato (id, nombre) VALUES 
  (1, 'CD'),
  (2, 'Vinilo'),
  (3, 'Digital'),
  (4, 'Papel'),
  (5, 'PDF'),
  (6, 'Blu-ray'),
  (7, 'DVD'),
  (8, 'Físico'),
  (9, 'eBook'),
  (10, 'Cartucho');

-- ==========================
-- tipo_formato RELACIONES
-- ==========================
INSERT INTO tipo_formato (tipo_id, formato_id) VALUES 
  (1,1), (1,2), (1,3),
  (2,4), (2,5), (2,9),
  (3,6), (3,7), (3,3),
  (4,4), (4,5),
  (5,10), (5,3);

-- ==========================
-- ITEMS
-- ==========================
INSERT INTO item (titulo, ubicacion, fecha_adquisicion, estado, tipo_id, formato_id, prestamo_id)
VALUES 
('Thriller - Michael Jackson', 'Estante A1', '2023-05-14', TRUE, 1, 1, NULL),
('Abbey Road - The Beatles', 'Estante A2', '2022-11-11', TRUE, 1, 2, NULL),
('Spotify Compilation Vol.1', 'Digital Shelf', '2024-08-02', TRUE, 1, 3, NULL),
('Harry Potter y la piedra filosofal', 'Estante B1', '2020-01-10', TRUE, 2, 4, NULL),
('Sapiens', 'Estante B2', '2021-03-18', TRUE, 2, 5, NULL),
('El nombre del viento', 'Estante B3', '2023-07-07', TRUE, 2, 9, NULL),
('Interstellar', 'Estante C1', '2023-09-01', TRUE, 3, 6, NULL),
('Pulp Fiction', 'Estante C2', '2022-12-12', TRUE, 3, 7, NULL),
('The Irishman', 'Estante C3', '2024-03-25', TRUE, 3, 3, NULL),
('National Geographic - Enero 2023', 'Estante D1', '2023-01-01', TRUE, 4, 4, NULL),
('Time Magazine - Marzo 2023', 'Estante D2', '2023-03-01', TRUE, 4, 5, NULL),
('The Legend of Zelda: Breath of the Wild', 'Estante E1', '2019-06-15', TRUE, 5, 10, NULL),
('Minecraft', 'Estante E3', '2021-09-21', TRUE, 5, 3, NULL);

-- ==========================
-- PRESTAMOS
-- ==========================
INSERT INTO prestamo (persona, fecha_prestamo, fecha_prevista_devolucion, fecha_devolucion, activo)
VALUES 
('Sofía Reyes', '2025-03-10', '2025-04-10', NULL, TRUE),
('Diego Luna', '2025-01-15', '2025-03-01', '2025-02-28', FALSE),
('Ana Torres', '2025-02-05', '2025-04-05', NULL, TRUE),
('Matías Rojas', '2024-12-20', '2025-02-20', '2025-01-15', FALSE),
('Camila Fernández', '2025-03-25', '2025-05-25', NULL, TRUE),
('Federico González', '2025-01-10', '2025-03-10', '2025-03-05', FALSE);

-- ==========================
-- ASOCIACIÓN ítems <-> préstamos
-- ==========================
UPDATE item SET prestamo_id = 1 WHERE titulo = 'Thriller - Michael Jackson';
UPDATE item SET prestamo_id = 1 WHERE titulo = 'Sapiens';
UPDATE item SET prestamo_id = 2 WHERE titulo = 'Pulp Fiction';
UPDATE item SET prestamo_id = 3 WHERE titulo = 'Minecraft';
UPDATE item SET prestamo_id = 4 WHERE titulo = 'Time Magazine - Marzo 2023';
UPDATE item SET prestamo_id = 5 WHERE titulo = 'El nombre del viento';
