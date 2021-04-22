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
    public void inOrder(Node<E> node){
        if(node != null){
            //step 1 -> process left subtree
            //recursive call
            inOrder(node.getLeft());
            //step 2
            System.out.print(node.getData() + ", ");
            //step 3
            inOrder(node.getRight());
        }
    }
    public void postOrder(Node<E> node){
        if(node != null){
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getData() + ", ");
        }
    }
    public boolean search(E searchElement){
        boolean response = false;
        Node<E> temp = root;
        while(temp != null){
            //comparing with root
            if(searchElement.compareTo(temp.getData()) == 0){
                response = true;
                break;
            }
            else if(searchElement.compareTo(temp.getData()) < 0){
                temp = temp.getLeft();
            }
            else{
                temp = temp.getRight();
            }
        }

        return response;
    }
    public void delete(E deletingElement){
        Node<E> temp = root;
        Node<E> parent = null;
        while(temp != null){
            if(comapre(deletingElement,temp) == 0){
                break;
            }
            else{
                //update parent
                parent = temp;
                if(comapre(deletingElement,temp) < 0){
                    temp = temp.getLeft();
                }
                else{
                    temp = temp.getRight();
                }
            }
        }
        if(temp != null){
            //case 1 -leaf node
            if(isLeaf(temp)){
                //root case
                if(parent == null){
                    root = null;
                }
                else{
                    if(comapre(deletingElement, parent) < 0){
                        parent.setLeft(null);
                    }
                    else{
                        parent.setRight(null);
                    }
                }
            }

            //case 2
                //left child
            else if(hasLeftChild(temp)){
                //root node
                if(parent == null){
                    root = root.getLeft();
                }
                else{
                    if(comapre(deletingElement,parent) < 0){
                        parent.setLeft(temp.getLeft());
                    }
                    else{
                        parent.setRight(temp.getLeft());
                    }
                }
            }
                //right child
            else if(hasRightChild(temp)){
                //root case
                if(parent == null){
                    root = root.getRight();
                }
                else{
                    if(comapre(deletingElement,parent) < 0){
                        parent.setLeft(temp.getRight());
                    }
                    else{
                        parent.setRight(temp.getRight());
                    }
                }
            }
            //case 3
            else{
                //get successor
                Node<E> successor = getSuccesor(temp);
                //deleting successor
                delete(successor.getData());
                //root case
                if(parent == null){
                    successor.setRight(root.getRight());
                    successor.setLeft(root.getLeft());
                    root = successor;
                }
            }
                //root case
        }
        else{
            System.out.println("cannot delete");
        }
    }

    private Node<E> getSuccesor(Node<E> node) {
        Node<E> response = null;
        Node<E> temp = node.getRight();
        while (temp.getLeft() != null){
            temp = temp.getLeft();
        }
        response = temp;
        return response;
    }

    private boolean hasRightChild(Node<E> temp) {
        if(temp.getRight() != null && temp.getLeft() == null){
            return true;
        }
        return false;
    }

    private boolean hasLeftChild(Node<E> temp) {
        if(temp.getLeft() != null && temp.getRight() == null){
            return true;
        }
        return false;
    }

    private boolean isLeaf(Node<E> temp) {
        if(temp.getLeft() == null && temp.getRight() == null){
            return true;
        }
        return false;
    }


    private int comapre(E data, Node<E> node){
        return data.compareTo(node.getData());
    }

}
