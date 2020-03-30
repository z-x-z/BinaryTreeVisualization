package drawTree;

/**
 * @description:
 * @project: Algorithms
 * @author: caged_bird
 * @Create: 2020-03-24 14:48
 **/
public class AVLTree
{
    AVLTreeNode root;

    void insert(int insertVal)
    {
        root = AVLTreeNode.avlInsertHelper(root, insertVal);
    }

    boolean find(int findVal)
    {
        if(root==null) return true;
        else return root.find(findVal);
    }
    void draw()
    {
        if(root!=null) BinaryTreeVisualization.visualize(root);
    }
}

class AVLTreeNode extends BSTreeNode
{
    int height;

    public AVLTreeNode(int x)
    {
        super(x);
        height = 0;
    }


    static int getHeight(AVLTreeNode node) {return node != null ? node.height : -1; }

    static void updateHeight(AVLTreeNode node)
    {
        node.height = 1 + Math.max(getHeight((AVLTreeNode) node.left), getHeight((AVLTreeNode) node.right));
    }

    static AVLTreeNode LLRotate(AVLTreeNode node)
    {
        AVLTreeNode newNode = (AVLTreeNode) node.left;
        node.left = newNode.right;
        newNode.right = node;
        if(newNode.left!=null) updateHeight((AVLTreeNode) newNode.left);
        updateHeight((AVLTreeNode) newNode.right);
        return newNode;
    }
    static AVLTreeNode RRRotate(AVLTreeNode node)
    {
        AVLTreeNode newNode = (AVLTreeNode) node.right;
        node.right = newNode.left;
        newNode.left = node;
        if(newNode.right!=null) updateHeight((AVLTreeNode) newNode.right);
        updateHeight((AVLTreeNode) newNode.left);
        return newNode;
    }
    static AVLTreeNode LRRotate(AVLTreeNode node)
    {
        node.left = RRRotate((AVLTreeNode) node.left);
        return LLRotate(node);
    }
    static AVLTreeNode RLRotate(AVLTreeNode node)
    {
        node.right = LLRotate((AVLTreeNode) node.right);
        return RRRotate(node);
    }
    @Override
    public void insert(int val)
    {
        avlInsertHelper(this, val);
    }

    static AVLTreeNode avlInsertHelper(AVLTreeNode node, int insertVal)
    {
        if(node==null) node = new AVLTreeNode(insertVal);
        else if(insertVal > node.val)
        {
            node.right = avlInsertHelper((AVLTreeNode)node.right, insertVal);
            if(getHeight((AVLTreeNode) node.right)-getHeight((AVLTreeNode) node.left)>=2)
            {
                if(insertVal>node.right.val) node = RRRotate(node);
                else node = RLRotate(node);
            }
        }
        else
        {
            if(insertVal< node.val)
            {
                node.left = avlInsertHelper((AVLTreeNode) node.left, insertVal);
                if(getHeight((AVLTreeNode) node.left)-getHeight((AVLTreeNode) node.right)>=2)
                {
                    if(insertVal<node.left.val) node = LLRotate(node);
                    else node = LRRotate(node);
                }
            }
        }
        updateHeight(node);
        return node;
    }


    @Override
    public boolean find(int val)
    {
        return super.find(val);
    }
}
