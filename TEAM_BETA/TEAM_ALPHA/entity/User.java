package entity;

public class User {
    private int id;
    private String name;
    private String card;  // This represents the Library Card
    private String password;

    public User(int id, String name, String card, String password) {
        this.id = id;
        this.name = name;
        this.card = card;
        this.password = password;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCard() {
        return card;
    }

    public String getPassword() {
        return password;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCard(String card) {
        this.card = card;
    }
    public String getLibraryCard() {
        return card;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
