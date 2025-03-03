public class App {
   public static void main(String[] args) {
       SliceoHeaven sliceoHeaven = new SliceoHeaven();
       sliceoHeaven.takeOrder("ORDER-001", 25.0, "", "");
       sliceoHeaven.processCardPayment("12345678901234", "01/25", 123);
       sliceoHeaven.specialOfTheDay("", "", "");
   }
}