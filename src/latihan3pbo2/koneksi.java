/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan3pbo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class koneksi {
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost/db_daftarkontak";
    String user = "root";
    String password = "";
    
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    
    boolean respons;
    
    public koneksi(){
        try {
            Class.forName(jdbcDriver);
            System.out.println("driver terhubung");
        } catch (ClassNotFoundException ex) {
            System.out.println("driver tidak terhubung");
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("berhasil terkoneksi mysql");
        } catch (SQLException ex) {
            System.out.println("tidak berhasil terkoneksi mysql");
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean insertHp ( String nama, String no_telpon, String email, String alamat){
        String query = "Insert into kontak_hp ( nama, no_telpon, email, alamat) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nama); 
            ps.setString(2, no_telpon);
            ps.setString(3, email);
            ps.setString(4, alamat);
            ps.executeUpdate();
            respons = true;
            System.out.println("berhasil insert");
        } catch (SQLException ex) {
            respons = false;
            System.out.println("tidak berhasil insert");
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respons;
    }
    public ResultSet getAllHp(){
        String query = "SELECT * FROM kontak_hp";
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public boolean updatetHp (String nama, String no_telpon, String email, String alamat){
        String query = "update into kontak_hp set no_telpon = ?, email = ?, alamat =? where nama = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, no_telpon);
            ps.setString(2, email);
            ps.setString(3, alamat);
            ps.setString(4, nama);
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
return respons;
    }
    public void hapusHp (String nama, String no_telpon, String email, String alamat){
        String query = " delete from kontak_hp where nama= ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nama); 
           ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
