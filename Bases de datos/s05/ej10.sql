SELECT SUBSTRING(nombre,(POSITION(nombre,',')+2))AS nombre
FROM clientes
WHERE codcli < 50
ORDER by nombre
FETCH FIRST 10 ROWS ONLY

