package object;

public abstract class Weapon extends SuperObject {
    protected int strengthBonus;
    protected int price;
    private boolean hasBeenInteractedWith = false;
    private boolean isInteractable;  // New field to store the interactable state

    // Constructor
    public Weapon(String name, int strengthBonus, int price, boolean isInteractable) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.price = price;
        this.isInteractable = isInteractable;  // Initialize the interactable state
    }

    // Getters and setters
    public int getStrengthBonus() {
        return strengthBonus;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    // Method to set the interacted state
    public void setHasBeenInteracted(boolean interacted) {
        this.hasBeenInteractedWith = interacted;
    }

    // Method to check if the weapon has been interacted with
    public boolean hasBeenInteracted() {
        return hasBeenInteractedWith;
    }

    // Method to check if the weapon is interactable
    public boolean isInteractable() {
        return isInteractable;
    }

    // Method to set the weapon's interactable state
    public void setInteractable(boolean interactable) {
        this.isInteractable = interactable;
    }
}
