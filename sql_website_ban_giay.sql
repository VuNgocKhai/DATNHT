create database website_ban_giay
go
use website_ban_giay
go
create table thuong_hieu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table dia_hinh (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table gioi_tinh (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table thoi_tiet_thich_hop (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table danh_muc (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table de_giay (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table chat_lieu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table cam_giac (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table do_cao_giay (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table mau_sac (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
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
go
create table anh_giay (
	id uniqueidentifier primary key default newid(),
	ten_url varchar(20) unique,
	id_giay uniqueidentifier,
	trangthai int null,
	foreign key (id_giay) references giay(id)
)
go
create table kich_co (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
create table giay_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_giay uniqueidentifier,
	id_kich_co uniqueidentifier,
	so_luong_ton int default null,
	trangthai int null,
	foreign key (id_giay) references giay(id),
	foreign key (id_kich_co) references kich_co(id)
)
go
create table chuc_vu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
go
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
go
create table chuong_trinh_giam_gia_san_pham (
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
go
create table chuong_trinh_giam_gia_chi_tiet_san_pham (
	id uniqueidentifier primary key default newid(),
	id_giay uniqueidentifier,
	id_chuong_trinh_giam_gia uniqueidentifier,
	so_tien_da_giam decimal,
	trangthai int null,
	foreign key (id_giay) references giay(id),
	foreign key (id_chuong_trinh_giam_gia) references chuong_trinh_giam_gia_san_pham(id)
)
go
create table dia_chi (
	id uniqueidentifier primary key default newid(),
	ten_dia_chi nvarchar(250) default null,
	huyen nvarchar(50) default null,
	thanh_pho nvarchar(50) default null,
	trangthai int null
)
go
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
go
create table dia_chi_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_dia_chi uniqueidentifier,
	id_khach_hang uniqueidentifier,
	trangthai int null,
	foreign key (id_dia_chi) references dia_chi(id),
	foreign key (id_khach_hang) references khach_hang(id)
)
go
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
go
create table hoa_don_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_hoa_don uniqueidentifier,
	id_giay_chi_tiet uniqueidentifier,
	so_luong int,
	don_gia decimal,
	trangthai int null,
	foreign key (id_hoa_don) references hoa_don(id),
	foreign key (id_giay_chi_tiet) references giay_chi_tiet(id)
)
go
create table chuong_trinh_giam_gia_hoa_don (
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
go
create table chuong_trinh_giam_gia_chi_tiet_hoa_don (
	id uniqueidentifier primary key default newid(),
	id_hoa_don uniqueidentifier,
	id_chuong_trinh_giam_gia_hoa_don uniqueidentifier,
	trangthai int null,
	foreign key (id_hoa_don) references hoa_don(id),
	foreign key (id_chuong_trinh_giam_gia_hoa_don) references chuong_trinh_giam_gia_hoa_don(id)
)
---- Bảng CHỨC VỤ
Select * from chuc_vu
	insert into chuc_vu(ma,ten,trangthai) values('CV1',N'Chức Vụ 1',0)

	insert into chuc_vu(ma,ten,trangthai) values('CV2',N'Chức Vụ 2',0)
---- Bảng NHÂN VIÊN
Select * from nhan_vien

	DECLARE @chuc_vu_id_1 uniqueidentifier;
	SET @chuc_vu_id_1 = (SELECT id FROM chuc_vu WHERE ma = 'CV1');
	INSERT INTO nhan_vien (ma, ho_ten, ngay_sinh, dia_chi, thanh_pho, sdt, email, id_chuc_vu, mat_khau, trangthai)
	VALUES ('NV001', N'Nguyễn Văn A', '1990-01-15', N'123 Đường ABC', N'TP HCM', '0123456789', 'nva@example.com', @chuc_vu_id_1, 'matkhau123', 1);

	DECLARE @chuc_vu_id_2 uniqueidentifier;
	SET @chuc_vu_id_2 = (SELECT id FROM chuc_vu WHERE ma = 'CV2');
	INSERT INTO nhan_vien (ma, ho_ten, ngay_sinh, dia_chi, thanh_pho, sdt, email, id_chuc_vu, mat_khau, trangthai)
	VALUES ('NV002', N'Trần Thị B', '1985-05-20', N'456 Đường XYZ', N'Hà Nội', '0987654321', 'ttb@example.com', @chuc_vu_id_2, 'password456', 1);

---- Bảng KHÁCH HÀNG
select * from khach_hang
	INSERT INTO khach_hang (ma, ho_ten, ngay_sinh, sdt, email, mat_khau, trangthai)
	VALUES ('KH001', N'Nguyễn Thị C', '1988-08-10', '0123456789', 'ntc@example.com', 'khachhang123', 1);

	INSERT INTO khach_hang (ma, ho_ten, ngay_sinh, sdt, email, mat_khau, trangthai)
	VALUES ('KH002', N'Lê Văn D', '1995-04-25', '0987654321', 'lvd@example.com', 'password789', 0);

---- Bảng HÓA ĐƠN
Select * from hoa_don
select * from nhan_vien
select * from khach_hang

	-- Lấy id của nhân viên từ bảng nhan_vien
	DECLARE @nhan_vien_id_1 uniqueidentifier;
	SET @nhan_vien_id_1 = (SELECT TOP 1 id FROM nhan_vien);

	DECLARE @nhan_vien_id_2 uniqueidentifier;
	SET @nhan_vien_id_2 = (SELECT TOP 1 id FROM nhan_vien WHERE id != @nhan_vien_id_1);

	-- Lấy id của khách hàng từ bảng khach_hang
	DECLARE @khach_hang_id_1 uniqueidentifier;
	SET @khach_hang_id_1 = (SELECT TOP 1 id FROM khach_hang);

	DECLARE @khach_hang_id_2 uniqueidentifier;
	SET @khach_hang_id_2 = (SELECT TOP 1 id FROM khach_hang WHERE id != @khach_hang_id_1);

	

	-- Chèn bản ghi 1
	INSERT INTO hoa_don (ma, ngay_tao, ngay_thanh_toan, id_nhan_vien, id_khach_hang, mo_ta, tong_tien, trangthai)
	VALUES ('HD001', '2023-08-10', '2023-08-15', @nhan_vien_id_1, @khach_hang_id_1, N'Hóa đơn số 1', 1500000, 1);

	-- Chèn bản ghi 2
	INSERT INTO hoa_don (ma, ngay_tao, ngay_thanh_toan, id_nhan_vien, id_khach_hang, mo_ta, tong_tien, trangthai)
	VALUES ('HD002', '2023-08-12', '2023-08-16', @nhan_vien_id_2, @khach_hang_id_2, N'Hóa đơn số 2', 2200000, 0);

	-- Chèn bản ghi 3
	INSERT INTO hoa_don (ma, ngay_tao, ngay_thanh_toan, id_nhan_vien, id_khach_hang, mo_ta, tong_tien, trangthai)
	VALUES ('HD003', '2023-08-12', '2023-08-16', @nhan_vien_id_2, @khach_hang_id_2, N'Hóa đơn số 2', 2200000, 0);


---- Bảng KÍCH CỠ
Select * from kich_co
	-- Chèn bản ghi 1
	INSERT INTO kich_co (ma, ten, trangthai)
	VALUES ('KC001', N'Kích Cỡ 1', 1);

	-- Chèn bản ghi 2
	INSERT INTO kich_co (ma, ten, trangthai)
	VALUES ('KC002', N'Kích Cỡ 2', 0);

---- Bảng THƯƠNG HIỆU
	INSERT INTO thuong_hieu (ma, ten, trangthai)
	VALUES ('TH001', N'Thương Hiệu 1', 1);

	INSERT INTO thuong_hieu (ma, ten, trangthai)
	VALUES ('TH002', N'Thương Hiệu 2', 1);

---- Bảng ĐIẠ HÌNH
	INSERT INTO dia_hinh (ma, ten, trangthai)
	VALUES ('DH001', N'Địa Hình 1', 1);

	INSERT INTO dia_hinh (ma, ten, trangthai)
	VALUES ('DH002', N'Địa Hình 2', 1);

---- Bảng GIỚI TÍNH
	INSERT INTO gioi_tinh (ma, ten, trangthai)
	VALUES ('GT001', N'Giới Tính 1', 1);

	INSERT INTO gioi_tinh (ma, ten, trangthai)
	VALUES ('GT002', N'Giới Tính 2', 1);

---- Bảng THỜI TIẾT THÍCH HỢP
	INSERT INTO thoi_tiet_thich_hop (ma, ten, trangthai)
	VALUES ('TT001', N'Thời Tiết Thích Hợp 1', 1);

	INSERT INTO thoi_tiet_thich_hop (ma, ten, trangthai)
	VALUES ('TT002', N'Thời Tiết Thích Hợp 2', 1);

---- Bảng DANH MỤC
	INSERT INTO danh_muc (ma, ten, trangthai)
	VALUES ('DM001', N'Danh Mục 1', 1);

	INSERT INTO danh_muc (ma, ten, trangthai)
	VALUES ('DM002', N'Danh Mục 2', 1);

---- Bảng ĐẾ GIÀY
	INSERT INTO de_giay (ma, ten, trangthai)
	VALUES ('DG001', N'Dế Giày 1', 1);

	INSERT INTO de_giay (ma, ten, trangthai)
	VALUES ('DG002', N'Dế Giày 2', 1);

---- Bảng CHẤT LIỆU
	INSERT INTO chat_lieu (ma, ten, trangthai)
	VALUES ('CL001', N'Chất Liệu 1', 1);

	INSERT INTO chat_lieu (ma, ten, trangthai)
	VALUES ('CL002', N'Chất Liệu 2', 1);

---- Bảng CẢM GIÁC
	INSERT INTO cam_giac (ma, ten, trangthai)
	VALUES ('CG001', N'Cảm Giác 1', 1);

	INSERT INTO cam_giac (ma, ten, trangthai)
	VALUES ('CG002', N'Cảm Giác 2', 1);

---- Bảng ĐỘ CAO GIÀY
	INSERT INTO do_cao_giay (ma, ten, trangthai)
	VALUES ('DCG001', N'Dộ Cao Giày 1', 1);

	INSERT INTO do_cao_giay (ma, ten, trangthai)
	VALUES ('DCG002', N'Dộ Cao Giày 2', 1);

---- Bảng MÀU SẮC
	INSERT INTO mau_sac (ma, ten, trangthai)
	VALUES ('MS001', N'Màu Sắc 1', 1);

	INSERT INTO mau_sac (ma, ten, trangthai)
	VALUES ('MS002', N'Màu Sắc 2', 1);

---- Bảng GIÀY
Select * from giay
	-- Lấy id từ các bảng tham chiếu
	DECLARE @thuong_hieu_id uniqueidentifier;
	SET @thuong_hieu_id = (SELECT TOP 1 id FROM thuong_hieu);

	DECLARE @gioi_tinh_id uniqueidentifier;
	SET @gioi_tinh_id = (SELECT TOP 1 id FROM gioi_tinh);

	DECLARE @danh_muc_id uniqueidentifier;
	SET @danh_muc_id = (SELECT TOP 1 id FROM danh_muc);

	DECLARE @chat_lieu_id uniqueidentifier;
	SET @chat_lieu_id = (SELECT TOP 1 id FROM chat_lieu);

	DECLARE @cam_giac_id uniqueidentifier;
	SET @cam_giac_id = (SELECT TOP 1 id FROM cam_giac);

	DECLARE @dia_hinh_id uniqueidentifier;
	SET @dia_hinh_id = (SELECT TOP 1 id FROM dia_hinh);

	DECLARE @thoi_tiet_thich_hop_id uniqueidentifier;
	SET @thoi_tiet_thich_hop_id = (SELECT TOP 1 id FROM thoi_tiet_thich_hop);

	DECLARE @de_giay_id uniqueidentifier;
	SET @de_giay_id = (SELECT TOP 1 id FROM de_giay);

	DECLARE @do_cao_giay_id uniqueidentifier;
	SET @do_cao_giay_id = (SELECT TOP 1 id FROM do_cao_giay);

	DECLARE @mau_sac_id uniqueidentifier;
	SET @mau_sac_id = (SELECT TOP 1 id FROM mau_sac);

	-- Chèn bản ghi 1
	INSERT INTO giay (ma, ten, id_thuong_hieu, id_gioi_tinh, id_danh_muc, id_chat_lieu, id_cam_giac, id_dia_hinh, id_thoi_tiet_thich_hop, id_de_giay, id_do_cao_giay, id_mau_sac, mota, gianhap, giaban, trangthai)
	VALUES ('G001', N'Giày Thể Thao 1', @thuong_hieu_id, @gioi_tinh_id, @danh_muc_id, @chat_lieu_id, @cam_giac_id, @dia_hinh_id, @thoi_tiet_thich_hop_id, @de_giay_id, @do_cao_giay_id, @mau_sac_id, N'Giày thể thao cao cấp', 100000, 200000,1);

	-- Chèn bản ghi 2
	INSERT INTO giay (ma, ten, id_thuong_hieu, id_gioi_tinh, id_danh_muc, id_chat_lieu, id_cam_giac, id_dia_hinh, id_thoi_tiet_thich_hop, id_de_giay, id_do_cao_giay, id_mau_sac, mota, gianhap, giaban, trangthai)
	VALUES ('G002', N'Giày Đi Học 1', @thuong_hieu_id, @gioi_tinh_id, @danh_muc_id, @chat_lieu_id, @cam_giac_id, @dia_hinh_id, @thoi_tiet_thich_hop_id, @de_giay_id, @do_cao_giay_id, @mau_sac_id, N'Giày đi học thoải mái', 80000, 150000, 0);

---- Bảng GIÀY CHI TIẾT
-- Lấy id từ các bảng tham chiếu
Select * from giay_chi_tiet
	DECLARE @giay_id uniqueidentifier;
	SET @giay_id = (SELECT TOP 1 id FROM giay);

	DECLARE @kich_co_id uniqueidentifier;
	SET @kich_co_id = (SELECT TOP 1 id FROM kich_co);

	-- Chèn bản ghi 1
	INSERT INTO giay_chi_tiet (id_giay, id_kich_co, so_luong_ton, trangthai)
	VALUES (@giay_id, @kich_co_id, 100, 1);

	-- Chèn bản ghi 2
	INSERT INTO giay_chi_tiet (id_giay, id_kich_co, so_luong_ton, trangthai)
	VALUES (@giay_id, @kich_co_id, 50, 1);

---- Bảng HÓA ĐƠN CHI TIẾT
Select * from hoa_don_chi_tiet
	-- Lấy id từ các bảng tham chiếu
	DECLARE @hoa_don_id uniqueidentifier;
	SET @hoa_don_id = (SELECT TOP 1 id FROM hoa_don);

	DECLARE @giay_chi_tiet_id uniqueidentifier;
	SET @giay_chi_tiet_id = (SELECT TOP 1 id FROM giay_chi_tiet);

	-- Chèn bản ghi 1
	INSERT INTO hoa_don_chi_tiet (id_hoa_don, id_giay_chi_tiet, so_luong, don_gia, trangthai)
	VALUES (@hoa_don_id, @giay_chi_tiet_id, 2, 180000, 1);

	-- Chèn bản ghi 2
	-- Lấy id từ bảng giay_chi_tiet cho bản ghi thứ 2
	SET @giay_chi_tiet_id = (SELECT TOP 1 id FROM giay_chi_tiet WHERE id != @giay_chi_tiet_id);

	INSERT INTO hoa_don_chi_tiet (id_hoa_don, id_giay_chi_tiet, so_luong, don_gia, trangthai)
	VALUES (@hoa_don_id, @giay_chi_tiet_id, 3, 120000, 1);

Select * from danh_muc
---- Bảng Giảm Giá Hóa Đơn
select * from chuong_trinh_giam_gia_hoa_don
	-- Chèn bản ghi 1
	INSERT INTO chuong_trinh_giam_gia_hoa_don (ma, ten, dieu_kien, so_tien_giam, ngay_bat_dau, ngay_ket_thuc, so_luong, trangthai)
	VALUES ('GG001', N'Chương trình A', 100000, 50000, '2023-08-01', '2023-08-15', 10, 1);

	-- Chèn bản ghi 2
	INSERT INTO chuong_trinh_giam_gia_hoa_don (ma, ten, dieu_kien, so_tien_giam, ngay_bat_dau, ngay_ket_thuc, so_luong, trangthai)
	VALUES ('GG002', N'Chương trình B', 150000, 75000, '2023-09-01', '2023-09-30', 20, 0);

		INSERT INTO chuong_trinh_giam_gia_hoa_don (ma, ten, dieu_kien, so_tien_giam, ngay_bat_dau, ngay_ket_thuc, so_luong, trangthai)
	VALUES ('GG006', N'Chương trình B', 150000, 75000, '2023-09-01', '2023-09-30', 20, 0);
		INSERT INTO chuong_trinh_giam_gia_hoa_don (ma, ten, dieu_kien, so_tien_giam, ngay_bat_dau, ngay_ket_thuc, so_luong, trangthai)
	VALUES ('GG007', N'Chương trình B', 150000, 75000, '2023-09-01', '2023-09-30', 20, 0);
		INSERT INTO chuong_trinh_giam_gia_hoa_don (ma, ten, dieu_kien, so_tien_giam, ngay_bat_dau, ngay_ket_thuc, so_luong, trangthai)
	VALUES ('GG008', N'Chương trình B', 150000, 75000, '2023-09-01', '2023-09-30', 20, 0);

---- Bảng Giảm Giá Chi Tiết Hóa Đơn
select * from chuong_trinh_giam_gia_chi_tiet_hoa_don
select * from hoa_don
select * from chuong_trinh_giam_gia_hoa_don
delete from chuong_trinh_giam_gia_chi_tiet_hoa_don where id ='4D9E8165-48F7-4FDC-8FED-553B338C2F20'
insert into chuong_trinh_giam_gia_chi_tiet_hoa_don(id_hoa_don,id_chuong_trinh_giam_gia_hoa_don,trangthai)
values ('EE086990-69ED-4DB2-81A8-0A6748D1D258','36D3A32F-CD2F-4347-9C55-B17F323DDA73',1)

SELECT    dbo.chuong_trinh_giam_gia_chi_tiet_hoa_don.id, dbo.chuong_trinh_giam_gia_hoa_don.ten, dbo.hoa_don.ma
FROM         dbo.chuong_trinh_giam_gia_chi_tiet_hoa_don INNER JOIN
                      dbo.chuong_trinh_giam_gia_hoa_don ON dbo.chuong_trinh_giam_gia_chi_tiet_hoa_don.id_chuong_trinh_giam_gia_hoa_don = dbo.chuong_trinh_giam_gia_hoa_don.id INNER JOIN
                      dbo.hoa_don ON dbo.chuong_trinh_giam_gia_chi_tiet_hoa_don.id_hoa_don = dbo.hoa_don.id
