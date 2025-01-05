
-- Trigger pour insertion dans reparation_details
CREATE OR REPLACE FUNCTION update_stock_on_reparation_insert()
RETURNS TRIGGER AS $$
DECLARE
    component_id INT;
BEGIN
    -- Récupérer le id_component à partir de la table reparation_types
    SELECT id_component INTO component_id
    FROM reparation_types
    WHERE id_reparation_type = NEW.id_reparation_type;

    -- Insérer dans stock_history avec le id_component récupéré
    INSERT INTO stock_history (id_component, change)
    VALUES (component_id, -1);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER reparation_insert_trigger
AFTER INSERT ON reparation_details
FOR EACH ROW
EXECUTE FUNCTION update_stock_on_reparation_insert();

-- Trigger pour insertion dans achat
CREATE OR REPLACE FUNCTION update_stock_on_achat_insert()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO stock_history (id_component, change)
    VALUES (NEW.id_component, NEW.quantity);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER achat_insert_trigger
AFTER INSERT ON achat
FOR EACH ROW
EXECUTE FUNCTION update_stock_on_achat_insert();

CREATE OR REPLACE FUNCTION get_latest_reparation_type_prices(specified_date TIMESTAMP)
RETURNS TABLE (
    id_reparation_type_price INT,
    id_reparation_type INT,
    price NUMERIC,
    effective_date TIMESTAMP
) AS $$
BEGIN
    RETURN QUERY
    SELECT rtp.id_reparation_type_price, rtp.id_reparation_type, rtp.price, rtp.effective_date
    FROM reparation_type_prices rtp
    JOIN (
        SELECT rtpp.id_reparation_type, MAX(rtpp.effective_date) AS max_date
        FROM reparation_type_prices rtpp
        WHERE rtpp.effective_date <= specified_date
        GROUP BY rtpp.id_reparation_type
    ) sub
    ON rtp.id_reparation_type = sub.id_reparation_type AND rtp.effective_date = sub.max_date;
END;
$$ LANGUAGE plpgsql;