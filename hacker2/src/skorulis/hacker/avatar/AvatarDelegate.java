package skorulis.hacker.avatar;

import skorulis.hacker.computer.NetworkNode;

public interface AvatarDelegate {

	public void avatarDidReachNode(Avatar avatar, NetworkNode node);
	
}