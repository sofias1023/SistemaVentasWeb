/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Config.GenerarSerie;
import Modelo.Cliente;
import Modelo.ClientesDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class Controlador extends HttpServlet {
        Empleado em=new Empleado();
        EmpleadoDAO edao=new EmpleadoDAO();
        int ide;
        ClientesDAO cdao=new ClientesDAO();
        Producto pr = new Producto();
        ProductoDAO pdao = new ProductoDAO();
        Cliente cl = new Cliente();
        Cliente c = new Cliente();
        Cliente cli = new Cliente();
        Venta v=new Venta();
        List<Venta>lista=new ArrayList<>();
        int item;
        int cod;
        String descripcion;
        double precio;
        int cant;
        double subtotal;
        double totalpagar;
        
        String numeroserie;
        
        VentaDAO vdao=new VentaDAO();
      
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu=request.getParameter("menu");
         String accion=request.getParameter("accion");
         if(menu.equals("Principal")){
             request.getRequestDispatcher("Principal.jsp").forward(request, response);
         }
         if(menu.equals("Empleado")){
             switch(accion){
                 case"Listar":
                     List lista=edao.listar(); 
                     request.setAttribute("empleados", lista);
                     break;
                 case"Agregar":
                     String dni=request.getParameter("txtDni");
                     String nom=request.getParameter("txtNombres");
                     String tel=request.getParameter("txtTel");
                     String est=request.getParameter("txtEstado");
                     String user=request.getParameter("txtUsuario");
                     em.setDni(dni);
                     em.setNom(nom);
                     em.setTel(tel);
                     em.setEstado(est);
                     em.setUser(user);
                     edao.Agregar(em);
                     request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                     break;
                 case"Editar":
                     ide=Integer.parseInt(request.getParameter("id"));
                     Empleado e=edao.listarId(ide);
                     request.setAttribute("empleado", e);
                     request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                     
                     break;
                     case"Actualizar":
                     String dni1=request.getParameter("txtDni");
                     String nom1=request.getParameter("txtNombres");
                     String tel1=request.getParameter("txtTel");
                     String est1=request.getParameter("txtEstado");
                     String user1=request.getParameter("txtUsuario");
                     em.setDni(dni1);
                     em.setNom(nom1);
                     em.setTel(tel1);
                     em.setEstado(est1);
                     em.setUser(user1);
                     em.setId(ide);
                     edao.actualizar(em);
                     request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                     
                     break;
                 case"Delete":
                     ide=Integer.parseInt(request.getParameter("id"));
                     edao.delete(ide);
                      request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                     
                     break;
             }
             request.getRequestDispatcher("Empleado.jsp").forward(request, response);
             
         }
          if (menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String dir = request.getParameter("txtDir");
                    String est = request.getParameter("txtestado");
                    cli.setDni(dni);
                    cli.setNom(nom);
                    cli.setDir(dir);
                    cli.setEstado(est);
                    cdao.agregar(cli);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    System.out.println("ENTRA EN ACTUALIZAR 1");
                    ide = Integer.parseInt(request.getParameter("id"));
                    Cliente client = cdao.listarId(ide);
                    request.setAttribute("cliente", client);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    System.out.println("ENTRA EN ACTUALIZAR 2");
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String dir1 = request.getParameter("txtDir");
                    String est1 = request.getParameter("txtestado");
                    cli.setDni(dni1);
                    cli.setNom(nom1);
                    cli.setDir(dir1);
                    cli.setEstado(est1);
                    cli.setId(ide);
                    cdao.actualizar(cli);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    cdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
           if(menu.equals("Producto")){
             switch (accion) {
                case "Listar":
                    List lista = pdao.listar();
                    request.setAttribute("productos", lista);
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNombres");
                    int precio = Integer.parseInt(request.getParameter("txtPrecio"));
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String est = request.getParameter("txtestado");
                    pr.setNom(nom);
                    pr.setPrecio(precio);
                    pr.setStock(stock);
                    pr.setEstado(est);
                    pdao.agregar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    System.out.println("ENTRA EN ACTUALIZAR 1");
                    ide = Integer.parseInt(request.getParameter("id"));
                    Producto pro = pdao.listarId(ide);
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    System.out.println("ENTRA EN ACTUALIZAR 2");
                    String nom1 = request.getParameter("txtNombres");
                    int precio1 = Integer.parseInt(request.getParameter("txtPrecio"));
                    int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                    String est1 = request.getParameter("txtestado");
                    pr.setNom(nom1);
                    pr.setPrecio(precio1);
                    pr.setStock(stock1);
                    pr.setEstado(est1);
                    pr.setId(ide);
                    pdao.actualizar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
             
         }
            if(menu.equals("NuevaVenta")){
                 switch (accion) {
                case "BuscarCliente":
                  
                    String dni = request.getParameter("codigocliente");
                    c.setDni(dni);
                    cl = cdao.buscar(dni);
                    request.setAttribute("c", cl);
                    break;
                case"BuscarProducto":
                    int id=Integer.parseInt(request.getParameter("codigoproducto"));
                    pr=pdao.listarId(id);
                    request.setAttribute("c", cl);
                    request.setAttribute("producto", pr);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalpagar);
                    break;
                    case"Agregar":
                    request.setAttribute("c", cl);
                    totalpagar=0.0;
                    item=item+1;
                    cod=pr.getId();
                    descripcion=request.getParameter("nomproducto");
                    precio=Double.parseDouble(request.getParameter("precio"));
                    cant=Integer.parseInt(request.getParameter("cant"));
                    subtotal=precio*cant;
                    v=new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        totalpagar = totalpagar + lista.get(i).getSubtotal();

                    }
                    request.setAttribute("totalpagar", totalpagar);
                    request.setAttribute("lista", lista);

                    break;
                    case"GenerarVenta":
                        for(int i=0;i<lista.size();i++){
                            Producto pr=new Producto();
                            int cantidad=lista.get(i).getCantidad();
                            int idproducto=lista.get(i).getIdproducto();
                            ProductoDAO ao=new ProductoDAO();
                            pr=ao.buscar(idproducto);
                            int sac=pr.getStock()-cantidad;
                            ao.actualizarStock(idproducto, sac);
                        }
                        v.setIdcliente(c.getId());
                        v.setIdempleado(2);
                        v.setNumserie(numeroserie);
                        v.setFecha("2023-10-29");
                        v.setMonto(totalpagar);
                        v.setEstado("1");
                        vdao.guardarVenta(v);
                        //Guardar Detalle ventas
                        int idv=Integer.parseInt(vdao.IdVentas());
                        for(int i=0;i<lista.size();i++){
                            v=new Venta();
                            v.setId(idv);
                            v.setIdproducto(lista.get(i).getIdproducto());
                            v.setCantidad(lista.get(i).getCantidad());
                            v.setPrecio(lista.get(i).getPrecio());
                            vdao.guardarDetalleventas(v);
                        }
                    break;
                default:
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);

        }
    }
    
//      private String asegurarClave(String textoclaro){
//        String clavesha="";
//        try{
//            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
//            sha256.update(textoclaro.getBytes());
//            clavesha=Base64.getEncoder().encodeToString(sha256.digest());        
//        }catch(NoSuchAlgorithmException e){
//            System.out.println("ERROR EN LA ENCRIPTACION: "+e.getMessage());
//        }
//        return clavesha;
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
