SELECT c.codart, c.descrip, cs 
FROM ( SELECT SUM(l.cant) AS cs, l.codart 
	FROM lineas_fac AS l JOIN facturas AS f USING(codfac)
	JOIN vendedores AS v USING(codven)
	JOIN pueblos AS p USING(codpue)
	WHERE codpro LIKE '12'
	GROUP BY l.codart) AS t
	JOIN articulos AS c USING(codart)
WHERE UPPER(c.descrip) LIKE 'BASE%'
ORDER BY c.descrip, c.codart
