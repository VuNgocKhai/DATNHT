drop database website_ban_giay
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
	gia_sau_khuyen_mai decimal,
	ngay_nhap date,
	do_hot int null,
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
	so_tien_giam decimal  default null,
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

SELECT * FROM thuong_hieu
SELECT * FROM dia_hinh
SELECT * FROM gioi_tinh
SELECT * FROM thoi_tiet_thich_hop
SELECT * FROM danh_muc
SELECT * FROM de_giay
SELECT * FROM cam_giac
SELECT * FROM do_cao_giay
SELECT * FROM mau_sac
SELECT * FROM giay
SELECT * FROM anh_giay
SELECT * FROM kich_co
SELECT * FROM giay_chi_tiet
SELECT * FROM chuc_vu
SELECT * FROM nhan_vien
SELECT * FROM chuong_tring_giam_gia_san_pham
SELECT * FROM chuong_trinh_giam_gia_chi_tiet_san_pham
SELECT * FROM khach_hang
SELECT * FROM dia_chi
SELECT * FROM gio_hang
SELECT * FROM gio_hang_chi_tiet
SELECT * FROM hoa_don
SELECT * FROM hoa_don_chi_tiet
SELECT * FROM chuong_trinh_giam_gia_hoa_don
SELECT * FROM chuong_trinh_giam_gia_chi_tiet_hoa_don

INSERT INTO website_ban_giay.dbo.thuong_hieu (id,ma,ten,trangthai) VALUES
	 (N'8803F722-D7E9-4B7D-A8B9-6161A02B2A79',N'TH09',N'Puma',1),
	 (N'D33E243F-62E3-41E4-9E10-49B3455263BE',N'TH08',N'New Balance',1),
	 (N'F6BB1959-2BD6-4287-94D8-010A2703CEF1',N'TH06',N'Nike',1),
	 (N'7FE4DEC1-1736-4929-8133-0BEBF8D7EF80',N'TH05',N'Converse',1),
	 (N'676A3832-0E93-4324-99E3-0CA4C41239F3',N'TH07',N'Vans',1),
	 (N'56DA1831-7647-446A-A150-0CF3DF82A9F2',N'TH01',N'Adidas',1),
	 (N'68546213-C563-4273-A10B-762A9C8A1BD1',N'TH04',N'YSL',1),
	 (N'CE37EF88-5AE4-43E0-AC6C-AB88EEB227CD',N'TH03',N'Louis Vuitton',1),
	 (N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'TH02',N'Balenciaga',1);
INSERT INTO website_ban_giay.dbo.dia_hinh (id,ma,ten,trangthai) VALUES
	 (N'0CB15488-6B50-455D-8D50-61AC7A060960',N'DH03',N'Đồng Bằng',1),
	 (N'AD7BA4E3-9DA9-49D8-A5B5-8063A6C3C0B8',N'DH04',N'Vùng nhiều sỏi đá',1),
	 (N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'DH02',N'Sân cỏ',1),
	 (N'ED504C4E-664F-46C1-9B2B-ADE4082C0D47',N'DH01',N'Đồi Núi',1);
INSERT INTO website_ban_giay.dbo.gioi_tinh (id,ma,ten,trangthai) VALUES
	 (N'5A6F129E-A597-4A23-8967-030A746FDB56',N'GT03',N'Khác',1),
	 (N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'GT02',N'Nữ',1),
	 (N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'GT01',N'Nam',1);
INSERT INTO website_ban_giay.dbo.thoi_tiet_thich_hop (id,ma,ten,trangthai) VALUES
	 (N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'TTTH02',N'Trời Mưa',1),
	 (N'5BC9DCB2-000B-46B6-8C94-557ED7D65251',N'TTTH03',N'Thời tiết lạnh giá',1),
	 (N'91A0C9F9-DD97-4F2E-A8C7-BEF58E70E2E0',N'TTTH01',N'Ngày hè nắng nóng',1);
INSERT INTO website_ban_giay.dbo.danh_muc (id,ma,ten,trangthai) VALUES
	 (N'17E562BE-B3C9-42C3-84A6-C8F47A393A71',N'DM02',N'Sandal',1),
	 (N'C0728D3E-7E71-4121-8879-16B917291F0D',N'DM03',N'Slip-on',1),
	 (N'6BA6A6BA-66DC-496D-AAFC-2DEEDAACB63D',N'DM04',N'Boots',1),
	 (N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'DM01',N'Giày Thể Thao',1);
