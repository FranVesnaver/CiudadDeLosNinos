CREATE TABLE Donante 
(
    dni_donante CHAR(10) PRIMARY KEY,
    direccion VARCHAR(100),
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    email VARCHAR(100),
    facebook VARCHAR(100),
    tel_fijo VARCHAR(20),
    tel_celular VARCHAR(20),
    fecha_nac DATE,
    ocupacion VARCHAR(50),
    cuil_cuit CHAR(13)
);

CREATE TABLE Contacto (
    dni_contacto CHAR(10) PRIMARY KEY,
    direccion VARCHAR(100),
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    email VARCHAR(100),
    facebook VARCHAR(100),
    tel_fijo VARCHAR(20),
    tel_celular VARCHAR(20),
    fecha_nac DATE,
	estado VARCHAR(100) 
    CONSTRAINT tipo_estado 
		CHECK(estado IN('Sin llamar','ERROR','En gestión','Adherido','Amigo','No acepta','Baja','Voluntario')),
    fecha_primer_cont DATE,
    fecha_alta DATE,
    fecha_baja DATE
);

CREATE TABLE Programa (
    id_programa SERIAL,
	PRIMARY KEY (id_programa),
    nombre VARCHAR(100),
    descripcion TEXT
);

CREATE TABLE MedioDePago (
    nro_pago SERIAL,
	PRIMARY KEY (nro_pago),
    nombre_titular VARCHAR(100)
);

CREATE TABLE Aporta (
    dni_donante CHAR(10),
    id_programa SERIAL,
    monto FLOAT,
    frecuencia VARCHAR(100),
	nro_pago INT,
	CONSTRAINT tipo_frecuencia
		CHECK (frecuencia IN('mensual', 'semestral')),
    PRIMARY KEY (dni_donante, id_programa),
    FOREIGN KEY (dni_donante) REFERENCES Donante(dni_donante)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_programa) REFERENCES Programa(id_programa)
        ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (nro_pago) REFERENCES MedioDePago(nro_pago)
);

CREATE TABLE Debito_Transferencia (
    nro_pago INT PRIMARY KEY,
    CBU CHAR(22),
    nro_cuenta VARCHAR(20),
    tipo_de_cuenta VARCHAR(20),
    nombre_titular VARCHAR(100),
    nombre_sucursal_banco VARCHAR(100),
    FOREIGN KEY (nro_pago) REFERENCES MedioDePago(nro_pago)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Credito (
    nro_pago INT PRIMARY KEY,
    fecha_vto DATE,
    nombre_tarjeta VARCHAR(50),
    nro_tarjeta CHAR(16),
    nombre_titular VARCHAR(100),
    FOREIGN KEY (nro_pago) REFERENCES MedioDePago(nro_pago)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE AuditoriaEliminacionDonante (
    id_auditoria SERIAL PRIMARY KEY,
    dni_donante VARCHAR(20),
    fecha_hora_eliminacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario VARCHAR(100)
);

-- función trigger
CREATE OR REPLACE FUNCTION auditoria_eliminar_donante()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO ciudadDeLosNinos.AuditoriaEliminacionDonante (id_auditoria, dni_donante, usuario)
    VALUES (
        (SELECT COALESCE(MAX(id_auditoria), 0) + 1 FROM ciudadDeLosNinos.AuditoriaEliminacionDonante),
        OLD.dni_donante::VARCHAR(20),
        CURRENT_USER
    );
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- trigger
CREATE TRIGGER trg_auditoria_donante
BEFORE DELETE ON ciudadDeLosNinos.Donante
FOR EACH ROW
EXECUTE FUNCTION auditoria_eliminar_donante();
