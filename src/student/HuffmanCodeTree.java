package student;

import provided.BinarySequence;

import java.util.Iterator;

public class HuffmanCodeTree {
    /**
     * Root node of tree.
     */
    private HuffmanNode root;
    /**
     * Creates instance of HuffmanCodeTree object.
     *
     * @param root Set root node of tree.
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }
    /**
     * Creates instance of HuffmanCodeTree object using a HuffmanCodeBook.
     *
     * @param codeBook set up the tree with an established codebook.
     */
    public HuffmanCodeTree(HuffmanCodeBook codeBook) {
        codeBook.forEach((node) -> {
            put(node.getValue(), node.getKey());
        });
    }
    /**
     * Checks if the Huffman code tree is valid.
     *
     * @return T/F if the tree is valid
     */
    public boolean isValid() {
        return root.isValidTree();
    }
    /**
     * Puts a new character in the tree using BinarySequence as a guide.
     *
     * @param seq The path.
     * @param letter The character to be inserted into the tree.
     */
    public void put(BinarySequence seq, char letter) {
        root = putHelper(root, seq.iterator(), letter);
    }
    /**
     * put helper method.
     *
     * @param node The current node being processed.
     * @param seq The iterator from the binary sequence.
     * @param letter The character to be added to the tree.
     * @return The updated node after being updated.
     */
    private HuffmanNode putHelper(HuffmanNode node, Iterator<Boolean> seq, char letter) {
        if (seq.hasNext()) {
            if (node == null) {
                node = new HuffmanNode(null, null);
            }
            if (seq.next()) {
                node.setOne(putHelper(node.getOne(), seq, letter));
            } else {
                node.setZero(putHelper(node.getZero(), seq, letter));
            }
            return node;
        } else {
            return new HuffmanNode(letter);
        }
    }
    /**
     * Decodes a binary sequence into a string using a BinarySequence.
     *
     * @param s The binary sequence to be decoded.
     * @return The decoded string.
     */
    public String decode(BinarySequence s) {
        return decodeHelper(root, s.iterator(), new StringBuilder());
    }
    /**
     * decode helper method.
     *
     * @param node The current node being used.
     * @param s The iterator from binary sequence.
     * @param out The StringBuilder to store the decoded string.
     * @return The decoded string.
     */
    private String decodeHelper(HuffmanNode node, Iterator<Boolean> s, StringBuilder out) {
        boolean keepLooping = true;
        while (keepLooping) {
            if (node.isLeaf()) {
                out.append(node.getData());
                node = root;
            }
            keepLooping = s.hasNext();
            if (s.next() && keepLooping) {
                node = node.getOne();
            } else {
                node = node.getZero();
            }
        }
        return out.toString();
    }
}
