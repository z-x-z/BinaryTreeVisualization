package drawTree;

/**
 * @description: A BSTree (Binary Search Tree) implement
 * @project: Algorithms
 * @author: caged_bird
 * @Create: 2020-03-19 23:26
 **/
public class BSTree
{
    BinaryTreeNode root;
    void insert(int val)
    {
        root = insertHelper(root, val);
    }
    static BinaryTreeNode insertHelper(BinaryTreeNode node, int val)
    {
        if(node==null)  node = new BSTreeNode(val);
        else
        {
            if(val>node.val) node.right = insertHelper(node.right, val);
            else if(val<node.val) node.left = insertHelper(node.left, val);
        }
        return (BSTreeNode) node;
    }
    boolean find(int val)
    {
        BinaryTreeNode node = root;
        while(node!=null)
        {
            if(val > node.val) node = node.right;
            else if(val<node.val) node = node.left;
            else return true;
        }
        return false;
    }

    void draw()
    {
        BinaryTreeVisualization.visualizeCore(root);
    }

    void preOrder(){if(root!=null) root.preOrder();}

}

class BSTreeNode extends BinaryTreeNode
{
    public BSTreeNode(int x)
    {
        super(x);
    }

}
