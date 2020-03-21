package drawTree;

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
        Random random = new Random();
        final int num = 100;
        BSTree bsTree= new BSTree();
        for(int i = 0; i < num; i++)
        {
            int randi = random.nextInt();
            bsTree.insert(randi);
        }
        bsTree.draw();
    }
}
