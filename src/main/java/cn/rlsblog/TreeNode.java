package cn.rlsblog;

import java.util.Map;

/**
 * Author: 973969022@qq.com
 * Date: 2024/7/22 14:43
 * 决策树节点类
 */
public class TreeNode {
    String attribute;//属性名称
    Map<String, TreeNode> children;//子节点
    String label;//类别标签
    //构造函数，叶节点
    public TreeNode(String label) {
        this.label = label;
    }
    //构造函数，决策节点
    public TreeNode(String attribute, Map<String, TreeNode> children) {
        this.attribute = attribute;
        this.children = children;
    }
}
