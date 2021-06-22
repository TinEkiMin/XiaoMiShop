use XiaoMiMail
go
--商品类型表
create table tb_goods_type(
		id int primary key,
		name varchar(50) not null
		)

--商品表
create table tb_goods(
		id int primary key,
		name varchar(100) not null,
		--添加购物车日期
		pubDate date default getDate(),
		picture varchar(255),
		price int,
		--星级
		star int,
		--描述
		intro varchar(50),
		typeid int foreign key(typeid) references tb_goods_type(id)
		)
--用户
create table tb_user(
		id int primary key,
		userName varchar(20) not null,
		password varchar(32) not null,
		email varchar(50),
		gender varchar(50),
		--账户激活标记 （0未激活，1已激活）
		flag int default 1,
		--管理员标记 （0用户，1管理员）
		role int default 0,
		--激活码
		code varchar(100)
		)
--用户地址
create table tb_address(
		id int primary key,
		detail varchar(200),
		name varchar(20),
		phone varchar(20),
		uid int foreign key(uid) references tb_user(id),
		--默认地址标记
		level int 
		)
--订单
create table tb_order(
		id int primary key,
		uid int foreign key(uid) references tb_user(id),
		--小计
		money decimal(13,2),
		--订单状态
		status varchar(10),
		--下单时间
		time date default getDate(),
		aid int foreign key(aid) references tb_address(id)
		)
--购物车
create table tb_cart(
		id int primary key,
		--商品id
		pid int foreign key(pid) references tb_goods(id),
		--数量
		num int,
		--小计
		money decimal(13,2)
		)
--订单详情
create table tb_orderdetail(
		id int primary key,
		oid int foreign key(oid) references tb_order(id),
		pid int foreign key(pid) references tb_goods(id),
		num int,
		money decimal(13,2)
		)