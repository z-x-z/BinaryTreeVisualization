package drawTree;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Random;

/**
 * @description: Test TreeVisualization
 * @project: Algorithms
 * @author: caged_bird
 * @Create: 2020-03-21 14:20
 **/
public class Test
{
    public static void main(String[] args)
    {
        Random random = new Random(127);
        final int randIntNum = 30;
        BSTree bsTree = new BSTree();
        AVLTree avlTree = new AVLTree();
        for(int i = 0; i < randIntNum; i++)
        {
            int num = random.nextInt(200);
//            bsTree.insert(num);
            avlTree.insert(num);
        }
        avlTree.draw();
        bsTree.draw();
    }
}
