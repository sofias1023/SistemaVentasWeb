/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ClientesDAO {
      Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int respuesta;
    
    public Cliente buscar(String dni){
        Cliente c=new Cliente();
        String sql="select * from cliente where Dni='"+dni+"'";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setDir(rs.getString(4));
                c.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("ERROR EN CDAO1: "+e.getMessage());
        }
        return c;
    }
    
    
    
    public List listar(){
        String sql="select * from cliente";
        List<Cliente>lista = new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente em = new Cliente();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setDir(rs.getString(4));
                em.setEstado(rs.getString(5));
                lista.add(em);
            }
        } catch (Exception e) {
            System.out.println("ERROR EN CDAO2: "+e.getMessage());
        }
        return lista;
    }
    
     public int agregar(Cliente em){
        String sql="insert into cliente(Dni, Nombres, Direccion,Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getDir());
            ps.setString(4, em.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR EN CDAO3: "+e.getMessage());
        }
        return respuesta;
    }
    
    public Cliente listarId(int id){
        Cliente emp = new Cliente();
        String sql="select * from cliente where idCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setDir(rs.getString(4));
                emp.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("ERROR EN CDAO4: "+e.getMessage());
        }
        return emp;
    }
    
    public int actualizar(Cliente em){
        String sql="update cliente set Dni=?, Nombres=?, Direccion=?,Estado=? where idCliente=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getDir());
            ps.setString(4, em.getEstado());
            ps.setInt(5, em.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR EN CDAO5: "+e.getMessage());
        }
        return respuesta;
    }
    
    public void delete(int id){
        String sql="delete from cliente where idCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR EN BCDAO6: "+e.getMessage());
        }
    }
    
}
