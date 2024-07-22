package cn.rlsblog;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Author: 973969022@qq.com
 * Date: 2024/7/22 14:15
 * 决策树类
 */
public class DecisionTree {
    // 构建决策树
    public static TreeNode buildTree(List<Map<String, String>> data, List<String> attributes) {
        if (data.isEmpty()) {
            return null; // 数据为空，返回null
        }

        // 检查所有实例是否具有相同的标签
        Set<String> labels = data.stream().map(d -> d.get("label")).collect(Collectors.toSet());
        if (labels.size() == 1) {
            return new TreeNode(labels.iterator().next()); // 所有实例具有相同的标签，创建叶节点
        }

        if (attributes.isEmpty()) {
            // 返回一个具有最常见标签的叶节点
            String commonLabel = getMostCommonLabel(data);
            return new TreeNode(commonLabel);
        }

        String bestAttribute = selectBestAttribute(data, attributes); // 选择最佳属性
        Map<String, List<Map<String, String>>> partitions = partitionData(data, bestAttribute); // 分割数据

        Map<String, TreeNode> children = new HashMap<>();
        for (String value : partitions.keySet()) {
            List<String> newAttributes = new ArrayList<>(attributes);
            newAttributes.remove(bestAttribute); // 删除已使用的属性
            children.put(value, buildTree(partitions.get(value), newAttributes)); // 递归构建子树
        }

        return new TreeNode(bestAttribute, children); // 返回根节点
    }

    // 选择最佳属性（此处使用简单的方法，实际中应使用信息增益等更复杂的方法）
    private static String selectBestAttribute(List<Map<String, String>> data, List<String> attributes) {
        return attributes.get(0);
    }

    // 分割数据集
    private static Map<String, List<Map<String, String>>> partitionData(List<Map<String, String>> data, String attribute) {
        return data.stream().collect(Collectors.groupingBy(d -> d.get(attribute)));
    }

    // 获取最常见的标签
    private static String getMostCommonLabel(List<Map<String, String>> data) {
        return data.stream().collect(Collectors.groupingBy(d -> d.get("label"), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    // 打印决策树
    public static void printTree(TreeNode node, String indent) {
        if (node.label != null) {
            System.out.println(indent + "Label: " + node.label); // 叶节点
        } else {
            System.out.println(indent + "Attribute: " + node.attribute); // 决策节点
            for (String value : node.children.keySet()) {
                System.out.println(indent + " Value: " + value);
                printTree(node.children.get(value), indent + "  ");
            }
        }
    }
}
