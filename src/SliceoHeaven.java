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
    public static final long BLACKLISTED_NUMBER = 12345678901234L;
    
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
        
        String ing1 = null;
        String ing2 = null;
        String ing3 = null;
        boolean validChoices = false;

        while (!validChoices) {
            System.out.println("Please pick any three of the following ingredients:");
            System.out.println("1. Mushroom");
            System.out.println("2. Paprika");
            System.out.println("3. Sun-dried tomatoes");
            System.out.println("4. Chicken");
            System.out.println("5. Pineapple");
            System.out.println("Enter any three choices (1, 2, 3,…) separated by spaces:");

            int ingChoice1 = 0;
            int ingChoice2 = 0;
            int ingChoice3 = 0;

            try {
                ingChoice1 = scanner.nextInt();
                ingChoice2 = scanner.nextInt();
                ingChoice3 = scanner.nextInt();

                if (isValidChoice(ingChoice1) && isValidChoice(ingChoice2) && isValidChoice(ingChoice3)) {
                    validChoices = true;
                    ing1 = getIngredientName(ingChoice1);
                    ing2 = getIngredientName(ingChoice2);
                    ing3 = getIngredientName(ingChoice3);
                } else {
                    System.out.println("Invalid choice(s). Please pick only from the given list:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid numbers separated by spaces.");
                scanner.nextLine(); 
            }

        pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;       

        int sizeChoice;
        String pizzaSize = null;
        boolean validSizeChoice = false;
        while (!validSizeChoice) {
            System.out.println("What size should your pizza be?");
            System.out.println("1. Large");
            System.out.println("2. Medium");
            System.out.println("3. Small");
            System.out.println("Enter only one choice (1, 2, or 3):");
            try {
                sizeChoice = scanner.nextInt();
                if (sizeChoice >= 1 && sizeChoice <= 3) {
                    validSizeChoice = true;
                    switch (sizeChoice) {
                        case 1:
                            pizzaSize = "Large";
                            break;
                        case 2:
                            pizzaSize = "Medium";
                            break;
                        case 3:
                            pizzaSize = "Small";
                            break;
                    }
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.next();

        int sideDishChoice;
        String sideDish = null;
        boolean validsideDishChoice = false;
        while(!validsideDishChoice) {
            System.out.println("Following are the side dish that go well with your pizza:");
            System.out.println("1. Calzone");
            System.out.println("2. Garlic bread");
            System.out.println("3. Chicken puff");
            System.out.println("4. Muffin");
            System.out.println("5. Nothing for me");

            System.out.println("What would you like? Pick one (1, 2, 3,…):");

            try {
                sideDishChoice = scanner.nextInt();
                if (sideDishChoice >=1 && sideDishChoice <=5) {
                    validsideDishChoice = true;
                    switch (sideDishChoice) {
                        case 1:
                            sideDish = "Calzone";
                            break;
                        case 2:
                            sideDish = "Garlic bread";
                            break;
                        case 3:
                            sideDish = "Chicken puff";    
                            break;
                        case 4:
                            sideDish = "Muffin";
                            break;
                        case 5:
                            sideDish = "Nothing for me";
                            break;        
                    }
                } else{
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4 or 5.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        
        int drinkChoice;
        String drinks = null;
        boolean validDrinkChoice = false;
        while (!validDrinkChoice) {
            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:");
            System.out.println("1. Coca Cola");
            System.out.println("2. Cold coffee");
            System.out.println("3. Cocoa Drink");
            System.out.println("4. No drinks for me");
            System.out.println("Enter your choice:");

            try {
                drinkChoice = scanner.nextInt();
                if (drinkChoice >=1 && drinkChoice <=5) {
                    validDrinkChoice = true;
                    switch (drinkChoice) {
                        case 1:
                            drinks = "Coca Cola";
                            break;
                        case 2:
                            drinks = "Cold coffee";
                            break;
                        case 3:
                            drinks = "Cocoa Drink";    
                            break;
                        case 4:
                            drinks = "No drinks for me";
                            break;      
                    }
                } else{
                    System.out.println("Invalid choice. Please enter 1, 2, 3 or 4.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.next();

        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
        scanner.close();
        }
    }

    private boolean isValidChoice(int choice) {
        return choice >= 1 && choice <= 5;
    }

    private String getIngredientName(int choice) {
        switch (choice) {
            case 1:
                return "Mushroom";
            case 2:
                return "Paprika";
            case 3:
                return "Sun-dried tomatoes";
            case 4:
                return "Chicken";
            case 5:
                return "Pineapple";
            default:
                return null;
        }
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
            System.out.println("Enter the card’s expiry date (MM/yyyy):");
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

        System.out.println("Enter the card’s cvv number:");
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
        return "********RECEIPT********\n" +
                "Order ID: " + orderID + "\n" +
                "Pizza Ingredients: " + pizzaIngredients + "\n" +
                "Side Dish: " + sides + "\n" +
                "Drinks: " + drinks + "\n" +
                "Order Total: " + orderTotal;
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
    


