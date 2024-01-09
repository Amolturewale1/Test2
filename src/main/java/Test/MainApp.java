package Test;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static Service service=new Service();
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Select Option");
        System.out.println("1.Display ALl Products");
        System.out.println("2.Remove Product");
        System.out.println("3.Update Product");
        System.out.println("4.Place Order");
        System.out.println("5.Display All Orders");
        System.out.println("6.Exit");
        int ch= sc.nextInt();
        switch (ch){
            case 1:
                displayAllProducts();
                break;
            case 2:
                removeProducts();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                placeOrder();
                break;
            case 5:
                displayAllOrders();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Option");
        }
        main(args);
    }



    private static void displayAllProducts() {
        List<Product> productList=service.displayAllProducts();
        System.out.println("PId PName PQty PPrice");
        for (Product p:productList){
            System.out.println(p.getProductId()+" "+p.getProductName()+" "+p.getProductQty()+" "+p.getProductPrice());
        }
        System.out.println("\n\n\n");
    }
    private static void removeProducts() {
        System.out.println("Enter Product Id");
        int pID= sc.nextInt();
        Product p=new Product(pID);
        int n=service.removeProduct(p);
        System.out.println(n+" Product Removed");
        System.out.println("\n\n\n");
    }
    private static void updateProduct() {
        System.out.println("Enter Product Id Which Want To Update ");
        int pId= sc.nextInt();
        System.out.println("Enter Product Qty");
        int pQty= sc.nextInt();
        System.out.println("Enter Product Price");
        double pPrice= sc.nextDouble();
        Product product=new Product(pId,pQty,pPrice);
        int n=service.updateProduct(product);
        System.out.println(n +" Product Updated ");
        System.out.println("\n\n\n");
    }
    private static void placeOrder() {
        System.out.println("Enter Your Name ");
        String cName=sc.next();
        System.out.println("Enter Product Id");
        int pId= sc.nextInt();
        System.out.println("Enter Product Qty");
        int pQty = sc.nextInt();
        Order order=new Order(cName,pId,pQty);
        int n=service.placeOrder(order);
        System.out.println(n+ "Order Placed");
    }
    private static void displayAllOrders() {
        List<Order> orderList=service.displayAllOrders();
        System.out.println("OID\tCName\tPID\tPQty\tTotalAmt");
        for (Order o:orderList){
            System.out.println(o.getOrderId()+"\t"+o.getCustomerName()+"\t"+o.getProductId()+"\t"+o.getProductQty()+"\t"+o.getTotalAmt());
        }
    }
}
