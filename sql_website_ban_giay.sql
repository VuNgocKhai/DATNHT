create database website_ban_giay

use website_ban_giay

create table thuong_hieu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table dia_hinh (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table gioi_tinh (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table thoi_tiet_thich_hop (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table danh_muc (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table de_giay (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table chat_lieu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table cam_giac (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table do_cao_giay (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table mau_sac (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table giay (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	id_thuong_hieu uniqueidentifier,
	id_gioi_tinh uniqueidentifier,
	id_danh_muc uniqueidentifier,
	id_chat_lieu uniqueidentifier,
	id_cam_giac uniqueidentifier,
	id_dia_hinh uniqueidentifier,
	id_thoi_tiet_thich_hop uniqueidentifier,
	id_de_giay uniqueidentifier,
	id_do_cao_giay uniqueidentifier,
	id_mau_sac uniqueidentifier,
	mota nvarchar(255) null,
	gianhap decimal,
	giaban decimal,
	trangthai int null,
	foreign key (id_thuong_hieu) references thuong_hieu(id),
	foreign key (id_gioi_tinh) references gioi_tinh(id),
	foreign key (id_danh_muc) references danh_muc(id),
	foreign key (id_chat_lieu) references chat_lieu(id),
	foreign key (id_cam_giac) references cam_giac(id),
	foreign key (id_dia_hinh) references dia_hinh(id),
	foreign key (id_thoi_tiet_thich_hop) references thoi_tiet_thich_hop(id),
	foreign key (id_de_giay) references de_giay(id),
	foreign key (id_do_cao_giay) references do_cao_giay(id),
	foreign key (id_mau_sac) references mau_sac(id)
)
create table anh_giay (
	id uniqueidentifier primary key default newid(),
	ten_url varchar(20) unique,
	id_giay uniqueidentifier,
	trangthai int null,
	foreign key (id_giay) references giay(id)
)
create table kich_co (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table giay_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_giay uniqueidentifier,
	id_kich_co uniqueidentifier,
	so_luong_ton int default null,
	trangthai int null,
	foreign key (id_giay) references giay(id),
	foreign key (id_kich_co) references kich_co(id)
)
create table chuc_vu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)

select*from chuc_vu
INSERT INTO chuc_vu (ma, ten, trangthai)
VALUES
    ('CV01', 'Quản lý', 1),
    ('CV02', 'Nhân viên', 1);

create table nhan_vien (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ho_ten nvarchar(50) default null,
	ngay_sinh date null,
	dia_chi nvarchar(255) null,
	thanh_pho nvarchar(255) null,
	sdt nvarchar(15) null,
	email nvarchar(255) null,
	id_chuc_vu uniqueidentifier,
	mat_khau nvarchar(255) null,
	trangthai int null,
	foreign key (id_chuc_vu) references chuc_vu(id),
)

-- Thêm dữ liệu vào bảng nhan_vien
INSERT INTO nhan_vien (ma, ho_ten, ngay_sinh, dia_chi, thanh_pho, sdt, email, id_chuc_vu, mat_khau, trangthai)
VALUES
    ('NV001', 'Nguyễn Văn A', '1990-01-15', '123 Đường ABC', 'Hà Nội', '0123456789', 'nva@example.com', '63DD6DFF-AA3B-4F79-B22A-316E3B40258A', 'password123', 1),
    ('NV002', 'Trần Thị B', '1995-03-20', '456 Đường XYZ', 'Hồ Chí Minh', '0987654321', 'ttb@example.com', 'C2744FF7-9304-49A3-83BC-3D09BB8583E6', 'securepass', 1);


create table chuong_tring_giam_gia_san_pham (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	phan_tram_giam int,
	ngay_bat_dau date,
	ngay_ket_thuc date,
	id_nhan_vien_create uniqueidentifier,
	id_nhan_vien_update uniqueidentifier,
	trangthai int null,
	foreign key (id_nhan_vien_create) references nhan_vien(id),
	foreign key (id_nhan_vien_update) references nhan_vien(id)
)

delete from chuong_tring_giam_gia_san_pham

select * from chuong_tring_giam_gia_san_pham

create table chuong_trinh_giam_gia_chi_tiet_san_pham (
	id uniqueidentifier primary key default newid(),
	id_giay uniqueidentifier,
	id_chuong_trinh_giam_gia uniqueidentifier,
	so_tien_da_giam decimal,
	trangthai int null,
	foreign key (id_giay) references giay(id),
	foreign key (id_chuong_trinh_giam_gia) references chuong_tring_giam_gia_san_pham(id)
)



DROP TABLE khach_hang
create table khach_hang (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ho_ten nvarchar(50) default null,
	ngay_sinh date null,
	sdt nvarchar(15) null,
	email nvarchar(255) null,
	mat_khau nvarchar(255) null,
	trangthai int null,
)
select * from khach_hang

ALTER TABLE dia_chi
ADD ma nvarchar(50)
INSERT INTO khach_hang (ma, ho_ten, ngay_sinh, sdt, email, mat_khau, trangthai)
VALUES
('KH01', 'Nguyễn Văn A', '1970-01-01', '0912345678', 'nguyenvana@gmail.com', '123456', 1),
('KH02', 'Trần Ngọc B', '1975-02-02', '0923456789', 'tranngoc@gmail.com', '123456', 1),
('KH03', 'Đỗ Thị C', '1980-03-03', '0934567890', 'dothic@gmail.com', '123456', 1),
('KH04', 'Lê Minh D', '1985-04-04', '0945678901', 'leminhd@gmail.com', '123456', 1),
('KH05', 'Hồ Thị E', '1990-05-05', '0956789012', 'hothie@gmail.com', '123456', 1);


DROP TABLE dia_chi
create table dia_chi (
	id uniqueidentifier primary key default newid(),
	ma nvarchar(50) default null,
	id_khach_hang uniqueidentifier,
	ten_dia_chi nvarchar(250) default null,
	xa nvarchar(250) default null,
	huyen nvarchar(50) default null,
	thanh_pho nvarchar(50) default null,
	trangthai int null,
	foreign key (id_khach_hang) references khach_hang(id)
)
select * from dia_chi 



/*
create table dia_chi_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_dia_chi uniqueidentifier,
	id_khach_hang uniqueidentifier,
	trangthai int null,
	foreign key (id_dia_chi) references dia_chi(id),
	foreign key (id_khach_hang) references khach_hang(id)
)*/

drop table hoa_don
create table hoa_don (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ngay_tao date null,
	ngay_thanh_toan date null,
	id_nhan_vien uniqueidentifier,
	id_khach_hang uniqueidentifier,
	mo_ta nvarchar(255) null,
	tong_tien decimal,
	trangthai int null,
	foreign key (id_nhan_vien) references nhan_vien(id),
	foreign key (id_khach_hang) references khach_hang(id)
)

select * from hoa_don
-- Thêm dữ liệu vào bảng hoa_don
INSERT INTO hoa_don (ma, ngay_tao, ngay_thanh_toan, id_nhan_vien, id_khach_hang, mo_ta, tong_tien, trangthai)
VALUES
    ('HD01', '2023-09-01', '2023-09-05', 'F54FADD1-8A3E-4A83-8AED-6274D1C9729D', 'EA3BB83C-75FE-7947-8F0E-14C2BAF7DC44', 'Mua hàng tháng 9', 150.00, 1),
    ('HD02', '2023-09-02', '2023-09-06', '01FE0289-E23F-4DC0-9275-6A9B7B7C760A', '5A5E7947-DBE5-A440-AE2F-757CDED687C4', 'Mua hàng tháng 9', 200.00, 1),
    ('HD03', '2023-09-03', '2023-09-07', 'F54FADD1-8A3E-4A83-8AED-6274D1C9729D', 'EA3BB83C-75FE-7947-8F0E-14C2BAF7DC44', 'Mua hàng tháng 9', 250.00, 1),
    ('HD04', '2023-09-04', '2023-09-08', '01FE0289-E23F-4DC0-9275-6A9B7B7C760A', '88A8F46E-B604-F740-B412-78CD4BA17607', 'Mua hàng tháng 9', 180.00, 1),
    ('HD05', '2023-09-05', '2023-09-09', 'F54FADD1-8A3E-4A83-8AED-6274D1C9729D', '26C356A6-BB3F-1A4B-8353-EDF5705EC09F', 'Mua hàng tháng 9', 300.00, 1);


drop table hoa_don_chi_tiet
create table hoa_don_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_hoa_don uniqueidentifier,
	id_giay_chi_tiet uniqueidentifier,
	so_luong int,
	don_gia decimal,
	trangthai int null,
	foreign key (id_hoa_don) references hoa_don(id),
	foreign key (id_giay_chi_tiet) references giay(id)
)

drop table chuong_tring_giam_gia_hoa_don
create table chuong_tring_giam_gia_hoa_don (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	dieu_kien decimal default null, --gia hoa don nho nhat de duoc phep sd giam gia vd :100000vnd
	so_tien_giam decimal  default null,
	ngay_bat_dau date  default null,
	ngay_ket_thuc date  default null,
	so_luong int  default null,
	trangthai int null
)

drop table chuong_trinh_giam_gia_chi_tiet_hoa_don
create table chuong_trinh_giam_gia_chi_tiet_hoa_don (
	id uniqueidentifier primary key default newid(),
	id_hoa_don uniqueidentifier,
	id_chuong_tring_giam_gia_hoa_don uniqueidentifier,
	trangthai int null,
	foreign key (id_hoa_don) references hoa_don(id),
	foreign key (id_chuong_tring_giam_gia_hoa_don) references chuong_tring_giam_gia_hoa_don(id)
)
