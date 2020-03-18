package microsoft.Onsite;

import java.util.HashSet;
import java.util.Set;

/**
 * @author franksun
 * 
 *         Mar 2, 2020
 * 
 */
public class LC_489_Robot_Room_Cleaner {

	int[] dx = { 0, 1, 0, -1 };
	int[] dy = { 1, 0, -1, 0 };
	Set<String> visited = new HashSet<>();
	Robot robot;

	public void cleanRoom(Robot robot) {
		this.robot = robot;
		dfs(0, 0, 0);
	}

	private void dfs(int x, int y, int curDir) {
		visited.add(x + "#" + y);
		robot.clean();

		for (int i = 0; i < 4; i++) {
			int nextDir = (curDir + i) % 4;
			int nextX = x + dx[nextDir];
			int nextY = y + dy[nextDir];
			if (!visited.contains(nextX + "#" + nextY) && robot.move()) {
				dfs(nextX, nextY, nextDir);

				// go back to start cell;
				robot.turnRight();
				robot.turnRight();
				robot.move();
				// go back to correct direction
				robot.turnRight();
				robot.turnRight();
			}
			// because we purposely arranged dx, dy to be clockwise. If we are facing right,
			// we will be facing down in the next iteration
			robot.turnRight();
		}
	}
}

//just hard code one, ignore it 
class Robot {
	public boolean move() {
		return true;
	}

	public void turnLeft() {
	}

	public void turnRight() {
	}

	public void clean() {
	}
}
