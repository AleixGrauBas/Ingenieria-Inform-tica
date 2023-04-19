CREATE OR REPLACE FUNCTION funImporteBruto() RETURNS TRIGGER
AS $func$
BEGIN
  IF ( TG_OP = 'INSERT' ) THEN
    -- Accumulate the gross cost of this line to the importeBruto 
    -- (gross amount) column of the invoice this line belongs to.
    UPDATE facturas
    SET    importeBruto = COALESCE( importeBruto, 0 ) +
                          COALESCE( NEW.cant, 0 ) *
                          COALESCE( NEW.precio, 0 )
    WHERE  codfac = NEW.codfac;
 ELSEIF( TG_OP = 'UPDATE' ) THEN
UPDATE facturas
    SET    importeBruto = COALESCE( importeBruto, 0 ) +
                          COALESCE( NEW.cant, 0 ) *
                          COALESCE( NEW.precio, 0 ) -(
			  COALESCE( OLD.cant, 0 ) *
			  COALESCE( OLD.precio, 0))
    WHERE  codfac = NEW.codfac;

 ELSEIF( TG_OP = 'DELETE' ) THEN
UPDATE facturas
    SET    importeBruto = COALESCE( importeBruto, 0 ) -
                          COALESCE( OLD.cant, 0 ) *
                          COALESCE( OLD.precio, 0 )
    WHERE  codfac = OLD.codfac;


  END IF;

  RETURN NEW;
END;
$func$ LANGUAGE 'plpgsql';

