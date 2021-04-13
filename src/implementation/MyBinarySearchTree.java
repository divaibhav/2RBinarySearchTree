package implementation;

public class MyBinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;//head

    public Node<E> getRoot() {
        return root;
    }
    //insert
    public void insert(E data){
        Node<E> node = new Node<>(data);
        if(isEmpty()){
            root = node;
        }
        else{
            Node<E> temp = root;
            Node<E> parent = null;
            while(temp != null){
                parent = temp;
                if(data.compareTo(temp.getData()) <= 0){
                    temp = temp.getLeft();
                }
                else{
                    temp = temp.getRight();
                }
            }
            //identifying the position left or right
            //to insert new node
            if(data.compareTo(parent.getData()) <= 0){
                parent.setLeft(node);
            }
            else{
                parent.setRight(node);
            }
        }
    }

    private boolean isEmpty() {
        if(root == null){
            return true;
        }
        return false;
    }
    public void preOrder(Node<E> node){
        if(node != null){
            //step 1
            System.out.print(node.getData() + ", ");
            //step 2
            //recursive call
            preOrder(node.getLeft());
            //step 3
            preOrder(node.getRight());
        }
    }
}
