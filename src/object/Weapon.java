package object;

public abstract class Weapon extends SuperObject {
    protected int strengthBonus;
    protected int price;
    private boolean hasBeenInteractedWith = false;
    private boolean isInteractable;

    // Constructor
    public Weapon(String name, int strengthBonus, int price, boolean isInteractable) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.price = price;
        this.isInteractable = isInteractable;
    }

    // Getters
    public int getStrengthBonus() {
        return strengthBonus;
    }

    public int getPrice() {
        return price;
    }

    public boolean hasBeenInteracted() {
        return hasBeenInteractedWith;
    }

    public boolean isInteractable() {
        return isInteractable;
    }

    // Setters
    public void setHasBeenInteracted(boolean interacted) {
        this.hasBeenInteractedWith = interacted;
    }

    public void setInteractable(boolean interactable) {
        this.isInteractable = interactable;
    }
}
