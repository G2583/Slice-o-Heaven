public class SliceoHeaven {
    public String storeAddress;
    private String storeMenu;
    public String storeName;
    public long storePhone;
    public String storeEmail;
    public double pizzaPrice;
    private String pizzaIngredients;
    private String orderID;
    private String sides;
    private String drinks;
    private double orderTotal;

    private final String DEF_ORDER_ID = "DEF-SOH-099";
    private final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private final double DEF_ORDER_TOTAL = 15.00;
    
    public SliceoHeaven() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.sides = "";
        this.drinks = "";
    }

    public SliceoHeaven(String orderID,String pizzaIngredients,double orderTotal) {
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void takeOrder(String id, double total, String selectedSides, String selectedDrinks) {
        orderID = id;
        orderTotal = total;
        sides = selectedSides;
        drinks = selectedDrinks;

        System.out.println("Order accepted!");
        System.out.println("Order is being prepared");

        makePizza();
    }

    public void makePizza() {
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Order is ready for pickup!");
        }

        System.out.println("Your order is ready!");

        printReceipt();
    }
    
    private void printReceipt(){
        System.out.println("********RECEIPT********");

        System.out.println("Order ID: " + orderID);
        System.out.println("Order Total: " + orderTotal);
        
    }

     public void processCardPayment(String cardNumber, String expiryDate, int cvv) {
        String blacklistedNumber = "12345678901234";

        int cardLength = cardNumber.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        }
        else {
            System.out.println("Invalid card");
        }

        int firstCardDigit = Integer.parseInt(cardNumber.substring(0, 1));

        if (cardNumber.equals(blacklistedNumber)) {
            System.out.println("Card is blacklisted. Please use another card");
        }

        int lastFourDigits = Integer.parseInt(cardNumber.substring(cardNumber.length() - 4));

        StringBuilder cardNumberToDisplay = new StringBuilder(cardNumber);
        for (int i = 1; i < cardNumber.length() - 4; i++) {
            cardNumberToDisplay.setCharAt(i, '*');
        }
        String cardNumberToDisplayStr = cardNumberToDisplay.toString();

        System.out.println("First digit of card: " + firstCardDigit);
        System.out.println("Last four digits of card: " + lastFourDigits);
        System.out.println("Card number to display: " + cardNumberToDisplayStr);

    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder specialInfo = new StringBuilder();
        specialInfo.append("Special of the day:\n");
        specialInfo.append("Pizza: ").append(pizzaOfTheDay).append("\n");
        specialInfo.append("Side: ").append(sideOfTheDay).append("\n");
        specialInfo.append("Price: ").append(specialPrice).append("\n");

        System.out.println(specialInfo.toString());
    }
}

