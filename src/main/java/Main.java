import cn.rlsblog.DecisionTree;
import cn.rlsblog.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: 973969022@qq.com
 * Date: 2024/7/22 14:15
 * 主类
 */
public class Main {
    public static void main(String[] args) {
        // 示例数据集
        List<Map<String, String>> data = new ArrayList<>();
        data.add(Map.of("color", "red", "shape", "round", "label", "apple"));
        data.add(Map.of("color", "yellow", "shape", "round", "label", "apple"));
        data.add(Map.of("color", "red", "shape", "oblong", "label", "banana"));
        data.add(Map.of("color", "yellow", "shape", "oblong", "label", "banana"));

        List<String> attributes = List.of("color", "shape"); // 属性列表

        TreeNode tree = DecisionTree.buildTree(data, attributes); // 构建决策树
        DecisionTree.printTree(tree, ""); // 打印决策树
    }
}