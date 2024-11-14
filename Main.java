import java.util.Scanner;

class Menu {
    String nama;
    double harga;
    String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }
}

public class Main {
    public static void main(String[] args) {
        Menu[] menu = {
            new Menu("Nasi Goreng", 15000, "Makanan"),
            new Menu("Ayam Gulai", 25000, "Makanan"),
            new Menu("Sate Taican", 25000, "Makanan"),
            new Menu("Pecel", 10000, "Makanan"),
            new Menu("Teh Obeng", 5000, "Minuman"),
            new Menu("Jeruk Peras", 7000, "Minuman"),
            new Menu("Kopi Susu", 15000, "Minuman"),
            new Menu("Teh Hangat", 8000, "Minuman")
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Daftar Menu:");
        for (Menu m : menu) {
            System.out.println(m.getNama() + " - Rp " + m.getHarga() + " (" + m.getKategori() + ")");
        }

        System.out.println("\nMasukkan pesanan Anda (maksimal 4 menu):");
        String[] pesanan = new String[4];
        int[] jumlah = new int[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Pesanan " + (i + 1) + ": ");
            pesanan[i] = scanner.nextLine();
            System.out.print("Jumlah: ");
            jumlah[i] = scanner.nextInt();
            scanner.nextLine(); 
        }

        double totalBiaya = 0;
        for (int i = 0; i < 4; i++) {
            for (Menu m : menu) {
                if (m.getNama().equalsIgnoreCase(pesanan[i])) {
                    totalBiaya += m.getHarga() * jumlah[i];
                }
            }
        }

        double pajak = totalBiaya * 0.1;
        double biayaPelayanan = 20000;
        double totalBiayaAkhir = totalBiaya + pajak + biayaPelayanan;

        if (totalBiaya > 100000) {
            totalBiayaAkhir *= 0.9;
        } else if (totalBiaya > 50000) {
            
            for (int i = 0; i < 4; i++) {
                for (Menu m : menu) {
                    if (m.getNama().equalsIgnoreCase(pesanan[i]) && m.getKategori().equalsIgnoreCase("Minuman")) {
                        totalBiayaAkhir -= m.getHarga();
                        break;
                    }
                }
            }
        }

        System.out.println("\nStruk Pesanan:");
        for (int i = 0; i < 4; i++) {
            for (Menu m : menu) {
                if (m.getNama().equalsIgnoreCase(pesanan[i])) {
                    System.out.println(m.getNama() + " x " + jumlah[i] + " = Rp " + (m.getHarga() * jumlah[i]));
                }
            }
        }
        System.out.println("Total Biaya: Rp " + totalBiaya);
        System.out.println("Pajak (10%): Rp " + pajak);
        System.out.println("Biaya Pelayanan: Rp " + biayaPelayanan);
        System.out.println("Total Biaya Akhir: Rp " + totalBiayaAkhir);
    }
}