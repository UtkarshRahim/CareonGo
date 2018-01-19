SET ECHO ON
SET VERIFY ON
SET FEEDBACK OFF
SET DEFINE ON
CLEAR SCREEN
set serveroutput on

COLUMN date_time NEW_VAL filename noprint;
SELECT to_char(systimestamp,'yyyy-mm-dd_hh24-mi-ssxff') date_time FROM DUAL;
spool "Testingconnection_&filename..log"

-- Password file execution
@passworddefinition.sql

PROMPT Dropping Role ROLE_Testingconnection ...
DROP ROLE ROLE_Testingconnection ;
PROMPT Creating Role ROLE_Testingconnection ...
CREATE ROLE ROLE_Testingconnection ;

-- PROMPT Drop pro user
-- drop user pro cascade;
   
PROMPT Create user pro
CREATE USER pro identified by &&pro_password DEFAULT TABLESPACE USERS TEMPORARY TABLESPACE TEMP;
GRANT CREATE SESSION, RESOURCE, CREATE VIEW, CREATE MATERIALIZED VIEW, CREATE SYNONYM TO pro;

-- PROMPT Drop Emulation user
-- drop user Emulation cascade;
   
PROMPT Create user Emulation
CREATE USER Emulation identified by &&Emulation_password DEFAULT TABLESPACE USERS TEMPORARY TABLESPACE TEMP;
GRANT CREATE SESSION, RESOURCE, CREATE VIEW, CREATE MATERIALIZED VIEW, CREATE SYNONYM TO Emulation;

set define on
prompt connecting to Emulation
connect Emulation/&&Emulation_password;
set define off

set define on
prompt connecting to pro
connect pro/&&pro_password;
set define off

-- DROP SEQUENCE customers_CUSTOMER_ID_SEQ;


PROMPT Creating Sequence customers_CUSTOMER_ID_SEQ ...
CREATE SEQUENCE  customers_CUSTOMER_ID_SEQ  
  MINVALUE 1 MAXVALUE 999999999999999999999999 INCREMENT BY 1  NOCYCLE ;

-- DROP SEQUENCE inventory_INVENTORY_ID_SEQ;


PROMPT Creating Sequence inventory_INVENTORY_ID_SEQ ...
CREATE SEQUENCE  inventory_INVENTORY_ID_SEQ  
  MINVALUE 1 MAXVALUE 999999999999999999999999 INCREMENT BY 1  NOCYCLE ;

-- DROP SEQUENCE order_det_ORDER_DET_ID_SEQ;


PROMPT Creating Sequence order_det_ORDER_DET_ID_SEQ ...
CREATE SEQUENCE  order_det_ORDER_DET_ID_SEQ  
  MINVALUE 1 MAXVALUE 999999999999999999999999 INCREMENT BY 1  NOCYCLE ;

-- DROP SEQUENCE orders_ORDER_ID_SEQ;


PROMPT Creating Sequence orders_ORDER_ID_SEQ ...
CREATE SEQUENCE  orders_ORDER_ID_SEQ  
  MINVALUE 1 MAXVALUE 999999999999999999999999 INCREMENT BY 1  NOCYCLE ;

-- DROP SEQUENCE products_PRODUCT_ID_SEQ;


PROMPT Creating Sequence products_PRODUCT_ID_SEQ ...
CREATE SEQUENCE  products_PRODUCT_ID_SEQ  
  MINVALUE 1 MAXVALUE 999999999999999999999999 INCREMENT BY 1  NOCYCLE ;

-- DROP SEQUENCE suppliers_SUPPLIER_ID_SEQ;


PROMPT Creating Sequence suppliers_SUPPLIER_ID_SEQ ...
CREATE SEQUENCE  suppliers_SUPPLIER_ID_SEQ  
  MINVALUE 1 MAXVALUE 999999999999999999999999 INCREMENT BY 1  NOCYCLE ;

-- DROP TABLE customers CASCADE CONSTRAINTS;


PROMPT Creating Table customers ...
CREATE TABLE customers (
  CUSTOMER_ID NUMBER(10,0) NOT NULL,
  CUSTOMER_NAME VARCHAR2(20 CHAR) NOT NULL,
  EMAIL_ADDRESS VARCHAR2(60 CHAR) NOT NULL,
  PHONE_NUMBER VARCHAR2(10 CHAR) NOT NULL,
  ADDRESS VARCHAR2(60 CHAR),
  Username VARCHAR2(20 CHAR),
  PASSWORD VARCHAR2(20 CHAR)
);


PROMPT Creating Primary Key Constraint PRIMARY on table customers ... 
ALTER TABLE customers
ADD CONSTRAINT PRIMARY PRIMARY KEY
(
  CUSTOMER_ID
)
ENABLE
;

GRANT ALL ON customers TO ROLE_Testingconnection;
-- DROP TABLE inventory CASCADE CONSTRAINTS;


