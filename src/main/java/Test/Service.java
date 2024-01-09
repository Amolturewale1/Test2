package Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service {
    static Connection conn=null;
    static {
        String url="jdbc:mysql://localhost:3306/testdb";
        String userName="root";
        String password="tiger";
        try {
          conn=DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }


    public List<Product> displayAllProducts() {
        String displayQuery="Select * from product_info";
        List<Product> productList=new ArrayList<>();
        try {
           Statement stmt= conn.createStatement();
           ResultSet rs= stmt.executeQuery(displayQuery);
           while (rs.next()){
               Product product=new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4));
               productList.add(product);
           }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return productList;
    }

    public int removeProduct(Product p) {
        int n=0;
        String removeQuery="delete from product_info where product_id=?";
        try {
            PreparedStatement pstmt= conn.prepareStatement(removeQuery);
            pstmt.setInt(1,p.getProductId());
            n=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public int updateProduct(Product product) {
        int n=0;
        String updateQuery="update product_info set product_qty=? ,product_price=? where product_id=?;";
        try {
            PreparedStatement pstmt= conn.prepareStatement(updateQuery);
            pstmt.setInt(1,product.getProductQty());
            pstmt.setDouble(2,product.getProductPrice());
            pstmt.setInt(3,product.getProductId());
            n= pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public int placeOrder(Order order) {
        String insertQuery="{call placeOrder(?,?,?)}";
        int n=0;
        boolean status=false;
        try {
            CallableStatement cstmt= conn.prepareCall(insertQuery);
            cstmt.setString(1,order.getCustomerName());
            cstmt.setInt(2,order.getProductId());
            cstmt.setInt(3,order.getProductQty());
            status=cstmt.execute();
            if (status){
                n=1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;

    }

    public List<Order> displayAllOrders() {
        String display="select * from order_info";
        List<Order> orderList=new ArrayList<>();
        try {
            Statement stmt= conn.createStatement();
            ResultSet rs=stmt.executeQuery(display);
            while (rs.next()){
                Order order=new Order(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5));
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }
}
