-- Insertar programas
INSERT INTO Programa (id_programa, nombre, descripcion) VALUES
	(1,'Programa Escolar', 'Apoyo escolar'),
	(2,'Taller de Oficios', 'Capacitación práctica'),
	(3,'Vida en Familia', 'Hogar para niños');

-- Donantes
INSERT INTO Donante (dni_donante, direccion, nombre, apellido, email, facebook, tel_fijo, tel_celular, fecha_nac, ocupacion, cuil_cuit) VALUES
	('12345678', 'Calle 1', 'Juan', 'Pérez', 'juanp@gmail.com', 'juanperez', '03584444444', '3584111222', '1985-03-15', 'Contador', '20-12345678-9'),
	('87654321', 'Av. Libertador', 'María', 'Gómez', 'mariag@gmail.com', 'marigomez', '03586666666', '3584384938', '1990-07-22', 'Ingeniera', '27-87654321-5'),
	('11111111', 'Av. Libertad', 'Marta', 'Sanchez', 'martas@gmail.com', 'marsanchez', '035877777777', '3584385744', '1991-08-23', 'Abogacía', '27-11111111-5'),
	('22222222', 'Av. Pacifico', 'Marcos', 'Acuña', 'macuna@gmail.com', 'macuna', '03587483747', '3584384752', '1991-02-12', 'Psicología', '20-22222222-0'),
	('33333333', 'Rivadavia', 'Sebastian', 'Driussi', 'sdriussi@gmail.com', 'sdriussi', '03580009583', '3584493857', '1996-07-22', 'Ingeniera', '27-33333333-5'),
	('45838750', 'Urquiza', 'Franco', 'Vesnaver', 'vesnaverf@gmail.com', 'fvesnaver', '03589483950', '3584109576', '2004-08-18', 'Computación', '20-45838750-9'),
	('45938698', 'Eliana Alcaraz', 'Luciano', 'Gonzalez', 'luchog@gmail.com', 'lucianog', '03581087906', '3584478987', '2004-10-24', 'Computación', '20-45938698-0'),
	('45938662', 'Juan Filloy', 'Valentino', 'Vilar', 'vilarv@gmail.com', 'vilarv', '03583849502', '35843887477', '2004-10-08', 'Computación', '20-45938662-9'),
	('46104873', '9 de Julio', 'Francisco', 'Barosco', 'baroscof@gmail.com', 'baroscof', '03588392847', '3584488968', '2004-12-01', 'Computación', '20-46104873-0'),
	('46110472', 'Buenos Aires', 'Agustín', 'Laner', 'alaner@gmail.com', 'alaner', '03587364626', '3584999888', '2004-12-28', 'Computación', '20-46110472-9');

-- Contactos
INSERT INTO Contacto (dni_contacto, direccion, nombre, apellido, email, facebook, tel_fijo, tel_celular, fecha_nac, estado, fecha_primer_cont, fecha_alta, fecha_baja) VALUES
	('11223344', 'Alvear', 'Luis', 'Martínez', 'luis@gmail.com', 'luismi', '0358777777', '3584223344', '1995-12-05', 'En gestión', '2025-03-25', '2025-04-01', NULL),
	('22334455', 'San Martín', 'Ana', 'Rivas', 'ana@gmail.com', 'anarias', '0358555555', '3584337444', '1988-11-11', 'Amigo', '2025-03-10', '2025-03-20', '2025-04-01'),
	('32893098', 'San Marcos', 'Mario', 'Garriga', 'magarriga@gmail.com', 'magarriga', '03584890387', '3584094829', '1988-05-13', 'Voluntario', '2025-03-15', '2025-03-30', '2025-04-01');

-- Medios de pago
INSERT INTO MedioDePago (nro_pago, nombre_titular) VALUES 
	(1,'Juan Pérez'), 
	(2,'María Gómez'),
	(3,'Marta Sanchez'),
	(4,'Marcos Acuña'),
	(5,'Sebastian Driussi'),
	(6,'Franco Vesnaver'),
	(7,'Luciano Gonzalez'),
	(8,'Valentino Vilar'),
	(9,'Francisco Barosco'),
	(10,'Agustin Laner'),
	(11,'Valentino Vilar'),
	(12,'Valentino Vilar'), 
	(13,'Luciano Gonzalez'),
	(14,'Luciano Gonzalez');
	
-- Credito
INSERT INTO Credito VALUES 
	(1, '2030-12-18', 'Visa', '4111111111111111', 'Juan Pérez'),
	(3, '2028-12-09', 'Mastercard', '3425786790876333', 'Marta Sanchez'),
	(4, '2029-09-13', 'Visa', '4739516632098573', 'Marcos Acuña'),
	(6, '2031-08-01', 'Visa', '3758297477777895', 'Franco Vesnaver'),
	(9, '2027-06-30', 'Mastercard', '6493250509647364', 'Francisco Barosco');

-- Débito/Transferencia
INSERT INTO Debito_Transferencia VALUES 
	(2, '0720500123456789012345', '12345678', 'Caja de Ahorro', 'María Gómez', 'Banco Nación'),
	(5, '8492385372958732857664', '98765432', 'Cuenta Corriente', 'Sebastian Driussi', 'Banco Patagonia'),
	(7, '3903297529327583274874', '83940193', 'Caja de Ahorro', 'Luciano Gonzalez', 'Banco Patagonia'),
	(8, '4930296867498394987438', '88888381', 'Caja de Ahorro', 'Valentino Vilar', 'Banco Nación'),
	(10, '4829348327483275875778', '91291291', 'Cuenta Corriente', 'Agustin Laner', 'Banco Patagonia'),
	(11, '4930296867498394987438', '88888381', 'Caja de Ahorro', 'Valentino Vilar', 'Banco Nación'),
	(12, '4930296867498394987438', '88888381', 'Caja de Ahorro', 'Valentino Vilar', 'Banco Nación'),
	(13, '3903297529327583274874', '83940193', 'Caja de Ahorro', 'Luciano Gonzalez', 'Banco Patagonia'),
	(14, '3903297529327583274874', '83940193', 'Caja de Ahorro', 'Luciano Gonzalez', 'Banco Patagonia');

-- Aportes
INSERT INTO Aporta (dni_donante, id_programa, monto, frecuencia, nro_pago) VALUES
	('12345678', 1, 3000.00, 'mensual', 1),
	('87654321', 2, 2000.00, 'mensual', 2),
	('11111111', 3, 6500.00, 'semestral', 3),
	('22222222', 3, 2500.00, 'semestral', 4),
	('33333333', 2, 8000.00, 'mensual', 5),
	('45838750', 3, 2300.00, 'semestral', 6),
	('45938662', 3, 2500.00, 'mensual', 8),
	('45938698', 1, 10000.00, 'semestral', 7),
	('46104873', 1, 8000.00, 'semestral', 9),
	('46110472', 1, 7000.00, 'semestral', 10),
	('45938662', 2, 8000.00, 'semestral', 11),
	('45938662', 1, 8500.00, 'semestral', 12),
	('45938698', 3, 11000.00, 'semestral', 13),
	('45938698', 2, 15000.00, 'mensual', 14);
