import java.util;

class UnionFind 
{
	private int[] _parent;
	private int[] _rank;

	public UnionFind(int size) {
		_parent = new int[size];
		_rank = new int[size];
	}

	public int find(int x) {
		if(_parent[x] != x) {
			_parent[x] = find(_parent[x]);
		}
		return _parent[x];
	}

	public int createSet(int x) {
		_parent[x] = x;
		_rank[x] = 0;
	}

	public int merge(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(_rank[px] > _rank[py]) {
			_parent[py] = px;
		} else if(_rank[px] < _rank[py]) {
			_parent[px] = py;
		} else {
			_parent[py] = px;
			_rank[px] += 1;
		}
	}
}