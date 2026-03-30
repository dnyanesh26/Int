package org.example.constvsenum;

public class OrderStatusConst {

    public static final int PENDING = 1;
    public static final int SHIPPED = 2;
    public static final int DELIVERED = 3;

}

class Test {
    public static void main(String[] args) {

        int status = OrderStatusConst.PENDING;

        if(status == OrderStatusConst.SHIPPED) {
            System.out.println("Order shipped");
        }
    }
}