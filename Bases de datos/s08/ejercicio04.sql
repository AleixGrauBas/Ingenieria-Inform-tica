SELECT *
FROM clientes
WHERE codcli IN (SELECT codcli
                FROM facturas
                WHERE fecha IN ( SELECT MAX(fecha)
                                 FROM facturas
          		)

        )
ORDER BY codcli
