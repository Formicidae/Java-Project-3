
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import LinkList.LinkedList;
import LinkList.DoubleLinkNode;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        FileOutputStream out = null;
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
		for(int i = 0; i < line.length();i++){
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
        
        LinkedList A2Available = new LinkedList(new DoubleLinkNode());
        LinkedList A2Reserved = new LinkedList(new DoubleLinkNode());
        try {
            File A2f = new File("A2.txt");
            Scanner A2 = new Scanner(A2f);
            int row = 0;
            A2Available = new LinkedList();
            A2Reserved = new LinkedList();
            while(A2.hasNextLine()){
                String line = A2.nextLine();
		for(int i = 0; i < line.length();i++){
                    if(line.charAt(i) == '#'){
                        A2Available.addNode(row,i);
                    }
                    else{
                        A2Reserved.addNode(row,i);
                    }
                }
                row++;
            }
            A2.close();
        } catch (IOException e) {
             //Print error for file not found
             System.out.println("File not found" + e);
        }
        
        LinkedList A3Available = new LinkedList(new DoubleLinkNode());
        LinkedList A3Reserved = new LinkedList(new DoubleLinkNode());
        try {
            File A3f = new File("A3.txt");
            Scanner A3 = new Scanner(A3f);
            int row = 0;
            A3Available = new LinkedList();
            A3Reserved = new LinkedList();
            while(A3.hasNextLine()){
                String line = A3.nextLine();
		for(int i = 0; i < line.length();i++){
                    if(line.charAt(i) == '#'){
                        A3Available.addNode(row,i);
                    }
                    else{
                        A3Reserved.addNode(row,i);
                    }
                }
                row++;
            }
            A3.close();
        } catch (IOException e) {
             //Print error for file not found
             System.out.println("File not found" + e);
        }
        
        Scanner input = new Scanner(System.in);
        
        LinkedList aval = null;
        LinkedList res = null;
        boolean aud = true;
        while(aud){
            System.out.println("Which Auditorium?");    
                switch(input.nextInt()){
                    case 1:
                        aval = A1Available;
                        res = A1Reserved;
                        aud = false;
                        try {
                            out = new FileOutputStream("A1.txt");
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found" + ex);
                        }
                        break;
                    case 2:
                        aval = A2Available;
                        res = A2Reserved;
                        aud = false;
                        try {
                            out = new FileOutputStream("A2.txt");
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found" + ex);
                        }
                        break;
                    case 3:
                        aval = A3Available;
                        res = A3Reserved;
                        aud = false;
                        try {
                            out = new FileOutputStream("A3.txt");
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found" + ex);
                        }
                        break;
                    default:
                        System.out.println("Invalid Auditorium");
                    
                }
        }
        
        int middleRow;
        if(aval.getTail().getRow() > res.getTail().getRow()){
            middleRow = aval.getTail().getRow() / 2;
        }
        else{
            middleRow = res.getTail().getRow() / 2;
        }
        
        int middleSeat;
        if(aval.getTail().getSeat() > res.getTail().getSeat()){
            middleSeat = aval.getTail().getSeat() / 2;
        }
        else{
            middleSeat = res.getTail().getSeat() / 2;
        }
        
        boolean running = true;        
        while(running){
            System.out.println("\n1) Reserve Seats \n" + "2) View Auditorium \n" + "3) Exit");
            switch(input.nextInt()){
                case 1:
                    System.out.println(middleRow + " " + middleSeat);
                    System.out.println("Which row, seat and how many tickets would you like?");
                    //Add Validation for each number
                    int row = input.nextInt() - 1;
                    int seat = input.nextInt() - 1;
                    int num = input.nextInt();
                    if(checkAvalible(row,seat,num,aval)){
                        System.out.println("Avalible");
                        reserveSeats(row,seat,num,res,aval);
                        System.out.println("Seats reserved");
                    }
                    else{
                        System.out.println("Looking for best");
                        //Display best
                        int bestR = 2147483647;
                        int bestS = 2147483647;
                        
                        DoubleLinkNode cur = aval.getHead();
                        while(cur.getNext() != null){
                            if(distance(bestR, bestS, middleRow, middleSeat) > distance(cur.getRow(), cur.getSeat(), middleRow, middleSeat)){
                                if(checkAvalible(cur.getRow(),cur.getSeat(),num,aval)){
                                    bestR = cur.getRow();
                                    bestS = cur.getSeat();
                                }
                            }
                            cur = cur.getNext();
                        }
                        System.out.println("Would you like seat: " + bestS + " row: " + bestR + " ?");
                        char yn = input.next().charAt(0);
                        if(yn == 'Y' || yn == 'y'){
                            reserveSeats(bestR,bestS,num,res,aval);
                            System.out.println("Seats reserved");
                        }
                        else{
                            System.out.println("Seats NOT reserved");
                        }
                    }
                    break;

                case 2:
                    //Show aud
                    viewAuditorium(res,aval);
                    break;
                case 3:
                    running = false;
                    break;
            }
        }
        
        try {
            printAuditorium(res,aval,out);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean checkAvalible(int row, int seat, int num, LinkedList A){
        DoubleLinkNode cur = A.getHead();
        while(cur.getNext() != null){
            if(cur.getRow() == row){
                if(cur.getSeat() == seat){
                    for(int i = 0;i < num;i++){
                        if(cur.getRow() == row){
                            if(cur.getSeat() == seat + i){     
                            }
                            else{
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                        cur = cur.getNext();
                    }
                    return true;
                }
            }
            cur = cur.getNext();
        }
        return false;
    }
    
    public static void reserveSeats(int row,int seat,int num,LinkedList Res,LinkedList Aval){
        for(int i = 0; i < num; i++){
            System.out.println("reserving");
            Res.addNode(row, seat + i);
            Aval.deleteNode(row, seat + i);
        }
    }
    
    public static double distance(int row, int seat, int mr, int ms){
        double dis = Math.sqrt(Math.pow(Math.abs((double)row - (double)mr),2) + Math.pow(Math.abs((double)seat - (double)ms),2));
        if(row == mr){
            //Tie breaker
            dis -= .1;
        }
        return dis;
    }
    public static void printAuditorium(LinkedList Res,LinkedList Aval,FileOutputStream out) throws IOException{
        int tmprow = 2147483647;
        DoubleLinkNode a = Aval.getHead();
        DoubleLinkNode r = Res.getHead();
        while(true){
            //System.out.println(a.getRow() + " " + a.getSeat() + "\n" + r.getRow() + " " + r.getSeat());
            if(a.getRow() <= r.getRow() && (a.getSeat() < r.getSeat() || a.getRow() < r.getRow())){
                out.write('#');
                tmprow = a.getRow();
                if(a.getNext() != null){
                    a = a.getNext();
                }
                else{
                    if(r.getRow() == 2147483647){
                        return;
                    }
                    a = new DoubleLinkNode(2147483647,2147483647,null,null);
                }
            }
            else if(r.getRow() <= a.getRow() && (r.getSeat() < a.getSeat() || r.getRow() < a.getRow())){
                out.write('.');
                tmprow = r.getRow();
                if(r.getNext() != null){
                    //System.out.print("next");
                    r = r.getNext();
                }
                else{
                    if(a.getRow() == 2147483647){
                        return;
                    }
                    r = new DoubleLinkNode(2147483647,2147483647,null,null);
                }

            }
            //check if row has changed
            if(a.getRow() > tmprow && r.getRow() > tmprow){
                tmprow = 2147483647;
                out.write('\r');
                out.write('\n');
            }
        }
      
    }
    
    public static void viewAuditorium(LinkedList Res,LinkedList Aval){
        System.out.print("  ");
        int rowLength = 0;
        if(Aval.getTail().getSeat() > Res.getTail().getSeat()){
            rowLength = Aval.getTail().getSeat();
        }
        else{
            rowLength = Res.getTail().getSeat();
        }
        for(int i = 1; i <= rowLength + 1;i++){
            System.out.print(i % 10);
        }
        System.out.print("\n1 ");
        
        int tmprow = 2147483647;
        DoubleLinkNode a = Aval.getHead();
        DoubleLinkNode r = Res.getHead();
        while(true){
            //System.out.println(a.getRow() + " " + a.getSeat() + "\n" + r.getRow() + " " + r.getSeat());
            if(a.getRow() <= r.getRow() && (a.getSeat() < r.getSeat() || a.getRow() < r.getRow())){
                System.out.print("#");
                tmprow = a.getRow();
                if(a.getNext() != null){
                    a = a.getNext();
                }
                else{
                    if(r.getRow() == 2147483647){
                        return;
                    }
                    a = new DoubleLinkNode(2147483647,2147483647,null,null);
                }
            }
            else if(r.getRow() <= a.getRow() && (r.getSeat() < a.getSeat() || r.getRow() < a.getRow())){
                System.out.print(".");
                tmprow = r.getRow();
                if(r.getNext() != null){
                    r = r.getNext();
                }
                else{
                    if(a.getRow() == 2147483647){
                        return;
                    }
                    r = new DoubleLinkNode(2147483647,2147483647,null,null);
                }

            }
            //check if row has changed
            if(a.getRow() > tmprow && r.getRow() > tmprow){
                System.out.print("\n" + (tmprow + 2) + " ");
                tmprow = 2147483647;
            }
        }
      
    }
    
}
