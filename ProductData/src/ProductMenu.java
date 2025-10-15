import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        // buat object window
        ProductMenu menu = new ProductMenu();

        // atur ukuran window
        menu.setSize(700, 600);

        // letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // isi window
        menu.setContentPane(menu.mainPanel);

        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // tampilkan window
        menu.setVisible(true);

        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua produk
    private Database database;

    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;
    private JLabel kondisiLabel;
    private JRadioButton baruRadioButton;
    private JRadioButton bekasRadioButton;
    private ButtonGroup kondisiButtonGroup;

    // constructor
    public ProductMenu() {

        // buat objek database
        database = new Database();

        // isi tabel produk
        productTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont(). deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] kategoriData = { "Laptop", "HP", "TV", "Kulkas", "AC" };
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // atur radio button
        kondisiButtonGroup = new ButtonGroup();
        kondisiButtonGroup.add(baruRadioButton);
        kondisiButtonGroup.add(bekasRadioButton);
        baruRadioButton.setSelected(true);

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                // Jika user klik YES, hapus data
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = productTable.getSelectedRow();

                // simpan value textfield dan combo box
                String curId = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curHarga = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = productTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curKondisi = productTable.getModel().getValueAt(selectedIndex, 5).toString();

                // ubah isi textfield dan combo box
                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                kategoriComboBox.setSelectedItem(curKategori);

                // set radio button sesuai kondisi
                if (curKondisi.equals("Baru")) {
                    baruRadioButton.setSelected(true);
                } else {
                    bekasRadioButton.setSelected(true);
                }

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);

            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = { "No", "ID Produk", "Nama", "Harga", "Kategori", "Kondisi" };

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        try{
            ResultSet resultSet = database.selectQuery("SELECT * FROM inventori");

            // isi tabel dengan hasil query
            int i = 1;
            while (resultSet.next()) {
                Object[] row = new Object[6];
                row[0] = i;
                row[1] = resultSet.getString("id");
                row[2] = resultSet.getString("nama");
                double harga = resultSet.getDouble("harga");
                row[3] = String.format("%.0f", harga);
                row[4] = resultSet.getString("kategori");
                row[5] = resultSet.getString("kondisi");
                tmp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tmp;
    }

    public void insertData() {
        try {
            // ambil value dari textfield, combobox, dan radio button
            String id = idField.getText();
            String nama = namaField.getText();
            String hargaText = hargaField.getText();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String kondisi = baruRadioButton.isSelected() ? "Baru" : "Bekas";

            // validasi kalau input kosong
            if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty() || kategori.isEmpty() || kondisi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // cek kalau id udah ada di database
            ResultSet rs = database.selectQuery("SELECT id FROM inventori WHERE id='" + id + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "ID sudah ada! Gunakan ID lain.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // parse harga setelah validasi
            double harga = Double.parseDouble(hargaText);

            // tambahkan data ke dalam database
            String sqlQuery = "INSERT INTO inventori VALUES ('" + id + "', '" + nama + "', '" + harga + "', '" + kategori + "', '" + kondisi + "')";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error database: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() {
        try {
            // ambil data dari form
            String id = idField.getText();
            String nama = namaField.getText();
            String hargaText = hargaField.getText();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String kondisi = baruRadioButton.isSelected() ? "Baru" : "Bekas";

            // validasi kalau input kosong
            if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty() || kategori.isEmpty() || kondisi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // parse harga setelah validasi
            double harga = Double.parseDouble(hargaText);

            // update data di database
            String sqlQuery = "UPDATE inventori SET nama='" + nama + "', harga='" + harga +
                    "', kategori='" + kategori + "', kondisi='" + kondisi +
                    "' WHERE id='" + id + "'";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        // ambil ID dari textfield
        String id = idField.getText();

        // hapus data dari database
        String sqlQuery = "DELETE FROM inventori WHERE id='" + id + "'";
        database.insertUpdateDeleteQuery(sqlQuery);

        // update tabel
        productTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        baruRadioButton.setSelected(true);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;

    }

}