
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        ArrayList<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        int[] prev = new int[n];

        Arrays.fill(last, -1);

        ArrayList<Integer> order = new ArrayList<>();

        Stack<Integer> st = new Stack<>();
        st.push(0);

        while (!st.isEmpty()) {

            int node = st.pop();

            if (node < 0) {
                node = ~node;
                last[s.charAt(node) - 'a'] = prev[node];
                continue;
            }

            int ch = s.charAt(node) - 'a';

            prev[node] = last[ch];

            if (last[ch] != -1) {
                newParent[node] = last[ch];
            }

            last[ch] = node;

            order.add(node);

            st.push(~node);

            for (int child : tree[node]) {
                st.push(child);
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = n - 1; i > 0; i--) {
            int node = order.get(i);
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
