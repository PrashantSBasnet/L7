import java.util.*;

public class BuyerAgent {

    private String name;
    private String targetBook;
    private double selectedPrice;
    private boolean madePurchase;
    private int quantity; // New field to track the quantity of the target book
    private SellerAgent selectedSeller;

    public String getName() {
        return name;
    }

    public SellerAgent getSelectedSeller() {
        return selectedSeller;
    }

    public String getTargetBook() {
        return targetBook;
    }

    public double getSelectedPrice() {
        return selectedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BuyerAgent(String name, String targetBook) {
        this.name = name;
        this.targetBook = targetBook;

    }

    // Request offers from seller agents for the target book and select the seller with the best offer
    public void requestOffers(List<SellerAgent> sellerAgents) {
        double bestOffer = Double.MAX_VALUE;
        SellerAgent bestSeller = null;

        for (SellerAgent sellerAgent : sellerAgents) {
            double offer = sellerAgent.provideOffer(targetBook);
            if (offer != -1 && offer < bestOffer) {
                bestOffer = offer;
                bestSeller = sellerAgent;
            }
        }

        selectedSeller = bestSeller; // Assign the bestSeller to selectedSeller
        selectedPrice = bestOffer; // Assign the bestOffer to selectedPrice
    }

    // Make a purchase by serving the purchase order and updating the catalog of the selected seller
    public void makePurchase() {
        if (selectedSeller != null) {
            madePurchase = true;
            selectedSeller.servePurchase(targetBook); // Serve the purchase order with specified quantity
        }
    }

    // Check if the buyer has made a purchase
    public boolean hasMadePurchase() {
        return madePurchase;
    }
}
