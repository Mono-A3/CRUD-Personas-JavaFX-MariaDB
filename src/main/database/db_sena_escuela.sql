CREATE DATABASE db_sena_personas;

USE db_sena_personas;

CREATE TABLE persona (
	id_persona INT NOT NULL AUTO_INCREMENT,
	cedula VARCHAR(10) COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE,
	nombre VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL,
	domicilio VARCHAR(200) COLLATE utf8mb4_unicode_ci NOT NULL,
	telefono VARCHAR(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
	correo_electronico VARCHAR(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL UNIQUE,
	fecha_nacimiento DATE DEFAULT NULL,
	genero VARCHAR(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
	PRIMARY KEY (id_persona)
) ENGINE = innoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT
	INTO
	db_sena_personas.persona (cedula,
	nombre,
	domicilio,
	telefono,
	correo_electronico,
	fecha_nacimiento,
	genero
)
VALUES (
	'123456',
	'Andres Araque',
	'Carrera 1',
	'1234567890',
	'aaraqueamaya397@gmail.com',
	NULL,
	'Masculino'
);

INSERT
	INTO
	db_sena_personas.persona (cedula,
	nombre,
	domicilio,
	telefono,
	correo_electronico,
	fecha_nacimiento,
	genero
)
VALUES (
	'56789',
	'Stiven Araque',
	'Carrera 2',
	'1234567890',
	'correo@gmail.com',
	NULL,
	'Masculino'
);

INSERT
	INTO
	db_sena_personas.persona (cedula,
	nombre,
	domicilio,
	telefono,
	correo_electronico,
	fecha_nacimiento,
	genero
)
VALUES (
	'prueba',
	'prueba',
	'carrera prueba',
	'1234567890',
	'prueba@gmail.com',
	NULL,
	'Masculino'
);

SELECT
	*
FROM
	db_sena_personas.persona;