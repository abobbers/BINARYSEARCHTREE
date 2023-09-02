import java.util.Iterator;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {
    public boolean flag;
    private Node<E> findIOP(Node<E> curr) {
        curr = curr.left;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }
    public void insert(E data){
        root = insert(root, data);
    }

    public Node<E> insert(Node<E> pivot, E data){
        if(pivot == null){
            return new Node<E>(data);
        } else if(data.compareTo(pivot.data) <= 0){
            pivot.left = insert(pivot.left, data);
        } else if(data.compareTo(pivot.data) > 0){
            pivot.right = insert(pivot.right, data);
        } return pivot;
    }

    public void remove(E data){
        root = remove(root, data);
    }

    public Node<E> remove(Node<E> pivot, E data){
        if(pivot == null){
            return null;
        } else if(data.compareTo(pivot.data) < 0){
            pivot.left = remove(pivot.left, data);
        } else if(data.compareTo(pivot.data) > 0){
            pivot.right = remove(pivot.right, data);
        } else {
            if (pivot.left == null) {
                return pivot.right;
            } else if (pivot.right == null) {
                return pivot.left;
            }
            pivot.data = findIOP(pivot).data;
            pivot.left = remove(pivot.left, pivot.data);
        } return pivot;
    }

    public boolean search(E data){
        search(root, data);
        return flag;
    }

    public void search(Node<E> pivot, E data){
        if(pivot == null){
            flag = false;
        } else if(data.compareTo(pivot.data) == 0){
            flag = true;
        } else if(data.compareTo(pivot.data) < 0){
            search(pivot.left, data);
        } else {
            search(pivot.right, data);
        }
    }

    private Vector<E> vector;

    public Iterator<E> iterator() {
        vector = new Vector<E>();
        traverse(root);
        return vector.iterator();
    }

    private void traverse(Node<E> curr){
        if(curr != null){
            traverse(curr.left);
            vector.add(curr.data);
            traverse(curr.right);
        }
    }
}
