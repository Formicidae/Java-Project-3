/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkList;
import LinkList.DoubleLinkNode;
/**
 *
 * @author Eddie
 */
public class LinkedList {
    private DoubleLinkNode head;
    private DoubleLinkNode tail;
    
    public LinkedList(DoubleLinkNode node){
        head = node;
        DoubleLinkNode cur = node;
        while(cur.getNext() != null){
            cur.setNext(cur.getNext());
        }
        tail = cur;
    }

    public LinkedList(){
        head = null;
        tail = null;
    }
    public void addNode(int r, int s){
        //edge case for head
        if(head == null){
            head = new DoubleLinkNode(r,s,tail,null);
            tail = head;
            return;
        }
        //edge case for tail
        if(tail.getRow() < r || (tail.getRow() == r && tail.getSeat() < s)){
            tail.setNext(new DoubleLinkNode(r,s,null,tail));
            tail = tail.getNext();
            return;
        }
        DoubleLinkNode cur = head;
        //looks for node that is after new node
        while(cur.getNext() != null){
            if(cur.getRow() >= r){
                if(cur.getSeat() > s || cur.getRow() > r){
                    //adds new node right before node that is bigger than it
                    DoubleLinkNode newN = new DoubleLinkNode(r,s,cur,cur.getPrev());
                    cur.getPrev().setNext(newN);
                    cur.setPrev(newN);
                    return;
                }
            }
            cur = cur.getNext();
        }
        //used when first making list from file
        tail.setNext(new DoubleLinkNode(r,s,null,tail));
        tail = tail.getNext();
    }

    public void deleteNode(int r, int s){
        DoubleLinkNode cur = head;
        //Loops though list util it finds a node with matching row and seat
        while(cur != null){
            if(cur.getRow() == r){
                if(cur.getSeat() == s){
                    //edge case for deleting head
                    if(cur == head){
                        head = head.getNext();
                        return;
                    }
                    //edge case for deleteing tail
                    if(cur == tail){
                        tail = tail.getPrev();
                        tail.setNext(null);
                        return;
                    }
                    //manges pointers to "go around" deleted node and thus delete it
                    cur.getPrev().setNext(cur.getNext());
                    cur.getNext().setPrev(cur.getPrev());
                    return;
                }
            }
            cur = cur.getNext();
        }
    }
    
    public DoubleLinkNode getHead() {
        return head;
    }

    public void setHead(DoubleLinkNode head) {
        this.head = head;
    }

    public DoubleLinkNode getTail() {
        return tail;
    }

    public void setTail(DoubleLinkNode tail) {
        this.tail = tail;
    }

}
