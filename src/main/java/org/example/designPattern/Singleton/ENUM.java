package org.example.designPattern.Singleton;

public class ENUM {
    public static void main(String[] args) {
        //enum to string and enum index
        System.out.println(PlanType.BASIC.name()+":"+PlanType.BASIC.ordinal());

        String enumString="PREMIUM";
        PlanType stringtoEnum = PlanType.valueOf(enumString);
        System.out.println(stringtoEnum.getDetails());
        for(PlanType p: PlanType.values()){
            System.out.println(p.getDetails());
        }
    }
}

enum PlanType {
    //enum fields are compile time constants, but they are instances of their enum type.
    // And, they're constructed when the enum type is referenced for the first time.

    BASIC(0, 100),
    PREMIUM(15, 500),
    ENTERPRISE(50, 2000);

    private final int price;
    private final int storageGb;

    // Enum constructors are implicitly private
    PlanType(int price, int storageGb) {
        this.price = price;
        this.storageGb = storageGb;
    }

    public int getMonthlyCost(int months) {
        return this.price * months;
    }

    public String getDetails() {
        return String.format("Plan: %s, Price: $%d, Storage: %dGB",
                this.name(), this.price, this.storageGb);
    }
}