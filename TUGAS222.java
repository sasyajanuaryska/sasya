import java.util.*;

public class TUGAS222 {

    public static class Menu {
        private String nama;
        private double harga;
        private String kategori;

        public Menu(String nama, double harga, String kategori) {
            this.nama = nama;
            this.harga = harga;
            this.kategori = kategori;
        }


        public String getNama() { return nama; }
        public void setNama(String nama) { this.nama = nama; }

        public double getHarga() { return harga; }
        public void setHarga(double harga) { this.harga = harga; }

        public String getKategori() { return kategori; }
        public void setKategori(String kategori) { this.kategori = kategori; }
    }

    private static List<Menu> menuList = new ArrayList<>();
    private static Map<Menu, Integer> pesanan = new HashMap<>();

    public static void main(String[] args) {

        menuList.add(new Menu("Nasi Goreng", 25000, "Makanan"));
        menuList.add(new Menu("Sate Ayam", 25000, "Makanan"));
        menuList.add(new Menu("Mie Ayam", 10000, "Makanan"));
        menuList.add(new Menu("Bakso", 15000, "Makanan"));
        menuList.add(new Menu("Sop Buntut", 40000, "Makanan"));
        menuList.add(new Menu("Ayam Bakar", 35000, "Makanan"));

        menuList.add(new Menu("Es Teh", 8000, "Minuman"));
        menuList.add(new Menu("Es Jeruk", 10000, "Minuman"));
        menuList.add(new Menu("Jus Alpukat", 15000, "Minuman"));
        menuList.add(new Menu("Jus Mangga", 12000, "Minuman"));
        menuList.add(new Menu("Kopi Hitam", 10000, "Minuman"));
        menuList.add(new Menu("Teh Panas", 5000, "Minuman"));

        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Pesan Makanan/Minuman");
            System.out.println("2. Manajemen Menu");
            System.out.println("3. Keluar");
            int pilihan = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    pesanMenu(sc);
                    break;
                case 2:
                    kelolaMenu(sc);
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void tampilkanMenu() {
        System.out.println("Daftar Menu:");
        for (Menu m : menuList) {
            System.out.println(m.getNama() + " - Rp. " + m.getHarga() + " [" + m.getKategori() + "]");
        }
    }

    public static void pesanMenu(Scanner sc) {
        tampilkanMenu();
        while (true) {
            System.out.print("Masukkan nama menu (atau 'selesai' untuk menyelesaikan): ");
            String namaMenu = sc.nextLine();
            if (namaMenu.equalsIgnoreCase("selesai")) break;

            Menu menu = cariMenu(namaMenu);
            if (menu != null) {
                System.out.print("Masukkan jumlah: ");
                int jumlah = sc.nextInt();
                sc.nextLine(); 
                pesanan.put(menu, pesanan.getOrDefault(menu, 0) + jumlah);
            } else {
                System.out.println("Menu tidak ditemukan.");
            }
        }

        cetakStruk();
    }

    public static Menu cariMenu(String nama) {
        for (Menu m : menuList) {
            if (m.getNama().equalsIgnoreCase(nama)) {
                return m;
            }
        }
        return null;
    }

    public static void cetakStruk() {
        double total = 0;
        System.out.println("Struk Pemesanan:");
        for (Map.Entry<Menu, Integer> entry : pesanan.entrySet()) {
            Menu menu = entry.getKey();
            int jumlah = entry.getValue();
            double hargaItem = menu.getHarga() * jumlah;
            System.out.println(menu.getNama() + " x " + jumlah + " = Rp. " + hargaItem);
            total += hargaItem;
        }

        double pajak = total * 0.1;
        double biayaPelayanan = 20000;

        System.out.println("Total: Rp. " + total);
        System.out.println("Pajak 10%: Rp. " + pajak);
        System.out.println("Biaya Pelayanan: Rp. " + biayaPelayanan);

        total += pajak + biayaPelayanan;

        if (total > 100000) {
            double diskon = total * 0.1;
            total -= diskon;
            System.out.println("Diskon 10%: -Rp. " + diskon);
        } else if (total > 50000) {
            System.out.println("Penawaran Beli 1 Gratis 1 minuman!");
        }

        System.out.println("Total Biaya Keseluruhan: Rp. " + total);
    }

    public static void kelolaMenu(Scanner sc) {
        while (true) {
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            int pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {
                case 1:
                    tambahMenu(sc);
                    break;
                case 2:
                    ubahHargaMenu(sc);
                    break;
                case 3:
                    hapusMenu(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void tambahMenu(Scanner sc) {
        System.out.print("Masukkan nama menu: ");
        String nama = sc.nextLine();
        System.out.print("Masukkan harga menu: ");
        double harga = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Masukkan kategori menu (Makanan/Minuman): ");
        String kategori = sc.nextLine();

        menuList.add(new Menu(nama, harga, kategori));
        System.out.println("Menu berhasil ditambahkan.");
    }

    public static void ubahHargaMenu(Scanner sc) {
        tampilkanMenu();
        System.out.print("Masukkan nama menu yang ingin diubah harganya: ");
        String nama = sc.nextLine();
        Menu menu = cariMenu(nama);

        if (menu != null) {
            System.out.print("Masukkan harga baru: ");
            double hargaBaru = sc.nextDouble();
            sc.nextLine(); 
            menu.setHarga(hargaBaru);
            System.out.println("Harga menu berhasil diubah.");
        } else {
            System.out.println("Menu tidak ditemukan.");
        }
    }

    public static void hapusMenu(Scanner sc) {
        tampilkanMenu();
        System.out.print("Masukkan nama menu yang ingin dihapus: ");
        String nama = sc.nextLine();
        Menu menu = cariMenu(nama);

        if (menu != null) {
            System.out.print("Apakah Anda yakin ingin menghapus menu ini? (Ya/Tidak): ");
            String konfirmasi = sc.nextLine();

            if (konfirmasi.equalsIgnoreCase("Ya")) {
                menuList.remove(menu);
                System.out.println("Menu berhasil dihapus.");
            } else {
                System.out.println("Penghapusan menu dibatalkan.");
            }
        } else {
            System.out.println("Menu tidak ditemukan.");
        }
    }
}
