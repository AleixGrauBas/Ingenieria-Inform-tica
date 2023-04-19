SELECT v.codven, v.nombre
FROM vendedores AS v
JOIN facturas AS f USING(codven)
JOIN lineas_fac AS l USING(codfac)
JOIN articulos AS a USING(codart)

EXCEPT

SELECT v.codven, v.nombre
FROM vendedores AS v
JOIN facturas AS f USING(codven)
JOIN lineas_fac AS l USING(codfac)
JOIN articulos AS a USING(codart)
WHERE UPPER(a.descrip) LIKE 'AL%'
ORDER BY 2;

