SELECT codart 
FROM articulos
WHERE stock > 5 AND precio > 3
EXCEPT
SELECT l.codart 
FROM lineas_fac AS l JOIN facturas AS f USING(codfac)
WHERE EXTRACT(quarter from f.fecha) = 4 AND EXTRACT(year from CURRENT_DATE) - 
        EXTRACT(year from f.fecha) = 1
ORDER BY codart


