SELECT c.codcli, c.nombre, COUNT(DISTINCT f.codfac) AS count, p.codpro
FROM clientes AS c JOIN facturas AS f USING(codcli) 
	JOIN pueblos AS p USING(codpue) 
	JOIN lineas_fac AS lf USING(codfac)
WHERE p.codpro IN ('12','46','03')
GROUP BY c.codcli, c.nombre, p.codpro
HAVING COUNT(DISTINCT f.codfac) > 10 AND SUM(lf.precio*lf.cant) > 1000
ORDER BY c.codcli

