--
-- Script to insert a few rows in the minidatabase with articulos, facturas, 
-- and lineas_fac.
--

insert into articulos values ( 'CPU1', 'CPU Intel 3.2 GHz', 500, 10,  5, 0 );
insert into articulos values ( 'Pan1', 'Pantalla LG',       150, 20, 10, 0 );
insert into articulos values ( 'RAM1', 'RAM DDR4',          100, 15, 10, 0 );
insert into articulos values ( 'SSD1', 'Disco SSD Samsung', 400,  5,  5, 0 );

insert into facturas values ( 501, current_date - 3, null, null, 21, 0 );
insert into facturas values ( 502, current_date - 2, null, null, 21, 0 );
insert into facturas values ( 503, current_date - 1, null, null, 21, 0 );


