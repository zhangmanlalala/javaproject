package _174_Dungeon_Game;
/**
 * 
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner
 *  of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant 
 *  knight (K) was initially positioned in the top-left room and must fight his way through 
 *  the dungeon to rescue the princess.
The knight has an initial health point represented by a positive integer. If at any point 
his health point drops to 0 or below, he dies immediately.
Some of the rooms are guarded by demons, so the knight loses health (negative integers) 
upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that 
increase the knight's health (positive integers).
In order to reach the princess as quickly as possible, the knight decides to move only 
rightward or downward in each step.
Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the 
optimal path RIGHT-> RIGHT -> DOWN -> DOWN
 * @author Administrator
 *
 */
public class Solution {
	
	
	/**
	 * 
	 * 
	 * 考察动态规划，
	 * @param dungeon
	 * @return
	 */
	
	/**
	 * 
	 * 还是看标准解法
	 * 思路：动态规划
	 * ans[i][j]表示勇士在[i,j]处，至少需要ans[i][j]魔力才能到达[m,n]救出皇后
	 * 技巧从[m,n]往回遍历到[1,1]   //这一点很难想到，之前我用过，只是这里我不知道用
	 * 动态规划方程：如果当前位置位置魔力正，且大于等于右边/下边需要的魔力，则该出不需要额外的魔力
	 * ，否则，勇士到达该处是需要一定的魔力来满足该出和右边/下边需要的魔力
	 * @param dungeon
	 * @return
	 */
    public int calculateMinimumHP(int[][] dungeon) {
    	int m = dungeon.length;
    	int n = dungeon[0].length;
    	
    	int[][] ans = new int[m][n];
    	
    	//初始化最后一行和最后一列
    	ans[m-1][n-1] = dungeon[m-1][n-1]>0? 0:-dungeon[m-1][n-1];
    	for(int i=m-2;i>=0;i--){
    		ans[i][n-1] = dungeon[i][n-1] >=ans[i+1][n-1] ? 0:ans[i+1][n-1] - dungeon[i][n-1];
    	}
    	
    	for(int j=n-2;j>=0;j--){
    		ans[m-1][j] = dungeon[m-1][j]>=ans[m-1][j+1]? 0:ans[m-1][j+1]-dungeon[m-1][j];
    	}
    	
    	//从右下角往左上角遍历
    	for(int i=m-2;i>0;i--){
    		for(int j=n-2;j<=0;j--){
    			int down = dungeon[i][j]>=ans[i+1][j]?0 : ans[i+1][j]-dungeon[i][j];
    			int right = dungeon[i][j]>=ans[i][j+1]?0 : ans[i][j+1] - dungeon[i][j];
    			ans[i][j]  =Math.min(down, right);
    		}
    	}
    	
    	//保证勇士存活，至少需要1魔力
    	return ans[0][0]+1;
    }
}
