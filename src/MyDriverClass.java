import student.HuffmanCodeBook;
import student.HuffmanCodeTree;

public class MyDriverClass {
    public static void main(String[] args) {
        HuffmanCodeBook codeBook = ProvidedHuffmanCodeBook.getEbookHuffmanCodebook();

        HuffmanCodeTree tree = new HuffmanCodeTree(codeBook);
    }
}
