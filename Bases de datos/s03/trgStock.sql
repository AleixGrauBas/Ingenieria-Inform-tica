CREATE TRIGGER trgStock
    AFTER INSERT ON articulos
    FOR EACH ROW
    EXECUTE PROCEDURE funStock();

