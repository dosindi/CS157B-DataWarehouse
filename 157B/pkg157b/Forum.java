package pkg157b;

import java.util.List;

/**
 * Forum
 * 
 * Many Forum to many User (moderator relationship)
 * One Forum to many Thread
 * 
 * @author chris
 * @version January 2013
 */
public interface Forum {

	public void addModerator(User user);
	public void removeModerator(User user);
	public List<User> getModerators();
	
	public void addThread(Thread thread);
	public void removeThread(Thread thread);
	public List<Thread> getThreads();
	
	public String getForumName();
}
