package cn.wansnow.code;

import java.util.ArrayList;

public class TreeNode {
    public String value;
    public ArrayList<TreeNode> child = new ArrayList<TreeNode>();
    public static ArrayList<String> results;

    public TreeNode(String value) {
        this.value = value;
    }

    public TreeNode() {
    }

    public static ArrayList<String> searchWord(ArrayList<TreeNode> trees, String s){
        results = new ArrayList<String>();
        for(TreeNode tree:trees){
            if(s.startsWith(tree.value)){
                if(tree.value.equals(s)){
                    results.add(tree.value);
                }else if(tree.child.size()!=0){
                    searchWord(tree, s.substring(tree.value.length()), tree.value);
                }

            }
            if(tree.value.startsWith(s)){
                if(s.length()<tree.value.length()){
                    results.add(tree.value);
                }
                searchWord(tree, "", tree.value);
            }
        }
        return results;
    }

    public static void searchWord(TreeNode tree, String s, String prefix){
        if(s==null||"".equals(s)){
            for(TreeNode node:tree.child) {
                if (node.child.size() != 0 && !node.value.equals(s)) {
                    searchWord(node, "", prefix + node.value);
                } else {
                    results.add(prefix + node.value);
                }

            }
        }else {
            for (TreeNode node : tree.child) {
                if (s.startsWith(node.value)) {
                    if (node.child.size() != 0 && !node.value.equals(s)) {
                        searchWord(node, s.substring(node.value.length()), prefix + node.value);
                    } else {
                        results.add(prefix + node.value);
                    }
                }
                if(node.value.startsWith(s)){
                    if(s.length()<node.value.length()){
                        results.add(prefix+node.value);
                    }
                    searchWord(node, "", node.value);
                }
            }
        }
    }

    public static boolean insertWord(ArrayList<TreeNode> trees, String word){
        for(TreeNode tree:trees){
            for(int i = 1,j=Math.min(word.length(), tree.value.length()); i<=j; i++){
                if(word.regionMatches(0,tree.value,0,i)){
                    if(i!=j) {
                        continue;
                    }else {
                        if(j<word.length()){
                            return insertWord(tree, word.substring(i));
                        }else if(j==tree.value.length()){
                            return false;
                        }else {
                            trees.remove(tree);
                            tree.value=tree.value.substring(j);
                            TreeNode newTreenode = new TreeNode(word);
                            newTreenode.child.add(tree);
                            trees.add(newTreenode);
                            return true;
                        }
                    }
                }else if(i==1){
                    break;
                }else{
                    String newNodeValue = String.copyValueOf(tree.value.toCharArray(), 0, i-1);
                    trees.remove(tree);
                    tree.value = tree.value.substring(i-1);
                    TreeNode newChild = new TreeNode(word.substring(i-1));
                    TreeNode newTreeNode = new TreeNode(newNodeValue);
                    newTreeNode.child.add(tree);
                    newTreeNode.child.add(newChild);
                    trees.add(newTreeNode);
                    return true;
                }
            }
        }
        trees.add(new TreeNode(word));
        return true;
    }

    public static boolean insertWord(TreeNode father, String word){
        for(TreeNode tree:father.child){
            for(int i = 1,j=Math.min(word.length(), tree.value.length()); i<=j; i++){
                if(word.regionMatches(0,tree.value,0,i)){
                    if(i!=j) {
                        continue;
                    }else {
                        if(j<word.length()){
                            return insertWord(tree, word.substring(i));
                        }else if(j==tree.value.length()){
                            return false;
                        }else {
                            father.child.remove(tree);
                            tree.value=tree.value.substring(j);
                            TreeNode newTreenode = new TreeNode(word);
                            newTreenode.child.add(tree);
                            father.child.add(newTreenode);
                            return true;
                        }
                    }
                }else if(i==1){
                    break;
                }else{
                    String newNodeValue = String.copyValueOf(tree.value.toCharArray(), 0, i-1);
                    father.child.remove(tree);
                    tree.value = tree.value.substring(i-1);
                    TreeNode newChild = new TreeNode(word.substring(i-1));
                    TreeNode newTreeNode = new TreeNode(newNodeValue);
                    newTreeNode.child.add(tree);
                    newTreeNode.child.add(newChild);
                    father.child.add(newTreeNode);
                    return true;
                }
            }
        }
        father.child.add(new TreeNode(word));
        return true;
    }

    public static boolean deleteWord(ArrayList<TreeNode> trees, String word){
        return false;
    }


}
