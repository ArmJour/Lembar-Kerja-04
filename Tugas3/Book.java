package Tugas3;

public class Book {
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private boolean status;
    private String peminjam;

    public Book(String judul, String penulis, int tahunTerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.status = true;
        this.peminjam = null;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public boolean isStatus() {
        return status;
    }

    public String getPeminjam() {
        return peminjam;
    }

    public void pinjamBuku(String namaPeminjam) throws BukuTidakTersediaException {
        if (!status) {
            throw new BukuTidakTersediaException("Buku '" + judul + "' sedang dipinjam oleh " + peminjam);
        }
        status = false;
        this.peminjam = namaPeminjam;
        System.out.println("Buku '" + judul + "' berhasil dipinjam oleh " + namaPeminjam);
    }

    public void kembaliBuku() throws BukuTidakDipinjamException {
        if (status) {
            throw new BukuTidakDipinjamException("Buku '" + judul + "' tidak sedang dipinjam");
        }
        status = true;
        System.out.println("Buku '" + judul + "' berhasil dikembalikan oleh " + peminjam);
        peminjam = null;
    }
}
