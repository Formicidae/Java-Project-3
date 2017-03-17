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
public abstract class BaseNode {
    private int row;
    private int seat;
    
    public BaseNode(){
        row = -1;
        seat = -1;
    }
    public BaseNode(int r,int s){
        row = r;
        seat = s;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
    
    
}