PROMPT Creating Table inventory ...
CREATE TABLE inventory (
  INVENTORY_ID NUMBER(10,0) NOT NULL,
  BATCH_NUMBER VARCHAR2(10 CHAR) NOT NULL,
  SUPPLIER_ID NUMBER(10,0) NOT NULL,
  PRODUCT_ID NUMBER(10,0) NOT NULL,
  MFG_DATE DATE NOT NULL,
  EXP_DATE DATE NOT NULL,
  QUANTITY NUMBER(10,0) NOT NULL,
  UNIT_PURCHASE_PRICE FLOAT NOT NULL,
  UNIT_SELL_PRICE FLOAT NOT NULL,
  TYPE VARCHAR2(20 CHAR)
);


PROMPT Creating Primary Key Constraint PRIMARY_1 on table inventory ... 
ALTER TABLE inventory
ADD CONSTRAINT PRIMARY_1 PRIMARY KEY
(
  INVENTORY_ID
)
ENABLE
;
PROMPT Creating Unique Constraint BATCH_NUMBER on table inventory
ALTER TABLE inventory
ADD CONSTRAINT BATCH_NUMBER UNIQUE (
  BATCH_NUMBER
)
ENABLE
;

GRANT ALL ON inventory TO ROLE_Testingconnection;
-- DROP TABLE order_det CASCADE CONSTRAINTS;


PROMPT Creating Table order_det ...
CREATE TABLE order_det (
  ORDER_DET_ID NUMBER(10,0) NOT NULL,
  ORDER_ID NUMBER(10,0) NOT NULL,
  PRODUCT_ID NUMBER(10,0) NOT NULL,
  QUANTITY NUMBER(10,0) NOT NULL,
  Batch_no VARCHAR2(10 CHAR)
);


PROMPT Creating Primary Key Constraint PRIMARY_5 on table order_det ... 
ALTER TABLE order_det
ADD CONSTRAINT PRIMARY_5 PRIMARY KEY
(
  ORDER_DET_ID
)
ENABLE
;

GRANT ALL ON order_det TO ROLE_Testingconnection;
-- DROP TABLE orders CASCADE CONSTRAINTS;


PROMPT Creating Table orders ...
CREATE TABLE orders (
  ORDER_ID NUMBER(10,0) NOT NULL,
  PRICE FLOAT,
  TIME DATE,
  STATUS VARCHAR2(20 CHAR),
  CUSTOMER_ID NUMBER(10,0)
);


ALTER TABLE orders MODIFY (TIME DEFAULT SYSDATE);
PROMPT Creating Primary Key Constraint PRIMARY_3 on table orders ... 
ALTER TABLE orders
ADD CONSTRAINT PRIMARY_3 PRIMARY KEY
(
  ORDER_ID
)
ENABLE
;

GRANT ALL ON orders TO ROLE_Testingconnection;
-- DROP TABLE products CASCADE CONSTRAINTS;


PROMPT Creating Table products ...
CREATE TABLE products (
  PRODUCT_ID NUMBER(10,0) NOT NULL,
  PRODUCT_NAME VARCHAR2(30 CHAR) NOT NULL,
  Description VARCHAR2(100 CHAR)
);


PROMPT Creating Primary Key Constraint PRIMARY_4 on table products ... 
ALTER TABLE products
ADD CONSTRAINT PRIMARY_4 PRIMARY KEY
(
  PRODUCT_ID
)
ENABLE
;

GRANT ALL ON products TO ROLE_Testingconnection;
-- DROP TABLE suppliers CASCADE CONSTRAINTS;


PROMPT Creating Table suppliers ...
CREATE TABLE suppliers (
  SUPPLIER_ID NUMBER(10,0) NOT NULL,
  SUPPLIER_NAME VARCHAR2(20 CHAR) NOT NULL,
  EMAIL_ADDRESS VARCHAR2(20 CHAR) NOT NULL,
  PHONE_NUMBER VARCHAR2(10 CHAR) NOT NULL,
  Address VARCHAR2(30 CHAR)
);


PROMPT Creating Primary Key Constraint PRIMARY_2 on table suppliers ... 
ALTER TABLE suppliers
ADD CONSTRAINT PRIMARY_2 PRIMARY KEY
(
  SUPPLIER_ID
)
ENABLE
;

GRANT ALL ON suppliers TO ROLE_Testingconnection;
PROMPT Creating Index INVENTORY_fk0 on inventory ...
CREATE INDEX INVENTORY_fk0 ON inventory
(
  SUPPLIER_ID
) 
;
PROMPT Creating Index INVENTORY_fk1 on inventory ...
CREATE INDEX INVENTORY_fk1 ON inventory
(
  PRODUCT_ID
) 
;
PROMPT Creating Index ORDER_DET_fk0 on order_det ...
CREATE INDEX ORDER_DET_fk0 ON order_det
(
  PRODUCT_ID
) 
;
PROMPT Creating Index ORDER_DET_fk1 on order_det ...
CREATE INDEX ORDER_DET_fk1 ON order_det
(
  ORDER_ID
) 
;
set define on
prompt connecting to pro
connect pro/&&pro_password;
set define off

