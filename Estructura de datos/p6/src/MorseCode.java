import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCode {

    EDBinaryNode<Character> morseRoot = null;

    public MorseCode() {
        morseRoot= new EDBinaryNode(null);
        readFileInfo();
    }

    private void readFileInfo() {
        Scanner input = null;
        try {
            input = new Scanner(new File("morseCodes.txt"));
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
        }

        while (input.hasNextLine()) {
            String cars = input.next();
            char car = cars.charAt(0);
            String code = input.next();
            insert(car, code);
        }
        input.close();
    }

    public String inorderPrint() {
        return inorderPrint(morseRoot);
    }

    private String inorderPrint(EDBinaryNode<Character> n) {
        String s1, s2;
        if (n != null) {
            s1 = inorderPrint(n.left());
            s2 = inorderPrint(n.right());
            if (!n.containsNull())
                return s1 + n.data() + s2;
            else
                return s1 + s2;
        }
        return "";
    }

    /**
     *  Añade un nuevo carácter al árbol binario
     *
     *  El método debe añadir un nodo con el caracter car en el árbol binario, según la secuencia de puntos y rayas.
     *  Si el nodo ya existía se sobreescribw.
     *
     * @param car   Carácter que debe añadirse en el árbol binario
     * @param code  Codificación del carácter con puntos y rayas
     */
    public void insert(char car, String code) {
       // TO DO
        EDBinaryNode<Character> aux = morseRoot;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '.') {
                if (aux.left() == null)
                    aux.setLeft(new EDBinaryNode<>(null));
                aux = aux.left();
            } else if (code.charAt(i) == '-') {
                if (aux.right() == null)
                    aux.setRight(new EDBinaryNode<>(null));
                aux = aux.right();
            }
        }
        aux.setData(car);
    }

    /**
     * Decodifica una secuencia de puntos y rayas y la convierte en un texta alfabético.
     *
     * @param codetext Secuencia de puntos y rayas. Los códigos de cada letra independiente están separados por un
     *                 espacio en blanco.
     *
     * @return La cadena de de letras resultante
     */
    public String decode(String codetext) {
        //TO DO
        String res = "";
        EDBinaryNode<Character> aux = morseRoot;
        for (int i = 0; i < codetext.length();i++){
            if(codetext.charAt(i) == '.')
                aux = aux.left();
            else if(codetext.charAt(i) == '-')
                aux = aux.right();
            if (codetext.charAt(i) == ' ' || i == codetext.length() -1) {
                res += aux.data();
                aux = morseRoot;
            }
        }
        return res;
    }

    /**
     * Toma una cadena de texto y la codifica en puntos y rayas
     * @param text El texto a codificar
     *
     * @return Una cadena de puntos y rayas, se añaden espacios para separar los códigos de la letras.
     */
    //añadir metodo recursivo para buscar cada letra, devuelve un String y le pasamos un Nodo y un char
    public String encode(String text) {
       // TO DO
        String res = "";
        for (int i = 0; i < text.length();i++){
            EDBinaryNode<Character> aux = morseRoot;
            char c = text.charAt(i);
            if (c != ' ') {
                res += encode(aux, c, "");
                res += ' ';
            }
        }
        res = res.substring(0,res.length() -1);
        return res;
    }

    public String encode(EDBinaryNode<Character> node, char c, String res){
        if(node.data() != null && node.data() == c) {
            return res;
        }
        if (!node.isLeaf()) {
            if (node.hasLeft()) {
                res += ".";
                String aux = encode(node.left(), c, res);
                if (aux != null)
                    return aux;
                else res = res.substring(0,res.length() -1);
            }
            if (node.hasRight()) {
                res += "-";
                String aux = encode(node.right(), c, res);
                return aux;
            }
        }
        return null;
    }
}
