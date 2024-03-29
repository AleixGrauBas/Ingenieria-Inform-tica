SELECT codcli
FROM facturas
WHERE codcli IS NOT NULL
AND EXTRACT(year FROM CURRENT_DATE) - EXTRACT(year FROM fecha) = 1
EXCEPT
SELECT codcli
FROM facturas
WHERE COALESCE(iva, 0)=0
AND EXTRACT(year FROM CURRENT_DATE) - EXTRACT(year FROM fecha) = 1

ORDER BY codcli;

