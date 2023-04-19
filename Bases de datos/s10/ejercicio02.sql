SELECT c.codcli, c.nombre, EXTRACT(year FROM fecha)
FROM clientes AS c JOIN facturas AS f USING(codcli)
	JOIN lineas_fac AS lf USING(codfac)
GROUP BY c.codcli, EXTRACT(year FROM fecha)
WHERE fecha IN (SELECT MAX(f.fecha) FROM facturas AS f
			JOIN lineas_fac AS lf
		group by f.codcli, ls.codfac
		WHERE 1
		HAVING (MAX(SUM(precio*cant)))
		)
ORDER BY c.codcli
