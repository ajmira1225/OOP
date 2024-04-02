import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dataStructure {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a sample list of objects (Exercise 1)
        List<Object> objects = new ArrayList<>();
        objects.add(new Object("Object1", 1));
        objects.add(new Object("Object2", 2));
        objects.add(new Object("Object3", 3));

        // Creating child objects for Object1
        List<Object> object1Children = new ArrayList<>();
        object1Children.add(new Object("Object1.1", 11));
        object1Children.add(new Object("Object1.2", 12));
        object1Children.add(new Object("Object1.3", 13));
        objects.get(0).setChildren(object1Children);

        // Creating child objects for Object1.1
        List<Object> object1_1Children = new ArrayList<>();
        object1_1Children.add(new Object("Object1.1.1", 111));
        objects.get(0).getChildren().get(0).setChildren(object1_1Children);

        // Creating child objects for Object3
        List<Object> object3Children = new ArrayList<>();
        object3Children.add(new Object("Object3.1", 31));
        objects.get(2).setChildren(object3Children);

        // Creating child objects for Object3.1
        List<Object> object3_1Children = new ArrayList<>();
        object3_1Children.add(new Object("Object3.1.1", 311));
        objects.get(2).getChildren().get(0).setChildren(object3_1Children);

        // Create the hierarchical data structure
        Hierarchy hierarchy = new Hierarchy(objects);

        // Make the program interactive
        System.out.println("Pipili o Mamimili:");
        System.out.println("1. Puno");
        System.out.println("2. Bala");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            hierarchy.displayTree();
        } else if (choice == 2) {
            hierarchy.displayBullet();
        } else {
            System.out.println("Invalid choice.");
        }
    }
}

class Object {
    private String name;
    private int id;
    private List<Object> children;

    public Object(String name, int id) {
        this.name = name;
        this.id = id;
        this.children = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }
}

class Hierarchy {
    private List<Object> objects;

    public Hierarchy(List<Object> objects) {
        this.objects = objects;
    }

    public void displayTree() {
        System.out.println("Eto ang puno:");
        for (Object obj : objects) {
            displayTreeRecursive(obj, 0);
        }
    }

    private void displayTreeRecursive(Object obj, int level) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < level; i++) {
            prefix.append("    "); // Four spaces per level
        }
        System.out.println(prefix.toString() + "└─ " + obj.getName());
        for (Object child : obj.getChildren()) {
            displayTreeRecursive(child, level + 1);
        }
    }

    public void displayBullet() {
        System.out.println("Ito ang itsura ng bala:");
        for (Object obj : objects) {
            displayBulletRecursive(obj, 0);
        }
    }

    private void displayBulletRecursive(Object obj, int level) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < level; i++) {
            prefix.append("    "); // Four spaces per level
        }
        String bullet = level == 0 ? "●" : "○"; // Root node is a circle, other nodes are dots
        System.out.println(prefix.toString() + bullet + " " + obj.getName());
        for (Object child : obj.getChildren()) {
            displayBulletRecursive(child, level + 1);
        }
    }
}