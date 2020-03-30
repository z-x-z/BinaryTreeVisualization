package drawTree;

import edu.princeton.cs.algs4.StdDraw;

/**
 * @description: Tree visualization core algorithm.
 * @project: Algorithms
 * @author: caged_bird
 * @Create: 2020-03-19 23:28
 **/


class TreeNodeWithDescendantNum
{
    TreeNodeWithDescendantNum left;
    TreeNodeWithDescendantNum right;
    int val;
    int descendantNum;

    public TreeNodeWithDescendantNum(int val)
    {
        this.val = val;
        descendantNum = 0;
    }
}

abstract class BinaryTreeVisualization
{
    static double radius;
    static double yIncrement;
    static boolean isDrawNodeVal;
    static boolean isPintTreeInfo = true;
    static int levels = 0;
    static int treeNodeNum = 0;

    static final double MIN_VISUAL_RADIUS = 1./800;

    static TreeNodeWithDescendantNum preProcess(BinaryTreeNode node, int level)
    {
        if(node == null) return null;
        if(level > levels) levels = level;
        TreeNodeWithDescendantNum newNode = new TreeNodeWithDescendantNum(node.val);
        newNode.left = preProcess(node.left, level + 1);
        newNode.right = preProcess(node.right, level + 1);
        if(newNode.left != null) newNode.descendantNum += (1 + newNode.left.descendantNum);
        if(newNode.right != null) newNode.descendantNum += (1 + newNode.right.descendantNum);
        return newNode;
    }


    // preorder traversal the tree
    static Point visualizeHelperPoint(TreeNodeWithDescendantNum root,  double leftBound, double rightBound, double y)
    {
        int leftNum = 0, rightNum = 0;
        if(root.left != null) leftNum = (1 + root.left.descendantNum);
        if(root.right != null) rightNum = (1 + root.right.descendantNum);
        double ratio = 0.5;
        if(leftNum + rightNum != 0) ratio = 1. * leftNum / (leftNum + rightNum);
        double x = leftBound + (rightBound - leftBound) * (ratio);
        Point point = new Point(x, y);
        DrawBase.drawPoint(x, y);
        if(root.left != null)
        {
            Point leftPoint = visualizeHelperPoint(root.left, leftBound, x - 3 * radius, y - yIncrement - 2 * radius);
            DrawBase.drawLine(point, leftPoint);
        }
        if(root.right != null)
        {

            Point rightPoint = visualizeHelperPoint(root.right, x + 3 * radius, rightBound, y - yIncrement - 2 * radius);
            DrawBase.drawLine(point, rightPoint);
        }
        return point;
    }

    static Circle visualizeHelperCircle(TreeNodeWithDescendantNum root, double leftBound, double rightBound, double y)
    {
        int leftNum = 0, rightNum = 0;
        if(root.left != null) leftNum = (1 + root.left.descendantNum);
        if(root.right != null) rightNum = (1 + root.right.descendantNum);
        double ratio = 0.5;
        if(leftNum + rightNum != 0) ratio = 1. * leftNum / (leftNum + rightNum);
        double x = leftBound + (rightBound - leftBound) * (ratio);
        Circle circle = new Circle(x, y, radius);
        DrawBase.drawCircle(x, y, radius);
        if(isDrawNodeVal) StdDraw.text(x, y, String.valueOf(root.val));
        if(root.left != null)
        {
            Circle leftCircle = visualizeHelperCircle(root.left, leftBound, x - 3 * radius, y - yIncrement - 2 * radius);
            DrawBase.drawLineTwoCircle(circle, leftCircle);
        }
        if(root.right != null)
        {

            Circle rightCircle = visualizeHelperCircle(root.right, x + 3 * radius, rightBound, y - yIncrement - 2 * radius);
            DrawBase.drawLineTwoCircle(circle, rightCircle);
        }
        return circle;
    }

    static void visualizeHelper(TreeNodeWithDescendantNum root, double leftBound, double rightBound, double y)
    {
        if(radius>MIN_VISUAL_RADIUS) visualizeHelperCircle(root, leftBound, rightBound, y);
        else visualizeHelperPoint(root, leftBound, rightBound, y);
    }
    static void initParameter(TreeNodeWithDescendantNum root)
    {
        // To calculate the radius and yIncrement
        treeNodeNum = root.descendantNum + 1;
        radius = 1. / (3 * treeNodeNum);
        yIncrement = (1. - 2 * levels * radius) / levels;

        // To confirm the text font according to calculated radius and canvasSize
        int canvasSize;
        if(treeNodeNum<=40) canvasSize = DrawBase.DEFAULT_CANVAS_Size;
        else if(treeNodeNum<80) canvasSize = DrawBase.DEFAULT_CANVAS_Size * treeNodeNum / 40;
        else canvasSize = 2 * DrawBase.DEFAULT_CANVAS_Size;
        DrawBase.setCanvasSize(canvasSize, canvasSize);
        int fontSize = (int) (300 * radius * (canvasSize / DrawBase.DEFAULT_CANVAS_Size));
        isDrawNodeVal = treeNodeNum <= 30;
        fontSize = fontSize > 9 ? fontSize : 10;
        DrawBase.setFontSize(fontSize);
    }


    static void printTreeInfo()
    {
        System.out.println("Tree node num: "+treeNodeNum);
        System.out.println("Tree height: "+ levels);
    }

    public static void visualizeCore(TreeNodeWithDescendantNum treeNodeWithDescendantNum)
    {
        // Init parameter
        initParameter(treeNodeWithDescendantNum);

        if(isPintTreeInfo) printTreeInfo();
        // PreOrder travel and draw the whole tree
        double initLeftBound = 0. + 1.5 * radius;
        double initRightBound = 1 - 1.5 * radius;
        double initYCoordinate = 1 - (yIncrement / 2) - radius;
        visualizeHelper(treeNodeWithDescendantNum, initLeftBound, initRightBound, initYCoordinate);
    }

    public static void visualize(BinaryTreeNode node)
    {
        TreeNodeWithDescendantNum treeNodeWithDescendantNum = preProcess(node, 1);
        visualizeCore(treeNodeWithDescendantNum);
    }



}
