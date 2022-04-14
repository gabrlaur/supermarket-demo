CREATE TABLE product (
  id BIGINT NOT NULL,
   name VARCHAR(255) NOT NULL,
   quantity INT NOT NULL,
   price DOUBLE NOT NULL,
   CONSTRAINT pk_product PRIMARY KEY (id)
);
CREATE TABLE cash_register (
  id BIGINT AUTO_INCREMENT NOT NULL,
   value DOUBLE NULL,
   quantity INT NULL,
   CONSTRAINT pk_cash_register PRIMARY KEY (id)
);