import javax.swing.*;
import java.util.*;

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
    viaBayar payment = new viaBayar();
    dataDiri(){}
    dataDiri(String nama, String JK, int umur, int ktp, String email,int hp, String pembayaran, int poin, String kodeTiket){
        this.nama = nama;
        this.JK = JK;
        this.umur = umur;
        this.ktp = ktp;
        this.hp = hp;
        this.pembayaran = pembayaran;
        this.poin = poin;
        this.kodeTiket = kodeTiket;
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
    int atm;
    int mobilebanking;
    int minimarket;
    int visa;
    viaBayar(){}
    public viaBayar(int atm, int mobilebanking, int minimarket, int visa){
        this.atm = atm;
        this.mobilebanking = mobilebanking;
        this.minimarket = minimarket;
        this.visa = visa;
    }
}

public class Tiket_kereta{
    static ArrayList<kereta> ArrKereta =  new ArrayList<kereta>();
    static ArrayList<dataDiri> Arrperson = new ArrayList<dataDiri>();

    public static dataDiri inputDataPenumpang(){
        dataDiri data = new dataDiri();
        data.nama = JOptionPane.showInputDialog(null, "Masukkan Nama : ");
        data.JK = JOptionPane.showInputDialog(null, "Masukkan Jenis Kelamin : ");
        data.umur = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan Umur : "));
        data.ktp = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan No.KTP : "));
        data.email = JOptionPane.showInputDialog(null, "Masukkan e-mail : ");
        data.hp = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan No.Handphone : "));
        return data;
    }

