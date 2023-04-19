SELECT * 
FROM pueblos AS p JOIN provincias AS pro USING(codpro)
WHERE p.codpue IN ( SELECT codpue
			FROM clientes
			GROUP BY codcli
			HAVING COUNT(codpue) > 2
		)
              
ORDER BY p.codpue

