﻿drop database website_ban_giay
create database website_ban_giay

use website_ban_giay
create table thuong_hieu (
	id uniqueidentifier primary key default newid(),
	ten_url varchar(20) default null,
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
create table de_giay (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table xuat_xu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table kieu_dang (
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
	id_chat_lieu uniqueidentifier,
	id_de_giay uniqueidentifier,
	id_mau_sac uniqueidentifier,
	id_xuat_xu uniqueidentifier,
	id_kieu_dang uniqueidentifier,
	mota nvarchar(255) null,
	gianhap decimal,
	giaban decimal,
	trangthai int null,
	gia_sau_khuyen_mai decimal,
	ngay_nhap date,
	do_hot int null,
	foreign key (id_thuong_hieu) references thuong_hieu(id),
	foreign key (id_gioi_tinh) references gioi_tinh(id),
	foreign key (id_chat_lieu) references chat_lieu(id),
	foreign key (id_de_giay) references de_giay(id),
	foreign key (id_xuat_xu) references xuat_xu(id),
	foreign key (id_kieu_dang) references kieu_dang(id),
	foreign key (id_mau_sac) references mau_sac(id)
)

create table danh_gia(
	id uniqueidentifier primary key default newid(),
	id_khach_hang uniqueidentifier not null,
	id_giay uniqueidentifier not null,
	sao int,
	noi_dung nvarchar(max),
	trang_thai int,
	foreign key (id_khach_hang) references khach_hang(id),
	foreign key (id_giay) references giay(id)
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
create table nhan_vien (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ho_ten nvarchar(50) default null,
	ngay_sinh date null,
	dia_chi nvarchar(255) null,
	xa nvarchar(250) default null,
	huyen nvarchar(50) default null,
	thanh_pho nvarchar(255) null,
	sdt nvarchar(15) null,
	email nvarchar(255) null,
	id_chuc_vu uniqueidentifier,
	mat_khau nvarchar(255) null,
	ngay_vao_lam date null,
	ngay_nghi_viec date null,
	trangthai int null,
	foreign key (id_chuc_vu) references chuc_vu(id),
)
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
create table chuong_trinh_giam_gia_chi_tiet_san_pham (
	id uniqueidentifier primary key default newid(),
	id_giay uniqueidentifier,
	id_chuong_trinh_giam_gia uniqueidentifier,
	so_tien_da_giam decimal,
	trangthai int null,
	foreign key (id_giay) references giay(id),
	foreign key (id_chuong_trinh_giam_gia) references chuong_tring_giam_gia_san_pham(id)
)
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
create table dia_chi (
	id uniqueidentifier primary key default newid(),
	ma nvarchar(50) default null,
	id_khach_hang uniqueidentifier,
	ten_dia_chi nvarchar(250) default null,
	ten_nguoi_nhan nvarchar(250) default null,
	sdt_nguoi_nhan nvarchar(250) default null,
	xa nvarchar(250) default null,
	huyen nvarchar(50) default null,
	thanh_pho nvarchar(50) default null,
	trangthai int null,
	foreign key (id_khach_hang) references khach_hang(id)
)
create table gio_hang (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	id_khach_hang uniqueidentifier null,
	ngay_tao date null,
	ngay_cap_nhap date null,
	ghi_chu nvarchar(255) null,
	trangthai int null,
	foreign key (id_khach_hang) references khach_hang(id)
)
create table gio_hang_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_gio_hang uniqueidentifier null,
	id_giay_chi_tiet uniqueidentifier null,
	so_luong int null,
	ghi_chu nvarchar(255) null,
	trangthai int null,
	foreign key (id_gio_hang) references gio_hang(id),
	foreign key (id_giay_chi_tiet) references giay_chi_tiet(id)
)
create table hoa_don (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ngay_tao date null,
	ngay_thanh_toan date null,
	id_nhan_vien uniqueidentifier,
	id_khach_hang uniqueidentifier,
	mo_ta nvarchar(255) null,
	ten_nguoi_nhan nvarchar(255) null,
	sdt_nguoi_nhan nvarchar(255) null,
	dia_chi nvarchar(255) null,
	tong_tien decimal,
	hinh_thuc_mua int,
	hinh_thuc_thanh_toan int,
	so_tien_giam money,
	phi_ship money,
	trangthai int null,
	foreign key (id_nhan_vien) references nhan_vien(id),
	foreign key (id_khach_hang) references khach_hang(id)
)
create table hoa_don_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_hoa_don uniqueidentifier,
	id_giay_chi_tiet uniqueidentifier,
	so_luong int,
	gia_nhap decimal,
	don_gia decimal,
	trangthai int null,
	foreign key (id_hoa_don) references hoa_don(id),
	foreign key (id_giay_chi_tiet) references giay_chi_tiet(id)
)
create table chuong_trinh_giam_gia_hoa_don (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	dieu_kien decimal default null, --gia hoa don nho nhat de duoc phep sd giam gia vd :100000vnd
	so_tien_giam_max decimal  default null,
	ngay_bat_dau date  default null,
	ngay_ket_thuc date  default null,
	phan_tram_giam int  default null,
	so_luong int  default null,
	trangthai int null
)
create table chuong_trinh_giam_gia_chi_tiet_hoa_don (
	id uniqueidentifier primary key default newid(),
	id_hoa_don uniqueidentifier,
	id_chuong_trinh_giam_gia_hoa_don uniqueidentifier,
	tong_tien decimal  default null,
	so_tien_da_giam decimal  default null,
	tong_tien_thanh_toan decimal  default null,
	trangthai int null,
	foreign key (id_hoa_don) references hoa_don(id),
	foreign key (id_chuong_trinh_giam_gia_hoa_don) references chuong_trinh_giam_gia_hoa_don(id)
)
select * from khach_hang
INSERT INTO website_ban_giay.dbo.thuong_hieu (id,ma,ten,trangthai) VALUES
	 (N'56DA1831-7647-446A-A150-0CF3DF82A9F2',N'TH01',N'Dior',1),
	 (N'68546213-C563-4273-A10B-762A9C8A1BD1',N'TH02',N'Versace',1),
	 (N'CE37EF88-5AE4-43E0-AC6C-AB88EEB227CD',N'TH03',N'MLB',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH04',N'Adidas',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH05',N'Chanel',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH06',N'Gucci',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH07',N'Calvin Klein',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH08',N'YSL',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH09',N'Charles & Keith',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH10',N'Louis Vuitton',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH11',N'Balenciaga',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH12',N'Supreme',1),
INSERT INTO website_ban_giay.dbo.xuat_xu (id,ma,ten,trangthai) VALUES
	 (N'0CB15488-6B50-455D-8D50-61AC7A060960',N'DH03',N'Mỹ',1),
	 (N'AD7BA4E3-9DA9-49D8-A5B5-8063A6C3C0B8',N'DH04',N'Pháp',1),
	 (N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'DH02',N'Hàn Quốc',1),
	 (N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'DH02',N'Đức',1),
	 (N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'DH02',N'Singapore',1),
	 (N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'DH02',N'Anh',1),
	 (N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'DH02',N'Nhật Bản',1),
	 (N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'DH02',N'Việt Nam',1),
	 (N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'DH02',N'Tây Ban Nha',1),
	 (N'ED504C4E-664F-46C1-9B2B-ADE4082C0D47',N'DH01',N'Thụy Sỹ',1);
INSERT INTO website_ban_giay.dbo.chat_lieu (id,ma,ten,trangthai) VALUES
	 (N'5A6F129E-A597-4A23-8967-030A746FDB56',N'GT03',N'Da Cao Cấp',1),
	 (N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'GT02',N'100% Cotton',1),
	 (N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'GT02',N'Da Nhân Tạo',1),
	 (N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'GT02',N'Cao Su',1),
	 (N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'GT02',N'Canvas',1),
	 (N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'GT02',N'Da PU',1),
	 (N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'GT02',N'Da bê',1),
	 (N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'GT01',N'Polyester',1);
INSERT INTO website_ban_giay.dbo.gioi_tinh (id,ma,ten,trangthai) VALUES
	 (N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'DG02',N'Nam',1),
	 (N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'DG02',N'Trẻ Em',1),
	 (N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'DG02',N'Unisex',1),
	 (N'CE93E2F3-1008-40FA-83FE-8721E66DA4E6',N'DG01',N'Nữ',1);
INSERT INTO website_ban_giay.dbo.mau_sac (id,ma,ten,trangthai) VALUES
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Đen',1),
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Trắng',1),
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Nâu',1),
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Xanh navy',1),
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Bạc',1),
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Xám',1),
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Hồng',1),
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Hồng Nhạt',1),
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Xanh Blue',1),
	 (N'2903C8AD-D127-4BBF-B737-47867834EFE0',N'MS01',N'Đỏ Gạch',1);
INSERT INTO website_ban_giay.dbo.cam_giac (id,ma,ten,trangthai) VALUES
	 (N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'CG01',N'Thoải Mái',1),
	 (N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'CG02',N'Ôm chân',1);
INSERT INTO website_ban_giay.dbo.chat_lieu (id,ma,ten,trangthai) VALUES
	 (N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'CL01',N'Vải Thiều',1),
	 (N'B7CDAA3D-2A1E-4511-ACE6-EEF38BEA82BC',N'CL02',N'Vải Tơ',1);
INSERT INTO website_ban_giay.dbo.giay (id,ma,ten,id_thuong_hieu,id_gioi_tinh,id_danh_muc,id_chat_lieu,id_cam_giac,id_dia_hinh,id_thoi_tiet_thich_hop,id_de_giay,id_do_cao_giay,id_mau_sac,mota,gianhap,giaban,trangthai,gia_sau_khuyen_mai,ngay_nhap,do_hot) VALUES
	 (N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'SP01',N'Giày Balenciaga 01',N'56DA1831-7647-446A-A150-0CF3DF82A9F2',N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'17E562BE-B3C9-42C3-84A6-C8F47A393A71',N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'ED504C4E-664F-46C1-9B2B-ADE4082C0D47',N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'173D9356-A26B-498E-9D03-3F4A4C5239F6',N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'Giày Đẹp Lắm',100000,200000,1,200000,N'2023-12-23',1),
	 (N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'SP02',N'Giày Balenciaga 02',N'68546213-C563-4273-A10B-762A9C8A1BD1',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'B7CDAA3D-2A1E-4511-ACE6-EEF38BEA82BC',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'91A0C9F9-DD97-4F2E-A8C7-BEF58E70E2E0',N'CE93E2F3-1008-40FA-83FE-8721E66DA4E6',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'2903C8AD-D127-4BBF-B737-47867834EFE0',N'Giày Mới Lắm',200000,400000,1,400000,N'2023-12-23',1);
INSERT INTO website_ban_giay.dbo.kich_co (id,ma,ten,trangthai) VALUES
	 (N'F575F6DE-14B2-4F24-A159-E71280C3F033',N'KC01',N'38',1),
	 (N'A97F40B0-AFCE-4C0A-BF45-F1B45FD6B472',N'KC02',N'39',1);
INSERT INTO website_ban_giay.dbo.anh_giay (id,ten_url,id_giay,trangthai) VALUES
	 (N'65EB4C89-4CF3-47E4-8D08-229F5F264C99',N'anhsp2.jpeg',N'BC03F312-454A-48DA-A318-80D1E3979AD1',1),
	 (N'94278920-EDB0-4455-84B4-5D2C474CA7DE',N'anhsp4.jpeg',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',1),
	 (N'508934CD-E5DC-4E44-9E30-BA4BFF8B297D',N'anhsp3.jpeg',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',1),
	 (N'4300D49F-684B-42F9-ABA1-E83AD892D59E',N'anhsp1.jpeg',N'BC03F312-454A-48DA-A318-80D1E3979AD1',1);
INSERT INTO website_ban_giay.dbo.giay_chi_tiet (id,id_giay,id_kich_co,so_luong_ton,trangthai) VALUES
<<<<<<< HEAD
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'F575F6DE-14B2-4F24-A159-E71280C3F033',10,1),
	 (N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'F575F6DE-14B2-4F24-A159-E71280C3F033',10,1),
	 (N'CD1D3426-38E1-4BE3-BF90-8B83EEEE5676',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'A97F40B0-AFCE-4C0A-BF45-F1B45FD6B472',10,1);
select * from giay
=======
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'F575F6DE-14B2-4F24-A159-E71280C3F033',100,1),
	 (N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'F575F6DE-14B2-4F24-A159-E71280C3F033',100,1),
	 (N'CD1D3426-38E1-4BE3-BF90-8B83EEEE5676',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'A97F40B0-AFCE-4C0A-BF45-F1B45FD6B472',100,1);
>>>>>>> origin/gghd-contact
INSERT INTO website_ban_giay.dbo.chuc_vu (id,ma,ten,trangthai) VALUES
	 (N'7B52F69D-5508-4745-926F-A22F9907E523',N'CV02',N'USER',1),
	 (N'F09E9132-7F9E-48BD-A30C-E58C9E16717E',N'CV01',N'ADMIN',1);
INSERT INTO website_ban_giay.dbo.nhan_vien (id,ma,ho_ten,ngay_sinh,dia_chi,xa,huyen,thanh_pho,sdt,email,id_chuc_vu,mat_khau,ngay_vao_lam,ngay_nghi_viec,trangthai) VALUES
	 (N'9D8372B8-AAA4-464A-A938-3C6927210010',N'NV01',N'Nguyễn Văn Đạt','2003-12-12',N'Cấn Hữu-Quốc Oai-Hà Nội',NULL,NULL,NULL,N'0385090080',N'danhnt@gmail.com',N'7B52F69D-5508-4745-926F-A22F9907E523',N'1234567','2020-12-12',NULL,1),
	 (N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'NV02',N'Hoàng Đại Ka','2003-09-09',N'Cấn Hữu-Quốc Oai 2-Hà Nội',NULL,NULL,NULL,N'0385090080',N'danhnt2@gmail.com',N'F09E9132-7F9E-48BD-A30C-E58C9E16717E',N'1234567','2023-09-12',NULL,1);
INSERT INTO website_ban_giay.dbo.khach_hang (id,ma,ho_ten,ngay_sinh,sdt,email,mat_khau,trangthai) VALUES
	 (N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'KH01',N'Nguyễn Danh','2003-09-09',N'0385090080',N'danhnt@gmail.com',N'123456',1),
	 (N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'KH02',N'Nguyễn Đại','2003-08-08',N'0387090080',N'danhng@gmail.com',N'123457',1),
	 (N'22B427F5-1E52-402F-B5DC-EC17AD5F139A',N'KH03',N'Đào Gia Phong','2003-09-16',N'0385370656',N'phong@gmail.com',N'1',1)
	-- địa chỉ khách hàng chỉ có 1 trạng thái mặc định là trạng thái = 1 , các trạng thái còn lại phải = 0
update khach_hang set mat_khau=N'$2a$10$6xUrgawUAXvPCohIWbL9e.lD8LKI1.ZyeZyo8hKRzQqlqVJ3CpBtq'
INSERT INTO website_ban_giay.dbo.dia_chi (id,ma,id_khach_hang,ten_dia_chi,ten_nguoi_nhan,sdt_nguoi_nhan,xa,huyen,thanh_pho,trangthai) VALUES
	 ('1096266E-2B53-4987-8945-3DE015BB737E',N'DC01',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'Xóm 1 Triều Đông',N'Nguyễn Thành Danh',N'0385090080',N'Xã Tân Minh',N'Huyện Thường Tín',N'Thành phố Hà Nội',1),
	 ('8B214DA0-AB58-4113-B9F8-359C501253CC',N'DC02',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'Xóm 2 Triều Đông',N'Nguyễn Thành Danh',N'0385090081',N'Xã Nhị Khê',N'Huyện Thường Tín',N'Thành phố Hà Nội',0),
	 ('458916FB-C823-43F7-BC01-81252C9F4023',N'DC03',N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm 1 La Uyên',N'Nguyễn Đại',N'0385090082',N'Xã Nguyễn Trãi',N'Huyện Thường Tín',N'Hà Nội',1),
	 ('B193CF3C-EBCB-4775-AFA0-358760461311',N'DC04',N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm 2 Lộc Dư',N'Nguyễn Đại',N'0385090080',N'Xã Hòa Bình',N'Huyện Thường Tín',N'Thành phố Hà Nội',0),
	 ('4B2B8E2C-9ACD-4768-8D2E-5F319B58CB5E',N'DC05',N'22B427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm bến',N'Đào Gia Phong',N'0385370656',N'Xã Hiền Giang',N'Huyện Thường Tín',N'Thành phố Hà Nội',1),
	 ('306CFDDC-9ECA-4371-8B09-E9CB33C46D89',N'DC06',N'22B427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm 3 Thọ Giáo',N'Đào Gia Phong',N'0385370656',N'Xã Liên Phương',N'Huyện Thường Tín',N'Thành phố Hà Nội',0)
INSERT INTO website_ban_giay.dbo.gio_hang (id,ma,id_khach_hang,ngay_tao,ngay_cap_nhap,ghi_chu,trangthai) VALUES
	 (N'5E83CE5E-CEEF-484F-BE89-021B0464CC8B',N'GH02',N'019427F5-1E52-402F-B5DC-EC17AD5F139A','2023-09-09',NULL,NULL,1),
	 (N'CE792713-9983-4E45-9D9B-532A003CC015',N'GH01',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843','2023-09-09',NULL,NULL,1);
INSERT INTO website_ban_giay.dbo.gio_hang_chi_tiet (id,id_gio_hang,id_giay_chi_tiet,so_luong,ghi_chu,trangthai) VALUES
	 (N'092052B5-324D-4DB4-94FE-46C8A159236C',N'CE792713-9983-4E45-9D9B-532A003CC015',N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',1,NULL,1),
	 (N'93A2B0C6-19E4-4403-B33A-4D6BDF05AE32',N'5E83CE5E-CEEF-484F-BE89-021B0464CC8B',N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',1,NULL,1);

/* Tạm thời chưa cần insert hóa đơn , tự tạo trên project*/
/*
INSERT INTO website_ban_giay.dbo.hoa_don (id,ma,ngay_tao,ngay_thanh_toan,id_nhan_vien,id_khach_hang,mo_ta,tong_tien,ten_nguoi_nhan,sdt_nguoi_nhan,trangthai) VALUES
	 (N'3CAD650A-4B71-41FB-8FA4-6682B25F6541',N'HD01','2023-09-09','2023-09-09',N'9D8372B8-AAA4-464A-A938-3C6927210010',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'Đã Thanh Toán',111111,N'Nguyễn Thành Danh',N'0385090080',1),
	 (N'DEC073F7-5FBB-4E5F-9766-AFAC0B275AEB',N'HD02','2023-09-09','2023-09-09',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'Đã Thanh Toán',111111,N'Nguyễn Thành Danh1',N'0385090081',1);
INSERT INTO website_ban_giay.dbo.hoa_don_chi_tiet (id,id_hoa_don,id_giay_chi_tiet,so_luong,gia_nhap,don_gia,trangthai) VALUES
	 (N'D284FD17-641F-463C-8AFD-48390823FFD2',N'3CAD650A-4B71-41FB-8FA4-6682B25F6541',N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',10,1000,10000,1),
	 (N'E3F66A89-7C7F-4FD4-A94E-F0D3275E4960',N'DEC073F7-5FBB-4E5F-9766-AFAC0B275AEB',N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',10,2000,20000,1);
	 */

INSERT INTO website_ban_giay.dbo.chuong_trinh_giam_gia_hoa_don(id,ma,ten,dieu_kien,so_tien_giam_max,ngay_bat_dau,ngay_ket_thuc,phan_tram_giam,so_luong,trangthai) VALUES
	 (N'AA43BE03-0834-4FCB-8AB1-0680CE0FA164',N'CTGGHD02',N'GGHD02',2000,200,'2023-09-09','2023-12-12',10,12,1),
	 (N'0DD0562F-83BC-406A-AB12-22C9FFE7A8AE',N'CTGGHD01',N'GGHD01',1000,500,'2023-09-09','2023-12-12',8,12,1);
INSERT INTO website_ban_giay.dbo.chuong_trinh_giam_gia_chi_tiet_hoa_don (id,id_hoa_don,id_chuong_trinh_giam_gia_hoa_don,tong_tien,so_tien_da_giam,tong_tien_thanh_toan,trangthai) VALUES
	 (N'02A1DEF4-6E76-4841-8944-ABD9CB120118',N'3CAD650A-4B71-41FB-8FA4-6682B25F6541',N'AA43BE03-0834-4FCB-8AB1-0680CE0FA164',111111,111,111000,1),
	 (N'0570A63A-C380-4722-B8E4-C2DFAD029CFF',N'DEC073F7-5FBB-4E5F-9766-AFAC0B275AEB',N'0DD0562F-83BC-406A-AB12-22C9FFE7A8AE',211111,111,211000,1);
INSERT INTO website_ban_giay.dbo.chuong_tring_giam_gia_san_pham (id,ma,ten,phan_tram_giam,ngay_bat_dau,ngay_ket_thuc,id_nhan_vien_create,id_nhan_vien_update,trangthai) VALUES
	 (N'13090853-794B-4746-BA0D-006172DD4976',N'GGSP02',N'Mừng Tết',20,'2023-12-12','2023-12-12',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'9D8372B8-AAA4-464A-A938-3C6927210010',1),
	 (N'5A9AFEF6-1ADC-43AE-A5DD-E160754913CA',N'GGSP01',N'Mừng Ngày Quốc Khánh',10,'2023-12-12','2023-12-12',N'9D8372B8-AAA4-464A-A938-3C6927210010',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',1);
INSERT INTO website_ban_giay.dbo.chuong_trinh_giam_gia_chi_tiet_san_pham (id,id_giay,id_chuong_trinh_giam_gia,so_tien_da_giam,trangthai) VALUES
	 (N'2D2A2541-2D46-425A-835D-578E7F92EA26',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'13090853-794B-4746-BA0D-006172DD4976',100,1),
	 (N'A70E75F8-2662-41F3-96EE-8F4D4A1CA41E',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'5A9AFEF6-1ADC-43AE-A5DD-E160754913CA',200,1);

	 /*
	 --- CẬP NHẬT MẬT KHẨU MÃ HÓA CHO NHÂN VIÊN VÀ KHÁCH HÀNG
	update nhan_vien set mat_khau=N'$2a$10$6xUrgawUAXvPCohIWbL9e.lD8LKI1.ZyeZyo8hKRzQqlqVJ3CpBtq' where ma = 'NV02'
	update khach_hang set mat_khau=N'$2a$10$6xUrgawUAXvPCohIWbL9e.lD8LKI1.ZyeZyo8hKRzQqlqVJ3CpBtq' where ma = 'KH03'

	 --- CẬP NHẬT TRẠNG THÁI HÓA ĐƠN
	update hoa_don set trangthai = 1 where ma ='HD1'
	update hoa_don set trangthai = 1 where ma ='HD2'
	update hoa_don set trangthai = 1 where ma ='HD3'
	update hoa_don set trangthai = 1 where ma ='HD4'
	update hoa_don set trangthai = 1 where ma ='HD5'
	update hoa_don set trangthai = 1 where ma ='HD6'
	update hoa_don set ngay_thanh_toan = null where ma ='HD1'
	update hoa_don set ngay_thanh_toan = null where ma ='HD2'
	update hoa_don set ngay_thanh_toan = null where ma ='HD3'
	update hoa_don set ngay_thanh_toan = null where ma ='HD4'
	update hoa_don set ngay_thanh_toan = null where ma ='HD5'
	update hoa_don set ngay_thanh_toan = null where ma ='HD6'
	update hoa_don set trangthai = 2 where ma ='HD7'
	update hoa_don set trangthai = 2 where ma ='HD8'
	update hoa_don set trangthai = 2 where ma ='HD9'
	update hoa_don set trangthai = 2 where ma ='HD10'
	update hoa_don set trangthai = 2 where ma ='HD11'
	update hoa_don set trangthai = 2 where ma ='HD12'

	--- TRUY VẤN CÁC BẢNG
	select * from gio_hang
	select * from gio_hang_chi_tiet
	select * from khach_hang
	select * from nhan_vien
	select * from chuc_vu
	select * from dia_chi
	select * from giay
	select * from giay_chi_tiet
	select * from chuong_tring_giam_gia_san_pham
	select * from chuong_trinh_giam_gia_chi_tiet_san_pham
	select * from hoa_don
	select * from hoa_don_chi_tiet
	select * from chuong_trinh_giam_gia_chi_tiet_hoa_don
	select * from chuong_trinh_giam_gia_hoa_don


	--- XÓA DATA Ở CÁC BẢNG
	delete from giay_chi_tiet
	delete from giay
	delete from anh_giay
	delete from hoa_don_chi_tiet
	delete from hoa_don
	delete from nhan_vien
	delete from khach_hang
	delete from dia_chi
	delete from gio_hang_chi_tiet
	delete from gio_hang
	delete from chuong_trinh_giam_gia_chi_tiet_san_pham
	delete from chuong_tring_giam_gia_san_pham
	delete from chuong_trinh_giam_gia_chi_tiet_hoa_don
	delete from chuong_trinh_giam_gia_hoa_don
	*/

