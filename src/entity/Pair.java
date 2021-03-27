/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author acer
 * @param <T1>
 * @param <T2>
 */
public class Pair<T1, T2> {
    private T1 first;
    private T2 second;
    
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    
    public T1 getFirst() {
        return first;
    }
    
    public T2 getSecond() {
        return second;
    }
}
