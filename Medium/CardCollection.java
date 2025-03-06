import java.util.*;

class CardCollection {
    private Map<String, List<String>> cards;

    public CardCollection() {
        cards = new HashMap<>();
    }

    public void addCard(String symbol, String cardName) {
        cards.computeIfAbsent(symbol, k -> new ArrayList<>()).add(cardName);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cards.getOrDefault(symbol, Collections.emptyList());
    }

    public void displayAllCards() {
        for (Map.Entry<String, List<String>> entry : cards.entrySet()) {
            System.out.println("Symbol: " + entry.getKey() + " -> Cards: " + entry.getValue());
        }
    }
}

public class CardCollectionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        while (true) {
            System.out.println("\nCard Collection System:");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter card symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter card name: ");
                    String cardName = scanner.nextLine();
                    collection.addCard(symbol, cardName);
                    System.out.println("Card added successfully!");
                    break;
                case 2:
                    System.out.print("Enter symbol to search: ");
                    String searchSymbol = scanner.nextLine();
                    List<String> foundCards = collection.getCardsBySymbol(searchSymbol);
                    if (foundCards.isEmpty()) {
                        System.out.println("No cards found for this symbol.");
                    } else {
                        System.out.println("Cards with symbol " + searchSymbol + ": " + foundCards);
                    }
                    break;
                case 3:
                    collection.displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
