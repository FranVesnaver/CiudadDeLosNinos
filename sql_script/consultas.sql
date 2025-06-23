-- Nombre y apellido de los donantes y los programas a los que aportaron
SELECT 
    d.nombre AS nombre_donante,
    d.apellido AS apellido_donante,
    p.nombre AS nombre_programa
FROM 
    ciudaddelosninos.Donante d
JOIN
    ciudaddelosninos.Aporta a ON d.dni_donante = a.dni_donante
JOIN 
    ciudaddelosninos.Programa p ON a.id_programa = p.id_programa;



-- INCISO 6 

-- a)
SELECT ID_PROGRAMA, NOMBRE, (SUM (MONTO)) AS TOTAL_DE_APORTES_MENSUALES_DE_LOS_DONANTES
FROM ciudaddelosninos.PROGRAMA
NATURAL JOIN ciudaddelosninos.APORTA WHERE (frecuencia = 'mensual')
GROUP BY ID_PROGRAMA


-- b)
SELECT DNI_DONANTE, COUNT (ID_PROGRAMA) AS CANTIDAD_APORTES 
FROM ciudaddelosninos.APORTA
GROUP BY (DNI_DONANTE)
HAVING COUNT (ID_PROGRAMA) > 2;

-- c)
SELECT *
FROM ciudaddelosninos.Aporta NATURAL JOIN ciudaddelosninos.MedioDePago
WHERE Aporta.frecuencia = 'mensual'
  AND MedioDePago.nro_pago IN (
      SELECT nro_pago FROM ciudaddelosninos.debito_transferencia
  )

UNION

SELECT *
FROM ciudaddelosninos.Aporta NATURAL JOIN ciudaddelosninos.MedioDePago
WHERE Aporta.frecuencia = 'mensual'
  AND MedioDePago.nro_pago IN (
      SELECT nro_pago FROM ciudaddelosninos.Credito
);

DELETE FROM ciudaddelosninos.Donante WHERE dni_donante = '45938662';
SELECT * FROM ciudaddelosninos.AuditoriaEliminacionDonante;