package Tugas3;
import java.util.List;
import java.util.Scanner;

public class AppRun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("============================================");
        System.out.println("Selamat datang di Perpustakaan FILKOM");
        System.out.println("============================================");
        System.out.println("\nMenambahkan Buku bawaan perpustakaan");
        System.out.println("--------------------------------------------");
        Perpustakaan perpusFILKOM = new Perpustakaan();
        
        perpusFILKOM.tambahBuku(new Book("Laskar Pelangi", "Andrea Hirata", 2005));
        perpusFILKOM.tambahBuku(new Book("Bumi Manusia", "Pramoedya Ananta Toer", 1980));
        perpusFILKOM.tambahBuku(new Book("Harry Potter", "J.K. Rowling", 1997));
        perpusFILKOM.tambahBuku(new Book("Atomic Habits", "James Clear", 2018));
        perpusFILKOM.tambahBuku(new Book("The Hobbit", "J.R.R. Tolkien", 1937));
        perpusFILKOM.tambahBuku(new Book("Sapiens", "Yuval Noah Harari", 2011));
        perpusFILKOM.tambahBuku(new Book("Rich Dad Poor Dad", "Robert Kiyosaki", 1997));
        
        System.out.println("--------------------------------------------");
        System.out.println("Proses penambahan selesai\n");
        
        boolean kunci = true;
        while(kunci){
            System.out.println("============================================");
            System.out.println("Masukkan angka opsi untuk memilihnya");
            System.out.println("1. Tambahkan Buku");
            System.out.println("2. Tambahkan Anggota");
            System.out.println("3. Cari Buku Berdasarkan Judul");
            System.out.println("4. Cari Buku Berdasarkan Penulis");
            System.out.println("5. Pinjam Buku");
            System.out.println("6. Kembalikan Buku");
            System.out.println("7. keluar");
            System.out.println("============================================");
            System.out.printf("%-28s: ","Masukkan angka opsi");
            int opsi = sc.nextInt(); sc.nextLine();
            switch (opsi){
                case(1):
                    System.out.printf("%-28s: ","Masukkan judul buku");
                    String inputJudul = sc.nextLine();
                    System.out.printf("%-28s: ","Masukkan nama penulis buku");
                    String inputPenulis = sc.nextLine();
                    System.out.printf("%-28s: ","Masukkan tahun terbit buku");
                    int inputTahunTerbit = sc.nextInt();
                    perpusFILKOM.tambahBuku(new Book(inputJudul,inputPenulis,inputTahunTerbit));
                    break;
                case(2):
                    System.out.printf("%-28s: ","Masukkan nama anggota");
                    String inputNama = sc.nextLine();
                    System.out.printf("%-28s: ","Masukkan ID anggota");
                    String inputIDAnggota = sc.nextLine();
                    perpusFILKOM.tambahAnggota(new Anggota(inputNama,inputIDAnggota));
                    break;
                case(3):
                    System.out.printf("%-28s: ","Masukkan judul buku");
                    inputJudul = sc.nextLine();
                    try {
                        Book bookTemp = perpusFILKOM.cariBuku(inputJudul);
                        System.out.println("Berikut informasi tentang buku tersebut: ");
                        System.out.printf("%-12s: %s\n","Judul",bookTemp.getJudul());
                        System.out.printf("%-12s: %s\n","Penulis",bookTemp.getPenulis());
                        System.out.printf("%-12s: %d\n","tahun terbit",bookTemp.getTahunTerbit());
                        if (bookTemp.isStatus()){
                            System.out.printf("%-12s: %s\n","Status","tidak dipinjam");
                        } else {
                            System.out.printf("%-12s: %s%s\n","Status","dipinjam oleh ",bookTemp.getPeminjam());
                        }
                    } catch (BukuTidakDitemukanException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case(4):
                    System.out.printf("%-28s: ","Masukkan nama penulis");
                    inputPenulis = sc.nextLine();
                    try {
                        List<Book> bukuDitemukan = perpusFILKOM.cariBukuByPenulis(inputPenulis);
                        System.out.println("Berikut informasi buku yang ditulis "+inputPenulis+": ");
                        for (Book b: bukuDitemukan){
                            System.out.println("--------------------------------------------");
                            System.out.printf("%-12s: %s\n","Judul",b.getJudul());
                            System.out.printf("%-12s: %s\n","Penulis",b.getPenulis());
                            System.out.printf("%-12s: %d\n","tahun terbit",b.getTahunTerbit());
                            if (b.isStatus()){
                                System.out.printf("%-12s: %s\n","Status","tidak dipinjam");
                            } else {
                                System.out.printf("%-12s: %s%s\n","Status","dipinjam oleh ",b.getPeminjam());
                            }
                        }
                    } catch (BukuTidakDitemukanException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case(5):
                    System.out.printf("%-28s: ","Masukkan ID peminjam");
                    inputIDAnggota = sc.nextLine();
                    System.out.printf("%-28s: ","Masukkan judul buku");
                    inputJudul = sc.nextLine();
                    try {
                        perpusFILKOM.pinjamBuku(inputIDAnggota, inputJudul);
                    } catch (AnggotaTidakDitemukanException e){
                        System.out.println(e.getMessage());
                    } catch (BukuTidakTersediaException e){
                        System.out.println(e.getMessage());
                    } catch (MaksimalBukuTercapaiException e){
                        System.out.println(e.getMessage());
                    } catch (BukuTidakDitemukanException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case(6):
                    System.out.printf("%-28s: ","Masukkan ID peminjam");
                    inputIDAnggota = sc.nextLine();
                    System.out.printf("%-28s: ","Masukkan judul buku");
                    inputJudul = sc.nextLine();
                    try {
                        perpusFILKOM.kembaliBuku(inputIDAnggota, inputJudul);
                    } catch (AnggotaTidakDitemukanException e){
                        System.out.println(e.getMessage());
                    } catch (BukuTidakTersediaException e){
                        System.out.println(e.getMessage());
                    } catch (BukuTidakDipinjamException e){
                        System.out.println(e.getMessage());
                    } catch (BukuTidakDitemukanException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case(7):
                    kunci=false;
                    break;
            }
        }
        System.out.println("Terimakasih sudah menggunakan jasa Perpustakaan FILKOM :D");
    }
}
