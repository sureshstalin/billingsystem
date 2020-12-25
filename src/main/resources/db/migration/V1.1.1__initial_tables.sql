CREATE TABLE user (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) NOT NULL,
  email_id varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  middle_name varchar(255) DEFAULT NULL,
  mobile_no varchar(255) NOT NULL,
  password varchar(10) NOT NULL,
  user_type varchar(10) NOT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE address (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  address1 varchar(255) DEFAULT NULL,
  address2 varchar(255) DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  country varchar(255) DEFAULT NULL,
  landmark varchar(255) DEFAULT NULL,
  mobile varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY address_fk_user (user_id),
  CONSTRAINT address_fk_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB;

CREATE TABLE customer (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  customer_code varchar(255) NOT NULL,
  full_name varchar(255) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX customer_code_UNIQUE (customer_code ASC) VISIBLE,
  KEY customer_fk_user (user_id),
  CONSTRAINT customer_fk_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB;

CREATE TABLE employee (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  employee_code varchar(255) NOT NULL,
  full_name varchar(255) NOT NULL,
  user_id bigint DEFAULT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX employee_code_UNIQUE (employee_code ASC) VISIBLE,
  KEY employee_fk_user (user_id),
  CONSTRAINT employee_fk_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB;

CREATE TABLE category (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  category_code varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX category_code_UNIQUE (category_code ASC) VISIBLE
) ENGINE=InnoDB;

CREATE TABLE tax (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  hsn_code varchar(255) NOT NULL,
  tax_description varchar(255) NOT NULL,
  tax_percentage float NOT NULL DEFAULT 0,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE offer (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  offer_code varchar(10) NOT NULL,
  offer_name varchar(255) NOT NULL,
  offer_description VARCHAR(150) NOT NULL,
  status VARCHAR(10) NOT NULL,
  date_created datetime DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX offer_code_UNIQUE (offer_code ASC) VISIBLE
) ENGINE=InnoDB;

CREATE TABLE product (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  product_code varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  price double NOT NULL,
  category_id bigint DEFAULT NULL,
  tax_id bigint DEFAULT NULL,
  stock_count int NOT NULL DEFAULT 0,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE,
  UNIQUE INDEX product_code_UNIQUE (product_code ASC) VISIBLE,
  KEY product_fk_category (category_id),
  KEY product_fk_tax (tax_id),
  CONSTRAINT product_fk_category FOREIGN KEY (category_id) REFERENCES category (id),
  CONSTRAINT product_fk_tax FOREIGN KEY (tax_id) REFERENCES tax (id)
) ENGINE=InnoDB;

CREATE TABLE product_item (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  product_item_code varchar(100) NOT NULL,
  product_id bigint NOT NULL,
  status int NOT NULL DEFAULT 0,
  date_created datetime DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX product_item_code_UNIQUE (product_item_code ASC) VISIBLE,
  KEY product_item_fk_product (product_id),
  CONSTRAINT product_item_fk_product FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB;

CREATE TABLE biller (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  bill_no varchar(255) NOT NULL,
  customer_id bigint DEFAULT NULL,
  grand_total double NOT NULL,
  total_tax_amount double NOT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX bill_no_UNIQUE (bill_no ASC) VISIBLE,
  KEY biller_fk_customer (customer_id),
  CONSTRAINT biller_fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
) ENGINE=InnoDB;

CREATE TABLE payment (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  biller_id bigint NOT NULL,
  product_item_id bigint NOT NULL,
  price DOUBLE NOT NULL,
  tax_id BIGINT NOT NULL,
  total_price DOUBLE NOT NULL,
  tax_amount DOUBLE NOT NULL DEFAULT 0,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY payment_fk_biller (biller_id),
  CONSTRAINT payment_fk_biller FOREIGN KEY (biller_id) REFERENCES biller(id),
  KEY payment_fk_product_item (product_item_id),
  CONSTRAINT payment_fk_product_item FOREIGN KEY (product_item_id) REFERENCES product_item(id),
  KEY payment_fk_tax (tax_id),
  CONSTRAINT payment_fk_tax FOREIGN KEY (tax_id) REFERENCES tax(id)
) ENGINE=InnoDB;

CREATE TABLE organization (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  org_code varchar(255) NOT NULL,
  org_name varchar(255) NOT NULL,
  user_id bigint NOT NULL,
  date_created datetime DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX org_code_UNIQUE (org_code ASC) VISIBLE,
  UNIQUE INDEX org_name_UNIQUE (org_name ASC) VISIBLE,
  KEY ogranization_fk_user (user_id),
  CONSTRAINT ogranization_fk_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB;

CREATE TABLE role (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  role_description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL,
  date_created datetime DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX role_name_UNIQUE (role_name ASC) VISIBLE
) ENGINE=InnoDB;

CREATE TABLE stock (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  stock_count bigint NOT NULL,
  product_id bigint DEFAULT NULL,
  date_created datetime DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY stock_fk_product (product_id),
  CONSTRAINT stock_fk_product FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB;

CREATE TABLE vendor (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  full_name varchar(255) NOT NULL,
  vendor_code varchar(255) NOT NULL,
  user_id bigint DEFAULT NULL,
  vendor_id bigint DEFAULT NULL,
  date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX vendor_code_UNIQUE (vendor_code ASC) VISIBLE,
  KEY vendor_fk_user (user_id),
  KEY vendor_fk_stock (vendor_id),
  CONSTRAINT vendor_fk_user FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT vendor_fk_stock FOREIGN KEY (vendor_id) REFERENCES stock (id)
) ENGINE=InnoDB;

CREATE TABLE purchase_order (
  id bigint NOT NULL AUTO_INCREMENT,
  is_deleted bit(1) DEFAULT NULL,
  purchase_order_code varchar(10) NOT NULL,
  product_name varchar(255) NOT NULL,
  product_description varchar(255) NOT NULL,
  unit_price double NOT NULL,
  quantity int NOT NULL,
  total_amount double NOT NULL,
  tax_id bigint DEFAULT NULL,
  tax_amount double DEFAULT 0,
  grand_total double NOT NULL DEFAULT 0,
  vendor_id bigint DEFAULT NULL,
  category_id bigint NOT NULL,
  status tinyint(1) NOT NULL,
  date_created datetime DEFAULT CURRENT_TIMESTAMP,
  date_modified datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX purchase_order_code_UNIQUE (purchase_order_code ASC) VISIBLE,
  KEY purchase_order_fk_vendor (vendor_id),
  CONSTRAINT purchase_order_fk_vendor FOREIGN KEY (vendor_id) REFERENCES vendor (id),
  KEY product_fk_tax (tax_id),
  CONSTRAINT purchase_order_fk_tax FOREIGN KEY (tax_id) REFERENCES tax (id),
  CONSTRAINT purchase_order_fk_category FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE=InnoDB;

CREATE TABLE app_entity_code (
  id BIGINT NOT NULL AUTO_INCREMENT,
  code VARCHAR(10) NOT NULL,
  code_type VARCHAR(20) NOT NULL,
  date_created DATETIME NOT NULL,
  date_modified DATETIME NULL,
  is_deleted TINYINT NULL,
  UNIQUE INDEX code_UNIQUE (code ASC) VISIBLE,
  PRIMARY KEY (id));


  CREATE TABLE system_codes (
    id int NOT NULL AUTO_INCREMENT,
    code_prefix varchar(5) NOT NULL,
    code_type varchar(20) DEFAULT NULL,
    is_deleted bit(1) DEFAULT 0,
    date_created datetime DEFAULT CURRENT_TIMESTAMP,
    date_modified datetime DEFAULT NULL,
    PRIMARY KEY (id)
  ) ENGINE=InnoDB;