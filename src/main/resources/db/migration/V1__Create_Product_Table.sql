create table if not exists tb_product
(
    product_id  int auto_increment primary key,
    name        varchar(100) not null,
    description varchar(250) not null,
    price      DECIMAL(6, 2) not null
) engine = innoDB
  default charset = utf8;