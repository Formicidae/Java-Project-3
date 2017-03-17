
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import LinkList.LinkedList;
import LinkList.DoubleLinkNode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Eddie
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinkedList A1Available = new LinkedList(new DoubleLinkNode());
        LinkedList A1Reserved = new LinkedList(new DoubleLinkNode());
        try {
            File A1f = new File("A1.txt");
            Scanner A1 = new Scanner(A1f);
            int row = 0;
            A1Available = new LinkedList();
            A1Reserved = new LinkedList();
            while(A1.hasNextLine()){
                String line = A1.nextLine();
		for(int i = 0; i < line.length() - 1;i++){
                    if(line.charAt(i) == '#'){
                        A1Available.addNode(row,i);
                    }
                    else{
                        A1Reserved.addNode(row,i);
                    }
                }
                row++;
            }
            A1.close();
        } catch (IOException e) {
             //Print error for file not found
             System.out.println("File not found" + e);
        }
        
        DoubleLinkNode cur = A1Available.getHead();
        while(cur.getNext() != null){
            System.out.println(cur.getRow() + " " + cur.getSeat());
            cur = cur.getNext();
        }
        
         cur = A1Reserved.getHead();
        while(cur.getNext() != null){
            System.out.println(cur.getRow() + " " + cur.getSeat());
            cur = cur.getNext();
        }


    }
    
}
