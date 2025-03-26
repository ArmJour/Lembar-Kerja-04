package Tugas3;

import java.util.ArrayList;
import java.util.List;

public class Anggota {
    private String nama;
    private String ID_Anggota;
    private List<Book> daftarBukuDipinjam;
    private static final int MAX_BUKU_DIPINJAM = 3;

    public Anggota() {

    }

    public Anggota(String nama, String ID_Anggota) {
        this.nama = nama;
        this.ID_Anggota = ID_Anggota;
        this.daftarBukuDipinjam = new ArrayList<>();
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setID_Anggota(String ID_Anggota) {
        this.ID_Anggota = ID_Anggota;
    }

    public void setDaftarBukuDipinjam(List<Book> daftarBukuDipinjam) {
        this.daftarBukuDipinjam = daftarBukuDipinjam;
    }

    public String getNama() {
        return nama;
    }

    public String getID_Anggota() {
        return ID_Anggota;
    }

    public List<Book> getDaftarBukuDipinjam() {
        return daftarBukuDipinjam;
    }

    public void pinjamBuku(Book buku) throws BukuTidakTersediaException, MaksimalBukuTercapaiException {
        if(daftarBukuDipinjam.size() >= MAX_BUKU_DIPINJAM) {
            throw new MaksimalBukuTercapaiException("Anda sudah mencapai batas maksimal peminjaman buku (" + MAX_BUKU_DIPINJAM + " buku)");
        }

        if(buku.isStatus()) {
            buku.pinjamBuku(this.nama);
            daftarBukuDipinjam.add(buku);
            System.out.println(this.nama + " berhasil meminjam buku '" + buku.getJudul() + "'");
        } else {
            throw new BukuTidakTersediaException("Buku tidak tersedia untuk dipinjam (tidak ada atau sudah dipinjam)");
        }
    }

    public void kembaliBuku(Book buku) throws BukuTidakDipinjamException{
        if(daftarBukuDipinjam.contains(buku)) {
            buku.kembaliBuku();
            daftarBukuDipinjam.remove(buku);
            System.out.println(this.nama + " mengembalikan buku '" + buku.getJudul() + "'");
        } else {
            throw new BukuTidakDipinjamException("Anda tidak meminjam buku '" + buku.getJudul() + "'");
        }
    }

    public Book cariBukuDipinjam(String judul) throws BukuTidakDitemukanException {
        for(Book buku : daftarBukuDipinjam) {
            if(buku.getJudul().equalsIgnoreCase(judul)) {
                return buku;
            }
        }
        throw new BukuTidakDitemukanException("Buku dengan judul '" + judul + "' tidak ditemukan");
    }
}
