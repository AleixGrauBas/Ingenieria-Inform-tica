SELECT codcli
FROM facturas
EXCEPT 
SELECT codcli
FROM facturas 
WHERE EXTRACT(quarter from fecha) > 1
ORDER BY codcli
