package student;

import provided.BinarySequence;

import java.util.function.Consumer;

public class HuffmanCodeBook {
    /**
     * Root node housing all the children in the tree.
     */
    private Node root;
    /**
     * Creates an instance of the HuffmanCodeBook.
     */
    public HuffmanCodeBook() {

    }
    /**
     * Adds a character and binary sequence to the tree.
     *
     * @param c The character the binary represents.
     * @param seq The binary sequence to add.
     */
    public void addSequence(char c, BinarySequence seq) {
        root = addSequenceHelper(root, c, seq);
    }
    /**
     * Helper method for addSequence.
     *
     * @param node Node to be checked/modified
     * @param key Used for comparing
     * @param value Stored in node
     * @return updated node
     */
    private Node addSequenceHelper(Node node, char key, BinarySequence value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key > node.getKey()) {
            node.setLeft(addSequenceHelper(node.getLeft(), key, value));
        } else if (key < node.getKey()) {
            node.setRight(addSequenceHelper(node.getRight(), key, value));
        }
        return node;
    }
    /**
     * Checks if the code book contains a Huffman code sequence for the specified character.
     *
     * @param letter The character to check for.
     * @return T/F is the tree contains the char
     */
    public boolean contains(char letter) {
        return containsHelper(root, letter);
    }
    /**
     * Contains helper method.
     *
     * @param node node to be searched for.
     * @param key checking to see if the tree contains this.
     * @return True if the code book contains a sequence for the character, otherwise false.
     */
    private boolean containsHelper(Node node, char key) {
        if (node == null) {
            return false;
        }
        if (key > node.getKey()) {
            return containsHelper(node.getLeft(), key);
        } else if (key < node.getKey()) {
            return containsHelper(node.getRight(), key);
        } else {
            return true;
        }
    }
    /**
     * Checks if a whole string is contained in the tree.
     *
     * @param string The string to check for.
     * @return T/F if the string is contained.
     */
    public boolean containsAll(String string) {
        if (string.isEmpty()) {
            return true;
        }
        if (contains(string.charAt(0))) {
            return containsAll(string.substring(1));
        } else {
            return false;
        }
    }
    /**
     * Gets the binary sequence associated with the key value.
     *
     * @param c The key value to use.
     * @return The Huffman code sequence for the character, or null if not found.
     */
    public BinarySequence getSequence(char c) {
        return getSequenceHelper(root, c);
    }
    /**
     * getSequence helper method.
     *
     * @param node used to look through child nodes.
     * @param key used to look through for value in nodes.
     * @return Binary sequence found with the key.
     */
    private BinarySequence getSequenceHelper(Node node, char key) {
        if (node == null) {
            return null;
        }
        if (key > node.getKey()) {
            return getSequenceHelper(node.getLeft(), key);
        } else if (key < node.getKey()) {
            return getSequenceHelper(node.getRight(), key);
        } else {
            return node.getValue();
        }
    }
    /**
     * Encodes the given string using a BinarySequence.
     *
     * @param s The string to encode.
     * @return The BinarySequence representing the encoded string.
     */
    public BinarySequence encode(String s) {
        if (s.isEmpty()) {
            return new BinarySequence();
        }

        BinarySequence out = new BinarySequence();
        for (int i = 0; i < s.length(); i++) {
            out.append(getSequence(s.charAt(i)));
        }
        return out;
    }
    /**
     * Performs an action on every node.
     *
     * @param action The action to be performed for each node.
     */
    public void forEach(Consumer<Node> action) {
        action.accept(forEachHelper(root, action));
    }
    /**
     * forEach helper method.
     *
     * @param node Node to look through.
     * @param action The action to be performed for each node.
     * @return node after being looped through.
     */
    private Node forEachHelper(Node node, Consumer<Node> action) {
        if (node != null) {
            action.accept(node);

            forEachHelper(node.getLeft(), action);
            forEachHelper(node.getRight(), action);
        }

        return node;
    }
    /**
     * Generates a string representation of the code book.
     *
     * @return The string representation of the code book.
     */
    @Override
    public String toString() {
        return toStringHelper(root, "");
    }
    /**
     * toString helper method.
     *
     * @param node Node to look through.
     * @param out string for output.
     * @return The string representation of the code book.
     */
    private String toStringHelper(Node node, String out) {
        if (node == null) {
            return out;
        }
        out += node + " \n";
        out = toStringHelper(node.getLeft(), out);
        out = toStringHelper(node.getRight(), out);

        return out;
    }
    /**
     * Represents a node in the Huffman code book binary tree.
     */
    public static class Node {
        /**
         * Left child node.
         */
        private Node left;
        /**
         * Right child node.
         */
        private Node right;
        /**
         * Key value for searching.
         */
        private Character key;
        /**
         * Value corresponding to key.
         */
        private BinarySequence value;
        /**
         * Constructs a node with the specified character key and binary sequence value.
         *
         * @param key The character associated with the value.
         * @param value The binary sequence value associated with the key.
         */
        public Node(Character key, BinarySequence value) {
            this.key = key;
            this.value = value;
        }
        /**
         * Retrieves the character key of the node.
         *
         * @return The character key of the node.
         */
        public Character getKey() {
            return key;
        }
        /**
         * Gets the binary sequence value associated with the node.
         *
         * @return The binary sequence value associated with the node.
         */
        public BinarySequence getValue() {
            return this.value;
        }
        /**
         * Gets the left child node of the current node.
         *
         * @return The left child node.
         */
        public Node getLeft() {
            return this.left;
        }
        /**
         * Gets the right child node of the current node.
         *
         * @return The right child node.
         */
        public Node getRight() {
            return this.right;
        }
        /**
         * Sets the left child node of the current node.
         *
         * @param left The left child node to set.
         */
        public void setLeft(Node left) {
            this.left = left;
        }
        /**
         * Sets the right child node of the current node.
         *
         * @param right The right child node to set.
         */
        public void setRight(Node right) {
            this.right = right;
        }
        /**
         * Sets key for this node.
         *
         * @param key to be set.
         */
        public void setKey(char key) {
            this.key = key;
        }
        /**
         * Sets value for this node.
         *
         * @param seq to be set.
         */
        public void setValue(BinarySequence seq) {
            this.value = seq;
        }
        /**
         * Generates a string representation of the node.
         *
         * @return The string representation of the node.
         */
        @Override
        public String toString() {
            return this.key + ": " + this.value;
        }
    }
}
