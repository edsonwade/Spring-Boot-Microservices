create table if not exists tb_order_line_item
(
    order_items_id int auto_increment primary key,
    sku_code       varchar(250)  not null,
    price          DECIMAL(6, 2) not null,
    quantity       int           not null
) engine = innoDB
  default charset = utf8;

create table if not exists tb_order_order_line_items
(
    order_order_id                  integer not null,
    order_line_items_order_items_id integer not null
) engine = innoDB
  default charset = utf8;

create table if not exists tb_order
(
    order_id integer not null auto_increment,
    primary key (order_id)
) engine = innoDB
  default charset = utf8;


alter table tb_order_order_line_items
    add constraint UK_f3l9x8bnmic2sw3vquk8xjbg6 unique (order_line_items_order_items_id);

alter table tb_order_order_line_items
    add constraint FKedajyn6iirp4rn33pebij4364
        foreign key (order_line_items_order_items_id)
            references tb_order_line_item (order_items_id);


alter table tb_order_order_line_items
    add constraint FKm3vfst7cxcd8kiga56pc1wiga
        foreign key (order_order_id)
            references tb_order (order_id)

