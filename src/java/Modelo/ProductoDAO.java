/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Config.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AJ-Barreto
 */
public class ProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int respuesta;
    
   
    
    
    public Producto buscar(int id){
        Producto p = new Producto();
        String sql="select * from producto where IdProducto ="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return p;
    }
    
    public int actualizarStock(int id, int stock){
        String sql="update producto set Stock=? where IdProducto =?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return respuesta;
    }
    
    //CRUD
    
    public List listar(){
        String sql="select * from producto";
        List<Producto>lista = new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto em = new Producto();
                em.setId(rs.getInt(1));
                em.setNom(rs.getString(2));
                em.setPrecio(rs.getDouble(3));
                em.setStock(rs.getInt(4));
                em.setEstado(rs.getString(5));
                lista.add(em);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int agregar(Producto em){
        String sql="insert into producto(Nombres, Precio,Stock, Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getNom());
            ps.setDouble(2, em.getPrecio());
            ps.setInt(3, em.getStock());
            ps.setString(4, em.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return respuesta;
    }
    
    public Producto listarId(int id){
        Producto emp = new Producto();
        String sql="select * from producto where idProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                emp.setId(rs.getInt(1));
                emp.setNom(rs.getString(2));
                emp.setPrecio(rs.getDouble(3));
                emp.setStock(rs.getInt(4));
                emp.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return emp;
    }
    
    public int actualizar(Producto em){
        String sql="update producto set Nombres=?, Precio=?, Stock=?, Estado=? where idProducto=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getNom());
            ps.setDouble(2, em.getPrecio());
            ps.setInt(3, em.getStock());
            ps.setString(4, em.getEstado());
            ps.setInt(5, em.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return respuesta;
    }
    
    public void delete(int id){
        String sql="delete from producto where idProducto ="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
