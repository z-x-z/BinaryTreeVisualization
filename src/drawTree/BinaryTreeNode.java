package drawTree;

/**
 * @description: Binary tree node implements
 * @project: Algorithms
 * @author: caged_bird
 * @Create: 2020-03-19 15:55
 **/


public class BinaryTreeNode
{
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(int x)
    {
        val = x;
    }

    public void preOrder()
    {
        System.out.print(val+" ");
        if(left!=null) left.preOrder();
        if(right!=null) right.preOrder();
    }

}
