CREATE TABLE jwt_token (
  id INT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(100) NOT NULL,
  access_token VARCHAR(250) NOT NULL,
  refresh_token VARCHAR(250) NOT NULL,
  access_token_expiration DATETIME NOT NULL,
  refresh_token_expiration DATETIME NOT NULL,
  date_created DATETIME NULL,
  date_modified DATETIME NULL,
  PRIMARY KEY (id));