INSERT INTO website_ban_giay.dbo.do_cao_giay (id,ma,ten,trangthai) VALUES
	 (N'173D9356-A26B-498E-9D03-3F4A4C5239F6',N'DCG01',N'Cổ Cao',1),
	 (N'1D88D79C-D761-47F8-B6A5-98DAAEFBE897',N'DCG03',N'Giày bệp',1),
	 (N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'DCG02',N'Cổ Thấp',1);
INSERT INTO website_ban_giay.dbo.de_giay (id,ma,ten,trangthai) VALUES
	 (N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'DG02',N'Cao su nhiệt dẻo',1),
	 (N'3E11DC22-B20B-45CC-BB68-66021A6113F0',N'DG03',N'CloudFoam',1),
	 (N'A5218612-2619-46C2-B40B-6489991CD376',N'DG03',N'PVC',1),
	 (N'2F5169FE-1D80-435D-9780-612738EA52D2',N'DG04',N'PU',1),
	 (N'CE93E2F3-1008-40FA-83FE-8721E66DA4E6',N'DG01',N'EVA',1);
INSERT INTO website_ban_giay.dbo.mau_sac (id,ma,ten,trangthai) VALUES
	 (N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'MS02',N'Màu Đen',1),
	 (N'64FEFE23-5112-4694-8CBB-6E9D74F66EAE',N'MS03',N'Màu Trắng',1),
	 (N'28B4B331-F4C7-4A0F-AE67-8BDFD28F52C5',N'MS04',N'Màu Xanh Naxvy',1),
	 (N'080FEF10-8EB8-47D0-9DCA-921B331DF2DB',N'MS05',N'Trắng xanh',1),
	 (N'225112C3-2163-495C-9D14-9C911B399ACB',N'MS06',N'Màu Tím',1),
	 (N'06E033EC-996F-42E9-BEEE-D62C7C26EC99',N'MS07',N'Màu Xám',1),
	 (N'25A36D99-9018-4721-8355-CE60ED607EA8',N'MS08',N'Màu Xanh Lá',1),
	 (N'A16952BE-E20B-484B-A7C6-BB57DDAB9B2D',N'MS09',N'Màu Xanh Dương',1),
	 (N'2903C8AD-D127-4BBF-B737-47867834EFE0',N'MS01',N'Màu Hồng',1);
INSERT INTO website_ban_giay.dbo.cam_giac (id,ma,ten,trangthai) VALUES
	 (N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'CG01',N'Thoải Mái',1),
	 (N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'CG02',N'Ôm chân',1);
INSERT INTO website_ban_giay.dbo.chat_lieu (id,ma,ten,trangthai) VALUES
	 (N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'CL01',N'Da',1),
	 (N'FA5D25BF-8A11-4865-ADC0-D9C1AA0AD801',N'CL02',N'Vải tổng hợp',1),
	 (N'FA3C1EAE-524E-4109-B699-F6B0CC6251BD',N'CL03',N'Lưới',1),
	 (N'00C0634C-6138-43BC-9594-7DF461FCAC5B',N'CL04',N'Cao su',1),
	 (N'AC121F20-9E53-4FF2-9196-838B0D0EEC2F',N'CL05',N'Bọt Open Cell',1),
	 (N'B7CDAA3D-2A1E-4511-ACE6-EEF38BEA82BC',N'CL06',N'Vải dải kim sợi',1);
	 /* thêm cột insert giá tiền sau giảm*/

