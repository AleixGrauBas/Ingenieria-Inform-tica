SELECT c.*
FROM facturas AS f JOIN clientes AS c USING(codcli)
EXCEPT 
SELECT c.*
FROM facturas AS f JOIN clientes AS c USING(codcli)
WHERE EXTRACT(quarter from fecha) > 1
ORDER BY codcli
