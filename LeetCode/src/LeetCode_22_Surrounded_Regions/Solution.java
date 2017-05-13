package LeetCode_22_Surrounded_Regions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

 * 
 * @author Administrator
 *
 */

/**
 * 
 * 
 * ������ֱ�ӷ������ֱ�ӿ���׼��
 * ������
 * ��˼��ž��ǣ���û����߽���ͨ��O������X
 * 1����ǳ���߽���ͨ��Oλ�ã�
 * 2����û�б�ǵ�O������X
 * 
 * ��ô�ҵ���
 * ��߽���O��ͨ��O��Ӧ�ñ�ǣ���ͼ�����㷨��BFS��,����������ؿ����ҵ���ЩO
 * 
 * ���԰��ҵ�����ЩO���������ַ���������ǣ�����ٻ���O
 * 
 * ������BFS
 * @author Administrator
 *
 */
public class Solution {
	
	/**
	 * 
	 * ���ֽⷨ��̫��⣬�Ժ���˵
	 * @param board
	 */
    public void solve(char[][] board) {
        if(board==null || board.length==0 || board[0].length==0) return ;
        Queue<Integer> xIndex = new LinkedList<Integer>();
        Queue<Integer> yIndex = new LinkedList<Integer>();
        int xSize = board.length;
        int ySize = board[0].length;
        for(int i=0;i<xSize;i++){
        	//up
        	if(board[i][0] == 'O'){
        		xIndex.add(i);
        		yIndex.add(0);
        	}
        	//bottom
        	if(board[i][ySize-1] == 'O'){
        		xIndex.add(i);
        		yIndex.add(ySize-1);
        	}
        }
        
        for(int j=0;j<ySize;j++){
        	//left
        	if(board[0][j]=='O'){
        		xIndex.add(0);
        		yIndex.add(j);
        	}
        	
        	//right
        	if(board[xSize-1][j]=='O'){
        		xIndex.add(xSize-1);
        		yIndex.add(j);
        	}
        }
        
        while(!xIndex.isEmpty()){
        	int x=xIndex.remove();
        	int y=yIndex.remove();
        	board[x][y] = '#';
        	if(x>0&&board[x-1][y] == 'O'){xIndex.add(x-1);yIndex.add(y);}
        	if(x<xSize-1&&board[x+1][y] == 'O'){xIndex.add(x+1);yIndex.add(y);}
        	if(y>0 && board[x][y-1]=='O'){xIndex.add(x);yIndex.add(y-1);}
        	if(y<ySize-1 && board[x][y+1] == 'O'){xIndex.add(x);yIndex.add(y+1);}
        	
        	
        }
        
        for(int i=0;i<xSize;i++){
        	for(int j=0;j<ySize;j++){
        		if(board[i][j] == 'O') board[i][j] ='X';
        		if(board[i][j] == '#') board[i][j] = 'O';
        	}
        }
    }
}
