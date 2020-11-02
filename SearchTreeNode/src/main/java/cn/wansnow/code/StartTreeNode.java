package cn.wansnow.code;

import java.util.ArrayList;
import java.util.Scanner;

public class StartTreeNode {
    private static ArrayList<TreeNode> treeNodes;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int cmd = 0;
        String file = StartTreeNode.class.getProtectionDomain().getCodeSource().getLocation().getPath()+
                "/res/nodeSource.json";
        System.out.println("初始化中...");
        treeNodes = FileUtils.readJsonFile(file);
//        System.out.println(StartTreeNode.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        while(true){
            System.out.println("请输入你的操作:");
            System.out.println("1. 查找单词");
            System.out.println("2. 插入单词");
            System.out.println("3. 删除单词");
            System.out.println("4. 退出");
            while(true) {
                try {
                    cmd = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("输入有误，请重新输入");
                    String temp = sc.nextLine();
                    continue;
                }
            }
            switch (cmd){
                case 1:
                    System.out.print("请输入你要查的单词：");
                    String word = sc.next();
                    ArrayList<String> results = searchWord(word);
                    System.out.println("结果是：");
                    for (String s:results){
                        System.out.println(s);
                    }
                    break;
                case 2:
                    System.out.print("请输入你要插入的单词：");
                    word = sc.next();
                    if(insertWord(word)){
                        System.out.println("插入成功");
                    }else {
                        System.out.println("插入失败，单词可能已存在");
                    }
                    break;
                case 3:
                    System.out.print("请输入你要删除的单词：");
                    word = sc.nextLine();
                    if(deleteWord(word)){
                        System.out.println("删除成功");
                    }
                    break;
                case 4:
                    System.out.println("正在保存数据，请稍等...");
                    System.out.println("程序结束");
                    FileUtils.writeJsonFile(file, treeNodes);
                    return;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }

    }

    public static ArrayList<String> searchWord(String s){
        return TreeNode.searchWord(treeNodes, s);
    }

    public static boolean insertWord(String word){
        return TreeNode.insertWord(treeNodes, word);
    }

    public static boolean deleteWord(String word){
        return TreeNode.deleteWord(treeNodes, word);
    }

}
