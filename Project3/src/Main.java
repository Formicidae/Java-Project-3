
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import LinkList.LinkedList;
import LinkList.DoubleLinkNode;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eddie Bates emb160030
 */
public class Main {

    public static void main(String[] args) {
        //creates empty linklists
        FileOutputStream out = null;
        LinkedList A1Available = new LinkedList(new DoubleLinkNode());
        LinkedList A1Reserved = new LinkedList(new DoubleLinkNode());
        //looks for first file and reads it
        try {
            File A1f = new File("A1.txt");
            Scanner A1 = new Scanner(A1f);
            int row = 0;
            A1Available = new LinkedList();
            A1Reserved = new LinkedList();
            //goes character by character and if it's # adds it to avalible if not adds to reserve
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
        //opens and reads file
        try {
            File A2f = new File("A2.txt");
            Scanner A2 = new Scanner(A2f);
            int row = 0;
            A2Available = new LinkedList();
            A2Reserved = new LinkedList();
            //goes character by character and if it's # adds it to avalible if not adds to reserve
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
        //Opens and reads file which checking for it not existing
        try {
            File A3f = new File("A3.txt");
            Scanner A3 = new Scanner(A3f);
            int row = 0;
            A3Available = new LinkedList();
            A3Reserved = new LinkedList();
            //goes character by character and if it's # adds it to avalible if not adds to reserve
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
        
        //Creates only input scanner
        Scanner input = new Scanner(System.in);
        
        LinkedList aval = null;
        LinkedList res = null;
        boolean aud = true;
        //lops until valid auditorium is chosen
        while(aud){
            System.out.println("Which Auditorium?");
                //When auditorium is selected it asigns realavent aval and res link list aswell as file to output to
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
        
        //Calculate middle seat for later use in finding best avalible
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
        
        //Loops for valid input for main menu
        boolean running = true;        
        while(running){
            System.out.println("\n1) Reserve Seats \n" + "2) View Auditorium \n" + "3) Exit");
            switch(input.nextInt()){
                case 1:
                    int row = 0;
                    int seat = 0;
                    int num = 0;
                    //Loops and validates row , seat and num
                    boolean seating = true;
                    while(seating){
                        System.out.println("Which row would you like?");
                        row = input.nextInt() - 1;
                        if(row >= 0 && (row <= aval.getTail().getRow() + 1 || row <= res.getTail().getRow() + 1))
                            seating = false;
                    }
                    seating = true;
                    while(seating){
                        System.out.println("Which seat would you like?");
                        seat = input.nextInt() - 1;
                        if(seat >= 0 &&( seat <= aval.getTail().getSeat() + 1 || seat < res.getTail().getSeat() + 1))
                            seating = false;
                    }
                    seating = true;
                    while(seating){
                        System.out.println("How many seats would you like?");
                        num = input.nextInt();
                        if(num >= 0)
                            seating = false;
                    }
                    //once seat position is validated avalibility is checked and seats are reserved if avalible and better ones are found if not
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
                        
                        //looks though open seats to find the closest to the middle
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
                        //asks user if they want the seats and if so reserves them
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
                    //end input loop
                    running = false;
                    break;
            }
        }
        
        //atempt to print aud to file
        try {
            printAuditorium(res,aval,out);
            System.out.println("Printed");
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean checkAvalible(int row, int seat, int num, LinkedList A){
        DoubleLinkNode cur = A.getHead();
        //Finds starting seat in avalible
        while(cur != null){
            if(cur.getRow() == row){
                if(cur.getSeat() == seat){
                    //sees if the requested number of seats are avalible after starting seat, if not returns false
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
        //reserves as many seats as needs most logic handled by linkedlist class
        for(int i = 0; i < num; i++){
            Res.addNode(row, seat + i);
            Aval.deleteNode(row, seat + i);
        }
    }
    
    public static double distance(int row, int seat, int mr, int ms){
        //uses standard distance formula to find distance fron point (usaully middle seat)
        double dis = Math.sqrt(Math.pow(Math.abs((double)row - (double)mr),2) + Math.pow(Math.abs((double)seat - (double)ms),2));
        if(row == mr){
            //Tie breaker
            dis -= .1;
        }
        return dis;
    }
    //Print method for file, same as view aud but doesnt have line numbers
    public static void printAuditorium(LinkedList Res,LinkedList Aval,FileOutputStream out) throws IOException{
        int tmprow = 2147483647;
        DoubleLinkNode a = Aval.getHead();
        DoubleLinkNode r = Res.getHead();
        while(true){
            //looks for smallest seat value
            if(a.getRow() <= r.getRow() && (a.getSeat() < r.getSeat() || a.getRow() < r.getRow())){
                out.write('#');
                tmprow = a.getRow();
                //goes to next node if it exists
                if(a.getNext() != null){
                    a = a.getNext();
                }
                else{
                    //if it is last node it is set for max int value to make sure reserved finishes
                    if(r.getRow() == 2147483647){
                        return;
                    }
                    a = new DoubleLinkNode(2147483647,2147483647,null,null);
                }
            }
            //if smallest value is not in avalible it checks reserved
            else if(r.getRow() <= a.getRow() && (r.getSeat() < a.getSeat() || r.getRow() < a.getRow())){
                out.write('.');
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
                tmprow = 2147483647;
                out.write('\r');
                out.write('\n');
            }
        }
      
    }
    
    //same as printAuditorium but adds row ans seat nubmers for user
    public static void viewAuditorium(LinkedList Res,LinkedList Aval){
        System.out.print("  ");
        int rowLength = 0;
        //finds which tail is the end to get biggest seat and row value
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
            //looks for smallest not printed seat and ditirmiens which array it is in
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
                //if smallest not printed seat is in reserved it is printed as .
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
