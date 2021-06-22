use XiaoMiMail
go
--��Ʒ���ͱ�
create table tb_goods_type(
		id int primary key,
		name varchar(50) not null
		)

--��Ʒ��
create table tb_goods(
		id int primary key,
		name varchar(100) not null,
		--��ӹ��ﳵ����
		pubDate date default getDate(),
		picture varchar(255),
		price int,
		--�Ǽ�
		star int,
		--����
		intro varchar(50),
		typeid int foreign key(typeid) references tb_goods_type(id)
		)
--�û�
create table tb_user(
		id int primary key,
		userName varchar(20) not null,
		password varchar(32) not null,
		email varchar(50),
		gender varchar(50),
		--�˻������� ��0δ���1�Ѽ��
		flag int default 1,
		--����Ա��� ��0�û���1����Ա��
		role int default 0,
		--������
		code varchar(100)
		)
--�û���ַ
create table tb_address(
		id int primary key,
		detail varchar(200),
		name varchar(20),
		phone varchar(20),
		uid int foreign key(uid) references tb_user(id),
		--Ĭ�ϵ�ַ���
		level int 
		)
--����
create table tb_order(
		id int primary key,
		uid int foreign key(uid) references tb_user(id),
		--С��
		money decimal(13,2),
		--����״̬
		status varchar(10),
		--�µ�ʱ��
		time date default getDate(),
		aid int foreign key(aid) references tb_address(id)
		)
--���ﳵ
create table tb_cart(
		id int primary key,
		--��Ʒid
		pid int foreign key(pid) references tb_goods(id),
		--����
		num int,
		--С��
		money decimal(13,2)
		)
--��������
create table tb_orderdetail(
		id int primary key,
		oid int foreign key(oid) references tb_order(id),
		pid int foreign key(pid) references tb_goods(id),
		num int,
		money decimal(13,2)
		)