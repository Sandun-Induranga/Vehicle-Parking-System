package Db;

import model.Driver;

public class Stack<T> extends Driver{
    public Stack(String driverName, String nic, String licenseNum, String address, String contactNum) {
        super(driverName, nic, licenseNum, address, contactNum);
    }

    class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node top;

    public void push(T data) {
        Node n1 = new Node(data); //1
        n1.next = top; //2
        top = n1; //3
    }

    public void printStack() {
        Node temp = top;
        System.out.print("[");
        while (temp != null) {
            System.out.print(temp.data + ", ");
            temp = temp.next;
        }
        System.out.println(top == null ? "empty]" : "\b\b]");
    }

    public void pop() {
        if (top != null) {
            top = top.next;
        } else {
            System.out.println("Stack is empty...");
        }
    }

    public int size() {
        Node temp = top;
        int count = 0;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    public void clear() {
        top = null;
    }

    public T peek() {
        return top == null ? null : top.data;
    }

    public T poll() {
        if (top == null) {
            return null;
        } else {
            T data = top.data;
            top = top.next;
            return data;
        }
    }

    public Driver[] toArrayDriver() {
        Object[] tempDataArray = new Object[size()];
        Node temp = top;
        int i = 0;
        while (temp != null) {
            tempDataArray[i++] = temp.data;
            temp = temp.next;
        }
        return (Driver[]) tempDataArray;
    }
}



