create database testdb;
use testdb;

create table product_info
 (
	product_id int primary key,
    product_name varchar(20)not null,
    product_qty int check (product_qty>=0),
    product_price double
    );
    
    drop table product_info;
    
    insert into product_info values(1,'Abc',10,100);
    insert into product_info values(2,'Def',5,50);
    insert into product_info values(3,'Ghi',8,150);
    
    create table order_info 
    (
		order_id int primary key auto_increment,
        customer_name varchar(20) not null,
        product_id int ,
        prduct_qty int,
        total_amt double
    );
    delimiter //
    create procedure placeOrder(in cName varchar(20),in pId int,in pQty int)
    begin
		declare pOqty int;
        declare pPrice int;
		select product_qty,product_price into pOqty,pPrice from product_info where product_id=pid;
        if pOqty>=pQty then
			insert into order_info(customer_name,product_id,prduct_qty,total_amt) values(cName,pId,pQty,pQty*pPrice);
            update product_info set product_qty=product_qty-pQty where product_id=pid;
		end if;
    end//
    delimiter ;
    
   
