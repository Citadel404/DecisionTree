package cn.rlsblog;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Author: 973969022@qq.com
 * Date: 2024/7/22 14:15
 * 决策树类
 */
public class DecisionTree {
    /**
     * 根据给定的数据和属性列表构建决策树。
     *
     * @param data 数据列表，每个元素是一个包含属性和标签的映射。
     * @param attributes 可用的属性列表。
     * @return 构建的决策树的根节点。
     */
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

    /**
     * 选择最佳属性
     *
     * 此方法目前实现为简单地返回属性列表中的第一个属性。
     * 实际应用中，选择最佳属性应基于某些标准，如信息增益、信息增益率等，
     * 以最大化分类的准确性或减少分类的不纯度。
     *
     * @param data 数据集，每个元素是一个包含属性的映射
     * @param attributes 属性列表，用于选择最佳属性
     * @return 返回列表中的第一个属性作为最佳属性的示例
     */
    private static String selectBestAttribute(List<Map<String, String>> data, List<String> attributes) {
        return attributes.get(0);
    }

    /**
     * 根据指定的属性对数据集进行分区。
     *
     * 该方法通过流处理数据集列表，并根据每个数据项中指定属性的值来对数据进行分组。
     * 结果是一个映射表，其中键是属性的值，值是具有该属性值的数据项的列表。
     * 这种分区方式有助于根据特定属性对数据进行聚合或进一步处理。
     *
     * @param data 数据集，包含多个数据项，每个数据项是一个包含属性和值的映射。
     * @param attribute 用于分区的属性名称。这是每个数据项中的一个键，其值用于决定数据项所属的分区。
     * @return 一个映射表，其中每个键对应一个属性值，每个值是一个数据项列表，这些数据项的指定属性具有相应的键值。
     */
    private static Map<String, List<Map<String, String>>> partitionData(List<Map<String, String>> data, String attribute) {
        return data.stream().collect(Collectors.groupingBy(d -> d.get(attribute)));
    }

    /**
     * 获取最常见的标签
     *
     * 该方法通过分析数据列表，找出出现次数最多的标签。
     * 每个数据项是一个映射，其中包含至少一个键为“label”的条目。
     * 方法首先通过stream对数据进行处理，然后使用groupingBy对标签进行分组，
     * 并使用counting()计算每个标签出现的次数。接下来，通过对计数结果进行比较，
     * 找出出现次数最多的标签。最后，返回这个最常见标签。
     *
     * @param data 数据列表，每个数据项是一个包含标签的映射。
     * @return 出现次数最多的标签。
     */
    private static String getMostCommonLabel(List<Map<String, String>> data) {
        return data.stream().collect(Collectors.groupingBy(d -> d.get("label"), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    /**
     * 打印决策树的结构。
     *
     * 该方法通过递归的方式打印决策树的每个节点。如果节点是叶子节点，则打印其标签；
     * 如果节点是决策节点，则打印其属性和每个可能值对应的子树。
     *
     * @param node 当前正在处理的节点。
     * @param indent 用于缩进显示树结构的字符串，随着递归深度增加而增加。
     */
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
