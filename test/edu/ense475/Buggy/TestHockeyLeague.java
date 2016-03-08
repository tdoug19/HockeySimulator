/**
* TestHockeyLeague.java
*
* @author Trevor Douglas
*   <A HREF="mailto:douglatr@uregina.ca"> (douglatr@uregina.ca) </A>
*
* Original code copyright © Mar 18, 2010 Trevor Douglas.  Modifications can be made
* with Author's consent.
* @version Mar 18, 2010
*
**/

package edu.ense475.Buggy;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * @author tdouglas
 *
 */
public class TestHockeyLeague extends TestCase {

	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyLeague#HockeyLeague()}.
	 */
	public void testHockeyLeague() {
		ArrayList<HockeyTeam> teams = new ArrayList<HockeyTeam>();
		HockeyLeague hl = new HockeyLeague();
		assertTrue(verifyLeague(hl.teams));
	}

	private boolean verifyLeague(ArrayList<HockeyTeam> teams)
	{
		HockeyTeam getTeam;
		ArrayList<HockeyPlayer> roster;
		HockeyPlayer getPlayer;
		int centerCount=0;
		int rwCount=0;
		int lwCount=0;
		int defenseCount=0;
		int goalieCount=0;

		for (int i = 0; i < teams.size() ; i++)
		{
			getTeam = teams.get(i);
			roster = getTeam.getRoster();

			for (int j = 0; j < roster.size() ; j++)
			{
				getPlayer = roster.get(j);

				if(getPlayer.getPosition().equals("C"))
					++centerCount;
				else if(getPlayer.getPosition().equals("RW"))
					++rwCount;
				else if(getPlayer.getPosition().equals("LW"))
					++lwCount;
				else if(getPlayer.getPosition().equals("D"))
					++defenseCount;
				else if(getPlayer.getPosition().equals("G"))
					++goalieCount;
			}
			if((centerCount != 3)|| (rwCount !=3) || (lwCount !=3)
				|| (defenseCount !=6) || (goalieCount != 1))
			{
				return false;
			}
			centerCount=0;
			rwCount=0;
			lwCount=0;
			defenseCount=0;
			goalieCount=0;

        }
		return true;

	}

    public void testGetTeam()
    {
		HockeyLeague hl = new HockeyLeague();
    	assertEquals("Blackhawks", hl.getTeam("Blackhawks").teamName);
    	assertEquals("Bruins", hl.getTeam("Bruins").teamName);
    	assertEquals("Canadiens", hl.getTeam("Canadiens").teamName);
    	assertEquals("Maple Leafs", hl.getTeam("Maple Leafs").teamName);
    	assertEquals("Rangers", hl.getTeam("Rangers").teamName);
    	assertEquals("Red Wings", hl.getTeam("Red Wings").teamName);
    	assertNull(hl.getTeam("Maps"));
    }


    public void testGetPlayer()
    {
		String teamName = new String("Canadiens");
		String firstName =new String("Maxim");
		String lastName = new String("Lapierre");

    	HockeyLeague hl = new HockeyLeague();
		HockeyTeam ht;
		HockeyPlayer hp;
		ht = hl.getTeam("Canadiens");
		hp = hl.getPlayer(teamName, firstName, lastName);

    	assertEquals("Maxim", hp.getFirstName());
    	assertEquals("Lapierre", hp.getLastName());

    	firstName = "Joe";
    	ht = hl.getTeam("Canadiens");
		assertNull(hl.getPlayer(teamName, firstName, lastName));
    }


    /**
	 * Test method for {@link edu.ense475.Buggy.HockeyLeague#deletePlayer(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	public void testDeletePlayer() {
		String teamName = new String("Canadiens");
		String firstName =new String("Maxim");
		String lastName = new String("Lapierre");

		HockeyLeague hl = new HockeyLeague();

		assertTrue(hl.deletePlayer(teamName, firstName, lastName));
	}


	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyLeague#addPlayer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	public void testAddPlayer() {
		String teamName = new String("Canadiens");
		String firstName =new String("Maxim");
		String lastName = new String("Lapierre");

		HockeyLeague hl = new HockeyLeague();

		//Player exists
		assertFalse(hl.addPlayer(teamName, "RW", firstName, lastName, 80));

		//Make some room
		assertTrue(hl.deletePlayer("Canadiens", "Roman", "Hamrlik"));

		//Player exists
		assertFalse(hl.addPlayer(teamName, "RW", firstName, lastName, 80));
		assertTrue(hl.addPlayer("Canadiens", "RW", "Roman", "Hamrlik", 80));
		//Roster full.
		assertFalse(hl.addPlayer(teamName, "RW", "Joe", "Reekie", 80));


	}


	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyLeague#editPlayer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	public void testEditPlayer() {
		String teamName = new String("Canadiens");
		String firstName =new String("Maxim");
		String lastName = new String("Lapierre");

		HockeyLeague hl = new HockeyLeague();
		HockeyPlayer player;
		player = hl.getPlayer(teamName, firstName, lastName);
		assertNotNull(player);
		player.setPosition("C");
		player.setRating(50);

		player = hl.getPlayer(teamName, firstName, lastName);
		assertEquals("C", player.getPosition());
		assertEquals(firstName, player.getFirstName());
		assertEquals(lastName,player.getLastName());
		assertEquals(50,player.getRating());
	}


	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyLeague#playGame(java.lang.String, java.lang.String)}.
	 */
	public void testPlayGame() {
		String teamName = new String("Canadiens");
		String firstName =new String("Maxim");
		String lastName = new String("Lapierre");

		HockeyLeague hl = new HockeyLeague();

		//This test should cause a problem because the rosters are wrong
		assertTrue(hl.deletePlayer(teamName, firstName, lastName));
		assertEquals("Unplayable Game Rosters are wrong",
				hl.playGame("Maple Leafs", "Canadiens"));


		//This test should cause a problem because the roster is wrong
		hl = new HockeyLeague();
		assertTrue(hl.editPlayer(teamName, "D", firstName, lastName, 50));
		assertEquals("Unplayable Game Rosters are wrong",
				hl.playGame("Maple Leafs", "Canadiens"));
		
		hl = new HockeyLeague();
		assertNotSame("Need a Winner.. error", "Unplayable Game Rosters are wrong",
				hl.playGame("Maple Leafs", "Canadiens"));


	}

}
