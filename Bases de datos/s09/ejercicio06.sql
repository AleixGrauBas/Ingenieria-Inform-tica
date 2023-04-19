SELECT codigoprov, COUNT(codigocliente)
FROM (
SELECT c.codcli AS codigocliente, p.codpro AS codigoprov
FROM facturas AS f
JOIN clientes AS c USING(codcli)
JOIN pueblos AS p USING(codpue)
WHERE codcli IS NOT NULL
AND EXTRACT(year FROM CURRENT_DATE) - EXTRACT(year FROM fecha) = 1
EXCEPT
SELECT c.codcli AS codigocliente, p.codpro AS codigoprov
FROM facturas AS f
JOIN clientes AS c USING(codcli)
JOIN pueblos AS p USING(codpue)
WHERE COALESCE(iva, 0)=0
AND EXTRACT(year FROM CURRENT_DATE) - EXTRACT(year FROM fecha) = 1
) AS key
Group BY codigoprov;

