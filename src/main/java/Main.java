import cn.rlsblog.DecisionTree;
import cn.rlsblog.TreeNode;
import com.google.common.collect.Multimap;
import com.google.common.collect.HashMultimap;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: 973969022@qq.com
 * Date: 2024/7/22 14:15
 * 主类
 */
public class Main {
    @Test
    public void carbonAccountingBoundary() {
        // 创建一个SetMultimap实例，键是String类型，值是Integer类型
        Multimap<String, Integer> multimap = HashMultimap.create();

        // 向Multimap中添加元素
        multimap.put("key1", 1);
        multimap.put("key1", 2);
        multimap.put("key2", 3);
        multimap.put("key2", 4);
        multimap.put("key2", 5);

                // 输出所有键值对
                System.out.println("All entries: " + multimap.entries());

                // 获取某个键的所有值
                System.out.println("Values for 'key1': " + multimap.get("key1"));

                // 检查某个键是否存在
                System.out.println("Contains 'key1': " + multimap.containsKey("key1"));

                // 检查某个值是否存在
                System.out.println("Contains 3: " + multimap.containsValue(3));

                // 删除某个键的所有值
                multimap.removeAll("key1");
                System.out.println("After removing 'key1': " + multimap.entries());

                // 清空整个Multimap
                multimap.clear();
                System.out.println("After clearing: " + multimap.isEmpty());

        Map<String, String> carbonAccountingBoundary = new HashMap<>();
        //Multimap<String, String> energyClass = new HashMap<String, String>();
        carbonAccountingBoundary.put("feul","化石燃料燃烧");
        System.out.println(carbonAccountingBoundary);
    }

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