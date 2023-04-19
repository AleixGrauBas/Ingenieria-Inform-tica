SELECT DISTINCT c.*
FROM clientes AS c JOIN facturas AS f USING(codcli)
WHERE codcli  NOT IN ( SELECT codcli
					FROM facturas
					WHERE EXTRACT(month FROM fecha) <= 6
				AND codcli IS NOT NULL)
              
ORDER BY codcli;

