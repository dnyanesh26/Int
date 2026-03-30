package org.example.constvsenum;

//public final class OrderStatus extends Enum<OrderStatus>
public enum OrderStatus {
    PENDING,
    SHIPPED,
    DELIVERED
}
enum Status {

    PENDING(1),
    SHIPPED(2),
    DELIVERED(3);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

class TestEnum {

    public static void main(String[] args) {

        OrderStatus status = OrderStatus.PENDING;
        if(status == OrderStatus.SHIPPED) {
            System.out.println("Order shipped");
        }

        Status stat = Status.SHIPPED;
        System.out.println("Enum Method Call: "+stat.getCode());
        Status[] statuses = Status.values();
        for(Status s: statuses){
            System.out.println("values() Method iteration: "+s);
        }

        //String -> Enum
        Status status1 = Status.valueOf("DELIVERED");
        System.out.println("String to Enum: "+status1);

        System.out.println("Enum to String: "+status1.name());

        System.out.println("Enum Ordinal: "+Status.PENDING.ordinal());
        System.out.println("Enum Ordinal: "+Status.DELIVERED.ordinal());

        System.out.println(Status.DELIVERED.compareTo(Status.PENDING));

        System.out.println(Status.PENDING.equals(Status.SHIPPED));

        System.out.println(Status.SHIPPED.toString());

    }
}