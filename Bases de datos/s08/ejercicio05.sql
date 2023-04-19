SELECT *
FROM vendedores
WHERE codven NOT IN (SELECT codven
                FROM facturas
                WHERE EXTRACT(month FROM fecha) = 12 AND
                EXTRACT(year FROM CURRENT_DATE) - EXTRACT(year FROM fecha) = 1
                AND codven IS NOT NULL)
ORDER BY codven;

