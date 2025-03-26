package Tugas3;

import java.util.*;

public class Perpustakaan {
    private List<Book> daftarBuku;
    private Map<String, Anggota> daftarAnggota;

    public Perpustakaan() {
        this.daftarBuku = new ArrayList<>();
        this.daftarAnggota = new HashMap<>();
    }

    // Getter
    public List<Book> getDaftarBuku() {
        return new ArrayList<>(daftarBuku);
    }

    public Map<String, Anggota> getDaftarAnggota() {
        return new HashMap<>(daftarAnggota);
    }

    public void tambahBuku(Book buku) {
        daftarBuku.add(buku);
        System.out.println("Buku '"+buku.getJudul()+"' telah ditambah!");
    }

    public void tambahAnggota(Anggota anggota) {
        daftarAnggota.put(anggota.getID_Anggota(), anggota);
        System.out.println(anggota.getNama()+" Berhasil ditambah");
    }

    public void pinjamBuku(String ID_Anggota, String judul) throws AnggotaTidakDitemukanException, BukuTidakTersediaException, MaksimalBukuTercapaiException, BukuTidakDitemukanException {
        Anggota anggota = daftarAnggota.get(ID_Anggota);
        if(anggota == null) {
            throw new AnggotaTidakDitemukanException("Anggota dengan ID " + ID_Anggota + " tidak ditemukan");
        }
        Book bukuDipinjam = cariBuku(judul);
        anggota.pinjamBuku(bukuDipinjam);
    }

    public void kembaliBuku(String ID_Anggota, String judul) throws AnggotaTidakDitemukanException, BukuTidakTersediaException, BukuTidakDipinjamException, BukuTidakDitemukanException {
        Anggota anggota = daftarAnggota.get(ID_Anggota);
        if(anggota == null) {
            throw new AnggotaTidakDitemukanException("Anggota dengan ID " + ID_Anggota + " tidak ditemukan");
        }

        Book bukuDikembalikan = anggota.cariBukuDipinjam(judul);
        
        anggota.kembaliBuku(bukuDikembalikan);
    }

    public Book cariBuku(String judul) throws BukuTidakDitemukanException {
        for(Book buku : daftarBuku) {
            if(buku.getJudul().equalsIgnoreCase(judul)) {
                return buku;
            }
        }
        throw new BukuTidakDitemukanException("Buku dengan judul '" + judul + "' tidak ditemukan");
    }
    
    public List<Book> cariBukuByPenulis(String penulis) throws BukuTidakDitemukanException {
        List<Book> bukuDitemukan = new ArrayList<>();
        for (Book buku : daftarBuku) {
            if (buku.getPenulis().equalsIgnoreCase(penulis)) {
                bukuDitemukan.add(buku);
            }
        }
        if (bukuDitemukan.isEmpty()) {
            throw new BukuTidakDitemukanException("Tidak ada buku yang ditulis oleh '" + penulis + "'");
        }

        return bukuDitemukan;
    }

}
