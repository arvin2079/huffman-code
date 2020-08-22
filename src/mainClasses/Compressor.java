package mainClasses;

import Exceptions.EmptyFileException;
import helperClasses.BinaryTree;
import helperClasses.Node;
import helperClasses.NodeLinkedList;
import helperClasses.PriorityQueue;
import java.io.*;

public class Compressor {
    private File mainFile;

    public Compressor(File file) {
        this.mainFile = file;
    }



    public File compress() {
        try {
            FileReader reader = new FileReader(mainFile);
            int character = reader.read();

            if(character == -1)
                throw new EmptyFileException();

            PriorityQueue charQ = new PriorityQueue(String.valueOf((char)character), String.valueOf(character));
            character = reader.read();
            while (character != -1) {
                charQ.enqueue(new Node(String.valueOf((char)character), String.valueOf(character)));
                character = reader.read();
            }

            //main priority q
            PriorityQueue charNumQueue = new PriorityQueue();
            NodeLinkedList charNumList = new NodeLinkedList();

            int counter = 1;
            Node pre = charQ.dequeue();
            while (charQ.peek() != null) {

                if(charQ.peek().value.equals(pre.value)) {
                    counter ++;
                    pre = charQ.dequeue();
                } else {
                    charNumQueue.enqueue(new Node(pre.key, String.valueOf(counter)));
                    charNumList.addFirst(new Node(pre.key, String.valueOf(counter)));
                    counter = 1;
                    pre = charQ.dequeue();
                }
            }
            if (counter != 0) {
                charNumQueue.enqueue(new Node(pre.key, String.valueOf(counter)));
                charNumList.addFirst(new Node(pre.key, String.valueOf(counter)));
            }


            //for printing
//            int size = charNumQueue.getSize();
//            for(int i=0 ; i<size ; i++) {
//                System.out.println(charNumQueue.dequeue().toString());
//            }

            NodeLinkedList encodingList = new NodeLinkedList();
            BinaryTree bt = createBinaryTree(charNumQueue);
            encodBinaryTree(true , bt.root, encodingList);

            //for printing encodings
//            for (int i=0 ; i<encodingList.getSize() ; i++) {
//                System.out.println(encodingList.get(i));
//            }

            File resultFile = new File(mainFile.getAbsolutePath().substring(0, mainFile.getAbsolutePath().length() - mainFile.getName().length()) + "resultFile.txt");
            if(resultFile.createNewFile())
                System.out.println("result file created.");
            else {
                resultFile.delete();
                resultFile.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile, true));
            writer.newLine();
            for(int i=0 ;i<encodingList.getSize() ; i++) {
                writer.append(encodingList.get(i).toString() + "\n");
            }
            writer.append("finishfinishfinish\n");
            writer.close();

            writeInFile(mainFile, resultFile, encodingList);
            return resultFile;


        } catch (IOException e) {
            e.printStackTrace();
        }

        //unreachable statement
        return null;

    }

    private void writeInFile(File mainFile, File resultFile, NodeLinkedList encodingList) {
        try {

            FileReader reader = new FileReader(mainFile);
            int character = reader.read();

            if(character == -1)
                throw new EmptyFileException();

            byte temp = 0;
            byte addition = 0;
            int pointer = 0;
            while (character != -1) {

                String encod = encodingList.getValue(String.valueOf((char)character));
                for (int i=0 ; i<encod.length() ; i++) {
                    addition = encod.substring(i,i+1).equals("1") ? (byte) ((int) Math.pow(2, 6-pointer)) : 0;
                    temp = (byte) (temp | addition);
                    pointer++;
                    if(pointer > 6) {
                        ////////////////////
                        appendByteInFile(resultFile, temp);
                        pointer = 0;
                        temp = 0;
                    }
                }
                character = reader.read();
            }
            if (pointer != 0) {
                appendByteInFile(resultFile, temp);
                RandomAccessFile f = new RandomAccessFile(resultFile, "rw");
                f.write(String.valueOf(pointer).getBytes());
                f.close();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendByteInFile(File resultFile, byte temp) {
        try {

            FileOutputStream out = new FileOutputStream(resultFile , true);
            out.write(temp);
            out.close();

        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void encodBinaryTree(boolean firstTime, Node node , NodeLinkedList list) {
        if(node.right != null) {
            node.right.encod = node.encod.trim() + "1";
            firstTime = false;
            encodBinaryTree(firstTime, node.right, list);
        }

        if(node.left != null) {
            node.left.encod  += node.encod.trim() + "0";
            firstTime = false;
            encodBinaryTree(firstTime ,node.left, list);
        }

        if(node.right == null && node.left == null) {
            Node node1 = new Node(node.key, firstTime ? "0" : node.encod);
            list.addFirst(node1);
        }

    }

    public static BinaryTree createBinaryTree(PriorityQueue queue) {
        while (queue.getSize() != 1) {
            Node left  = queue.dequeue();
            Node right = queue.dequeue();
            Node root  = new Node(left.key.trim() + right.key.trim(), left.value.add(right.value));
            root.right = right;
            root.left  = left;
            left.parent = root;
            right.parent = root;
            queue.enqueue(root);
        }

        Node root = queue.dequeue();
        BinaryTree bt = new BinaryTree(root, new BinaryTree(root.left), new BinaryTree(root.right));
        return bt;
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\arvin\\Desktop\\qutue.txt");
        new Compressor(file).compress();
    }

}
