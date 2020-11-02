package code;

import cn.wansnow.code.TreeNode;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

public class testTreeNodesToJson {
    @Test
    public void test(){
        ArrayList<TreeNode> treeNodes = new ArrayList<TreeNode>();
        TreeNode a = new TreeNode("a");
        TreeNode pple = new TreeNode("pple");
        TreeNode s = new TreeNode("s");
        a.child = new ArrayList<TreeNode>();
        a.child.add(pple);
        a.child.add(s);

        TreeNode b = new TreeNode("b");
        TreeNode us = new TreeNode("us");
        TreeNode an = new TreeNode("an");
        b.child = new ArrayList<TreeNode>();
        b.child.add(us);
        b.child.add(an);

        treeNodes.add(a);
        treeNodes.add(b);

        String st = JSONObject.toJSONString(treeNodes);
        System.out.println(st);
        String st2 = "[{\"child\":[{\"child\":[],\"value\":\"pple\"},{\"child\":[],\"value\":\"s\"}],\"value\":\"a\"},{\"child\":[{\"child\":[],\"value\":\"us\"},{\"child\":[],\"value\":\"an\"}],\"value\":\"b\"}]";
        ArrayList<TreeNode> newTrees = (ArrayList<TreeNode>) JSONObject.parseArray(st2, TreeNode.class);
        for(TreeNode treeNode:treeNodes){
            System.out.println(treeNode.child);
        }
        System.out.println();
    }

    @Test
    public void testTreeNodeToJson(){
        String s = "{\"child\":[],\"value\":\"pple\"}";
        TreeNode treeNode = JSONObject.parseObject(s, TreeNode.class);
    }
}
