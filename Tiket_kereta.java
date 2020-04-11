import javax.swing.*;
import java.time.temporal.Temporal;
import java.util.ArrayList;

class kereta{
    String namaKereta;
    String asalKota;
    String tujuanKota;
    int jumlahEksekutif;
    int jumlahReguler;
    boolean[] booleanStatusEksekutif;
    boolean[] booleanStatusReguler;
    dataDiri[] personalDataEksekutif;
    dataDiri[] personalDataReguler;
    waktu dataWaktu;
    int biayaTiket;
    public kereta(String namaKereta, String asalKota, String tujuanKota, int jumlahEksekutif, int jumlahReguler, waktu dataWaktu, int biayaTiket){
        this.namaKereta = namaKereta;
        this.asalKota = asalKota;
        this.tujuanKota = tujuanKota;
        this.jumlahEksekutif = jumlahEksekutif;
        this.jumlahReguler = jumlahReguler;
        booleanStatusReguler = new boolean [jumlahReguler];
        booleanStatusEksekutif = new boolean [jumlahEksekutif];
        personalDataEksekutif = new dataDiri [jumlahEksekutif];
        personalDataReguler = new dataDiri [jumlahReguler];
        for(int i=0; i<jumlahEksekutif; i++){
            personalDataEksekutif[i] = new dataDiri();
        }
        for(int j=0; j<jumlahReguler; j++){
            personalDataReguler[j] = new dataDiri();
        }
        this.dataWaktu = dataWaktu;
        this.biayaTiket = biayaTiket;
    }
}

class dataDiri{
    String nama;
    String JK;
    int umur;
    int ktp;
    String email;
    int hp;
    String pembayaran;
    int poin;
    String kodeTiket;
    viaBayar payment;
    dataDiri(){}
    dataDiri(String nama, String JK, int umur, int ktp, String email,int hp, String pembayaran, int poin){
        this.nama = nama;
        this.JK = JK;
        this.umur = umur;
        this.ktp = ktp;
        this.hp = hp;
        this.pembayaran = pembayaran;
        this.poin = poin;
    }
}

class waktu{
    int tanggal;
    String bulan;
    int tahun;
    int jam;
    public waktu(int tanggal, String bulan, int tahun, int jam){
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jam = jam;
    }
}

class viaBayar{
    int atm;//nanti dibagi lagi jd bri,bca, dll
    int mobilebanking;// mobile transfer bca,mandiri dll
    int minimarket;// alfa indomart
    int visa;//kredit,tagihan akhir bulan,masukin nomor kartu
    int kantorpos;//dikasih kode,bayar di kantor pos terdekat
    //nnti aja hapusnya,biar ada bayangan ok
    public viaBayar(int atm, int mobilebanking, int minimarket, int visa, int kantorpos){
        this.atm = atm;//ell test keluar ga
        this.mobilebanking = mobilebanking;
        this.minimarket = minimarket;
        this.visa = visa;
        this.kantorpos = kantorpos; 
    }
}

public class Tiket_kereta{
    static ArrayList<kereta> ArrKereta =  new ArrayList<kereta>();
    static ArrayList<dataDiri> Arrperson = new ArrayList<dataDiri>();

    public static dataDiri inputDataPenumpang(){
        dataDiri data = new dataDiri();
        data.nama = JOptionPane.showInputDialog(null, "Masukkan Nama: ");
        data.JK = JOptionPane.showInputDialog(null, "Masukkan Jenis Kelamin: ");
        data.umur = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan Umur: "));
        data.ktp = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan No.KTP: "));
        data.email = JOptionPane.showInputDialog(null, "Masukkan e-mail: ");
        data.hp = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan No.Handphone:"));
        return data;
    }

