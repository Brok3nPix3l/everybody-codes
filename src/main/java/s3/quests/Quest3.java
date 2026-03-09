package s3.quests;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quest3 extends QuestLong {
    private class Node {
        private int id;
        private String plug;
        private String leftSocket;
        private String rightSocket;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int id, String plug, String leftSocket, String rightSocket) {
            this.id = id;
            this.plug = plug;
            this.leftSocket = leftSocket;
            this.rightSocket = rightSocket;
        }
    }

    @Override
    public long part1(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        List<Node> nodes = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(", ");
            int id = Integer.parseInt(split[0]);
            String plug = split[1];
            String leftSocket = split[2];
            String rightSocket = split[3];
            nodes.add(new Node(id, plug, leftSocket, rightSocket));
        }
        Node tree = nodes.removeFirst();
        Node head = tree;
        while (!lines.isEmpty()) {
            Node cur = nodes.removeFirst();
            if (Objects.equals(cur.plug, head.leftSocket)) {
                head.left = cur;
                cur.parent = head;
                if (lines.isEmpty()) {
                    break;
                }
                cur = nodes.removeFirst();
            }
            if (head.left != null) {

            }
            if (Objects.equals(cur.plug, head.rightSocket)) {
                head.right = cur;
                cur.parent = head;
            }
        }

        ans = generateChecksum(tree);
        return ans;
    }

    @Override
    public long part2(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
