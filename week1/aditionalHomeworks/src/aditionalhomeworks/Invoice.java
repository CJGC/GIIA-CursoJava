package aditionalhomeworks;

/**
 *
 * @author cj
 */
final class Invoice extends Price{
    private String emisor;
    private String client;
    
    public void printInvoice() {
        System.out.println("Invoice information");
        System.out.print("Emisor name: " + getEmisor());
        System.out.print("\nClient name: " + getClient());
        System.out.print("\nPrice: " + getPesos());
        System.out.println("\n");
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

}