    public static void registerPenumpang(dataDiri dataPenumpang){
        String namaKereta = JOptionPane.showInputDialog(null, "Masukkan nama Kereta: "); 
        String asal = JOptionPane.showInputDialog(null, "Masukkan asal kota anda: "); 
        String tujuan = JOptionPane.showInputDialog(null, "Masukkan tujuan kota anda: "); 
        
        int tanggalKeberangkatan = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan tanggal keberangkatan: "));
        String bulanKeberangkatan = JOptionPane.showInputDialog(null, "Masukkan bulan keberangkatan: ");
        int tahunKeberangkatan = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan tahun keberangkatan: "));
        int jamKeberangkatan = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan jam keberangkatan: "));
        String pilihanKereta = JOptionPane.showInputDialog(null, "Masukkan kelas kereta: ");
        // String pembayaran = JOptionPane.showInputDialog(null, "Masukkan jenis pembayaran yang anda pakai: ");
       
        //Search jadwal kereta
        for(int i=0; i<ArrKereta.size() ; i++){
            kereta tempKereta = ArrKereta.get(i);
            if(tempKereta.tujuanKota.equals(tujuan) && tempKereta.asalKota.equals(asal) &&
               tempKereta.dataWaktu.tanggal == tanggalKeberangkatan && tempKereta.dataWaktu.bulan.equals(bulanKeberangkatan) && 
               tempKereta.dataWaktu.tahun == tahunKeberangkatan && tempKereta.dataWaktu.jam == jamKeberangkatan){
                //Harga tiket per kelas
                if(pilihanKereta.equals("Eksekutif")){
                    for(int j=0; j<tempKereta.booleanStatusEksekutif.length; j++){
                        if(tempKereta.booleanStatusEksekutif[j] == false){
                            int biaya = tempKereta.biayaTiket;
                            int poin = tempKereta.biayaTiket * (3/100);
                            String pilihan = JOptionPane.showInputDialog(null, "Apakah ingin menggunakan poin?(Yes/No): ");
                            if(pilihan.equals("Yes")){
                                biaya = biaya - dataPenumpang.poin;
                                if(biaya <= 0){
                                    dataPenumpang.poin = dataPenumpang.poin - biaya;
                                    tempKereta.booleanStatusEksekutif[j] = false;
                                    tempKereta.personalDataEksekutif[j] = dataPenumpang;                                        
                                    break;
                                }else{
                                    JOptionPane.showMessageDialog(null, "Poin tidak mencukupi!",  "Alert", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Tiket kereta ditemukan dengan biaya " + biaya);
                            dataPenumpang.poin = dataPenumpang.poin + poin;
                            tempKereta.booleanStatusEksekutif[j] = false;
                            tempKereta.personalDataEksekutif[j] = dataPenumpang;                                        
                            break; 
                        }
                    }
                    break;
                }else if(pilihanKereta.equals("Reguler")){
                    for(int j=0; j<tempKereta.booleanStatusReguler.length; j++){
                        if(tempKereta.booleanStatusReguler[j] == false){
                            int biaya = tempKereta.biayaTiket;
                            int poin = tempKereta.biayaTiket * (3/100);
                            String pilihan = JOptionPane.showInputDialog(null, "Apakah ingin menggunakan poin?(Yes/No): ");
                            if(pilihan.equals("Yes")){
                                biaya = biaya - dataPenumpang.poin;
                                if(biaya <= 0){
                                    dataPenumpang.poin = dataPenumpang.poin - biaya;
                                    tempKereta.booleanStatusReguler[j] = false;
                                    tempKereta.personalDataReguler[j] = dataPenumpang;                                        
                                    break;
                                }else{
                                    JOptionPane.showMessageDialog(null, "Poin tidak mencukupi!",  "Alert", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Tiket kereta ditemukan dengan biaya " + biaya);
                            dataPenumpang.poin = dataPenumpang.poin + poin;
                            tempKereta.booleanStatusReguler[j] = false;
                            tempKereta.personalDataReguler[j] = dataPenumpang;                                        
                            break;
                        }
                    }
                    break;
                }
           }
           JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void editTiket(kereta dataPenumpang){
        String pilihan = JOptionPane.showInputDialog(null, "Apakah ingin mengedit data?(Yes/No): ");
        if(pilihan.equals("Yes")){
            //Search data pemumpang
            String kelasKereta = JOptionPane.showInputDialog(null, "Masukkan kelas kereta: ");
            String Name = JOptionPane.showInputDialog(null, "Masukkan Nama: ");
            int KTP =Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan Nomor KTP: "));
            for(int i=0; i<ArrKereta.size(); i++){
                kereta tempKereta = ArrKereta.get(i);
                if(kelasKereta.equals("Eksekutif")){
                    dataDiri[] person = tempKereta.personalDataReguler;
                    for(int j=0; j<person.length; j++){
                        if(person[i].nama.equals(Name) && person[i].ktp == KTP){
                            //edit data jika ditemukan
                            JOptionPane.showInputDialog(null,"Nama: " + person[i].nama);
                            JOptionPane.showInputDialog(null,"Jenis Kelamin: " + person[i].JK);
                            Integer.parseInt(showInputDialog(null, "Umur: ", person[i].umur));
                            Integer.parseInt(JOptionPane.showInputDialog(null, "No.KTP", person[i].ktp));
                            JOptionPane.showInputDialog(null,"Email: " + person[i].email);
                            Integer.parseInt(JOptionPane.showInputDialog(null, "No.HP: ", person[i].hp));
                            return;
                        }
                        
                    }
                }else if(kelasKereta.equals("Reguler")){
                    dataDiri[] person = tempKereta.personalDataReguler;
                    for(int j=0; j<person.length; j++){
                        if(person[i].nama.equals(Name) && person[i].ktp == KTP){
                            //edit data jika ditemukan
                            JOptionPane.showInputDialog(null,"Nama: " + person[i].nama);
                            JOptionPane.showInputDialog(null,"Jenis Kelamin: " + person[i].JK);
                            Integer.parseInt(showInputDialog(null, "Umur: ", person[i].umur));
                            Integer.parseInt(JOptionPane.showInputDialog(null, "No.KTP", person[i].ktp));
                            JOptionPane.showInputDialog(null,"Email: " + person[i].email);
                            Integer.parseInt(JOptionPane.showInputDialog(null, "No.HP: ", person[i].hp));
                            return;
                        }
                        
                    }
                    
                }
            }
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static boolean pembatalanTiket(kereta dataPenumpang) {
        return true;
    }

    static void printTiket(){
        /*
        String nomorTiket = JOptionPane.showInputDialog(null, "Masukkan kode tiket: ");
        boolean cekCode = false;
        for(int i=0; i<ArrKereta.size(); i++){
            kereta tempKode = ArrKereta.get(i);
            if (tempKode.kodeTiket.equals(nomorTiket)){
                cekCode = true;
                break;
            }
        }
        if(cekCode == true){
            for(int i=0; i<ArrKereta.size(); i++){
                for(int j=0; j<ArrKereta.get(i).personalDataEksekutif; j++){
                    kereta[] Kereta = ArrKereta.get(i);
                    
                    JOptionPane.showMessageDialog(null, 
                        "Nomor tiket " +  Kereta[i].kodeTiket +"\n" +
                        "Nama: " + orang[j].nama + "\n" + 
                        "Asal kota: " + Kereta[i].asalKota + "\n" +  
                        "Kota Tujuan: " + Kereta[i].tujuanKota + "\n" + 
                        "Tanggal: " + Kereta[i].dataWaktu.tanggal + " " + Kereta[i].dataWaktu.bulan + " " + Kereta[i].dataWaktu.tahun + " " + "\n" +
                        "Pukul: " + Kereta[i].dataWaktu.jam + "\n" +
                        "Nama Kereta: " + Kereta[i].namaKereta + "\n" +
                        //kelas kereta
                        //via bayar
                        "Poin: " + orang[j].poin
                    );
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Alert", JOptionPane.WARNING_MESSAGE);
        */
    }

public static void allData(){
    OUTER:
    while (true) {
        int Menu = Integer.parseInt(
            JOptionPane.showInputDialog(
                "Program Tiket Kereta:\n" + 
                "1. Pesan Tiket Kereta\n" + 
                "2. Edit Data Pemesanan Tiket Kereta\n" + 
                "3. Batalkan Pemesanan Tiket Kereta\n" + 
                "4, Print Tiket Kereta\n"
            )
        );
        switch (Menu) {
            case 1:
                dataDiri newDataDD = inputDataPenumpang();
                Arrperson.add(newDataDD);
                for(int i=0; i<Arrperson.size(); i++){
                    registerPenumpang(Arrperson.get(i));
                }
                break;
            case 2:
                for(int i=0; i<ArrKereta.size(); i++){
                    editTiket(ArrKereta.get(i));
                }
                break;
            case 3:
                
                break;
            case 4:
                printTiket();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Input Salah!", "Alert", JOptionPane.WARNING_MESSAGE);
                break OUTER;
        }
    }
}
    public static void main(String[] args) {
        //Dummy Data Jasdwal Kereta
        waktu waktu_kereta1 = new waktu(17, "Agustus", 2020, 7);
        kereta kereta1 = new kereta("Harina", "Surabaya", "Bandung", 5, 5, waktu_kereta1, 150000);
        ArrKereta.add(kereta1);
        waktu waktu_kereta2 = new waktu(6, "April", 2020, 9);
        kereta kereta2 = new kereta("Taksaka", "Yoyakarta", "Gambir", 5, 5, waktu_kereta2, 135000);
        ArrKereta.add(kereta2);
        waktu waktu_kereta3 = new waktu(9, "Juni", 2020, 8);
        kereta kereta3 = new kereta("Ciremai", "Bandung", "Semarang", 5, 5, waktu_kereta3, 150000);
        ArrKereta.add(kereta3);
        waktu waktu_kereta4 = new waktu(13, "Mei", 2020, 15);
        kereta kereta4 = new kereta("Sancaka", "Yoyakarta", "Surabaya", 5, 5, waktu_kereta4, 120000);
        ArrKereta.add(kereta4);
        waktu waktu_kereta5 = new waktu(20, "Juli", 2020, 10);
        kereta kereta5 = new kereta("Ranggajati", "Jember", "Cirebon", 5, 5, waktu_kereta5, 175000);
        ArrKereta.add(kereta5);
        //Dummt data person
        //datadiri(String nama, String JK, int umur, int ktp, String email,int hp, String pembayaran, int poin)
        dataDiri datadiri1 = new dataDiri("Elangel", "P", 18, 17, "elangel@mail.com" , 1 , "", 0);
        kereta1.booleanStatusReguler[0] = true;
        kereta1.personalDataReguler[0] = datadiri1;
        //menu pada program
        //djsd
        allData();
    }
    //Dari el
    //hilbert? :v
}

