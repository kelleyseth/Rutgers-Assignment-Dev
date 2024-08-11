public class QuickUnionUF {

    private int[] parent, size;
    
    public QuickUnionUF(int n){
        parent = new int[n];
        size = new int[n];

        for(int i = 0; i < n; i++) parent[i] = i;
    }
    
    public int find(int p){
        while(p != parent[p]) p = parent[p];
        return p;
    }

    public void union(int p, int q){
        int root1 = find(p);
        int root2 = find(q);
        if(root1 == root2) return;

        if(size[root1] >= size[root2]){
            int temp = root1;
            root1 = root2;
            root2 = temp;
        }
        parent[root1] = root2;
        size[root2] += size[root1];
    }
}