import java.util.*;
class BuyerAgent {
    private String name;
    private String targetBook;

    private String selectedSeller;
    private double selectedPrice;
    private boolean madePurchase;


    public BuyerAgent(String name, String targetBook) {
        this.name = name;
        this.targetBook = targetBook;
    }

    // Request offers from seller agents for the target book and purchase from the lowest offer
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

        if (bestSeller != null) {
            System.out.println("Buyer " + name + " accepted offer from seller " + bestSeller.getName() +
                    " for book '" + targetBook + "' at price: " + bestOffer);
        } else {
            System.out.println("Buyer " + name + " could not find a suitable offer for book '" + targetBook + "'");
        }
    }

    // Make purchase decision based on received offers
    public void makePurchase() {
        if (selectedSeller != null) {
            madePurchase = true;
            System.out.println("Buyer " + name + " accepted offer from seller " + selectedSeller +
                    " for book '" + targetBook + "' at price: " + selectedPrice);
        } else {
            System.out.println("Buyer " + name + " could not find a suitable offer for book '" + targetBook + "'");
        }
    }

    // Check if the buyer has made a purchase
    public boolean hasMadePurchase() {
        return madePurchase;
    }

    // Get the name of the selected seller
    public String getSelectedSeller() {
        return selectedSeller;
    }

    // Get the target book
    public String getTargetBook() {
        return targetBook;
    }


}