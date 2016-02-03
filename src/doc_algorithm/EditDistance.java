package doc_algorithm;
public class EditDistance {
	public static int min(int a, int b, int c) { // 编辑距离算法
		int t = a < b ? a : b;
		return t < c ? t : c;
	}
	// 不解释了。。直接用，。简单的动态规划问题
	public static int edit(char P[], char T[], int m, int n)
	{
		int i, j;
		int D[][] = new int[m + 1][n + 1];
		for (j = 0; j <= n; j++)
			D[0][j] = j;
		for (i = 1; i <= m; i++)
			D[i][0] = i;
		for (j = 1; j <= n; j++) {
			for (i = 1; i <= m; i++) {
				if (P[i - 1] == T[j - 1])
					D[i][j] = min(D[i - 1][j - 1], D[i - 1][j] + 1,
							D[i][j - 1] + 1);
				else if (P[i - 1] != T[j - 1])
					D[i][j] = min(D[i - 1][j - 1] + 1, D[i - 1][j] + 1,
							D[i][j - 1] + 1);
			}
		}
		return D[m][n];
	}
}
