package cn.wansnow.code;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {
    //读取文件
    public static ArrayList<TreeNode> readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            ArrayList<TreeNode> treeNodes = (ArrayList<TreeNode>) JSONObject.parseArray(jsonStr, TreeNode.class);
            for(TreeNode temp:treeNodes);
            return treeNodes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //写入文件
    public static void writeJsonFile(String fileName, ArrayList<TreeNode> treeNodes){
        try {
            String treeSrc = JSONObject.toJSONString(treeNodes);
            File jsonFile = new File(fileName);
            FileWriter fileWriter = new FileWriter(jsonFile);
            Writer writer = new OutputStreamWriter(new FileOutputStream(jsonFile), "utf-8");
            writer.write(treeSrc);
            writer.flush();
            fileWriter.close();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