PROMPT Creating Foreign Key Constraint INVENTORY_fk0 on table suppliers...
ALTER TABLE inventory
ADD CONSTRAINT INVENTORY_fk0 FOREIGN KEY
(
  SUPPLIER_ID
)
REFERENCES suppliers
(
  SUPPLIER_ID
)
ENABLE
;

PROMPT Creating Foreign Key Constraint INVENTORY_fk1 on table products...
ALTER TABLE inventory
ADD CONSTRAINT INVENTORY_fk1 FOREIGN KEY
(
  PRODUCT_ID
)
REFERENCES products
(
  PRODUCT_ID
)
ENABLE
;

PROMPT Creating Foreign Key Constraint ORDER_DET_fk0 on table products...
ALTER TABLE order_det
ADD CONSTRAINT ORDER_DET_fk0 FOREIGN KEY
(
  PRODUCT_ID
)
REFERENCES products
(
  PRODUCT_ID
)
ENABLE
;

PROMPT Creating Foreign Key Constraint ORDER_DET_fk1 on table orders...
ALTER TABLE order_det
ADD CONSTRAINT ORDER_DET_fk1 FOREIGN KEY
(
  ORDER_ID
)
REFERENCES orders
(
  ORDER_ID
)
ENABLE
;

CREATE OR REPLACE TRIGGER customers_CUSTOMER_ID_TRG BEFORE INSERT ON customers
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.CUSTOMER_ID IS NULL THEN
    SELECT  customers_CUSTOMER_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(CUSTOMER_ID),0) INTO v_newVal FROM customers;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT customers_CUSTOMER_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
    --used to emulate LAST_INSERT_ID()
    --mysql_utilities.identity := v_newVal; 
   -- assign the value from the sequence to emulate the identity column
   :new.CUSTOMER_ID := v_newVal;
  END IF;
END;

/

CREATE OR REPLACE TRIGGER inventory_INVENTORY_ID_TRG BEFORE INSERT ON inventory
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.INVENTORY_ID IS NULL THEN
    SELECT  inventory_INVENTORY_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(INVENTORY_ID),0) INTO v_newVal FROM inventory;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT inventory_INVENTORY_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
    --used to emulate LAST_INSERT_ID()
    --mysql_utilities.identity := v_newVal; 
   -- assign the value from the sequence to emulate the identity column
   :new.INVENTORY_ID := v_newVal;
  END IF;
END;

/

CREATE OR REPLACE TRIGGER order_det_ORDER_DET_ID_TRG BEFORE INSERT ON order_det
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.ORDER_DET_ID IS NULL THEN
    SELECT  order_det_ORDER_DET_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(ORDER_DET_ID),0) INTO v_newVal FROM order_det;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT order_det_ORDER_DET_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
    --used to emulate LAST_INSERT_ID()
    --mysql_utilities.identity := v_newVal; 
   -- assign the value from the sequence to emulate the identity column
   :new.ORDER_DET_ID := v_newVal;
  END IF;
END;

/

CREATE OR REPLACE TRIGGER orders_ORDER_ID_TRG BEFORE INSERT ON orders
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.ORDER_ID IS NULL THEN
    SELECT  orders_ORDER_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(ORDER_ID),0) INTO v_newVal FROM orders;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT orders_ORDER_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
    --used to emulate LAST_INSERT_ID()
    --mysql_utilities.identity := v_newVal; 
   -- assign the value from the sequence to emulate the identity column
   :new.ORDER_ID := v_newVal;
  END IF;
END;

/

CREATE OR REPLACE TRIGGER products_PRODUCT_ID_TRG BEFORE INSERT ON products
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.PRODUCT_ID IS NULL THEN
    SELECT  products_PRODUCT_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(PRODUCT_ID),0) INTO v_newVal FROM products;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT products_PRODUCT_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
    --used to emulate LAST_INSERT_ID()
    --mysql_utilities.identity := v_newVal; 
   -- assign the value from the sequence to emulate the identity column
   :new.PRODUCT_ID := v_newVal;
  END IF;
END;

/

CREATE OR REPLACE TRIGGER suppliers_SUPPLIER_ID_TRG BEFORE INSERT ON suppliers
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.SUPPLIER_ID IS NULL THEN
    SELECT  suppliers_SUPPLIER_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(SUPPLIER_ID),0) INTO v_newVal FROM suppliers;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT suppliers_SUPPLIER_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
    --used to emulate LAST_INSERT_ID()
    --mysql_utilities.identity := v_newVal; 
   -- assign the value from the sequence to emulate the identity column
   :new.SUPPLIER_ID := v_newVal;
  END IF;
END;

/

spool off;

COMMIT;

