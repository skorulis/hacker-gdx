package skorulis.hacker.avatar;

import skorulis.hacker.computer.Computer;
import skorulis.hacker.computer.square.ComputerSquare;
import skorulis.hacker.level.NetworkConnection;
import skorulis.hacker.level.NetworkNode;

public interface AvatarDelegate {

	public void avatarDidReachNode(Avatar avatar, NetworkNode node, NetworkConnection connection);
	public void avatarDidPerformAction(Avatar avatar, Computer computer, ComputerSquare square);
	
}
