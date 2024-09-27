package student;

public class HuffmanNode {
    /**
     * Child node zero.
     */
    private HuffmanNode zero;
    /**
     * Child node one.
     */
    private HuffmanNode one;
    /**
     * data stored in the node.
     */
    private Character data;
    /**
     * Creates an instance of the HuffmanNode object.
     *
     * @param zero Child node representing 0 in binary.
     * @param one Child node representing 1 in binary.
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
    }
    /**
     * Creates an instance of the node with data in the object.
     *
     * @param data Character to be added to node.
     */
    public HuffmanNode(char data) {
        this.data = data;
    }
    /**
     * Retrieves the zero child node.
     *
     * @return The zero child node.
     */
    public HuffmanNode getZero() {
        return this.zero;
    }
    /**
     * Sets the zero child node.
     *
     * @param zero node to replace current zero.
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }
    /**
     * Retrieves the one child node.
     *
     * @return The one child node.
     */
    public HuffmanNode getOne() {
        return this.one;
    }
    /**
     * Sets the one child node.
     *
     * @param one node to replace current one.
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }
    /**
     * Gets the character data stored in the node.
     *
     * @return The character stored in the node.
     */
    public Character getData() {
        return this.data;
    }
    /**
     * Sets the data in the node.
     *
     * @param data The character to set.
     */
    public void setData(char data) {
        this.data = data;
    }
    /**
     * Checks if the node is a leaf node (has character data and has no children).
     *
     * @return T/F if the node is a leaf.
     */
    public boolean isLeaf() {
        return this.data != null && this.zero == null && this.one == null;
    }
    /**
     * Checks if the node is a leaf node or if this node has children.
     *
     * @return T/F if the node is valid.
     */
    public boolean isValidNode() {
        return isLeaf() || (this.zero != null && this.one != null);
    }
    /**
     * Checks if the current node, and it's descending children are valid.
     *
     * @return T/F if the tree is valid.
     */
    public boolean isValidTree() {
        if (this.isValidNode()) {
            if (this.isLeaf()) {
                return true;
            } else {
                return zero.isValidTree() && one.isValidTree();
            }
        } else {
            return false;
        }
//        return isValidNode() && (isLeaf() || (zero.isValidTree() && one.isValidTree()));
    }
}