    public static void registerPenumpang(dataDiri dataPenumpang){
        String namaKereta = JOptionPane.showInputDialog(null, "Masukkan nama Kereta : "); 
        String asal = JOptionPane.showInputDialog(null, "Masukkan asal kota anda : "); 
        String tujuan = JOptionPane.showInputDialog(null, "Masukkan tujuan kota anda : "); 
        
        int tanggalKeberangkatan = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan tanggal keberangkatan : "));
        String bulanKeberangkatan = JOptionPane.showInputDialog(null, "Masukkan bulan keberangkatan : ");
        int tahunKeberangkatan = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan tahun keberangkatan : "));
        int jamKeberangkatan = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan jam keberangkatan : "));
        String pilihanKereta = JOptionPane.showInputDialog(null, "Masukkan kelas kereta : ");
    
        String KTP = Integer.toString(dataPenumpang.ktp);
        String tanggal = Integer.toString(tanggalKeberangkatan);
        String jam = Integer.toString(jamKeberangkatan);
        String codeBook = 
            KTP.substring(0,2) +
            namaKereta.substring(0,1) + 
            asal.substring(0,1) + 
            tujuan.substring(0,1) + 
            tanggal.substring(0) + 
            pilihanKereta.substring(0,1) + 
            jam.substring(0);
        
        //Search jadwal kereta
        for(int i=0; i<ArrKereta.size() ; i++){
            kereta tempKereta = ArrKereta.get(i);
            if(tempKereta.namaKereta.equals(namaKereta) && tempKereta.tujuanKota.equals(tujuan) && tempKereta.asalKota.equals(asal) &&
               tempKereta.dataWaktu.tanggal == tanggalKeberangkatan && tempKereta.dataWaktu.bulan.equals(bulanKeberangkatan) && 
               tempKereta.dataWaktu.tahun == tahunKeberangkatan && tempKereta.dataWaktu.jam == jamKeberangkatan){
                boolean[] status;
                dataDiri[] personalData;
                int jml = 0;   
                //Harga tiket per kelas
                if(pilihanKereta.equals("Eksekutif")){
                    status = tempKereta.booleanStatusEksekutif;
                    personalData = tempKereta.personalDataEksekutif;
                    jml = tempKereta.jumlahEksekutif;
                }else{
                    status = tempKereta.booleanStatusReguler;
                    personalData = tempKereta.personalDataReguler;
                    jml = tempKereta.jumlahReguler;
                }
                   
                //Cari Kursi
                for(int j=0; j<jml ; j++){
                    if(status[j] == false){//false = kursi tersedia
                        int biaya = tempKereta.biayaTiket;
                        int poin = (biaya*3)/100;
                        String pilihan = JOptionPane.showInputDialog(null, "Tiket kereta ditemukan dengan biaya :  " + biaya + "\nApakah ingin menggunakan poin? (Yes/No) : ");
                        if(pilihan.equals("Yes")){
                            if(biaya - dataPenumpang.poin <= 0){ // kalau biayanya masih sisa jadi tidak cukup
                                dataPenumpang.poin = dataPenumpang.poin - biaya;
                                JOptionPane.showMessageDialog(null, "Kode Tiket : " + codeBook);
                                status[j] = true;
                                personalData[j] = dataPenumpang;
                                return;                                        
                            }else{
                                JOptionPane.showMessageDialog(null, "Poin tidak mencukupi!",  "Alert", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        //--- via bayar
                        via_bayar(dataPenumpang);
                        dataPenumpang.poin += poin;
                        dataPenumpang.kodeTiket = codeBook;
                        personalData[j] = dataPenumpang; 
                        status[j] = true;
                        JOptionPane.showMessageDialog(null, "Kode Tiket : " + codeBook);
                        return;
                    }
                }
           }
        }
        JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public static void via_bayar(dataDiri dataPenumpang) {
        int pilih = Integer.parseInt(
            JOptionPane.showInputDialog(null, 
                "Masukkan metode pembayaran:\n" + 
                "1. ATM\n" + 
                "2. Minimarket\n" +
                "3. Mobile banking\n" +
                "4. Visa\n")
            );
        if(pilih == 1){
            dataPenumpang.payment.atm = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukan nomor kartu atm : "));
            JOptionPane.showMessageDialog(null, "Via pembayaran atm berhasil!");
        }else if(pilih == 2){
            dataPenumpang.payment.minimarket = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan nomor kartu keanggotaan minimarket : "));
            JOptionPane.showMessageDialog(null, "Via pembayaran minimarket berhasil!");
        }else if(pilih == 3){
            dataPenumpang.payment.mobilebanking = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan nomor virtual account : "));
            JOptionPane.showMessageDialog(null, "Via pembayaran mobile banking berhasil!");
        }else if(pilih == 4){
            dataPenumpang.payment.visa = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan nomor kartu kredit : "));
            JOptionPane.showMessageDialog(null, "Via pembayaran kartu kredit berhasil!");
        }
    }

    public static void editTiket(){
        String pilihan = JOptionPane.showInputDialog(null, "Apakah ingin mengedit data? (Yes/No) : ");
        if(pilihan.equals("Yes")){
            //Search data pemumpang
            String kelasKereta = JOptionPane.showInputDialog(null, "Masukkan kelas kereta : ");
            String Name = JOptionPane.showInputDialog(null, "Masukkan Nama : ");
            int KTP =Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan Nomor KTP : "));
            boolean stat = true;
            for(int i=0; i<ArrKereta.size() && stat == true; i++){
                kereta tempKereta = ArrKereta.get(i);
                if(kelasKereta.equals("Eksekutif")){
                    dataDiri[] person = tempKereta.personalDataEksekutif;
                    for(int j=0; j<person.length && stat == true ; j++){
                        if(person[j].nama == null){
                            break;
                        }else if(person[j].nama.equals(Name) && person[j].ktp == KTP){
                            JOptionPane.showMessageDialog(null, "Data " + person[j].nama + " ditemukan!");
                            person[j].nama = JOptionPane.showInputDialog(null,"Nama : ");
                            person[j].JK = JOptionPane.showInputDialog(null,"Jenis Kelamin : ");
                            person[j].umur = Integer.parseInt(JOptionPane.showInputDialog(null, "Umur : "));
                            person[j].ktp = Integer.parseInt(JOptionPane.showInputDialog(null, "No.KTP : "));
                            person[j].email = JOptionPane.showInputDialog(null,"Email : ");
                            person[j].hp = Integer.parseInt(JOptionPane.showInputDialog(null, "No.HP : "));
                            stat = false;//false = sudah ditemukan dan di edit
                        }
                        
                    }
                }else if(kelasKereta.equals("Reguler")){
                    dataDiri[] person = tempKereta.personalDataReguler;
                    for(int j=0; j<person.length && stat == true; j++){
                        if(person[j].nama == null){
                            break;
                        } else  if(person[j].nama.equals(Name) && person[j].ktp == KTP){
                            JOptionPane.showMessageDialog(null, "Data " + person[j].nama + " ditemukan!");
                            person[j].nama = JOptionPane.showInputDialog(null,"Nama : ");
                            person[j].JK = JOptionPane.showInputDialog(null,"Jenis Kelamin : ");
                            person[j].umur = Integer.parseInt(JOptionPane.showInputDialog(null, "Umur : "));
                            person[j].ktp = Integer.parseInt(JOptionPane.showInputDialog(null, "No.KTP : "));
                            person[j].email = JOptionPane.showInputDialog(null,"Email : ");
                            person[j].hp = Integer.parseInt(JOptionPane.showInputDialog(null, "No.HP : "));
                            stat = false;                  
                        }
                    }
                }
            }
            if(stat){
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    static void pembatalanTiket(){
        String kelasKereta = JOptionPane.showInputDialog(null, "Masukkan kelas kereta : ");
        String Kode = JOptionPane.showInputDialog(null, "Masukkan kode tiket : ");
        boolean stat = true;
        for(int i=0; i<ArrKereta.size() && stat == true; i++){
            kereta Kereta = ArrKereta.get(i);
            if(kelasKereta.equals("Eksekutif")){
                dataDiri[] person = Kereta.personalDataEksekutif;
                for(int j=0; j<Kereta.booleanStatusEksekutif.length; j++){
                    if(person[j].kodeTiket.equals(Kode) && Kereta.booleanStatusEksekutif[j] == true){
                        Kereta.booleanStatusEksekutif[j] = false;
                        break;
                    }
                }     
            }else{
                dataDiri[] person = Kereta.personalDataReguler;
                for(int j=0; j<Kereta.booleanStatusReguler.length; j++){
                    if(person[j].kodeTiket.equals(Kode) && Kereta.booleanStatusReguler[j] == true){
                        Kereta.booleanStatusReguler[j] = false;
                        break;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus dari sistem");
    }
    
    static void printTiket(){
        String kelasKereta = JOptionPane.showInputDialog(null, "Masukkan kelas kereta : ");
        String code = JOptionPane.showInputDialog(null, "Masukkan kode tiket : ");
        boolean stat = true;
        for(int i=0; i<ArrKereta.size() && stat == true; i++){
            kereta Kereta = ArrKereta.get(i);
            if(kelasKereta.equals("Eksekutif")){
                dataDiri[] person = Kereta.personalDataEksekutif;
                for(int j=0; j<Kereta.booleanStatusEksekutif.length; j++){
                    if(person[j].kodeTiket.equals(code) && Kereta.booleanStatusEksekutif[j] == true){
                        JOptionPane.showMessageDialog(null, 
                        "Nomor tiket\t: " + person[j].kodeTiket +"\n" +
                        "Nama\t\t\t: " + person[j].nama + "\n" + 
                        "Asal kota\t\t: " + Kereta.asalKota + "\n" +  
                        "Kota Tujuan\t: " + Kereta.tujuanKota + "\n" + 
                        "Tanggal\t\t: " + Kereta.dataWaktu.tanggal + " " + Kereta.dataWaktu.bulan + " " + Kereta.dataWaktu.tahun + " " + "\n" +
                        "Pukul\t\t\t: " + Kereta.dataWaktu.jam + "\n" +
                        "Nama Kereta\t: " + Kereta.namaKereta + "\n" +
                        "Kelas Kereta\t: " + kelasKereta + "\n" +
                        "Poin\t\t\t: " + person[j].poin
                        );
                        return;
                    }
                }     
            }else{
                dataDiri[] person = Kereta.personalDataReguler;
                for(int j=0; j<Kereta.booleanStatusReguler.length; j++){
                    if(person[j].kodeTiket.equals(code) && Kereta.booleanStatusReguler[j] == true){
                        JOptionPane.showMessageDialog(null, 
                        "Nomor tiket\t: " + person[j].kodeTiket +"\n" +
                        "Nama\t\t\t: " + person[j].nama + "\n" + 
                        "Asal kota\t\t: " + Kereta.asalKota + "\n" +  
                        "Kota Tujuan\t: " + Kereta.tujuanKota + "\n" + 
                        "Tanggal\t\t: " + Kereta.dataWaktu.tanggal + " " + Kereta.dataWaktu.bulan + " " + Kereta.dataWaktu.tahun + " " + "\n" +
                        "Pukul\t\t\t: " + Kereta.dataWaktu.jam + "\n" +
                        "Nama Kereta\t: " + Kereta.namaKereta + "\n" +
                        "Kelas Kereta\t: " + kelasKereta + "\n" +
                        "Poin\t\t\t: " + person[j].poin
                        );
                        return;
                    }
                }
            }
        }
    JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Alert", JOptionPane.WARNING_MESSAGE);
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
                "4. Print Tiket Kereta\n"
            )
        );
        switch (Menu) {
            case 1:
                String choose = JOptionPane.showInputDialog(null, "Apakah Member?(Yes/No): ");
                if(choose.equals("No")){
                    dataDiri newDataDD = inputDataPenumpang();
                    Arrperson.add(newDataDD);
                    registerPenumpang(Arrperson.get(Arrperson.size()-1));    
                }else{
                    String email = JOptionPane.showInputDialog(null, "Email: ");
                    for(int i=0; i<Arrperson.size(); i++){
                        if(Arrperson.get(i).email.equals(email)){
                            registerPenumpang(Arrperson.get(i));
                            break;
                        }
                    }
                }
                break;
            case 2:
                editTiket();
                break;
            case 3:
                pembatalanTiket();
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
        kereta kereta2 = new kereta("Taksaka", "Yogyakarta", "Gambir", 5, 1, waktu_kereta2, 135000);
        ArrKereta.add(kereta2);
        waktu waktu_kereta3 = new waktu(9, "Juni", 2020, 8);
        kereta kereta3 = new kereta("Ciremai", "Bandung", "Semarang", 5, 5, waktu_kereta3, 1000);
        ArrKereta.add(kereta3);
        waktu waktu_kereta4 = new waktu(13, "Mei", 2020, 15);
        kereta kereta4 = new kereta("Sancaka", "Yoyakarta", "Surabaya", 5, 5, waktu_kereta4, 120000);
        ArrKereta.add(kereta4);
        waktu waktu_kereta5 = new waktu(20, "Juli", 2020, 10);
        kereta kereta5 = new kereta("Ranggajati", "Jember", "Cirebon", 5, 5, waktu_kereta5, 175000);
        ArrKereta.add(kereta5);
        //Dummy data person
        dataDiri datadiri1 = new dataDiri("Elangel", "P", 18, 17, "elangel@mail.com" , 1 , "", 0, "AA11BC");
        kereta1.booleanStatusReguler[0] = true;
        kereta1.personalDataReguler[0] = datadiri1;
        //menu pada program
        allData();
    }
}