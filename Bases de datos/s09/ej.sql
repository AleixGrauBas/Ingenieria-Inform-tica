SELECT COUNT(*) AS pueblos
FROM (SELECT codpue
	FROM pueblos 
	WHERE codpro = '03'
	EXCEPT
	SELECT p.codpue
	FROM clientes AS c JOIN pueblos AS p USING(codpue)
	
) AS p