INSERT INTO website_ban_giay.dbo.giay 
(id,ma,ten,id_thuong_hieu,id_gioi_tinh,id_danh_muc,id_chat_lieu,id_cam_giac,id_dia_hinh,id_thoi_tiet_thich_hop,id_de_giay,id_do_cao_giay,id_mau_sac,mota,gianhap,giaban,trangthai,gia_sau_khuyen_mai,ngay_nhap,do_hot) VALUES
	 (N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'SP01',N'Giày Balenciaga Triple S',						N'723C3006-215B-49F9-A0D5-E5525CBB4F3E',N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'FA5D25BF-8A11-4865-ADC0-D9C1AA0AD801',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'0CB15488-6B50-455D-8D50-61AC7A060960',N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'CE93E2F3-1008-40FA-83FE-8721E66DA4E6',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'64FEFE23-5112-4694-8CBB-6E9D74F66EAE',N'Giày Đẹp Lắm',680000,1200000,1,1200000,N'2023-12-23',1),	 
	 (N'6B656199-7B9D-4A2F-925E-9A1C94436FDF',N'SP02',N'Giày Adidas Galaxy 4',							N'56DA1831-7647-446A-A150-0CF3DF82A9F2',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'FA5D25BF-8A11-4865-ADC0-D9C1AA0AD801',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'ED504C4E-664F-46C1-9B2B-ADE4082C0D47',N'5BC9DCB2-000B-46B6-8C94-557ED7D65251',N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'173D9356-A26B-498E-9D03-3F4A4C5239F6',N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'Giày adidas là mẫu giày thời trang được rất nhiều các tín đồ yêu thích thời trang lựa chọn đi cùng bản thân.',	920000,1890000,1,1890000,N'2023-12-23',1),
	 (N'17A1D3AE-53E1-4A5F-927C-A537D242FF5C',N'SP03',N'Giày Puma RBD Game Low Better II',				N'8803F722-D7E9-4B7D-A8B9-6161A02B2A79',N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'AD7BA4E3-9DA9-49D8-A5B5-8063A6C3C0B8',N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'A5218612-2619-46C2-B40B-6489991CD376',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'2903C8AD-D127-4BBF-B737-47867834EFE0',N'Giày sneaker RBD Game là sự kết hợp hoàn hảo giữa văn hóa cổ điển cùng phong cách bóng rổ ',						1020000,2299000,1,2299000,N'2023-12-23',1),
	 (N'B4330BEB-4A15-4AF8-ACCB-B70D9550CA13',N'SP04',N'Giày Nike Air Jorrdan 1',						N'F6BB1959-2BD6-4287-94D8-010A2703CEF1',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'0CB15488-6B50-455D-8D50-61AC7A060960',N'5BC9DCB2-000B-46B6-8C94-557ED7D65251',N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'080FEF10-8EB8-47D0-9DCA-921B331DF2DB',N'Giày Đẹp Lắm',790000,1680000,1,1680000,N'2023-12-23',1),
	 (N'D482AAA8-3E22-4E7F-A396-B92F01B1F419',N'SP05',N'Giày Adidas Advancourt Base',					N'56DA1831-7647-446A-A150-0CF3DF82A9F2',N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'C0728D3E-7E71-4121-8879-16B917291F0D',N'FA5D25BF-8A11-4865-ADC0-D9C1AA0AD801',N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'ED504C4E-664F-46C1-9B2B-ADE4082C0D47',N'91A0C9F9-DD97-4F2E-A8C7-BEF58E70E2E0',N'A5218612-2619-46C2-B40B-6489991CD376',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'080FEF10-8EB8-47D0-9DCA-921B331DF2DB',N'Giày Đẹp Lắm',2160000,4120000,1,4120000,N'2023-12-23',1),
	 (N'A47CE414-381C-4350-B340-B9A472500598',N'SP06',N'Giày Puma Caven',								N'8803F722-D7E9-4B7D-A8B9-6161A02B2A79',N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'AD7BA4E3-9DA9-49D8-A5B5-8063A6C3C0B8',N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'173D9356-A26B-498E-9D03-3F4A4C5239F6',N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'Giày Đẹp Lắm',1350000,2800000,1,2800000,N'2023-12-23',1),
	 (N'B910A4B4-D837-41DA-9468-C6B8469A8478',N'SP07',N'Giày Converse Chuck Taylor Alt Star Spray Paint',N'7FE4DEC1-1736-4929-8133-0BEBF8D7EF80',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'0CB15488-6B50-455D-8D50-61AC7A060960',N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'CE93E2F3-1008-40FA-83FE-8721E66DA4E6',N'173D9356-A26B-498E-9D03-3F4A4C5239F6',N'64FEFE23-5112-4694-8CBB-6E9D74F66EAE',N'Giày Đẹp Lắm',630000,1350000,1,1350000,N'2023-12-23',1),
	 (N'30A691C2-0E8C-4BE8-9443-D7ACA0B6F51A',N'SP08',N'Giày Vans Ua Classic',							N'676A3832-0E93-4324-99E3-0CA4C41239F3',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'ED504C4E-664F-46C1-9B2B-ADE4082C0D47',N'5BC9DCB2-000B-46B6-8C94-557ED7D65251',N'A5218612-2619-46C2-B40B-6489991CD376',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'Giày Đẹp Lắm',2410000,4500000,1,4500000,N'2023-12-23',1),
	 (N'B102A6C1-D02B-4ED1-91C0-F284625414FF',N'SP09',N'Giày Yves Saint Laurent Designer',				N'68546213-C563-4273-A10B-762A9C8A1BD1',N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'FA5D25BF-8A11-4865-ADC0-D9C1AA0AD801',N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'AD7BA4E3-9DA9-49D8-A5B5-8063A6C3C0B8',N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'A5218612-2619-46C2-B40B-6489991CD376',N'1D88D79C-D761-47F8-B6A5-98DAAEFBE897',N'A16952BE-E20B-484B-A7C6-BB57DDAB9B2D',N'Giày Đẹp Lắm',850000,1900000,1,1900000,N'2023-12-23',1),
	 (N'50C5D844-E31A-4CA2-992E-257E919AB049',N'SP10',N'Giày Louis Vuitton Taiga',						N'CE37EF88-5AE4-43E0-AC6C-AB88EEB227CD',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'C0728D3E-7E71-4121-8879-16B917291F0D',N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'0CB15488-6B50-455D-8D50-61AC7A060960',N'91A0C9F9-DD97-4F2E-A8C7-BEF58E70E2E0',N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'173D9356-A26B-498E-9D03-3F4A4C5239F6',N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'Giày Đẹp Lắm',450000,950000,1,950000,N'2023-12-23',1),
	 (N'D53FE042-335C-4E44-A304-322D26B6B355',N'SP11',N'Giày Vans Color Theory',						N'676A3832-0E93-4324-99E3-0CA4C41239F3',N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'FA3C1EAE-524E-4109-B699-F6B0CC6251BD',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'AD7BA4E3-9DA9-49D8-A5B5-8063A6C3C0B8',N'5BC9DCB2-000B-46B6-8C94-557ED7D65251',N'CE93E2F3-1008-40FA-83FE-8721E66DA4E6',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'64FEFE23-5112-4694-8CBB-6E9D74F66EAE',N'Giày Đẹp Lắm',715000,1850000,1,1850000,N'2023-12-23',1),
	 (N'86AC05C7-8BA4-4A6F-BB5F-34D63B639EE4',N'SP12',N'Giày Vans Old Skool Vintage',					N'676A3832-0E93-4324-99E3-0CA4C41239F3',N'B83FE3A0-AF91-49F1-87DD-E6E374A4E183',N'C0728D3E-7E71-4121-8879-16B917291F0D',N'B7CDAA3D-2A1E-4511-ACE6-EEF38BEA82BC',N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'ED504C4E-664F-46C1-9B2B-ADE4082C0D47',N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'A5218612-2619-46C2-B40B-6489991CD376',N'173D9356-A26B-498E-9D03-3F4A4C5239F6',N'9E682E28-8958-40F8-AE41-3E5D0E298276',N'Giày Đẹp Lắm',350000,800000,1,800000,N'2023-12-23',1),
	 (N'9E9F760D-2EB1-4B26-BD3C-36B21A531974',N'SP13',N'Giày Converse One Star Pro Herringbone ',		N'7FE4DEC1-1736-4929-8133-0BEBF8D7EF80',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'A23FA17B-5682-48E5-A98B-4034B97799DE',N'E0DBC8F8-B2AA-4E25-A73B-0A80E8CE5720',N'0CB15488-6B50-455D-8D50-61AC7A060960',N'5D58E9E4-3808-48A3-90F2-6AA39936B1A7',N'6F6FF5BA-764A-4F74-B05F-80220497F165',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'06E033EC-996F-42E9-BEEE-D62C7C26EC99',N'Giày Đẹp Lắm',950000,2300000,1,2300000,N'2023-12-23',1),
	 (N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'SP15',N'Giày Thể Thao Nike Sb Alleyoop',				N'F6BB1959-2BD6-4287-94D8-010A2703CEF1',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'393C9E5A-2EFF-4A06-8FC8-CF269FBFA4A2',N'B7CDAA3D-2A1E-4511-ACE6-EEF38BEA82BC',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'87603097-6CC3-4EF7-BB14-814F1B1AA564',N'91A0C9F9-DD97-4F2E-A8C7-BEF58E70E2E0',N'CE93E2F3-1008-40FA-83FE-8721E66DA4E6',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692',N'2903C8AD-D127-4BBF-B737-47867834EFE0',N'Giày Mới Lắm',1120000,1800000,1,1800000,N'2023-12-23',1);
	 -- (N'7A2E3686-373E-4293-B32A-3ACC4BA3E40C',N'SP14',N'Dép Sandal Nike Oneonta',						N'F6BB1959-2BD6-4287-94D8-010A2703CEF1',N'F3F0FAF5-E1E4-4401-8F0D-1933EA634024',N'17E562BE-B3C9-42C3-84A6-C8F47A393A71',N'B7CDAA3D-2A1E-4511-ACE6-EEF38BEA82BC',N'226DE612-CF86-4A0A-9432-57B6375D9FFF',N'AD7BA4E3-9DA9-49D8-A5B5-8063A6C3C0B8',N'91A0C9F9-DD97-4F2E-A8C7-BEF58E70E2E0',N'CE93E2F3-1008-40FA-83FE-8721E66DA4E6',N'9AA29BE5-FB6C-4922-BBEB-68358D3D4692','N25A36D99-9018-4721-8355-CE60ED607EA8',N'Giày Đẹp Lắm',750000,1950000,1,1950000,N'2023-12-23',1),


INSERT INTO website_ban_giay.dbo.kich_co (id,ma,ten,trangthai) VALUES
	 (N'F575F6DE-14B2-4F24-A159-E71280C3F033',N'KC01',N'35',1),
	 (N'EA81EC34-2347-4516-B447-9D133CC647B3',N'KC02',N'36',1),
	 (N'14B56C7E-CFB4-400A-81F6-F9D46627D4B4',N'KC03',N'37',1),
	 (N'1C19C2B0-6B73-4FAB-A1DD-C71EF742F88B',N'KC04',N'38',1),
	 (N'5F45C91F-FD8A-48C9-B469-2C8A7C385F44',N'KC05',N'39',1),
	 (N'F4426373-2A1C-4C07-B803-419492906670',N'KC06',N'40',1),
	 (N'71BB58B7-1C66-42C9-994A-7547A757E0C1',N'KC07',N'41',1),
	 (N'789902DA-30DB-4CC3-B592-7AD289315C38',N'KC08',N'42',1),
	 (N'A97F40B0-AFCE-4C0A-BF45-F1B45FD6B472',N'KC09',N'43',1),
	 (N'638E4A6E-04DA-4779-B903-8B3870925AD9',N'KC010',N'44',1),
	 (N'986A4D3D-7319-410C-A551-B0B70BB5EB77',N'KC011',N'45',1);
INSERT INTO website_ban_giay.dbo.anh_giay (id,ten_url,id_giay,trangthai) VALUES
	 (N'65EB4C89-4CF3-47E4-8D08-229F5F264C99',N'anhsp2.jpeg',N'BC03F312-454A-48DA-A318-80D1E3979AD1',1),
	 (N'94278920-EDB0-4455-84B4-5D2C474CA7DE',N'anhsp4.jpeg',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',1),
	 (N'508934CD-E5DC-4E44-9E30-BA4BFF8B297D',N'anhsp3.jpeg',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',1),
	 (N'4300D49F-684B-42F9-ABA1-E83AD892D59E',N'anhsp1.jpeg',N'BC03F312-454A-48DA-A318-80D1E3979AD1',1);
INSERT INTO website_ban_giay.dbo.giay_chi_tiet (id,id_giay,id_kich_co,so_luong_ton,trangthai) VALUES
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'1C19C2B0-6B73-4FAB-A1DD-C71EF742F88B',10,1),
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'F575F6DE-14B2-4F24-A159-E71280C3F033',50,1),
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'EA81EC34-2347-4516-B447-9D133CC647B3',20,1),
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'50C5D844-E31A-4CA2-992E-257E919AB049',N'F575F6DE-14B2-4F24-A159-E71280C3F033',30,1),
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'50C5D844-E31A-4CA2-992E-257E919AB049',N'1C19C2B0-6B73-4FAB-A1DD-C71EF742F88B',40,1),
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'50C5D844-E31A-4CA2-992E-257E919AB049',N'EA81EC34-2347-4516-B447-9D133CC647B3',1,1),
	 (N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'789902DA-30DB-4CC3-B592-7AD289315C38',10,1),
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'D53FE042-335C-4E44-A304-322D26B6B355',N'789902DA-30DB-4CC3-B592-7AD289315C38',15,1),
	 (N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'A97F40B0-AFCE-4C0A-BF45-F1B45FD6B472',25,1),
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'D53FE042-335C-4E44-A304-322D26B6B355',N'F4426373-2A1C-4C07-B803-419492906670',35,1),
	 (N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'71BB58B7-1C66-42C9-994A-7547A757E0C1',35,1),
	 (N'396ED021-F577-4BA5-B5D3-353E40F6FA4E',N'D53FE042-335C-4E44-A304-322D26B6B355',N'71BB58B7-1C66-42C9-994A-7547A757E0C1',21,1),
	 (N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',N'86AC05C7-8BA4-4A6F-BB5F-34D63B639EE4',N'F575F6DE-14B2-4F24-A159-E71280C3F033',17,1),
	 (N'CD1D3426-38E1-4BE3-BF90-8B83EEEE5676',N'86AC05C7-8BA4-4A6F-BB5F-34D63B639EE4',N'A97F40B0-AFCE-4C0A-BF45-F1B45FD6B472',28,1);
INSERT INTO website_ban_giay.dbo.chuc_vu (id,ma,ten,trangthai) VALUES
	 (N'7B52F69D-5508-4745-926F-A22F9907E523',N'CV02',N'USER',1),
	 (N'F09E9132-7F9E-48BD-A30C-E58C9E16717E',N'CV01',N'ADMIN',1);
INSERT INTO website_ban_giay.dbo.nhan_vien (id,ma,ho_ten,ngay_sinh,dia_chi,xa,huyen,thanh_pho,sdt,email,id_chuc_vu,mat_khau,ngay_vao_lam,ngay_nghi_viec,trangthai) VALUES
	 (N'9D8372B8-AAA4-464A-A938-3C6927210010',N'NV01',N'Nguyễn Văn Đạt','1997-12-23',N'Mỳ Đình - Nam Từ Liêm - Hà Nội',NULL,NULL,NULL,N'0385090080',N'danhnt@gmail.com',N'7B52F69D-5508-4745-926F-A22F9907E523',N'1234567','2020-12-12',NULL,1),
	 (N'F184400D-6C00-41D9-98A8-61685763718B',N'NV02',N'Lê Thị Mai Linh','2003-03-21',N'Cầu Diễn-Từ Liêm - Hà Nội',NULL,NULL,NULL,N'0385090080',N'lemailinh2132003@gmail.com',N'7B52F69D-5508-4745-926F-A22F9907E523',N'1234567','2020-12-12',NULL,1),
	 (N'C2733E93-A1CD-4053-A59C-13B5C5992861',N'NV03',N'Bùi Minh Tú ','2003-09-12',N'Tố Hữu - Thanh Xuân - Hà Nội',NULL,NULL,NULL,N'0385090080',N'tui123@gmail.com',N'7B52F69D-5508-4745-926F-A22F9907E523',N'1234567','2020-12-12',NULL,1),
	 (N'8D8ED0FD-15DD-4972-90B5-EC1CDCB1960A',N'NV04',N'Lê Phương Trang','2004-03-04',N'Cấn Hữu-Quốc Oai-Hà Nội',NULL,NULL,NULL,N'0385090080',N'trangltp@gmail.com',N'7B52F69D-5508-4745-926F-A22F9907E523',N'1234567','2020-12-12',NULL,1),
	 (N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'nv05',N'Hoàng Đại Ka','2003-09-29',N'Kim Ngưu - Hai Bà Trưng-Hà Nội',NULL,NULL,NULL,N'0385090080',N'danhnt2@gmail.com',N'F09E9132-7F9E-48BD-A30C-E58C9E16717E',N'1234567','2023-09-12',NULL,1);
INSERT INTO website_ban_giay.dbo.khach_hang (id,ma,ho_ten,ngay_sinh,sdt,email,mat_khau,trangthai) VALUES
	 (N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'KH01',N'Nguyễn Danh','2003-09-09',N'0385090080',N'danhnt@gmail.com',N'123456',1),
	 (N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'KH02',N'Nguyễn Đại','2003-08-08',N'0387090080',N'danhng@gmail.com',N'123457',1),
	 (N'E55C5DF4-3533-47A9-A73F-77C9D1BDBB7B',N'KH04',N'Lê Linh','2003-03-21',N'0373872637',N'linhltmph26606@gmail.com',N'123456',1),
	 (N'22B427F5-1E52-402F-B5DC-EC17AD5F139A',N'KH03',N'Đào Gia Phong','2003-09-16',N'0385370656',N'phong@gmail.com',N'1',1)
	-- địa chỉ khách hàng chỉ có 1 trạng thái mặc định là trạng thái = 1 , các trạng thái còn lại phải = 0
INSERT INTO website_ban_giay.dbo.dia_chi (id,ma,id_khach_hang,ten_dia_chi,ten_nguoi_nhan,sdt_nguoi_nhan,xa,huyen,thanh_pho,trangthai) VALUES
	 ('1096266E-2B53-4987-8945-3DE015BB737E',N'DC01',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'Xóm 1 Triều Đông',N'Nguyễn Thành Danh',N'0385090080',N'Tân Minh',N'Thường Tín',N'Hà Nội',1),
	 ('8B214DA0-AB58-4113-B9F8-359C501253CC',N'DC02',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'Xóm 2 Triều Đông',N'Nguyễn Thành Danh',N'0385090081',N'Nhị Khê',N'Thường Tín',N'Hà Nội',0),
	 ('458916FB-C823-43F7-BC01-81252C9F4023',N'DC03',N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm 1 La Uyên',N'Nguyễn Đại',N'0385090082',N'Nguyễn Trãi',N'Thường Tín',N'Hà Nội',1),
	 ('B193CF3C-EBCB-4775-AFA0-358760461311',N'DC04',N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm 2 Lộc Dư',N'Nguyễn Đại',N'0385090080',N'Hòa Bình',N'Thường Tín',N'Hà Nội',0),
	 ('4B2B8E2C-9ACD-4768-8D2E-5F319B58CB5E',N'DC05',N'22B427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm bến',N'Đào Gia Phong',N'0385370656',N'Hiền Giang',N'Thường Tín',N'Hà Nội',1),
	 ('306CFDDC-9ECA-4371-8B09-E9CB33C46D89',N'DC06',N'22B427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm 3 Thọ Giáo',N'Đào Gia Phong',N'0385370656',N'Liên Phương',N'Thường Tín',N'Hà Nội',0)
INSERT INTO website_ban_giay.dbo.gio_hang (id,ma,id_khach_hang,ngay_tao,ngay_cap_nhap,ghi_chu,trangthai) VALUES
	 (N'5E83CE5E-CEEF-484F-BE89-021B0464CC8B',N'GH02',N'019427F5-1E52-402F-B5DC-EC17AD5F139A','2023-09-09',NULL,NULL,1),
	 (N'01451941-27D8-4B50-8B32-92F9C86CBE0F',N'GH03',N'E55C5DF4-3533-47A9-A73F-77C9D1BDBB7B','2023-011-09',NULL,NULL,1),
	 (N'CE792713-9983-4E45-9D9B-532A003CC015',N'GH01',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843','2023-09-09',NULL,NULL,1);
INSERT INTO website_ban_giay.dbo.gio_hang_chi_tiet (id,id_gio_hang,id_giay_chi_tiet,so_luong,ghi_chu,trangthai) VALUES
	 (N'092052B5-324D-4DB4-94FE-46C8A159236C',N'CE792713-9983-4E45-9D9B-532A003CC015',N'E22DEB74-ECA4-41BB-AB18-55A8CD2B9CB9',1,NULL,1),
	 (N'01384FF8-2C03-401B-B4DF-A18D093856E1',N'01451941-27D8-4B50-8B32-92F9C86CBE0F',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',1,NULL,1),
	 (N'80BADDEF-EEAB-47BA-A8DB-B9D01FA0CA42',N'01451941-27D8-4B50-8B32-92F9C86CBE0F',N'9E9F760D-2EB1-4B26-BD3C-36B21A531974',1,NULL,1),
	 (N'60017BD8-2994-44B4-85F5-CD79C124690E',N'01451941-27D8-4B50-8B32-92F9C86CBE0F',N'6B656199-7B9D-4A2F-925E-9A1C94436FDF',1,NULL,1),
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
INSERT INTO website_ban_giay.dbo.chuong_trinh_giam_gia_hoa_don (id,ma,ten,dieu_kien,so_tien_giam,ngay_bat_dau,ngay_ket_thuc,so_luong,trangthai) VALUES
	 (N'AA43BE03-0834-4FCB-8AB1-0680CE0FA164',N'CTGGHD01',N'Siêu sale tháng 12',1000000,200000,'2023-12-11','2023-12-12',120,1),
	 (N'C4D20CFF-E1D3-4DC4-BA0F-ED09C8518B88',N'CTGGHD02',N'Chào xuân 2023',2000000,300000,'2023-12-11','2023-12-12',50,1),
	 (N'8EF637BA-9A71-4A11-955C-F0DF72886FB0',N'CTGGHD03',N'Tưng bừng sinh nhật',888888,200000,'2023-08-15','2023-08-20',100,1),
	 (N'0DD0562F-83BC-406A-AB12-22C9FFE7A8AE',N'CTGGHD04',N'Khuyến mại quý 4 2023',500000,50000,'2023-09-09','2023-12-12',2000,1);

	 /*
	 Liên quan đến hóa đơn, chưa insert 
INSERT INTO website_ban_giay.dbo.chuong_trinh_giam_gia_chi_tiet_hoa_don (id,id_hoa_don,id_chuong_trinh_giam_gia_hoa_don,tong_tien,so_tien_da_giam,tong_tien_thanh_toan,trangthai) VALUES
	 (N'02A1DEF4-6E76-4841-8944-ABD9CB120118',N'3CAD650A-4B71-41FB-8FA4-6682B25F6541',N'AA43BE03-0834-4FCB-8AB1-0680CE0FA164',111111,111,111000,1),
	 (N'0570A63A-C380-4722-B8E4-C2DFAD029CFF',N'DEC073F7-5FBB-4E5F-9766-AFAC0B275AEB',N'0DD0562F-83BC-406A-AB12-22C9FFE7A8AE',211111,111,211000,1);
	 */
INSERT INTO website_ban_giay.dbo.chuong_tring_giam_gia_san_pham (id,ma,ten,phan_tram_giam,ngay_bat_dau,ngay_ket_thuc,id_nhan_vien_create,id_nhan_vien_update,trangthai) VALUES
	 (N'392FBF07-9BCA-4EB5-8188-E00770A83EAC',N'GGSP03',N'Chào hè',15,'2023-05-05','2023-06-06',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'9D8372B8-AAA4-464A-A938-3C6927210010',1),
	 (N'D5AEE93C-B41E-44EB-BF5F-F4159E2B2595',N'GGSP04',N'Noel vui vẻ',20,'2023-12-18','2023-12-18',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'9D8372B8-AAA4-464A-A938-3C6927210010',1),
	 (N'13090853-794B-4746-BA0D-006172DD4976',N'GGSP02',N'Mừng Tết 2023',20,'2023-12-12','2023-12-12',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'9D8372B8-AAA4-464A-A938-3C6927210010',1),
	 (N'5A9AFEF6-1ADC-43AE-A5DD-E160754913CA',N'GGSP01',N'Mừng Ngày Quốc Khánh',10,'2023-12-12','2023-12-12',N'9D8372B8-AAA4-464A-A938-3C6927210010',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',1);

/*
Không cần  insert chi tiết kmsp
INSERT INTO website_ban_giay.dbo.chuong_trinh_giam_gia_chi_tiet_san_pham (id,id_giay,id_chuong_trinh_giam_gia,so_tien_da_giam,trangthai) VALUES
	 (N'2D2A2541-2D46-425A-835D-578E7F92EA26',N'BC03F312-454A-48DA-A318-80D1E3979AD1',N'13090853-794B-4746-BA0D-006172DD4976',100,1),
	 (N'A70E75F8-2662-41F3-96EE-8F4D4A1CA41E',N'144262A4-C9BF-4764-A288-8C66D9A3C2F7',N'5A9AFEF6-1ADC-43AE-A5DD-E160754913CA',200,1);
*/
	 /*
	 select*from giay where id = 'BC03F312-454A-48DA-A318-80D1E3979AD1'

	 update giay set gia_sau_khuyen_mai = where id ='BC03F312-454A-48DA-A318-80D1E3979AD1'

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
	update hoa_don set trangthai = 1 where ma ='HD9'
	update hoa_don set trangthai = 2 where ma ='HD10'
	update hoa_don set trangthai = 2 where ma ='HD11'
	update hoa_don set trangthai = 2 where ma ='HD6'

		update hoa_don set trangthai = 1 where ma ='HD000001'
		update hoa_don set ngay_thanh_toan = null where ma ='HD000001'

	select * from hoa_don
	select * from hoa_don_chi_tiet
	select * from giay
	select * from giay_chi_tiet
	select * from hoa_don where ma ='HD000001'
	delete from hoa_don where ma ='HD000001'
	update hoa_don set ma ='HD6' where id ='046D6C46-6621-0246-9A02-CA8BAD5C9646'
	delete from hoa_don_chi_tiet
	delete from hoa_don

	*/
