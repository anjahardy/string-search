public class Leaf {

    char character;
    int frequency;
    Leaf left;
    Leaf right;

    public Leaf(char character, int frequency, Leaf left, Leaf right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}
