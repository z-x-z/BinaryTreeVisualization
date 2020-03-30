package drawTree;

/**
 * @description: A BSTree (Binary Search Tree) implement
 * @project: Algorithms
 * @author: caged_bird
 * @Create: 2020-03-19 23:26
 **/
public class BSTree
{
    BSTreeNode root;

    void insert(int val)
    {
        root = BSTreeNode.insertHelper(root, val);
    }

    boolean find(int val)
    {
        if(root==null) return false;
        return root.find(val);
    }


    void draw()
    {
        if(root!=null) BinaryTreeVisualization.visualize(root);
    }

    void preOrder(){if(root!=null) root.preOrder();}

}

class BSTreeNode extends BinaryTreeNode
{
    public BSTreeNode(int x)
    {
        super(x);
    }

    public void insert(int val)
    {
        insertHelper(this, val);
    }
    static BSTreeNode insertHelper(BinaryTreeNode node, int val)
    {
        if(node==null)  node = new BSTreeNode(val);
        else
        {
            if(val>node.val) node.right = insertHelper(node.right, val);
            else if(val<node.val) node.left = insertHelper(node.left, val);
        }
        return (BSTreeNode) node;
    }

    public boolean find(int val)
    {
        BinaryTreeNode node = this;
        while(node!=null)
        {
            if(val > node.val) node = node.right;
            else if(val<node.val) node = node.left;
            else return true;
        }
        return false;
    }
}
