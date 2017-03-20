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
        if(head == null){
            head = new DoubleLinkNode(r,s,tail,null);
            tail = head;
            return;
        }
        DoubleLinkNode cur = head;
        while(cur.getNext() != null){
            if(cur.getRow() >= r){
                if(cur.getSeat() > s){
                    DoubleLinkNode newN = new DoubleLinkNode(r,s,cur,cur.getPrev());
                    cur.getPrev().setNext(newN);
                    cur.setPrev(newN);
                    return;
                }
            }
            cur = cur.getNext();
        }
        tail.setNext(new DoubleLinkNode(r,s,null,tail));
        tail = tail.getNext();
    }

    public void deleteNode(int r, int s){
        DoubleLinkNode cur = head;
        while(cur.getNext() != null){
            if(cur.getRow() == r){
                if(cur.getSeat() == s){
                    cur.getPrev().setNext(cur.getNext());
                    cur.getNext().setPrev(cur.getPrev());
                    System.out.println("deleted");
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
