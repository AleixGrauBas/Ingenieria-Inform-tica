SELECT count(*)
FROM(
SELECT 1
FROM facturas AS f JOIN clientes AS c USING(codcli)
	JOIN pueblos AS p USING(codpue)
GROUP by f.codcli, p.codpro
HAVING COUNT(DISTINCT f.codfac) > 10
) AS key
