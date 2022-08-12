create table if not exists tb_inventory
(
    inventory_id int auto_increment primary key,
    sku_code     varchar(250) not null,
    quantity     int          not null
) engine = innoDB
  default charset = utf8;