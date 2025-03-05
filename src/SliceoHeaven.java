import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class SliceoHeaven {
    public String storeAddress;
    public String storeMenu;
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
        Scanner scanner = new Scanner(System.in);

        orderID = id;
        orderTotal = total;
        sides = selectedSides;
        drinks = selectedDrinks;

        System.out.println("Enter three ingredients for your pizza (use spaces to separate\r\n" + //
                        "ingredients):");
        String ing1 = scanner.next();
        String ing2 = scanner.next();
        String ing3 = scanner.next();
        pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;

        System.out.println("Enter size of pizza (Small, Medium, Large):");
        String pizzaSize = scanner.next();

        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.next();

        System.out.println("Enter one side dish (Calzone, Garlic bread, None):");
        sides = scanner.next();
        
        System.out.println("Enter drinks(Cold Coffee, Cocoa drink, Coke, None):");
        drinks = scanner.next();

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.next();

        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
        scanner.close();
    }

    public void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your birthday (MM/dd/yyyy):");
        String birthdateStr = scanner.next();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date birthdate = null;
        try {
            birthdate = sdf.parse(birthdateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
            scanner.close();
            return;
        }

        Calendar calBirth = Calendar.getInstance();
        calBirth.setTime(birthdate);
        Calendar calNow = Calendar.getInstance();

        int age = calNow.get(Calendar.YEAR) - calBirth.get(Calendar.YEAR);
        if (calNow.get(Calendar.DAY_OF_YEAR) < calBirth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        boolean isBirthday = calNow.get(Calendar.MONTH) == calBirth.get(Calendar.MONTH)
                && calNow.get(Calendar.DAY_OF_MONTH) == calBirth.get(Calendar.DAY_OF_MONTH);

        if (age < 18 && isBirthday) {
            System.out.println("Congratulations! You pay only half the price for your order");
            orderTotal = orderTotal / 2;
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
        }
        scanner.close();

        makeCardPayment();
    }

    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();

        System.out.println("Enter the card’s expiry date (MM/yyyy):");
        String expiryDate = scanner.next();

        System.out.println("Enter the card’s cvv number:");
        int cvv = scanner.nextInt();
        scanner.close();

        processCardPayment(cardNumber, expiryDate, cvv);
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        long blacklistedNumber = 12345678901234L;
        String cardNumberStr = Long.toString(cardNumber);

        if (cardNumberStr.length() == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
        }

        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));

        if (cardNumber == blacklistedNumber) {
            System.out.println("Card is blacklisted. Please use another card");
        }

        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardNumberStr.length() - 4));

        StringBuilder cardNumberToDisplay = new StringBuilder(cardNumberStr);
        for (int i = 1; i < cardNumberStr.length() - 4; i++) {
            cardNumberToDisplay.setCharAt(i, '*');
        }
        String cardNumberToDisplayStr = cardNumberToDisplay.toString();

        System.out.println("First digit of card: " + firstCardDigit);
        System.out.println("Last four digits of card: " + lastFourDigits);
        System.out.println("Card number to display: " + cardNumberToDisplayStr);

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
        System.out.println("Pizza Ingredients: " + pizzaIngredients);
        System.out.println("Side Dish: " + sides);
        System.out.println("Drinks: " + drinks);
        System.out.println("Order Total: " + orderTotal);
        
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

