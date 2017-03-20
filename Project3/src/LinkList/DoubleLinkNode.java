/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkList;

/**
 *
 * @author Eddie
 */
public class DoubleLinkNode extends BaseNode{
    private DoubleLinkNode next;
    private DoubleLinkNode prev;
    
    public DoubleLinkNode(){
        setRow(-1);
        setSeat(-1);
        next = null;
        prev = null;
    }
    public DoubleLinkNode(int r, int s, DoubleLinkNode n,DoubleLinkNode p){
        setRow(r);
        setSeat(s);
        next = n;
        prev = p;
    }

    public DoubleLinkNode getNext() {
        return next;
    }

    public void setNext(DoubleLinkNode next) {
        this.next = next;
    }

    public DoubleLinkNode getPrev() {
        return prev;
    }

    public void setPrev(DoubleLinkNode prev) {
        this.prev = prev;
    }

}
