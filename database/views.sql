CREATE VIEW components_with_stock AS
SELECT 
    c.id_component,
    c.name,
    c.serial_number,
    c.id_type,
    c.id_type_computer,
    COALESCE(SUM(sh.change), 0) AS stock
FROM 
    components c
LEFT JOIN 
    stock_history sh ON c.id_component = sh.id_component
GROUP BY 
    c.id_component, c.name, c.serial_number, c.id_type, c.id_type_computer;