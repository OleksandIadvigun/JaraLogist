public class Trailer implements Info {
   private String name;
   private int maxLoading;

   public Trailer(String name, int maxLoading ){
       this.name = name;
       this.maxLoading = maxLoading;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLoading() {
        return maxLoading;
    }

    public void setMaxLoading(int maxLoading) {
        this.maxLoading = maxLoading;
    }

    public void showInfo(){
        System.out.println("TRAILER: " + "name: " + name + "; maxLoading: " + maxLoading + " kg");
    }
    public String toString(){
       return "TRAILER: " + "name: " + name + "; maxLoading: " + maxLoading + " kg";
    }
}
