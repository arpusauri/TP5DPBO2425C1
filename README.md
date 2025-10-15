# TP5DPBO2425C1
Saya Arya Purnama Sauri dengan NIM 2408521 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain Pemrograman Berbasis Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

# Desain Program

## Database Toko Elektronik

### Struktur Tabel
Memiliki 1 tabel yaitu tabel Inventori.

#### Tabel: `inventori`
Tabel ini menyimpan data produk elektronik yang dijual di toko.


| Field        | Tipe Data             | Constraint   | Keterangan                                                   |
|-------       |-----------            |------------  |------------                                                  |
| `id`         | VARCHAR(51)           | PRIMARY KEY  | ID unik produk dengan format KATEGORI-NNN (contoh: HP-001)   |
| `nama`       | VARCHAR(51)           | NOT NULL     | Nama produk                                                  |
| `harga`      | DOUBLE                | NOT NULL     | Harga produk                                                 |
| `kategori`   | VARCHAR(51)           | NOT NULL     | Kategori produk (Laptop, HP, TV, Kulkas, AC)                 |
| `kondisi`    | ENUM('Baru','Bekas')  | NOT NULL     | Kondisi barang (Baru/Bekas)                                  |

##  Class Program Toko Elektronik

### 1. Class `Product`
Class model yang merepresentasikan entitas produk elektronik.

#### Atribut:
- `String id` - Identifier unik produk (Primary Key)
- `String nama` - Nama produk elektronik
- `double harga` - Harga produk dalam Rupiah
- `String kategori` - Kategori produk (Laptop, HP, TV, Kulkas, AC)
- `String kondisi` - Kondisi barang (Baru/Bekas)

#### Method:
- **Constructor**: `Product(String id, String nama, double harga, String kategori, String kondisi)`
- **Getter**: `getId()`, `getNama()`, `getHarga()`, `getKategori()`, `getKondisi()`
- **Setter**: `setId()`, `setNama()`, `setHarga()`, `setKategori()`, `setKondisi()`

### 2. Class `Database`

#### Method:
- `selectQuery(String query)` - Menjalankan query SELECT dan mengembalikan ResultSet
- `insertUpdateDeleteQuery(String query)` - Menjalankan query INSERT, UPDATE, DELETE

### 3. Class `ProductMenu`
Class utama yang menampilkan GUI dan mengelola operasi CRUD produk.

#### Atribut:
- `int selectedIndex` - Index baris yang dipilih di tabel (-1 jika tidak ada)
- `Database database` - Objek koneksi database
- Komponen GUI: `JPanel`, `JTextField`, `JTable`, `JButton`, `JComboBox`, `JRadioButton`

#### Method:
- **`ProductMenu()`** - Constructor untuk inisialisasi database, setup komponen GUI, dan mengatur event listener
- **`setTable()`** - Mengambil data dari database dan menampilkan ke JTable
- **`insertData()`** - Menambah data produk baru ke database dengan validasi input
- **`updateData()`** - Mengubah data produk yang sudah ada di database
- **`deleteData()`** - Menghapus data produk dari database dengan konfirmasi
- **`clearForm()`** - Mengosongkan semua input form dan reset ke mode "Add"

# Penjelasan Alur Program
## 1. Jalankan Program
## 2. Menampilkan tampilan awal 
   Tampilan dibagi menjadi 2 bagian yaitu :
 - Input (pada bagian atas) 
 - Tabel data (pada bagian bawah)
## 3. Melakukan CRUD
   * CREATE
      1. Masukan seluruh input
      2. Tekan tombol "ADD" di bagian input
      3. Menampilkan pesan "Data berhasil ditambahkan" dan button "OK"
      3. Tekan tombol "OK"
      4. Data berhasil ditambah
      5. **ERROR HANDLING : Jika ada input yang kosong**
      5. **ERROR HANDLING : Jika input ID sesuai dengan data yang ada di Database**
   * Update
      1. Tekan baris pada table yang ingin dirubah data nya
      2. Data baris tersebut akan muncul pada bagian input
      3. Ubah data pada input
      4. Tekan button "Cancel" jika ingin membatalkan perubahan
      5. Tekan button "Update" untuk melakukan perubahan data
      6. Menampilkan pesan "Data berhasil diubah" dan button "OK"
      7. Tekan tombol "OK"
      8. Data berhasil diubah
      9. **ERROR HANDLING  : Jika ada input yang kosong**
   * Delete
      1. Tekan baris pada table yang ingin dihapus data nya
      2. Tekan button "Delete" untuk menghapus data
      3. Menampilkan pesan "Apakah anda yakin ingin menghapus data ini?" serta button "YES" dan "NO"
      4. Tekan Tombol "NO" jika ingin membatalkan penghapusan data
      5. Tekan tombol "YES" untuk melakukan penghapusan data
      6. Menampilkan pesan "Data berhasil dihapus" dan button "OK"
      7. Tekan tombol "OK"
      8. Data berhasil dihapus

# Dokumentasi
## 1. CREATE
![create1](https://github.com/user-attachments/assets/82d690ce-07c5-442d-a74b-bad746e66a7e)<br>
![create2](https://github.com/user-attachments/assets/ee210af5-c018-486f-ba11-4f2cdc76ad7f)<br>
![create3](https://github.com/user-attachments/assets/2fef93c1-20be-4892-b8a2-21affaf5e47d)<br>
![create4](https://github.com/user-attachments/assets/21dfaf0b-880d-4dbe-af24-a9f5e1183a4d)<br>

## 2. READ
<img width="686" height="593" alt="image" src="https://github.com/user-attachments/assets/4288e0d1-e5bd-464c-95ab-1f90842afbb6" /><br>

## 3. UPDATE
![update1](https://github.com/user-attachments/assets/b71b05fc-1e87-4396-88c7-bc091c239fad)<br>
![update2](https://github.com/user-attachments/assets/c0538ee7-eed2-4c87-8fba-dbf1a3fa6470)<br>
![update3](https://github.com/user-attachments/assets/3a4afa33-d931-48ba-91e7-6ac88a901e1b)<br>

## 4. DELETE
![delete1](https://github.com/user-attachments/assets/9cb2de49-fd60-473c-b762-a67e78b78c08)<br>
![delete2](https://github.com/user-attachments/assets/a3f01851-edbe-48c5-b600-d85500cccb9a)<br>


