import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
   /*static final String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
   static final String USER = "guest";
   static final String PASS = "guest123";*/
   static final String QUERY = "SELECT * FROM cliente";

   public static Connection conectar(){
        Connection database = null;
        try{
            database = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bootcamp_market",
            "postgres", "postgres");
            return database;
        }catch (Exception e){
            System.out.println("Ha ocurrido un error al conectarse");
            return null;
        }
   }
   public static void mostrarCliente () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery(QUERY);
      try {		      
         while(rs.next()){
            //Display values
            System.out.print("id: " + rs.getInt("id"));
            System.out.print(", nombre: " + rs.getString("nombre"));
            System.out.print(", apellido: " + rs.getString("apellido"));
            System.out.println(", nro_cedula: " + rs.getString("nro_cedula"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   public static void clienteConMasFacturas () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select nombre, apellido, nro_cedula, count(cliente.id) from cliente join factura on cliente.id = factura.cliente_id \n" +
"group by nombre, apellido, nro_cedula order by count(cliente.id) desc");
      try {		      
         while(rs.next()){
            //Display values           
            System.out.print(" nombre: " + rs.getString("nombre"));
            System.out.print(", apellido: " + rs.getString("apellido"));
            System.out.print(", nro_cedula: " + rs.getString("nro_cedula"));
            System.out.println(", count: " + rs.getString("count"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   
   public static void clienteQueMasGastaron () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select cliente.nombre, cliente.apellido, cliente.nro_cedula, (producto.precio*factura_detalle.cantidad) as gasto from ((cliente inner join factura on cliente.id = factura.cliente_id) \n" +
"inner join  factura_detalle on factura.id = factura_detalle.factura_id) inner join \n" +
"producto on factura_detalle.producto_id = producto.id \n" +
"group by cliente.nombre, cliente.apellido, cliente.nro_cedula, producto.precio,factura_detalle.cantidad,(producto.precio*factura_detalle.cantidad) \n" +
"order by (producto.precio*factura_detalle.cantidad) desc");
      try {		      
         while(rs.next()){
            //Display values           
            System.out.print(" nombre: " + rs.getString("nombre"));
            System.out.print(", apellido: " + rs.getString("apellido"));
            System.out.print(", nro_cedula: " + rs.getString("nro_cedula"));
            System.out.println(", count: " + rs.getDouble("gasto"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   public static void monedasMasUtilizadas () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select moneda.nombre, count(moneda.id) from ((cliente inner join factura on cliente.id = factura.cliente_id) inner join\n" +
"moneda on factura.moneda_id = moneda.id)\n" +
"group by moneda.nombre\n" +
"order by count (moneda.id) desc");
      try {		      
         while(rs.next()){
            //Display values           
            System.out.print(" nombre: " + rs.getString("nombre"));
            System.out.println(", count: " + rs.getInt("count"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   public static void TopProveedor () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select proveedor.nombre, count(proveedor.id) from (proveedor inner join producto on proveedor.id = producto.proveedor_id) group by proveedor.nombre\n" +
"order by count(proveedor.id) desc");
      try {		      
         while(rs.next()){
            //Display values           
            System.out.print(" nombre: " + rs.getString("nombre"));
            System.out.println(", count: " + rs.getInt("count"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   public static void ProductosMasVendidos () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select producto.nombre,factura_detalle.cantidad from (producto inner join factura_detalle on factura_detalle.producto_id = producto.id) group by producto.nombre,factura_detalle.cantidad\n" +
"order by factura_detalle.cantidad desc");
      try {		      
         while(rs.next()){
            //Display values           
            System.out.print(" nombre: " + rs.getString("nombre"));
            System.out.println(", count: " + rs.getDouble("cantidad"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   public static void ProductosMenosVendidos () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select producto.nombre,factura_detalle.cantidad from (producto inner join factura_detalle on factura_detalle.producto_id = producto.id) group by producto.nombre,factura_detalle.cantidad\n" +
"order by factura_detalle.cantidad asc");
      try {		      
         while(rs.next()){
            //Display values           
            System.out.print(" nombre: " + rs.getString("nombre"));
            System.out.println(", count: " + rs.getDouble("cantidad"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
  
   public static void item7 () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select factura.fecha_emision, cliente.nombre, cliente.apellido, producto.nombre as nombre_producto, factura_detalle.cantidad, factura_tipo.nombre as nombre_factura from ((( cliente inner join factura on factura.cliente_id = cliente.id)\n" +
"inner join factura_detalle on factura_detalle.factura_id = factura.id) inner join factura_tipo on factura_tipo.id = factura.factura_tipo_id) inner join producto on producto.id =factura_detalle.producto_id\n" +
"group by factura.fecha_emision, cliente.nombre, cliente.apellido, producto.nombre, factura_detalle.cantidad, factura_tipo.nombre");
      try {		      
         while(rs.next()){
            //Display values           
            System.out.print(" fecha_emision: " + rs.getDate("fecha_emision"));
            System.out.println(", nombre: " + rs.getString("nombre"));
            System.out.println(", apellido: " + rs.getString("apellido"));
            System.out.println(", nombre_producto: " + rs.getString("nombre_producto"));
            System.out.println(", cantidad: " + rs.getDouble("cantidad"));
            System.out.println(", nombre_factura: " + rs.getString("nombre_factura"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   
   public static void facturaSegunTotales () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select factura_detalle.cantidad from factura_detalle inner join factura on factura_detalle.factura_id = factura.id group by factura_detalle.cantidad\n" +
"order by factura_detalle.cantidad desc");
      try {		      
         while(rs.next()){
            //Display values      
            System.out.println(", cantidad: " + rs.getDouble("cantidad"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   
   public static void iva () throws SQLException {
   // Open a connection
      Connection database = conectar();
      Statement stmt = database.createStatement();
      ResultSet rs = stmt.executeQuery("select ((producto.precio*factura_detalle.cantidad)*0.1) as iva from ((factura inner join factura_detalle on factura.id = factura_detalle.factura_id) \n" +
"inner join producto on factura_detalle.producto_id = producto.id) \n" +
"group by iva\n" +
"order by iva desc");
      try {		      
         while(rs.next()){
            //Display values      
            System.out.println(" iva: " + rs.getDouble("iva"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      database.close();
      //rs.close();
      stmt.close();
   }
   //se cierra solo por eso se ignora el rs.close()
   
   public static void main(String[] args) throws SQLException {
      //mostrarCliente();
      //clienteConMasFacturas ();
      //clienteQueMasGastaron ();
      //monedasMasUtilizadas ();
      //TopProveedor();
      //ProductosMasVendidos();
      //ProductosMenosVendidos();
      //item7();
      //facturaSegunTotales();
      //iva();
   }
}