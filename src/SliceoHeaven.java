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
    public String pizzaIngredients;
    private String orderID;
    public String sides;
    public String drinks;
    private double orderTotal;

    private final String DEF_ORDER_ID = "DEF-SOH-099";
    private final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private final double DEF_ORDER_TOTAL = 15.00;
    public static final long BLACKLISTED_NUMBER = 12345678901234L;
    private final double PIZZA_BASE_PRICE = 10.0;

    String[] pizzasOrdered = new String[10];
    String[] pizzaSizesOrdered = new String[10];
    String[] sideDishesOrdered = new String[20];
    String[] drinksOrdered = new String[20];

    enum PizzaSelection{
        PEPPERONI("Pepperoni", "Lots of pepperoni and extra cheese", 18),
        HAWAIIAN("Hawaiian", "Pineapple, ham, and extra cheese", 22),
        VEGGIE("Veggie", "Green pepper, onion, tomatoes, mushroom, and black olives", 25),
        BBQ_CHICKEN("BBQ Chicken", "Chicken in BBQ sauce, bacon, onion, green pepper, and cheddar cheese", 35),
        EXTRAVAGANZA("Extravaganza", "Pepperoni, ham, Italian sausage, beef, onions, green pepper, mushrooms, black olives, and extra cheese", 45);

        private final String pizzaName;
        private final String pizzaToppings;
        private final int price;

        PizzaSelection(String pizzaName, String pizzaToppings, int price) {
            this.pizzaName = pizzaName;
            this.pizzaToppings = pizzaToppings;
            this.price = price;
        }

        public String getPizzaName() {
            return pizzaName;
        }
    
        public String getPizzaToppings() {
            return pizzaToppings;
        }
    
        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("Pizza: %s\nToppings: %s\nPrice: $%.2f", pizzaName, pizzaToppings, price);
        }
    }

    enum PizzaToppings{
        HAM("Ham", 2), 
        PEPPERONI("Pepperoni", 2),
        BEEF("Beef", 2),
        CHICKEN("Chicken", 2), 
        SAUSAGE("Sausage", 2),
        PINEAPPLE("Pineapple", 1),
        ONION("Onion", 0.5), 
        TOMATOES("Tomatoes", 0.4), 
        GREEN_PEPPER("Green Pepper", 0.5), 
        BLACK_OLIVES("Black Olives", 0.5), 
        SPINACH("Spinach", 0.5), 
        CHEDDAR_CHEESE("Cheddar Cheese", 0.8), 
        MOZZARELLA_CHEESE("Mozzarella Cheese", 0.8), 
        FETA_CHEESE("Feta Cheese", 1), 
        PARMESAN_CHEESE("Parmesan Cheese", 1);

        private final String topping;
        private final double toppingPrice;

        PizzaToppings(String topping, double toppingPrice) {
            this.topping = topping;
            this.toppingPrice = toppingPrice;
        }

        public String getTopping() {
            return topping;
        }
    
        public double getToppingPrice() {
            return toppingPrice;
        }

        @Override
        public String toString() {
        return String.format("%s: $%.2f", topping, toppingPrice);
        }
    }

    enum PizzaSize{
        LARGE("Large", 10), 
        MEDIUM("Medium", 5),
        SMALL("Small", 0);

        private final String pizzaSize;
        private final int addToPizzaPrice;

        PizzaSize(String pizzaSize, int addToPizzaPrice) {
            this.pizzaSize = pizzaSize;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getPizzaSize() {
            return pizzaSize;
        }

        public int getAddToPizzaPrice() {
            return addToPizzaPrice;
        }

        @Override
        public String toString() {
            return String.format("%s: $%.2f", pizzaSize, addToPizzaPrice);
        }
    }

    enum SideDish{
        CALZONE("Calzone", 15), 
        CHICKEN_PUFF("Chicken Puff", 20),
        MUFFIN("Muffin", 12),
        NOTHING("No side dish", 0);

        private final String sideDishName;
        private final int addToPizzaPrice;

        SideDish(String sideDishName, int addToPizzaPrice) {
            this.sideDishName = sideDishName;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getSideDishName() {
            return sideDishName;
        }

        public int getAddToPizzaPrice() {
            return addToPizzaPrice;
        }
        @Override
        public String toString() {
            return String.format("%s: $%.2f", sideDishName, addToPizzaPrice);
        }
    }

    enum Drinks{
        COCA_COLA("Coca Cola", 8), 
        COCOA_DRINK("Cocoa Drink", 10),
        NOTHING("No drinks", 0);

        private final String drinkName;
        private final int addToPizzaPrice;

        Drinks(String drinkName, int addToPizzaPrice) {
            this.drinkName = drinkName;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getSideDishName() {
            return drinkName;
        }

        public int getAddToPizzaPrice() {
            return addToPizzaPrice;
        }
        @Override
        public String toString() {
            return String.format("%s: $%.2f", drinkName, addToPizzaPrice);
        }
    }
    
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
        int pizzaIndex = 0;

        System.out.println("Welcome to Slice-o-Heaven Pizzeria. Here's what we serve:");
        PizzaSelection[] pizzaSelections = PizzaSelection.values();
        for (int i = 0; i < pizzaSelections.length; i++) {
            PizzaSelection pizza = pizzaSelections[i];
            System.out.printf("%d. %s Pizza with %s, for €%d\n", i + 1, pizza.getPizzaName(), pizza.getPizzaToppings(), pizza.getPrice());
        }
        int customPizzaOption = pizzaSelections.length + 1;
        System.out.printf("%d. Custom Pizza with a maximum of 10 toppings that you choose\n", customPizzaOption);

        int pizzaChoice;
        boolean validPizzaChoice = false;
        while (!validPizzaChoice) {
            System.out.printf("Please enter your choice (1 - %d):\n", customPizzaOption);
            try {
                pizzaChoice = scanner.nextInt();
                if (pizzaChoice >= 1 && pizzaChoice <= customPizzaOption) {
                    validPizzaChoice = true;
                    if (pizzaChoice == customPizzaOption) {
                        String[] selectedToppings = new String[10];
                        int toppingCount = 0;
                        boolean validToppings = false;
                        double customPizzaPrice = PIZZA_BASE_PRICE;
                        while (!validToppings) {
                            System.out.println("Available toppings:");
                            PizzaToppings[] toppings = PizzaToppings.values();
                            for (int i = 0; i < toppings.length; i++) {
                                System.out.printf("%d. %s: €%.2f\n", i + 1, toppings[i].getTopping(), toppings[i].getToppingPrice());
                            }
                            System.out.println("Enter up to 10 topping choices (1 - " + toppings.length + ") separated by spaces. Enter 0 to finish:");
                            while (toppingCount < 10) {
                                int toppingChoice = scanner.nextInt();
                                if (toppingChoice == 0) {
                                    break;
                                }
                                if (toppingChoice >= 1 && toppingChoice <= toppings.length) {
                                    selectedToppings[toppingCount++] = toppings[toppingChoice - 1].getTopping();
                                    customPizzaPrice += toppings[toppingChoice - 1].getToppingPrice();
                                } else {
                                    System.out.println("Invalid topping choice. Please try again.");
                                    continue;
                                }
                            }
                            if (toppingCount > 0) {
                                validToppings = true;
                            } else {
                                System.out.println("You must select at least one topping.");
                            }
                        }
                        StringBuilder toppingsStr = new StringBuilder();
                        for (int i = 0; i < toppingCount; i++) {
                            if (i > 0) {
                                toppingsStr.append(", ");
                            }
                            toppingsStr.append(selectedToppings[i]);
                        }
                        String customPizzaDesc = "Custom Pizza with " + toppingsStr + ", for €" + String.format("%.2f", customPizzaPrice);
                        pizzasOrdered[pizzaIndex] = customPizzaDesc;
                        orderTotal += customPizzaPrice;
                    } else {
                        PizzaSelection selectedPizza = pizzaSelections[pizzaChoice - 1];
                        String predefinedPizzaDesc = selectedPizza.getPizzaName() + " Pizza with " + selectedPizza.getPizzaToppings() + ", for €" + selectedPizza.getPrice();
                        pizzasOrdered[pizzaIndex] = predefinedPizzaDesc;
                        orderTotal += selectedPizza.getPrice();
                    }
                    pizzaIndex++;

                    PizzaSize selectedSize = choosePizzaSize(scanner);
                    pizzaSizesOrdered[pizzaIndex - 1] = selectedSize.getPizzaSize();
                    orderTotal += selectedSize.getAddToPizzaPrice();

                    System.out.println("Do you want extra cheese (Y/N):");
                    String extraCheese = scanner.next();
                    if (extraCheese.equalsIgnoreCase("Y")) {
                        pizzasOrdered[pizzaIndex - 1] += ", Extra Cheese";
                        orderTotal += PizzaToppings.MOZZARELLA_CHEESE.getToppingPrice();
                    }

                    SideDish selectedSideDish = chooseSideDish(scanner);
                    sideDishesOrdered[pizzaIndex - 1] = selectedSideDish.getSideDishName();
                    orderTotal += selectedSideDish.getAddToPizzaPrice();

                    Drinks selectedDrink = chooseDrink(scanner);
                    drinksOrdered[pizzaIndex - 1] = selectedDrink.getSideDishName();
                    orderTotal += selectedDrink.getAddToPizzaPrice();

                    System.out.println("Current order total: €" + orderTotal);

                    System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
                    String wantDiscount = scanner.next();

                    if (wantDiscount.equalsIgnoreCase("Y")) {
                        isItYourBirthday();
                    } else {
                        makeCardPayment();
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private PizzaSize choosePizzaSize(Scanner scanner) {
        int sizeChoice;
        PizzaSize selectedSize = null;
        boolean validSizeChoice = false;
        while (!validSizeChoice) {
            System.out.println("What size should your pizza be?");
            PizzaSize[] sizes = PizzaSize.values();
            for (int i = 0; i < sizes.length; i++) {
                System.out.printf("%d. %s: €%d\n", i + 1, sizes[i].getPizzaSize(), sizes[i].getAddToPizzaPrice());
            }
            System.out.println("Enter only one choice (1 - " + sizes.length + "):");
            try {
                sizeChoice = scanner.nextInt();
                if (sizeChoice >= 1 && sizeChoice <= sizes.length) {
                    validSizeChoice = true;
                    selectedSize = sizes[sizeChoice - 1];
                } else {
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        return selectedSize;
    }

    private SideDish chooseSideDish(Scanner scanner) {
        int sideDishChoice;
        SideDish selectedSideDish = null;
        boolean validSideDishChoice = false;
        while (!validSideDishChoice) {
            System.out.println("Following are the side dishes that go well with your pizza:");
            SideDish[] sideDishes = SideDish.values();
            for (int i = 0; i < sideDishes.length; i++) {
                System.out.printf("%d. %s: €%d\n", i + 1, sideDishes[i].getSideDishName(), sideDishes[i].getAddToPizzaPrice());
            }
            System.out.println("What would you like? Pick one (1 - " + sideDishes.length + "):");
            try {
                sideDishChoice = scanner.nextInt();
                if (sideDishChoice >= 1 && sideDishChoice <= sideDishes.length) {
                    validSideDishChoice = true;
                    selectedSideDish = sideDishes[sideDishChoice - 1];
                } else {
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        return selectedSideDish;
    }

    private Drinks chooseDrink(Scanner scanner) {
        int drinkChoice;
        Drinks selectedDrink = null;
        boolean validDrinkChoice = false;
        while (!validDrinkChoice) {
            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:");
            Drinks[] drinkOptions = Drinks.values();
            for (int i = 0; i < drinkOptions.length; i++) {
                System.out.printf("%d. %s: €%d\n", i + 1, drinkOptions[i].getSideDishName(), drinkOptions[i].getAddToPizzaPrice());
            }
            System.out.println("Enter your choice (1 - " + drinkOptions.length + "):");
            try {
                drinkChoice = scanner.nextInt();
                if (drinkChoice >= 1 && drinkChoice <= drinkOptions.length) {
                    validDrinkChoice = true;
                    selectedDrink = drinkOptions[drinkChoice - 1];
                } else {
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        return selectedDrink;
    }

    public void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date birthdate = null;

        Calendar calNow = Calendar.getInstance();
        Calendar minCal = (Calendar) calNow.clone();
        minCal.add(Calendar.YEAR, -120);
        Calendar maxCal = (Calendar) calNow.clone();
        maxCal.add(Calendar.YEAR, -5);

        while (birthdate == null) {
            System.out.println("Enter your birthday (MM/dd/yyyy):");
            String birthdateStr = scanner.next();
            try {
                birthdate = sdf.parse(birthdateStr);
                Calendar calBirth = Calendar.getInstance();
                calBirth.setTime(birthdate);

                if (calBirth.after(maxCal) || calBirth.before(minCal)) {
                    System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                    birthdate = null;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        Calendar calBirth = Calendar.getInstance();
        calBirth.setTime(birthdate);

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

        makeCardPayment();
        scanner.close();
    }


    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        long cardNumber;
        String expiryDate = "";
        int cvv;

        do {
            System.out.println("Enter your card number:");
            while (!scanner.hasNextLong()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
            cardNumber = scanner.nextLong();
        } while (!isValidCardNumber(cardNumber));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        Calendar calNow = Calendar.getInstance();
        Date validDate = null;
        while (validDate == null) {
            System.out.println("Enter the card's expiry date (MM/yyyy):");
            expiryDate = scanner.next();
            try {
                validDate = sdf.parse(expiryDate);
                Calendar calExpiry = Calendar.getInstance();
                calExpiry.setTime(validDate);

                if (calExpiry.before(calNow)) {
                    System.out.println("Invalid expiry date. Please enter a future date.");
                    validDate = null;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use MM/yyyy.");
            }
        }

        System.out.println("Enter the card's cvv number:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        cvv = scanner.nextInt();

        processCardPayment(cardNumber,  expiryDate, cvv);
        scanner.close();
    }

    private boolean isValidCardNumber(long cardNumber) {
        String cardNumberStr = Long.toString(cardNumber);
        return cardNumberStr.length() == 14 && cardNumber != 12345678901234L;
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Order is ready for pickup!");
        }

        System.out.println("Your order is ready!");
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Thank you for dining with Slice-o-Heaven Pizzeria. Your order details are as follows:\n");
    
        for (int i = 0; i < pizzasOrdered.length; i++) {
            if (pizzasOrdered[i] != null) {
                orderDetails.append(i + 1).append(". ").append(pizzasOrdered[i]).append("\n");
                orderDetails.append(pizzaSizesOrdered[i]).append(": €").append(getPizzaSizePrice(pizzaSizesOrdered[i])).append("\n");
                orderDetails.append(sideDishesOrdered[i]).append(": €").append(getSideDishPrice(sideDishesOrdered[i])).append("\n");
                orderDetails.append(drinksOrdered[i]).append(": €").append(getDrinkPrice(drinksOrdered[i])).append("\n");
                orderDetails.append("\n");
            }
        }
    
        orderDetails.append("ORDER TOTAL: €").append(String.format("%.2f", orderTotal));
    
        return orderDetails.toString();
    }
    
    private int getPizzaSizePrice(String size) {
        for (PizzaSize pizzaSize : PizzaSize.values()) {
            if (pizzaSize.getPizzaSize().equals(size)) {
                return pizzaSize.getAddToPizzaPrice();
            }
        }
        return 0;
    }
    
    private int getSideDishPrice(String sideDish) {
        for (SideDish dish : SideDish.values()) {
            if (dish.getSideDishName().equals(sideDish)) {
                return dish.getAddToPizzaPrice();
            }
        }
        return 0;
    }
    
    private int getDrinkPrice(String drink) {
        for (Drinks drinkOption : Drinks.values()) {
            if (drinkOption.getSideDishName().equals(drink)) {
                return drinkOption.getAddToPizzaPrice();
            }
        }
        return 0;
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
    


