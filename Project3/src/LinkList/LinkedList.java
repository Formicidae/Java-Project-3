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
        }
        tail.setNext(new DoubleLinkNode(r,s,null,tail));
        tail = tail.getNext();
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
