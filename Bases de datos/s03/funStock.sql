CREATE OR REPLACE FUNCTION funStock() RETURNS TRIGGER
AS $func$
DECLARE
  currentStock  INTEGER ;
BEGIN
  IF ( TG_OP = 'INSERT' ) THEN

    -- Check if the new line has a valid quantity.
    IF ( COALESCE( NEW.cant, 0 ) > 0 ) THEN

      -- Subtract the amount of articles sold in this invoice line 
      -- from the stock of the same article.
      -- This update must check that there is enough stock.
	UPDATE articulos
	SET stock = COALESCE( stock, 0) - COALESCE(new.stock,0)
	WHERE codart = new.codart;
      -- Check if the update operation did not modify any line.
      -- If no line was modified, there was not enough stock.
      IF( NOT FOUND ) THEN

        -- Get the current stock of the article sold in the 
        -- invoice line.
        SELECT stock
        INTO   currentStock
        FROM articulos
	WHERE codart = new.codart;

        -- Raise an error.
        RAISE EXCEPTION 
          'There were not enough stock to sell % items of article "%"
           The current stock is: % ',
          NEW.cant, NEW.codart, currentStock ;
      END IF ;

    END IF;

  END IF;

  RETURN NEW;
END;
$func$ LANGUAGE 'plpgsql';

