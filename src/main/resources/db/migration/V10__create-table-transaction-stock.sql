CREATE TABLE transaction_stock (
                                   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                   id_product UUID NOT NULL,
                                   transaction_type VARCHAR(50) NOT NULL,
                                   transaction_date TIMESTAMP,
                                   movement_quantity INT NOT NULL
);
