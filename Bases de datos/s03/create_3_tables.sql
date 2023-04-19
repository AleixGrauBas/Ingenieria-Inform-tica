--
-- Script to create three tables: articulos, facturas, and lineas_fac.
--


--
-- Drop tables if they exists.
--

DROP TABLE IF EXISTS lineas_fac ;
DROP TABLE IF EXISTS articulos ;
DROP TABLE IF EXISTS facturas ;





--
-- Name: articulos; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE articulos (
    codart character varying(8) NOT NULL,
    descrip character varying(40) NOT NULL,
    precio numeric(6,2) NOT NULL,
    stock numeric(6,0),
    stock_min numeric(6,0),
    dto numeric(4,2),
    CONSTRAINT ri_dto_art CHECK (((dto >= (0)::numeric) AND (dto <= (50)::numeric)))
);


--
-- Name: facturas; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE facturas (
    codfac numeric(6,0) NOT NULL,
    fecha date NOT NULL,
    codcli numeric(5,0),
    codven numeric(5,0),
    iva numeric(2,0),
    dto numeric(4,2),
    CONSTRAINT ri_dto_fac CHECK (((dto >= (0)::numeric) AND (dto <= (50)::numeric)))
);


--
-- Name: lineas_fac; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE lineas_fac (
    codfac numeric(6,0) NOT NULL,
    linea numeric(2,0) NOT NULL,
    cant numeric(5,0) NOT NULL,
    codart character varying(8) NOT NULL,
    precio numeric(6,2) NOT NULL,
    dto numeric(4,2),
    CONSTRAINT ri_dto_lin CHECK (((dto >= (0)::numeric) AND (dto <= (50)::numeric)))
);






--
-- Name: articulos cp_articulos; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY articulos
    ADD CONSTRAINT cp_articulos PRIMARY KEY (codart);


--
-- Name: facturas cp_facturas; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY facturas
    ADD CONSTRAINT cp_facturas PRIMARY KEY (codfac);


--
-- Name: lineas_fac cp_lineas_fac; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lineas_fac
    ADD CONSTRAINT cp_lineas_fac PRIMARY KEY (codfac, linea);


--
-- Name: lineas_fac ca_lin_art; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lineas_fac
    ADD CONSTRAINT ca_lin_art FOREIGN KEY (codart) REFERENCES articulos(codart) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: lineas_fac ca_lin_fac; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY lineas_fac
    ADD CONSTRAINT ca_lin_fac FOREIGN KEY (codfac) REFERENCES facturas(codfac) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- End of script.
--


